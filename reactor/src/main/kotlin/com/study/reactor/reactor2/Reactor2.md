#Reactor-2

Flux와 Mono는 Publisher다.             

Flux: 0~N개를 가질 수 있다.            
Mono: 0~1개를 가질 수 있다.                

Flux와 Mono 모두 Static Method와 Instance Method를 가진다.              

Static Method --> Flux와 Mono 생성                     
Instance Method                 
--> Operator                
---> 비동기적으로 sequence 생성해준다.

onComplete & onError는 종료이벤트이다.              

onComplete --> Publisher 작업 완료 후 최종작업                   
onError --> 에러 처리 
