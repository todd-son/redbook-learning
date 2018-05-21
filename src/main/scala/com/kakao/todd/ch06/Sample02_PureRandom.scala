package com.kakao.todd.ch06

object PureRandomTest extends App {
  val (result, rng) = SimpleRNG(42).nextInt

  println(result)
  println(rng)

  val (result2, rng2) = rng.nextInt

  println(result2)
  print(rng2)

}

trait RNG {
  def nextInt: (Int, RNG)
}

case class SimpleRNG(seed: Long) extends RNG {
  override def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)
  }
}
