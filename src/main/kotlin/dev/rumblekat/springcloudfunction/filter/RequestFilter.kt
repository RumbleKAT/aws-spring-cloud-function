package dev.rumblekat.springcloudfunction.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class RequestFilter: OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        println("CustomFilter: Before request processing")
        val type: String? = request.getHeader("Content-Type")
        println("content-type: $type")
        filterChain.doFilter(request, response)
        println("CustomFilter: After request processing")
    }

}