package com.kakao.todd.ch05

import com.kakao.todd.ch05.Stream.{cons, empty}

object FunStream {
  def main(args: Array[String]): Unit = {
    def expensive = {
      println("expensive")
      "A"
    }

    val stream1 = Cons[String](() => expensive, () => Empty)

//    println(stream1.head)
//    println(stream1.head)
    println("==========")

    val stream2 = Stream(expensive, Empty)

//    println(stream2.head)
//    println(stream2.head)
//    println(stream2.head)
//    println(stream2.head)
//    println(stream2.head)
//    println(stream2.head)
    println("==========")

    def ones: Stream[Int] = Stream.cons(1, ones)

    println(ones.take(10).toList)
  }
}

