package com.kakao.todd.ch10

object Sample02_Fold extends App {
  val stringMonoid = new Monoid[String] {
    override def op(a1: String, a2: String): String = a1 + a2

    override def zero: String = ""
  }

  val intAddition = new Monoid[Int] {
    override def op(a1: Int, a2: Int): Int = a1 + a2

    override def zero: Int = 0
  }

  println(concatenate[String](List("a", "b", "c"), new Monoid[String] {
    override def zero: String = ""

    override def op(a1: String, a2: String): String = a1 + a2
  }))

  println(foldMap(List(1, 2, 3), stringMonoid)(a => a.toString()))
  println(foldMap(List("1", "2", "3"), intAddition)(a => a.toInt))
  println(foldMap2(List(1, 2, 3), stringMonoid)(a => a.toString()))
  println(foldMapV(IndexedSeq(1, 2, 3), stringMonoid)(a => a.toString()))

  def concatenate[A](as: List[A], monoid: Monoid[A]) = as.foldLeft(monoid.zero)(monoid.op)

  def foldMap[A, B](as: List[A], monoid: Monoid[B])(f: A => B): B =
    as match {
      case x :: xs =>
        monoid.op(f(x), foldMap(xs, monoid)(f))
      case Nil => monoid.zero
    }

  def foldMap2[A, B](as: List[A], monoid: Monoid[B])(f: A => B): B =
    as.foldLeft(monoid.zero)((b, a) => monoid.op(b, f(a)))

  def foldMapV[A,B](v: IndexedSeq[A], m: Monoid[B])(f: A => B): B =
    if (v.isEmpty) {
      m.zero
    } else if (v.length == 1)
      f(v.head)
    else {
      val (c1, c2) = v.splitAt(v.length / 2)
      m.op(foldMapV(c1, m)(f), foldMapV(c2, m)(f))
    }
}
