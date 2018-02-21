## Side effect of Random Utility

Referential transparency 를 만족하지 못한다. 이에 메소드의 검사성이 떨어진다.

[Sample01_SideEffect](Sample01_SideEffect.scala) 

```
rollDie 함수의 구현에 오류가 있지만 6번중 5번은 테스트에 통과하게 된다.

```

## Pure function Random Utility

Referential transparency 를 되찾는 관건은 상태 갱신을 명시적으로 드러내는 것이다. 
즉 상태를 부수효과로서 갱신하지 말고, 새 상태를 발생한 난수와 함께 돌려주면 된다. 

[Sample02_PureRandom](Sample02_PureRandom.scala)

대체로 어떤 형식 A에 대해 RNG => (A, RNG) 형태의 형식의 함수를 가지게 됨. 이런 종류의 함수를 state action (상태 동작) 또는 state transition 
(상태 전이)라고 한다. type 형식 별칭을 이용해서 리팩토링을 수행해보자.

[Sample03_RefactoringRandom](Sample03_RefactoringRandom.scala)