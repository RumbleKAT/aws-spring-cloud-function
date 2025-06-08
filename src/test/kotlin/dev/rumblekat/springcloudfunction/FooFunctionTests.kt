package dev.rumblekat.springcloudfunction

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.function.Function

@SpringBootTest
class FooFunctionTests(@Autowired val reverseString: Function<String, String>,
                       @Autowired val uppercaseString: Function<String, String>) {

    @Test
    fun `reverseString reverses input`() {
        val result = reverseString.apply("abc")
        assertEquals("cba", result)
    }

    @Test
    fun `uppercaseString converts input to uppercase`() {
        val result = uppercaseString.apply("Abc")
        assertEquals("ABC", result)
    }
}
