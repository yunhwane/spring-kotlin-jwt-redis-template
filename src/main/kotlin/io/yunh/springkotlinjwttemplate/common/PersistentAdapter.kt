package io.yunh.springkotlinjwttemplate.common

import org.springframework.stereotype.Component

@Component
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class PersistentAdapter {
}