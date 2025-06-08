package dev.rumblekat.springcloudfunction

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.function.context.FunctionCatalog
import java.util.function.Function

@SpringBootTest
class FooFunctionTests() {

    @Autowired
    lateinit var functionCatalog: FunctionCatalog

    @Test
    fun `reverseString reverses input`() {
        val reverseString = functionCatalog.lookup<Function<String, String>>("reverseString")
        val result = reverseString.apply("abc")
        assertEquals("cba", result)
    }

    @Test
    fun `uppercaseString converts input to uppercase`() {
        val uppercaseString = functionCatalog.lookup<Function<String, String>>("uppercaseString")
        val result = uppercaseString.apply("Abc")
        assertEquals("ABC", result)
    }
}
