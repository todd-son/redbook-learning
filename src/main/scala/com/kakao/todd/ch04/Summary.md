# Exception Handling
## Exception Handling in FP

예외를 던지는 것 자체가 side effect. 실패 상황과 예외를 보통의 값으로 표현할 수 있으며, 일반적인 오류 처리/복구 패턴을 추상화한 고차 함수를 작성할 수 있다.
오류를 값으로 돌려주면 referential transparency를 유지할 수 있다.
Scala에서는 C 스타일의 오류 부호와 달리 형식에 완전히 안전하며, 오류 처리와 전파에 관한 공통적인 패턴들은 고차 함수를 이용해서 캡슐화 할 것이다. 

## Strength and Weakness of Exception

### Weakness

**예외는 참조 투명성을 위반하고 문맥 의존성이 생긴다.** 자바에서도 예외는 오류 처리에만 사용하고 흐름의 제어에는 사용하지 말아야 한다는 원칙이 있다.
**예외는 안전하지 않다.** Function Int => Int만 보고도 이 함수가 예외를 던진다는 사실을 전혀 알 수 없다. 그래서 

## Strength

오류 처리 논리의 통합과 중앙집중화 

```
자바에서는 checked exception으로 오류 처리를 강제할 수 있다. 하지만 호출하는 쪽에서 판에 박힌(bolierplate) 코드가 양산된다.
더욱 중요한 점은 checked exception은 고차 함수에서 통하지 않는다. 고차 함수에서는 checked exception에 대한 오류 처리를 강제할 수 없다.

def map[A,B](l: List[A)(f: A => B): List[B]
```

## Problems of Sentinel value

- 오류가 소리 없이 전파될 수 있다. 호출자가 오류 점검을 실수로 빼억어도 컴파일러가 경고를 해주지 않는다.
- 진짜 결과를 받았는지 점검하는 boiler plate 코드가 양산된다.
- 출력형식에 따라서 경게값을 결정하는 것이 불가능할 수도 있다. 
- 호출자에게 특별한 방침이나 호출 규약을 요구한다.

## Option

오류에 대한 첫번째 해법은 함수가 항상 답을 내지는 못한다는 점을 명시적으로 표현하라.

[Sample](Sample01_FunOption.scala)

Option을 사용하기 시작하면 코드 기반 전체에 Option이 번지게 되리라는 판단을 할 수 있다. 그러나 실제로는 그런 부담을 가질 수 없다. 
일반 함수를 Option에 대해 작용하는 함수로 승급 시킬수 있기 때문이다. 

[Sample](Sample02_Lift.scala)

PartialFunction은 일부의 입력에 대한 결과만을 리턴하는 함수이다. Scala에서 PartialFunction은 항상 lift 할 수 있다. 

## Either

실패가 발생했음을 알면 충분한 때에는 Option을 사용하면 된다. 실패에 관해 알고 싶은 정보가 있고 그것을 부호화하는 자료 형식으로 만들고 싶다면 
Either를 사용하면 된다.