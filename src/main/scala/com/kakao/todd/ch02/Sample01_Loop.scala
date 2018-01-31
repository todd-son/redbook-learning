package com.kakao.todd.ch02

object Loop {
  def main(args: Array[String]): Unit = {
    println(factorial(1))
    println(factorial(2))
    println(factorial(3))

  }

  def factorial(n: Int) = {
    // naming convention for loop(loop or go)
    @annotation.tailrec
    def loop(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else loop(n - 1, n * acc)

    loop(n, 1)
  }
}