package com.kakao.todd.ch05

object ThunkTest {
  def main(args: Array[String]) {
    println(if2(true, 12, "Fail"))
  }

  def if2[A](cond: Boolean, onTrue: => A, onFalse: => A): A = if (cond) onTrue else onFalse
}
