# monoid

오직 대수에 의해서만 정의되는 간단한 구조
결합법칙과 항등법칙을 합쳐서 모노이드 법칙 

- op(op(x,y), z) == op(x, op(y,z))
- op(x, zero) == x 이고 op(zero, x) == x

모노이드란 하나의 형식이되 그 형식에 대해 결합법칙을 만족하며 항등원을 가진 이항 연산이 존재하는 형식

# fold in monoid 

모노이드는 결합 및 항등법칙이 성립하기 때문에 foldLeft와 foldRight의 결과가 같다.

이에 모노이드를 이용하면 균형접기(balance fold)를 할 수 있다. 

op(a, op(b, op(c,d))) = op(op(op(a,b) ,c) ,d)  = op(op(a,b), op(c,d))

균형접기가 중요한 이유는 바로 병렬처리가 가능하다는 것이다. 

# parallel parsing


