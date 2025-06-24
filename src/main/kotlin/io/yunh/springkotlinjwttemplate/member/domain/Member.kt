package io.yunh.springkotlinjwttemplate.member.domain

import org.springframework.security.crypto.password.PasswordEncoder

class Member(
    val id: Long? = null,
    val email: String,
    var password: String
) {
    init {
        require(EMAIL_REGEX.matches(email)) { "유효하지 않은 이메일 형식입니다: $email" }
    }

    fun encode(passwordEncoder: PasswordEncoder) {
        password = passwordEncoder.encode(password)
    }

    fun matches(passwordEncoder: PasswordEncoder, rawPassword: String) {
         require(passwordEncoder.matches(rawPassword, password), { "not matched password" })
    }

    companion object {
        private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
    }
}