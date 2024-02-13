package dev.rumblekat.springcloudfunction.config

import org.springframework.cloud.function.context.FunctionCatalog
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Function

@Configuration
class RouterConfig {
    @Bean
    fun route(functionCatalog: FunctionCatalog): Function<String, Any> = Function { value ->
        val parts = value.split("/")
        val functionName = parts[0]
        val argument = parts.getOrNull(1) ?: ""

        val function = functionCatalog.lookup(Function::class.java, functionName)
                as Function<String, String>? ?: throw IllegalArgumentException("Unsupported function: $functionName")
        function.apply(argument)
    }
}