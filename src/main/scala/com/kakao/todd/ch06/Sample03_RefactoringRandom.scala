package com.kakao.todd.ch06

import com.kakao.todd.ch06.RNG2.SimpleRNG2


object RefactoringRandomTest extends App {
  val result = RNG2.nextInt(SimpleRNG2(42))

  println(result._1)
  println(result._2)
}

case class State[S, +A](run: S => (A, S))

trait RNG2 {
  def nextInt: (Int, RNG2)
}

object RNG2 {
  type Rand[+A] = RNG2 => (A, RNG2)

  case class SimpleRNG2(seed: Long) extends RNG2 {
    override def nextInt: (Int, RNG2) = {
      val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFFL
      val nextRNG = SimpleRNG2(newSeed)
      val n = (newSeed >>> 16).toInt

      (n, nextRNG)
    }
  }

  def nextInt: Rand[Int] = _.nextInt
}

