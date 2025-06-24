package io.yunh.springkotlinjwttemplate.member.adapter.out.persistent

import io.yunh.springkotlinjwttemplate.common.PersistentAdapter
import io.yunh.springkotlinjwttemplate.member.application.port.out.SaveMemberPort
import io.yunh.springkotlinjwttemplate.member.application.port.out.ValidateMemberPort
import io.yunh.springkotlinjwttemplate.member.domain.Member

@PersistentAdapter
class MemberPersistentAdapter(
    private val jPAMemberRepository: JPAMemberRepository
) : ValidateMemberPort, SaveMemberPort {

    override fun checkDuplicatedEmail(email: String): Boolean {
        return jPAMemberRepository.existsByEmail(email)
    }

    override fun save(member: Member): Member? {
        val memberEntity = MemberEntity(
            email = member.email,
            password = member.password
        )

        return jPAMemberRepository.save(memberEntity).let {
            Member(
                id = it.id!!,
                email = it.email,
                password = it.password
            )
        }
    }
}