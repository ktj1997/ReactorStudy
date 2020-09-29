package com.study.reactor.reactor6

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import java.util.function.Consumer

//에러처리
class Reactor6 {
    /*
        에러가 발생하면, Subscriber의 onError메소드가 호출된다.
        에러신호는 종료신호이기 때문에, 에러신호가 발생하면 시퀀스가 종료되고,
        더 이상 데이터가 발생하지 않는다.
    */
    fun errorHandlingWithSubscribe() {
        Flux.range(1, 10).map {
            if (it == 5)
                throw CustomException("내가 만든 에러")
            else
                it
        }.subscribe({ next -> println(next) },
                { error -> println(error) },
                { println("Complete") })
        /*
            Subscribe로 에러 핸들링
            Next(Consumer)
            Error(Consumer)
            Complete(Runnable)
         */

    }

    fun whenErrorUseDefault() {
        Flux.range(1, 10).map {
            if (it == 5) throw CustomException("내가 만든 에러")
            else it
        }.onErrorReturn(5).subscribe { println(it) }
        /*
            onError 는 종료신호이기 때문에 에러가 났을 때 5라는 대체값을 주긴하지만,
            그 뒤에 값은 여전히 실행되지 않는다.
         */
    }

    fun whenErrorResume() {
        Flux.range(1, 10).map {
            when {
                it % 3 == 0 -> throw CustomException("내가 만든 에러")
                it % 5 == 0 -> throw IllegalArgumentException("내가만들진 않았지만 에러 2")
                else -> it
            }
        }.onErrorResume { err ->
            if (err is CustomException)
                Flux.just(3, 4, 5, 6, 7)
            else
                Flux.just(5, 7, 7, 8, 9)
        }.subscribe { println(it) }
        /*
            에러 발생 시 다른 시퀀스로 대체한다.
            다른 시퀀스로 대체할 때는 원래 있던 제네릭을 따른다.
         */
    }
}

class CustomException(msg: String) : RuntimeException(msg)