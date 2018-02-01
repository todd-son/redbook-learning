package com.kakao.todd.ch02

object FunctionAsValue {
  def main(args: Array[String]): Unit = {
    val lessThan = new Function2[Int, Int, Boolean] {
      def apply(a: Int, b: Int) = a < b
    }

    println(lessThan.apply(10, 20))

    println(((a: Int, b: Int) => a < b)(10, 20))
  }

}
