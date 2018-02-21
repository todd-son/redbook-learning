package com.kakao.todd.ch02

object PartialApplication {
  def main(args: Array[String]): Unit = {

    val par = partial[Int, Int, Int](3, (x, y) => x + y)

    println(par(2))

    val cur = curry[Int, Int, Int]((x, y) => x + y)

    println(cur(3)(2))

    val partial2 = cur(3)

    println(partial2(2))
  }

  def partial[A, B, C](a: A, f: (A, B) => C): B => C =
    (b: B) => f(a, b)

  def curry[A, B, C](f: (A, B) => C): A => (B => C) =
    (a: A) => (b: B) => f(a, b)
}
