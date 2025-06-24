package io.yunh.springkotlinjwttemplate.member.application.port.out

interface ValidateMemberPort {
    fun checkDuplicatedEmail(email: String): Boolean
}