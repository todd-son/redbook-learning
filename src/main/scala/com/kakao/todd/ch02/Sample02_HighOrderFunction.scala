package com.kakao.todd.ch02

object HighOrderFunction {
  def main(args: Array[String]): Unit = {
    println(formatResult("factorial", 3, Loop.factorial))
  }

  // naming convention for function arguments(f, g, h)
  def formatResult(name: String, n: Int, f: Int => Int) =
    s"The $name of $n is ${f(n)}"
}
