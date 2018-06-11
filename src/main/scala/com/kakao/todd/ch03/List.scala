package com.kakao.todd.ch03

sealed trait List[+A] {
  def tail = this match {
    case Nil => Nil
    case Cons(h, t) => t
  }
}

case object Nil extends List[Nothing]

case class Cons[+A](head: A, override val tail: List[A]) extends List[A]

object List {
  def apply[A](as: A*): List[A] =
    if (as.isEmpty)
      Nil
    else
      Cons(as.head, apply(as.tail: _*))
}
