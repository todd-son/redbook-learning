package com.kakao.todd.ch02

object Loop {
  def main(args: Array[String]): Unit = {
    for(i <- 1 to 10)
      println(fib(i))
  }

  def fib(n: Int): Int =
    if (n <= 1)
      0
    else if (n == 2)
      1
    else
      fib(n - 2) + fib(n - 1)
}