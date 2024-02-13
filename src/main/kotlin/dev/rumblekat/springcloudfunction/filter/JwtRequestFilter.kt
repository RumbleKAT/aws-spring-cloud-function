package dev.rumblekat.springcloudfunction.filter

import dev.rumblekat.springcloudfunction.util.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtRequestFilter: OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val userId: String? = request.getHeader("userId")

        if (!userId.isNullOrBlank()) {
            // userId가 존재하는 경우, 간단한 인증 정보를 생성하고 SecurityContext에 설정
            val auth = UsernamePasswordAuthenticationToken(userId, null, emptyList())
            SecurityContextHolder.getContext().authentication = auth
        }

        filterChain.doFilter(request, response)
    }

}