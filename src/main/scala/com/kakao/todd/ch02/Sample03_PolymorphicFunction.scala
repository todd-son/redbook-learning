package com.kakao.todd.ch02

object PolymorphicFunction {
  def main(args: Array[String]): Unit = {
    println(find[Int](Array(1,2,3,4), x => x > 2))
    println(find[String](Array("A", "B", "C"), x => x == "B"))
  }

  // naming convention for type parameter => one capital character (ex: A, B, C)
  def find[A](arr: Array[A], p: A => Boolean): Option[A] = {
    @annotation.tailrec
    def loop(i: Int): Option[A] =
      if (i == arr.length) None
      else if (p(arr(i))) Option(arr(i))
      else loop(i + 1)

    loop(0)
  }
}
