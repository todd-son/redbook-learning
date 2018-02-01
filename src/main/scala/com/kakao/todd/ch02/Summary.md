# Functional Loop

```
Tail recursion

호출자가 재귀 호출의 결과를 그대로 돌려주는 것 이외에 아무런 일을 하지 않을 때, 그런 호출을 꼬리에서의 호출 줄여서 꼬리 재귀라고 부른다.
재귀 호출이 꼬리 재귀의 호출이면 StackTrace를 사용하지 않는 반복문 형태로 스칼라는 컴파일 한다.
자신이 작성한 재귀 함수에 꼬리 호출이 실제로 제거되었는지 확인할 필요가 있다면 tailrec annotation를 재귀함수에 적용하면 된다.
```

[SampleCode](Sample01_Loop.scala)

# High-order Function (HOF)

함수를 인자로 받는 함수를 고차함수라고 한다.

[SampleCode](Sample02_HighOrderFunction.scala)

# Polymorphic Function

임의의 타입에 동작하는 함수.

[SampleCode](Sample03_PolymorphicFunction.scala)

# Function as value 

스칼라에서 함수 리터럴을 정의할 때 실제로 정의되는 것은 apply라는 메서드를 가진 하나의 객체
(a,b) => a < b 와 같은 함수 리터를은 객체 생성에 대한 구문적 겉치레이다.

[SampleCode](Sample04_FunctionAsValue.scala)

# Partial Application

함수의 인자의 일부만 적용하는 것

[SampleCode](Sample05_PartionApplication.scala)

