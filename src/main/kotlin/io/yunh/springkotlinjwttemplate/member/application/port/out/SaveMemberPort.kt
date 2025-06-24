package io.yunh.springkotlinjwttemplate.member.application.port.out

import io.yunh.springkotlinjwttemplate.member.domain.Member

interface SaveMemberPort {
    fun save(member: Member): Member?
}