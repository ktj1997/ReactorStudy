package com.study.reactor.reactor2

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.lang.RuntimeException
import java.time.Duration

class Reactor2 {
    fun returnEmptyFlux(): Flux<String> {
        return Flux.empty()
    }

    fun returnFlux(): Flux<String> {
        return Flux.just("Hello", "Kotlin")
    }

    fun returnFluxIterable(): Flux<String> {
        var list: List<String> = listOf("Hello", "Kotlin")
        return Flux.fromIterable(list)
    }

    fun intervalFlux() {
        Flux.interval(Duration.ofMillis(100)).take(10).subscribe { println(it) }
        /*
            MainThread가 먼저 죽으면 실해 안된다.
         */
    }

    fun errorMono(): Mono<String> {
        return Mono.error(RuntimeException())
        /*
            Error 던지기
         */
    }
}