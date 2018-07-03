package com.kakao.todd.ch03.exercise

import scala.annotation.tailrec

sealed trait List[+A] {
  def head: Option[A] = this match {
    case Nil => None
    case Cons(h, t) => Some(h)
  }

  def tail: List[A] = this match {
    case Nil => Nil
    case Cons(h, t) => t
  }
}

case object Nil extends List[Nothing]

case class Cons[+A](h: A, override val tail: List[A]) extends List[A]

object List {
  def apply[A](as: A*): List[A] =
    if (as.isEmpty)
      Nil
    else
      Cons(as.head, apply(as.tail: _*))

  def setHead[A](l: List[A], newHead: A) = l match {
    case Nil => Nil
    case Cons(h, t) => Cons(newHead, t)
  }

  def drop[A](l: List[A], n: Int): List[A] = n match {
    case 0 => l
    case x if x > 0 => drop(l.tail, x - 1)
  }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Cons(h, t) if f(h) => dropWhile(t, f)
    case _ => l
  }

  def init[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(h, Nil) => Nil
    case Cons(h, t) => Cons(h, init(t))
  }

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case Cons(h, t) => f(h, foldRight(t, z)(f))
  }

  def foldRightViaFoldLeft_1[A, B](l: List[A], z: B)(f: (A, B) => B): B =
    foldLeft(l, (b: B) => b)((g, a) => b => g(f(a, b)))(z)


  def length[A](as: List[A]): Int = foldRight(as, 0)((a, b) => 1 + b)

  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case Cons(h, t) => foldLeft(t, f(z, h))(f)
  }

  def sum(as: List[Int]): Int = foldLeft(as, 0)((b, a) => b + a)

  def product(as: List[Int]): Int = foldLeft(as, 1)((b, a) => b * a)

  def reverse[A](l: List[A]): List[A] = foldLeft(l, Nil: List[A])((a, b) => Cons(b, a))

  def append[A](l: List[A], r: List[A]): List[A] = foldRight(l, r)((a, b) => Cons(a, b))

  def concat[A](ls: List[List[A]]): List[A] =
    foldRight(ls, Nil: List[A])(append(_, _))

  def plusOne(l: List[Int]): List[Int] = foldRight(l, Nil: List[Int])((a, b) => Cons(a + 1, b))

  def toString(l: List[Double]): List[String] = foldRight(l, Nil: List[String])((a, b) => Cons(a.toString, b))

  def map[A, B](as: List[A])(f: A => B): List[B] = foldRight(as, Nil: List[B])((a, b) => Cons(f(a), b))

  def filter[A](as: List[A])(f: A => Boolean): List[A] = foldRight(as, Nil: List[A])((a, b) => if (f(a)) Cons(a, b) else b)

  def flatMap[A, B](as: List[A])(f: A => List[B]): List[B] =
    foldRight(map[A, List[B]](as)(a => f(a)), Nil: List[B])((a, b) => append(a, b))

  def filter2[A](as: List[A])(f: A => Boolean): List[A] = flatMap(as)(a => if (f(a)) List(a) else Nil)

  def zipwith[A](as1: List[A], as2: List[A])(f: (A, A) => A): List[A] = (as1, as2) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(f(h1, h2), zipwith(t1, t2)(f))
  }

  @tailrec
  def hasSequence[A](sup: List[A], sub: List[A]): Boolean = (sup, sub) match {
    case (Nil, _) => false
    case (_, Nil) => true
    case (Cons(h1, t1), (Cons(h2, t2))) =>
      if (h1 == h2)
        hasSequence(t1, t2)
      else
        hasSequence(t1, sub)
  }
}

object ListTest extends App {
  println(List.drop(List(1, 2, 3), 2))
  println(List.dropWhile[Int](List(3, 2, 1), x => x >= 2))
  println(List(1, 2, 3, 4))
  println(List.foldRight(List(1, 2, 3), "B")((a, b) => b + a))
  println(List.foldRight(List(1, 2, 3), Nil: List[Int])(Cons(_, _)))
  println(List.length(List(1, 2, 3, 4)))
  println(List.foldLeft(List(1, 2, 3, 4), "B")((b, a) => b + a))
  println(List.sum(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
  println(List.product(List(1, 2, 3, 4)))
  println(List.reverse(List(1, 2, 3)))

  val f = (a: Int, b: String) => a + b

  val g = (g: (String, Int) => String, a: Int) => (b: String) => g(f(a, b), 0)

  println(f(1, "B"))
  println(g((a, b) => a + b, 1)("B"))

  println(List.append(List(1, 2, 3), List(4, 5)))
  println(List.concat(List(List(1, 2, 3), List(4, 5))))
  println(List.plusOne(List(1, 2, 3)))
  println(List.toString(List(1, 2, 3)))
  println(List.map(List(1, 2, 3))(a => "A" + a.toString))
  println(List.filter(List(3, 1, 5, 7, 2, 8))(x => x > 3))
  println(List.flatMap(List("a", "b", "c"))(a => List(a, "x")))
  println(List.filter2(List(3, 1, 5, 7, 2, 8))(x => x > 3))
  println(List.zipwith(List(1, 2, 3), List(4, 5, 6))((a1, a2) => a1 + a2))
  println(List.hasSequence(List(1, 2, 3, 4, 5, 6), List(3, 4, 5)))
  println(List.hasSequence(List(1, 2, 3, 4, 5, 6), List(3, 4, 7)))
}
