# 서론
## Functional Programming
FP란 side effect가 없는 순수함수들로 프로그램을 구축하는 것

## Side effect 
- 변수 수정
- 자료구조 수정
- 객체의 필드 변경
- 예외를 던지거나 오류를 내면서 실행을 중단
- 콘솔에 출력하거나 사용자 입력을 읽어드린다.
- 파일을 기록하거나 파일을 읽어드린다.
- 화면을 그린다.

## FP의 특징
- 작성하는 방식의 제약이지 표현가능한 프로그램의 제약이 아님 (입출력, 오류처리, 자료수정 다 할 수 있다.)
- 모듈성 증가로 test, 재사용, 병렬화, 일반화가 쉽다

# 1.1. FP 이점
[SampleCode](Sample01_SideEffect.scala)


함수형 프로그래밍에서는 순수한 핵심부와 얇은 계층(부수 효과들이 처리되는 외부와의 경계를 이루는)을 이용해서 프로그램을 구현하는 방식이 흔히 쓰인다.
사실 유용한 프로그램은 대부분 어떤 형태로든 부수 효과나 변이를 수반하기 마련이다. 그런 프로그램을 어떻게 작성해야 할까? 답은 부수효과가 발생하긴
하지만 관찰되지 않도록 코드의 구조를 짜야 한다.(ex: 함수안에서 선언된 자료구조는 변이가 가능하다. 마찬가지로 함수를 감싸는 다른 함수가 알아채지
못한다면, 함수안에서 파일에 뭔가를 기록할 수 있다.???)


# 1.2. Pure Function

```
Referential transparency and Pure function, Sustitution model

만일 모든 프로그램 p에 대해 표현식 e의 모든 출현을 e의 평가 결과로 치환해도 p의 의미에 아무 영향이 미치지 않는다면, 그 표현식 e는 참조에 투명하다.
만일 표현식 f(x)가 모든 x에 대해 참조에 투명하면 함수 f는 순수 함수이다. 참조투명성을 지키면 치환모형이라고 프로그램에 대한 등식적 추론이 가능하게 된다.

```

## Referential Transparency
임의의 프로그램에서 만일 어떤 표현식을 그 평가 결과로 바꾸어도 프로그램의 의미가 변하지 않는다면 그 표현식은 참조에 투명한 것이다. 
(ex: 2 + 3 이라는 표현식을 모두 5로 바꾸어도 프로그램의 의미는 전혀 바뀌지 않는다.)

## Substitution Model
치환모형은 추론이 간단하다. 평가의 부수효과가 국소적으로 일어난다. 코드블럭을 이해하기 위해 머릿속에서 일련의 상태 갱신들을 따라갈 필요가 없다. 국소 추론만으로 
코드를 이해할 수 있다.

