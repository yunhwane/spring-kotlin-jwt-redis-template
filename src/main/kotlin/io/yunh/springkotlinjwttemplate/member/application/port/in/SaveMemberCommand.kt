package io.yunh.springkotlinjwttemplate.member.application.port.`in`

data class SaveMemberCommand(
    val email: String,
    val password: String
)