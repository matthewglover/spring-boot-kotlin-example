package com.matthewglover.app

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class RouteConfiguration {
    @Bean
    fun routes() = router {
        GET("/greet") { ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).bodyValue("Hello, World!") }
    }
}
