package com.study.reactor.reactor1

import reactor.core.publisher.Flux

class Reactor1 {
    fun reactor1() {
        var flux: Flux<String> = Flux.just("Kotlin", "Reactor", "Webflux")
        flux.map { it -> it + "foo" }.map { it -> println(it) }.subscribe()


    }
}