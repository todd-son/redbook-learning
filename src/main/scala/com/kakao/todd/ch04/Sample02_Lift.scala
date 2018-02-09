package com.kakao.todd.ch04

object LiftTest {
  def main(args: Array[String]) {
    println(List(1,2,3).lift(3).getOrElse(-1))
    println(List(1,2,3).lift(3))

    val pf: PartialFunction[Int, Boolean] = { case i if i > 0 => i % 2 == 0}

    def npf = pf.lift

    println(npf(-1))
    println(npf(4))
  }
}
