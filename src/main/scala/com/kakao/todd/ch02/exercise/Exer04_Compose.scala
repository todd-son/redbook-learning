package com.kakao.todd.ch02.exercise

object Exer04_Compose {
  def main(args: Array[String]) {
    println(compose((b: Int) => b + 1, (a: Int) => a + 2)(3))
  }

  def compose[A, B, C](f: B => C, g: A => B): A => C =
    a => f(g(a))
}
