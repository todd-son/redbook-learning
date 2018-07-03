package com.kakao.todd.ch04

object FunOption {
  def main(args: Array[String]): Unit = {
    println(mean(List(2.0, 1.0)).map(x => s"result : $x").getOrElse())
    println(mean(Nil).map(x => s"result : $x").getOrElse("empty"))

    val abs0: Option[Double] => Option[Double] = Option.lift(math.abs)
  }

  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty) None
    else Some(xs.sum / xs.size)
}
