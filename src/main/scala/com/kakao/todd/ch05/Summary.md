## Strictness vs Non-Strictness(Laziness)

비엄격성은 함수의 한 속성이다. 함수가 엄격하지 않다는 것은 그 함수가 하나 이상의 인수를 평가하지 않을 수도 있다는 뜻이다.
대표적으로 부울함수 &&와 ||의 단축 평가는 엄격하지 않다. if 문도 인수가 셋인 함수로 생각할 수 있다.

```
false && { println("!!); true} // 아무것도 출력되지 않는다.

val result =if (input.isEmpty) pritln("empty input") else input
```

() => A는 인수를 받지 않고 A를 돌려주는 함수이다. 이를 thunk라고 부른다.
[Sample01](Sample01_Thunk.scala)

## Stream

lazy list라고도 하며, 효율성과 모듈성에 좋은 예이다. Laziness를 이용해 서술과 평가를 분리할 수 있다. 
Stream을 활용해서 map과 filter들이 연산을 수행하면 map과 filter가 번갈아 수행된다. 
이는 중간 정보를 인스턴스화 하지 않는 다는 것을 의미하며 이에 스트림을 1급함수라고 부르기도 한다.


```
Memoization 

lazy라는 키워드를 사용해서 value를 정의하면 메모화가 수행된다.

```
[Sample02](Sample02_Stream.scala)

## infinite stream and corecursive

Stream을 lazy하기 떄문에 무한 스트림을 생성할 수도 있다.

공재귀라고 한다. 재귀 함수는 자료를 소비하지만 공재귀 함수는 자료를 생산한다. 공재귀함수는 생산성을 유지하는 한 종료될 필요가 없다.
