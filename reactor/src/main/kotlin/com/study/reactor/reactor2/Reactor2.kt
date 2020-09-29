package com.study.reactor.reactor2

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.lang.RuntimeException
import java.time.Duration

class Reactor2 {
    /*
        Flux --> 0~N개를 가질 수 있다.
        Mono --> 0~1개를 가질 수 있다.

        Flux와 Mono모두 Static Method와 Instance Method를 가지고있다.

        StaticMethod --> Publisher를 생성하는 메소드
        InstanceMethod --> Operator | 비동기적으로 sequence를 생성해준다.

        onComplete, onError --> 종료 이벤트이다
        onComplete --> Publisher의 flow가 다 끝나고 최종작업
        onError --> 에러처리
     */

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

    fun returnSwitchIfEmpty(): Unit {
        Flux.empty<String>().switchIfEmpty(Flux.just("qwer", "asdf")).subscribe{println(it)}
        /*
            비어있는 Flux라면 다른 시퀀스를 사용하게 한다.
         */
    }
    fun returnDefault()
    {
        Flux.empty<String>().defaultIfEmpty("hello").subscribe{println(it)}
        /*
            값이 필요하다.
         */
    }

    fun errorMono(): Mono<String> {
        return Mono.error(RuntimeException())
        /*
            Error 던지기
         */
    }
}