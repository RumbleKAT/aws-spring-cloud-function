package dev.rumblekat.springcloudfunction.handler

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class FooFunction {

    @Bean
    fun reverseString(): (String) -> String {
        return { it.reversed() }
    }

    @Bean
    fun uppercaseString(): (String) -> String {
        return { it.uppercase() }
    }

}