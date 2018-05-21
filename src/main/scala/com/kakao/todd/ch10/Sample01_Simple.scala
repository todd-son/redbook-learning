package com.kakao.todd.ch10

object Sample01_Simple extends App {
  val stringMonoid = new Monoid[String] {
    override def op(a1: String, a2: String): String = a1 + a2

    override def zero: String = ""
  }

  def listMonoid[A] = new Monoid[List[A]] {
    override def op(a1: List[A], a2: List[A]): List[A] = a1 ++ a2

    override def zero: List[A] = Nil
  }

  val intAddition = new Monoid[Int] {
    override def op(a1: Int, a2: Int): Int = a1 + a2

    override def zero: Int = 0
  }

  val booleanOr = new Monoid[Boolean] {
    override def op(a1: Boolean, a2: Boolean): Boolean = a1 || a2

    override def zero: Boolean = true
  }

  val booleanAnd = new Monoid[Boolean] {
    override def op(a1: Boolean, a2: Boolean): Boolean = a1 && a2

    override def zero: Boolean = false
  }

  println(stringMonoid.op(stringMonoid.op("a", "b"), "c") == stringMonoid.op("a", stringMonoid.op("b", "c")))
  println(stringMonoid.op(stringMonoid.zero, "a") == stringMonoid.op("a", stringMonoid.zero))
  println(booleanOr.op(booleanOr.op(true, false), false) == booleanOr.op(true, booleanOr.op(false, false)))
  println(booleanOr.op(booleanOr.zero, false) == booleanOr.op(false, booleanOr.zero))
  println(endoMonoid.op((x: Int) => x + 1, (x: Int) => x + 2)(3))
  println(endoMonoid.op((x: Int) => x + 1, endoMonoid.op((x: Int) => x + 2, (x: Int) => x + 4))(3))

  def endoMonoid[A]: Monoid[A => A] =
    new Monoid[A => A] {
      override def zero: A => A = (a: A) => a

      override def op(f: A => A, g: A => A): A => A = f.compose(g)
    }


  def concatenate[A](as: List[A], monoid: Monoid[A]) = as.foldLeft(monoid.zero)(monoid.op)

  def foldMap[A, B](as: List[A], monoid: Monoid[B])(f: A => B): B =
    as match {
      case a :: as => monoid.op(f(a), foldMap(as, monoid)(f))
      case Nil => monoid.zero
    }
}

trait Monoid[A] {
  def op(a1: A, a2: A): A

  def zero: A
}
