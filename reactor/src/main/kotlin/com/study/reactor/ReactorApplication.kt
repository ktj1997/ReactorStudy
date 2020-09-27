package com.study.reactor

import com.study.reactor.reactor1.Reactor1
import com.study.reactor.reactor2.Reactor2
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.core.publisher.Flux

@SpringBootApplication
class ReactorApplication

fun main(args: Array<String>) {
    runApplication<ReactorApplication>(*args)
    var reactor2: Reactor2 = Reactor2()
    var flux: Flux<String> = reactor2.returnFluxIterable()


    //flux.doOnNext { it -> println(it) }.map { it -> it.toUpperCase() }.subscribe{it -> println(it)}
}
