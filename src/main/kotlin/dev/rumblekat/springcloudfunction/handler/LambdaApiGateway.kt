package dev.rumblekat.springcloudfunction.handler

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.function.Function

@Component
class LambdaApiGateway: Function<Map<String, Object>, List<String>> {
    private val log = LoggerFactory.getLogger(LambdaApiGateway::class.java)

    override fun apply(map: Map<String, Object>): List<String> {
        log.info("lambda apply 수행")

        val strings = mutableListOf<String>()
        strings.add(map["title"].toString())
        strings.add(map["name"].toString())

        strings.add("sample")
        log.info("lambda apply 종료")

        return strings
    }
}