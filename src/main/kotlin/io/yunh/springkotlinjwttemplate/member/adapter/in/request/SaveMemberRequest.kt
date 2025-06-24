package io.yunh.springkotlinjwttemplate.member.adapter.`in`.request

import io.yunh.springkotlinjwttemplate.member.application.port.`in`.SaveMemberCommand


data class SaveMemberRequest (
    val email: String,
    val password: String
){

    fun toCommand() = SaveMemberCommand(
        email = email,
        password = password
    )
}