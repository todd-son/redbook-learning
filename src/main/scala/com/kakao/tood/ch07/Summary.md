# Parallelism

현 세대의 컴퓨터는 multi-core 이다. 그러나 병렬로 실행하는 프로그램을 작성하는 일은 매우 어렵다.
전통적으로 Thread 사이에 통신에 쓰이는 메커니즘은 추론 분석하기 어렵기로 악명이 높다. 
그러다보니 Race condition(경쟁 조건) 이나 Dead lock(교착)이 발생하기 슆고 Scalability 를 확보하기도 상당히 까다롭다.

# Library Design Example

착안 - 리스트의 합을 구하는데 계산을 병렬적으로 수행할 수 있는 라이브러리를 설계해보자.

라이브러리 설계의 과정은 착안한 아이디어를 refining 하는 과정을 거쳐서 원하는 기능을 가능하게 하는 방식으로 진행




