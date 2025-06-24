package io.yunh.springkotlinjwttemplate.member.adapter.`in`.web

import io.yunh.springkotlinjwttemplate.common.response.ApiResponse
import io.yunh.springkotlinjwttemplate.member.adapter.`in`.request.SaveMemberRequest
import io.yunh.springkotlinjwttemplate.member.adapter.`in`.response.SaveMemberResponse
import io.yunh.springkotlinjwttemplate.member.application.port.`in`.SaveMemberUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/members")
class MemberController(
    private val saveMemberUseCase: SaveMemberUseCase
) {

    @PostMapping
    fun save(@RequestBody request: SaveMemberRequest): ResponseEntity<ApiResponse<SaveMemberResponse>> {
        return ResponseEntity.ok(
            ApiResponse.with(
                httpStatus = HttpStatus.OK,
                data = saveMemberUseCase.save(request.toCommand())
                    .let { SaveMemberResponse(
                        id = it.id!!,
                        email = it.email,
                    ) }
            )
        )
    }
}