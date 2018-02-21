package com.kakao.todd.ch06

object RandomTest extends App {
  val rng = new scala.util.Random()

  println(rng.nextDouble())
  println(rng.nextDouble())
  println(rng.nextInt())
  println(rng.nextInt(10))

  def rollDie: Int = {
    val rng = new scala.util.Random()
    rng.nextInt(6) // 0 ~ 5
  }
}

