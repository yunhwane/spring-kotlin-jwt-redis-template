package io.yunh.springkotlinjwttemplate.common.response

import org.springframework.http.HttpStatus

class ApiResponse<T>(
    val code: Int,
    val status: String,
    val data: T? = null,
) {

    companion object {

        fun <T> with(httpStatus: HttpStatus, data: T): ApiResponse<T> {
            return ApiResponse(
                code = httpStatus.value(),
                status = httpStatus.name,
                data = data
            )
        }
    }
}