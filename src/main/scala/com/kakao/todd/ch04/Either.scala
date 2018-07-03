package com.kakao.todd.ch04

sealed trait Either[+E, +A] {
  def map[B](f: A => B): Either[E, B] = this match {
    case Right(a) => Right(f(a))
    case Left(e) => Left(e)
  }

  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = this match {
    case Right(a) => f(a)
    case Left(e) => Left(e)
  }

  def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] = this match {
    case Right(a) => Right(a)
    case _ => b
  }

  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = (this, b) match {
    case (Right(a), Right(b)) => Right(f(a,b))
    case (Left(a), Left(b)) => Left(a)
    case (Right(a), Left(b)) => Left(b)
    case (Left(a), Right(b)) => Left(a)
  }

  def map3[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = for {
    a1 <- this
    b1 <- b
  } yield f(a1, b1)
}

case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]

object Either {
  def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] = es match {
    case Nil => Right(Nil)
    case h :: t => h.flatMap(hh => sequence(t).map(tt => hh :: tt))
  }

  def traverse[E, A, B](as: List[A])(f: A => Either[E,B]): Either[E, List[B]] = as match {
    case Nil => Right(Nil)
    case h :: t => f(h).flatMap(hh => traverse(t)(f).map(tt => hh :: tt))
  }
}

object EitherTest extends App {
  println(Right(3).map(x => x + 20))
  println(Right(10).flatMap(x => {
    try {
      Right(x / 2)
    } catch {
      case e => Left(e)
    }
  }))

  println(Left(3).orElse(Right(20)))

  println(Right(5).map2(Right(10))((a,b) => a + b))
  println(Right(5).map3(Right(10))((a,b) => a + b))
//  println(Left(5).map3(Right(10))((a,b) => a + b))

  println(Either.sequence(List(Right(1), Right(3), Right(5))))
  println(Either.sequence(List(Right(1), Left(3), Right(5))))
  println(Either.traverse(List(1, 3, 5))(x => Right(x + 2)))
}

