package com.study.reactor.reactor4

import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

class Reactor4 {
    fun mapFunction() {
        Flux.just("qwer", "asdf", "zxc").map { it.length }.subscribe { println(it) }
        /*
            map은 Operator이며, 각 데이터를 가공할 수 있다.
            데이터를 1:1 방식으로 교환해준다.
            map은 동기적이다.
         */
    }

    fun flatMapFunction() {
        Flux.just(1, 2, 3).flatMap { Flux.range(1, it) }.subscribe { it -> println("$it" + Thread.currentThread()) }
        /*
            Flux.range() 는 범위에 따라서 새롭게 Flux생성
            하지만 flatMap이기에 Flux<Flux<Integer>>가 아니라, Flux<Integer>로 연결된다.
            map 대신 flatMap을 주로 사용해야한다. --> 비동기적이기 때문이다.
            하지만 위의 코드는 여러 쓰레드를 이용한 비동기적 코드가 아니다.
         */
    }

    fun flatMapAsyncFunction() {
        Flux.just("a", "b", "c", "d", "e", "f", "g", "h", "i").window(3).flatMap { d1 -> d1.map { d2 -> listOf<String>(d2, Thread.currentThread().name) }.subscribeOn(Schedulers.parallel()) }.doOnNext {println(it)}.blockLast()
        /*
            위의 코드는 flatMapFunction과 달리 비동기적으로 실행된다.
            window 함수는 데이터를 maxSize의 갯수만큼 쪼개서 또다른 Flux를 리턴한다.
            subscribeOn으로 구독처리 쓰레드를 스케줄링 한 것이다.
            subscribeOn이 있는 것 처럼 publishOn도 존재한다.
            비동기적이므로, 순서를 보장하지 않는다. --> 물론 flatMapSequential()이 존재한다.
            blockLast()는 다른 쓰레드들이 작업을 완료하기전에 main쓰레드가 종료되는 것을 막아준다.
         */
    }

    fun filterFunction() {
        var flux: Flux<Int> = Flux.just(1, 2, 3, 4, 5).filter { it % 2 == 0 }
        flux.subscribe { println(it) }
        /*
            filter는 조건이 참인 것만 걸러내는 기능을 가진다.
         */
    }
}