package com.study.reactor

import com.study.reactor.reactor2.Reactor2
import com.study.reactor.reactor3.Reactor3
import com.study.reactor.reactor4.Reactor4
import com.study.reactor.reactor5.Reactor5
import com.study.reactor.reactor6.Reactor6
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactorApplication

fun main(args: Array<String>) {
    runApplication<ReactorApplication>(*args)
    var reactor6 : Reactor6 = Reactor6()
    reactor6.replaceError()

    /*
        var flux: Flux<String> = reactor2.returnFluxIterable()
        flux.doOnNext { it -> println(it) }.map { it -> it.toUpperCase() }.subscribe{it -> println(it)}
     */
}
