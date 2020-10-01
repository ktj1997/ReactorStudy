package com.study.reactor

import com.study.reactor.reactor7.Reactor7
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactorApplication

fun main(args: Array<String>) {
    runApplication<ReactorApplication>(*args)
    var reactor7: Reactor7 = Reactor7()
    reactor7.subscribeOnScheduling()


    /*
        var flux: Flux<String> = reactor2.returnFluxIterable()
        flux.doOnNext { it -> println(it) }.map { it -> it.toUpperCase() }.subscribe{it -> println(it)}
     */
}
