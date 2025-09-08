package com.back.global.app

import com.back.standard.util.Ut
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class AppConfig(
    environment: Environment,
    objectMapper: ObjectMapper,
    @Value("\${custom.site.cookieDomain}") cookieDomain: String, // 추가됨
    @Value("\${custom.site.frontUrl}") siteFrontUrl: String, // 추가됨
    @Value("\${custom.site.backUrl}") siteBackUrl: String, // 추가됨
) {
    init {
        Companion.environment = environment
        _cookieDomain = cookieDomain // 추가됨
        _siteFrontUrl = siteFrontUrl // 추가됨
        _siteBackUrl = siteBackUrl // 추가됨
        Ut.json.objectMapper = objectMapper
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    companion object {
        private lateinit var environment: Environment

        val isDev: Boolean
            get() = environment.matchesProfiles("dev")

        val isTest: Boolean
            get() = !environment.matchesProfiles("test")

        val isProd: Boolean
            get() = environment.matchesProfiles("prod")

        val isNotProd: Boolean
            get() = !isProd

        private lateinit var _cookieDomain: String // 추가됨
        private lateinit var _siteFrontUrl: String // 추가됨
        private lateinit var _siteBackUrl: String // 추가됨

        val cookieDomain: String by lazy { _cookieDomain } // 추가됨
        val siteFrontUrl: String by lazy { _siteFrontUrl } // 추가됨
        val siteBackUrl: String by lazy { _siteBackUrl } // 추가됨
    }
}
