package io.yunh.springkotlinjwttemplate.member.application.service

import io.yunh.springkotlinjwttemplate.member.application.port.`in`.SaveMemberCommand
import io.yunh.springkotlinjwttemplate.member.application.port.`in`.SaveMemberUseCase
import io.yunh.springkotlinjwttemplate.member.application.port.out.SaveMemberPort
import io.yunh.springkotlinjwttemplate.member.application.port.out.ValidateMemberPort
import io.yunh.springkotlinjwttemplate.member.domain.Member
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SaveMemberService(
    private val saveMemberPort: SaveMemberPort,
    private val validateMemberPort: ValidateMemberPort,
    private val passwordEncoder: PasswordEncoder
) : SaveMemberUseCase {

    override fun save(command: SaveMemberCommand): Member {
        require(!validateMemberPort.checkDuplicatedEmail(email = command.email)) {
            "이미 사용 중인 이메일입니다."
        }

        val member = Member(
            email = command.email,
            password = command.password
        )

        member.encode(passwordEncoder = passwordEncoder)

        return saveMemberPort.save(member) ?: throw IllegalStateException("회원 저장에 실패했습니다.")
    }
}