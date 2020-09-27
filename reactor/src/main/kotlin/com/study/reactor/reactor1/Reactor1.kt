package com.study.reactor.reactor1

import reactor.core.publisher.Flux

class Reactor1 {
    fun reactor1() {
        var flux: Flux<String> = Flux.just("Kotlin", "Reactor", "Webflux")
        flux.map { it -> it + "foo" }.subscribe{println(it)}
    }
    /*
        Publisher는 데이터를 제공해주는 입장인데,
        Subscriber가 구독을 해줘야지만 데이터를 push한다.
        즉, Subscribe되지 않으면, 아무일도 일어나지 않는다.

        Subscriber는 BackPressure라는 것을 가지고있다.
        예를들어, Publisher가 Subscriber를 생각하지않고, 마구잡이로 데이터를 전송한다면 문제가 발생할 것이다.
        Subscriber는 pull을 하여, 데이터의 전송 갯수등을 조절 할 수있는데 이것이 BackPressure이다.

        위 코드의 Map같은 것이 Operator인데, Publisher의 코드를 가공하고 새로운 Publisher를 리턴한다.
     */
}