package dev.rumblekat.springcloudfunction.config

import dev.rumblekat.springcloudfunction.filter.JwtRequestFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(val jwtRequestFilter: JwtRequestFilter){
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { filter -> filter.disable() }
            .authorizeHttpRequests { authz -> authz
                .requestMatchers("/reverseString/**").permitAll()
                .anyRequest().authenticated()
            }
            .addFilterBefore(JwtRequestFilter(), UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

}