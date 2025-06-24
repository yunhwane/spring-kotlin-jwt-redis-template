package io.yunh.springkotlinjwttemplate.member.adapter.out.persistent

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Table(name = "members")
@Entity
class MemberEntity(
    val email: String,
    val password: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}