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

sealed trait Option[+A] {
  def map[B](f: A => B): Option[B] = this match {
    case None => None
    case Some(x) => Some(f(x))
  }

  // B >: A B must be super type of A
  // => B  laziness
  def getOrElse[B >: A](default: => B): B = this match {
    case None => default
    case Some(x) => x
  }

}

object Option {
  def lift[A,B](f: A => B): Option[A] => Option[B] = _.map(f)

}

case object None extends Option[Nothing]

case class Some[+A](get: A) extends Option[A]
