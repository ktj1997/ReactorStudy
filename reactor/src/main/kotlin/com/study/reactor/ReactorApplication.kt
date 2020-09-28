package com.study.reactor

import com.study.reactor.reactor2.Reactor2
import com.study.reactor.reactor3.Reactor3
import com.study.reactor.reactor4.Reactor4
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactorApplication

fun main(args: Array<String>) {
    runApplication<ReactorApplication>(*args)
    var reactor4 : Reactor4 = Reactor4()
    reactor4.flatMapAsyncFunction()

    /*
        var flux: Flux<String> = reactor2.returnFluxIterable()
        flux.doOnNext { it -> println(it) }.map { it -> it.toUpperCase() }.subscribe{it -> println(it)}
     */
}
