package io.yunh.springkotlinjwttemplate.member.application.port.`in`

import io.yunh.springkotlinjwttemplate.member.domain.Member
import io.yunh.springkotlinjwttemplate.member.application.port.`in`.SaveMemberCommand

interface SaveMemberUseCase {
    fun save(command: SaveMemberCommand): Member
}