package dev.rumblekat.springcloudfunction

import dev.rumblekat.springcloudfunction.handler.LambdaApiGateway
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.util.function.Function

@Component
class FooFunction {

    @Bean
    fun reverseString(): (String) -> String {
        return { it.reversed() }
    }
    @Bean
    fun lambdaApiGatewayFunction(): Function<Map<String, Object>, List<String>> {
        return LambdaApiGateway()
    }

}