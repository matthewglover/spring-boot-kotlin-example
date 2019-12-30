package com.matthewglover.app

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient


@ExtendWith(SpringExtension::class)
@WebFluxTest
@Import(RouteConfiguration::class)
internal class RouteConfigurationTest {
    @Autowired private lateinit var client: WebTestClient

    @Test
    internal fun `GET greet returns 200 OK`() {
        client.get()
            .uri("/greet")
            .exchange()
            .expectStatus().isOk
            .expectBody().consumeWith { response ->
                val bodyContent = String(response.responseBodyContent!!)
                assertEquals("Hello, World!", bodyContent)
            }
    }
}
