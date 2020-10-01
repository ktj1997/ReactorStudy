package com.study.reactor.reactor7

import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

class Reactor7 {
    /*
        Reator는 비동기실행을 강제시키지 않는다.
        스케줄링을 하지 않으면, 모두 Main 쓰레드에서 실행된다.


        쓰레드 스케줄링
        1) publishOn()
            publishOn() 메서드를 이용하면 next, complete, error신호를 별도 쓰레드에서 처리 가능.
            map(), flatMap() 등의 변환도 publishOn()이 지정한 쓰레드를 이용해서 처리 가능.
            인자로는 첫번재는 비동기로 처리할 스케줄러, 2번째는 스케줄링 이전에 가져올 데이터의 갯수이다.

        2) subscribeOn()
            Subscriber가 Sequence에 대한 request 신호를 별도의 스케줄러에서 처리,
            즉 Mono나 Flux를 실행할 스케줄러를 지정하는 것.


        <스케줄러의 종류>
        1)Schedulers.immediate() : 현재 쓰레드에서 실행
        2)Schedulers.single() : 쓰레드가 한 개인 쓰레드풀을 이용해서 실행, 한 쓰레드를 공유해서 실행
        3)Schedulers.elastic() :쓰레드 풀을 이용해서 실행, Blocking I/O를 리액터로 처리할 때 유용
                              쓰레드가 필요하면 생성, 60초이상 유휴상태인 쓰레드는 제거
        4)Schedulers.parrallel(): 고정크기의 쓰레드 풀을 이용해서 실행, 병렬작업에 적합

        -->single(), elastic(), parallel()이 생성하는 쓰레드는 데몬 쓰레드로서 main 쓰레드가 종료되면 함께 종료.
     */

    fun publishOnScheduling() {
        Flux.range(1, 10).map { it * -1 }
                .doOnNext { println("{$it}+${Thread.currentThread().name}") }
                .publishOn(Schedulers.elastic()).map { it * -1 }
                .doOnNext { println("{$it}+${Thread.currentThread().name}") }.subscribe()
        /*
            publishOn() 이전에는 MainThread에서 10개실행 , 이후에는 elastic으로 만들어진 Thread에서 실행.
            순서는 보장되지 않는다.
         */
    }

    fun subscribeOnScheduling() {
        Flux.range(1, 10).subscribeOn(Schedulers.elastic()).map { it * -1 }
                .doOnNext { println("{$it}+${Thread.currentThread().name}") }
                .map { it * -1 }
                .doOnNext { println("{$it}+${Thread.currentThread().name}") }.subscribe()

        /*
        subscribeOn()이 publishOn() 뒤에 위치하면 실질적으로 prefetch할 때를 제외하면 적용되지 않는다.
         subscribeOn()은 원본 시퀀스의 신호 발생을 처리할 스케줄러를 지정하므로 시퀀스 생성 바로 뒤에 subscribeOn()을 지정하도록 하자.
         또한 두 개 이상 subscribeOn()을 지정해도 첫 번째 subscribeOn()만 적용된다.
         */
    }
}