# Functional Loop

[SampleCode](Sample01_Loop.scala)

```
Tail recursion

호출자가 재귀 호출의 결과를 그대로 돌려주는 것 이외에 아무런 일을 하지 않을 때, 그런 호출을 꼬리에서의 호출 줄여서 꼬리 재귀라고 부른다.
재귀 호출이 꼬리 재귀의 호출이면 StackTrace를 사용하지 않는 반복문 형태로 스칼라는 컴파일 한다.
자신이 작성한 재귀 함수에 꼬리 호출이 실제로 제거되었는지 확인할 필요가 있다면 tailrec annotation를 재귀함수에 적용하면 된다.
```

# High-order Function (HOF)
[SampleCode](Sample02_HighOrderFunction.scala)

함수를 인자로 받는 함수를 고차함수라고 한다.

# Polymorphic Function
