package com.study.reactor.reactor3

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class Reactor3 {
    /*
    fun assertNextTest() {
        val flux: Flux<User> = Flux.just(User("kim", 24), User("lee", 23))
        StepVerifier.create(flux)
                .assertNext { u -> u.age == 24 && u.name == "kim" }
                .assertNext { u -> u.age == 23 && u.name == "lee" }
                .verifyComplete()
        /*
            assertNext는 람다식 사용가능
            verifyComplete은 expectComplete() + verify()이다.

         */
    }

    fun expectNextTest() {
        val flux: Flux<String> = Flux.just("kim", "lee")
        StepVerifier.create(flux)
                .expectNext("kim")
                .expectNext("lee")
                .expectComplete()
                .verify()
    }

    fun expectErrorTest() {
        val mono: Mono<String> = Mono.error(RuntimeException())

        StepVerifier.create(mono)
                .expectComplete()
        //.expectError(RuntimeException::class.java)
    }

    /*
       Test할 때 사용 해야한다.
       StepVerifier는 Publisher를 Subscribe하면서 정의하는대로 제대로 돌아가는지 확인한다.
       기대하지 않은 값이 나올 때에는 AssertionError가 발생한다.
       DSL을 제공하며, 종료 이벤트 액션은 Complenation,Error 둘중 하나이다.
    */
*/
}

class User(val name: String, val age: Int)