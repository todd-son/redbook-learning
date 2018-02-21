package com.kakao.tood.ch07


object ParSumTest extends App {
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case h :: t => h + sum(t)
  }

  // refining => divide and conquer for parallel
  def sum2(ints: List[Int]): Int =
    ints match {
      case Nil => 0
      case h :: Nil => h
      case s =>
        val (l, r) = s.splitAt(s.size / 2)
        sum2(l) + sum2(r)
    }

  // invent => parallelism structure
  def sum3(ints: List[Int]): Int =
    ints match {
      case Nil => 0
      case h :: Nil => h
      case s =>
        val (l, r) = s.splitAt(s.size / 2)
        val sumL = Par.unit(sum3(l))
        val sumR = Par.unit(sum3(r))
        Par.get(sumL) + Par.get(sumR)
    }

  def sum4(ints: List[Int]): Int =
    ints match {
      case Nil => 0
      case h :: Nil => h
      case s =>
        val (l, r) = s.splitAt(s.size / 2)
        val sumL = Par.unit(sum3(l))
        val sumR = Par.unit(sum3(r))
        Par.map2(sumL, sumR)(_ + _)
    }


  val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  println(sum(list))
  println(sum2(list))
}

trait Par[+A] {

}

object Par {
  def unit[A](a: => A): Par[A] = ???

  def get[A](a: Par[A]): A = ???

  def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C) = ???
}
