# Functional Data Structure
## Definition of Functional Data Structure

오직 순수 함수만으로 조작되는 자료구조이다. 순수 함수는 자료를 그 자리에서 변경하거나 기타 부수 효과를 수행하는 일이 없어야 한다. 
따라서 함수적 자료구조는 정의에 의해 불변이다.

```
3 + 4를 평가하면 3이나 4가 수정되는 일이 없이 새로운 정수 7이 나온다. 두 목록 a ++ b 를 하면 새로운 목록이 만들어진다.
자료구조가 그런 식으로 조작된다면 여분의 복사가 많이 일어나지 않을까? 답은 그렇지 않다이다.

```

## Generics, Variance

스칼라에서 기본적으로 제너릭타입은 기본적으로 nonvariant(무공변)이다. 이는 다른 말로 융통성이 없다라고 한다.
+A와 같이 타입 파라미터에 +기호를 붙이면 covariant를 의미한다.
-A와 같이 타입 파라미터에 -기호를 붙이면 contravariant를 의미한다.

[Image](Sample03_Variance.png)

## Data sharing 

Cons(1, xs)를 수행시 xs를 실제로 복사할 필요가 없다. xs 자체가 불변의 자료이므로 이 자료를 언제 어떻게 수정할지 걱정할 필요없이 
그냥 재사용하면 된다. 자료가 변하거나 깨지지 않도록 방어적으로 복사본을 만들어 둘 필요가 없다는 것이다.

[Image](Sample03_Datastructure.png)


## Encapsulation 

FP에서는 OOP와 다른 형태로 캡슐화를 바라본다. 함수적 자료형식에서는 내부가 불변이라면 그 내부를 외부에 노출하더라도 버그로 이어질 가능성이 적다.
Java에서 List 내부변수 size를 노출하면 위험하지만 그래서 getter를 사용해야만 한다. Scala에서는 무방하다고 봐도 된다.




