package com.example.greetingservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class GreetingsConfig (private val greetingsHandler: GreetingsHandler) {

    /**
     * This is the router function. In kotlin we can either write the
     * router in a Java way
     *
     *       return route()
     *            .GET("/greetings/{name}" , ::greetingsHandler )
     *            .GET("/greetings" , ::getGreetingsHandler
     *            .build()
     *  or in a more idiomatic kotlin way.
     * https://docs.spring.io/spring-framework/docs/5.0.0.BUILD-SNAPSHOT/kdoc-api/spring-framework/org.springframework.web.reactive.function.server/-router-function-dsl/
     */
    @Bean
    fun routes() = router {

        GET("/greeting/{name}" , greetingsHandler::handleGreeting )
        GET("/greetings/{name}", greetingsHandler::handleGreetings)
    }

}