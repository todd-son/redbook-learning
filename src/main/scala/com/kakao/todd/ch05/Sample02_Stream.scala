package com.kakao.todd.ch05

import com.kakao.todd.ch05.Stream.{cons, empty}

object FunStream {
  def main(args: Array[String]): Unit = {
    def expensive = {
      println("expensive")
      "A"
    }

    val stream1 = Cons[String](() => expensive, () => Empty)

    println(stream1.head)
    println(stream1.head)
    println("==========")

    val stream2 = Stream(expensive, Empty)

    println(stream2.head)
    println(stream2.head)
    println("==========")

    def ones: Stream[Int] = Stream.cons(1, ones)

    println(ones.take(10).toList)
  }
}

sealed trait Stream[+A] {
  def head: Option[A] = this match {
    case Empty => None
    case Cons(h, _) => Some(h())
  }

  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
    case Empty => z
    case Cons(h, t) => f(h(), t().foldRight(z)(f))
  }

  def map[B](f: (A => B)): Stream[B] = foldRight(empty[B])((h, t) => cons(f(h), t))

  def take(n: Int): Stream[A] = this match {
    case Cons(h, _) if n == 1 => cons(h(), Empty)
    case Cons(h, t) if n > 1 => cons(h(), t().take(n-1))
    case _ => Empty
  }

  def toList: List[A] = {
    def go(s: Stream[A]): List[A] = s match {
      case Empty => Nil
      case Cons(h, t) => h() :: go(t())
    }

    go(this)
  }
}

case object Empty extends Stream[Nothing]

case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  // smart constructor
  // naming convention of smart constructor
  def cons[A](h: => A, t: => Stream[A]): Stream[A] = {
    lazy val head = h
    lazy val tail = t
    Cons(() => head, () => tail)
  }

  def apply[A](as: A*): Stream[A] = cons(as.head, apply(as.tail: _*))

  def empty[A]: Stream[A] = Empty
}
