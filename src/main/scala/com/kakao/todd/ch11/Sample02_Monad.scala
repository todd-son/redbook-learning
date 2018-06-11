package com.kakao.todd.ch11

object Sample02_Monad extends App {
  val listMonad = new Monad[List] {
    override def unit[A](a: => A): List[A] = List(a)

    override def flatMap[A, B](ma: List[A])(f: A => List[B]): List[B] = ma match {
      case x :: l => f(x) ++ flatMap(l)(f)
      case Nil => Nil
    }
  }

  val result = listMonad.map(List(1, 2, 3))(a => a + "a")

  val result2 = listMonad.compose[Int, Int, Int](a => List(a + 2), b => List(b + 3))

  println(result)
  println(result2(20))
}

trait Monad[F[_]] extends Functor[F] {
  def unit[A](a: => A): F[A]

  def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B]

  def map[A, B](ma: F[A])(f: A => B): F[B] = flatMap(ma)(a => unit(f(a)))

  def map2[A, B, C](ma: F[A], mb: F[B])(f: (A, B) => C): F[C] =
    flatMap(ma)(a => map(mb)(b => f(a, b)))

  def compose[A, B, C](f: A => F[B], g: B => F[C]): A => F[C] = a => flatMap(f(a))(g)
}
