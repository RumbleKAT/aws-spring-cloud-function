package dev.rumblekat.springcloudfunction

import dev.rumblekat.springcloudfunction.handler.LambdaApiGateway
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.function.Function

@SpringBootApplication
class SpringCloudFunctionApplication
fun main(args: Array<String>) {
    runApplication<SpringCloudFunctionApplication>(*args)
}
