package com.kakao.todd.ch02.exercise

object Exer03_Curry {
  def main(args: Array[String]): Unit = {

    println(curry((a: Int, b: Int) => a + b)(5)(3))
    println(uncurry((a: Int) => (b: Int) => a + b)(5, 3))

  }

  def partial1[A, B, C](a: A, f: (A, B) => C): B => C = (b: B) => f(a, b)

  def curry[A, B, C](f: (A, B) => C): A => (B => C) =
    a => b => f(a, b)

  def uncurry[A, B, C](f: A => B => C): (A, B) => C =
    (a, b) => f(a)(b)
}
