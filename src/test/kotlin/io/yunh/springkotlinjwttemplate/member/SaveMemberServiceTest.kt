package io.yunh.springkotlinjwttemplate.member

import io.yunh.springkotlinjwttemplate.member.application.port.`in`.SaveMemberCommand
import io.yunh.springkotlinjwttemplate.member.application.port.out.SaveMemberPort
import io.yunh.springkotlinjwttemplate.member.application.port.out.ValidateMemberPort
import io.yunh.springkotlinjwttemplate.member.application.service.SaveMemberService
import io.yunh.springkotlinjwttemplate.member.domain.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.verify
import org.springframework.security.crypto.password.PasswordEncoder

@ExtendWith(MockitoExtension::class)
class SaveMemberServiceTest {

    @Mock
    lateinit var saveMemberPort: SaveMemberPort

    @Mock
    lateinit var validateMemberPort: ValidateMemberPort

    @Mock
    lateinit var passwordEncoder: PasswordEncoder

    lateinit var saveMemberService: SaveMemberService

    @BeforeEach
    fun setUp() {
        saveMemberService = SaveMemberService(
            saveMemberPort = saveMemberPort,
            validateMemberPort = validateMemberPort,
            passwordEncoder = passwordEncoder
        )
    }

    @Test
    fun `create member`() {
        // given
        val command = SaveMemberCommand(
            email = "test@test.com",
            password = "raw-password"
        )

        val encodedPassword = "encoded-password"

        given(validateMemberPort.checkDuplicatedEmail(command.email)).willReturn(false)
        given(passwordEncoder.encode(command.password)).willReturn(encodedPassword)

        val savedMember = Member(email = command.email, password = encodedPassword)
        given(saveMemberPort.save(any())).willReturn(savedMember)
        // when
        val result = saveMemberService.save(command)

        // then
        assertThat(result.email).isEqualTo(command.email)
        assertThat(result.password).isNotEqualTo(command.password) // 인코딩된 상태
        assertThat(result.password).isEqualTo(encodedPassword)

        verify(validateMemberPort).checkDuplicatedEmail(command.email)
        verify(passwordEncoder).encode(command.password)
        verify(saveMemberPort).save(any())
    }

    @Test
    fun `create member with duplicated email throws exception`() {
        val command = SaveMemberCommand(
            email = "test@test.com",
            password = "raw-password"
        )

        given(validateMemberPort.checkDuplicatedEmail(command.email)).willReturn(true)

        assertThat(assertThrows<IllegalArgumentException> { saveMemberService.save(command) }.message)
            .isEqualTo("이미 사용 중인 이메일입니다.")
    }

}
