package com.study.reactor

import com.study.reactor.reactor1.Reactor1
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.core.publisher.Flux

@SpringBootApplication
class ReactorApplication

fun main(args: Array<String>) {
    runApplication<ReactorApplication>(*args)
    var reactor1: Reactor1 = Reactor1()
    reactor1.reactor1()
}
