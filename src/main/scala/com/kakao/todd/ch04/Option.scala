package com.kakao.todd.ch04

sealed trait Option[+A] {
  def map[B](f: A => B): Option[B] = this match {
    case None => None
    case Some(x) => Some(f(x))
  }

  def flatMap[B](f: A => Option[B]): Option[B] = this match {
    case None => None
    case Some(x) => f(x)
  }

  // B >: A B must be super type of A
  // => B  laziness
  def getOrElse[B >: A](default: => B): B = this match {
    case None => default
    case Some(x) => x
  }

  def orElse[B >: A](ob: => Option[B]): Option[B] = this match {
    case None => ob
    case _ => this
  }

  def filter(f: A => Boolean): Option[A] = this match {
    case Some(x) if (f(x)) => this
    case _ => None
  }
}

case object None extends Option[Nothing]

case class Some[+A](get: A) extends Option[A]

object Option {
  def lift[A, B](f: A => B): Option[A] => Option[B] = _.map(f)

  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = (a, b) match {
    case (Some(a), Some(b)) => Some(f(a, b))
    case (None, _) => None
    case (_, None) => None
  }

  def sequence[A](as: List[Option[A]]): Option[List[A]] = as match {
    case Nil => Some(Nil)
    case h :: t => h.flatMap(hh => sequence(t).map(tt => hh :: tt))
  }

  def traverse[A, B](as: List[A])(f: A => Option[B]): Option[List[B]] = as match {
    case Nil => Some(Nil)
    case h :: t => f(h).flatMap(hh => traverse(t)(f).map(tt => hh :: tt))
  }
}

object OptionTest extends App {
  val f1 = Option.lift((x: Int) => x + 5)
  println(f1(Some(10)))

  println(Option.map2(Some(1), Some(2))((a, b) => a + b))
  println(Option.sequence(List(Some(1), Some(2))))
  println(Option.sequence(List(Some(1), None, Some(3))))
}

