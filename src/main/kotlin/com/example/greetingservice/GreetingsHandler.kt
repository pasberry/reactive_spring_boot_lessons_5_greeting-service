package com.example.greetingservice

import org.springframework.http.MediaType.TEXT_EVENT_STREAM
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono


@Component
class GreetingsHandler(val service: GreetingService){

    fun handleGreetings(request : ServerRequest): Mono<ServerResponse> {

        val name = request.pathVariable("name")
        return ServerResponse.ok()
                .contentType(TEXT_EVENT_STREAM)
                .body(service.greetMany(GreetingRequest(name)), GreetingResponse::class.java)

    }

    fun handleGreeting(request : ServerRequest): Mono<ServerResponse> {

        val name = request.pathVariable("name")
        return ServerResponse.ok()
                .body(service.greetOnce(GreetingRequest(name)), GreetingResponse::class.java)
                .doOnError { println(it)}
    }

}