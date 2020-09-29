package com.study.reactor.reactor5

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

class Reactor5 {
    /*
        Merge는 Publisher를 합치는 작업을 수행합니다.
        Merge는 Publisher를 비동기적으로 다루지만
        Concat은 Publisher가 Complete되기전에는 Push하지 않습니다.
        Flux 두개를 합쳐서 한개의 Flux를 만들 수 있으며,
        Mono 두개를 합쳐서 Flux를 만들어 낼 수도 있다.

     */

    fun mergeWithTest() {
        var flux1: Flux<Long> = Flux.interval(Duration.ofMillis(100)).take(10)
        var flux2: Flux<Long> = Flux.just(100, 99)

        flux1.mergeWith(flux2).subscribe { println(it) }
        /*
            Merge는 비동기적인 코드이기 때문에, 실행 순서를 보장 하지 않는다.
            flux2는 just( ) 로 만들어낸 코드이기 때문에 flux1과 다르게 시간이 걸리지 않는다.
            즉 실행의 출력은 100, 99,0~9 로 찍힌다.
         */
    }
    fun concatWithTest(){
        var flux1: Flux<Long> = Flux.interval(Duration.ofMillis(100)).take(10)
        var flux2: Flux<Long> = Flux.just(100, 99)
        flux1.concatWith(flux2).subscribe { println(it) }
        /*
            concatWith는 실행순서가 보장된다.
            flux1을 중심으로 concatWith가 되었기 때문에 flux1이 모두 작업이 끝날 때까지
            flux2는 출력되지않는다. flux1이 모두 complete된 이후에 flux2가 합쳐지고 출력된다.
         */
    }
    fun makeFluxWithTwoMono(){
        var mono1 : Mono<Int> = Mono.just(1)
        var mono2 : Mono<Int> = Mono.just(7)

        Flux.merge(mono1,mono2).subscribe { println(it) }
    }
}