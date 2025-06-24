package io.yunh.springkotlinjwttemplate.member.adapter.out.persistent

import org.springframework.data.jpa.repository.JpaRepository

interface JPAMemberRepository : JpaRepository<MemberEntity, Long> {
    fun findByEmail(email: String): MemberEntity?
    fun existsByEmail(email: String): Boolean
}