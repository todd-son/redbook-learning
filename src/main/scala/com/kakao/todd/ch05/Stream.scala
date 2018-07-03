package com.kakao.todd.ch05

import com.kakao.todd.ch05.Stream._

sealed trait Stream[+A] {
  def toList(): List[A] = this match {
    case Empty => Nil
    case Cons(h, t) => h() :: t().toList()
  }

  def take(n: Int): Stream[A] =
    this match {
      case Cons(h, t) if (n > 0) => cons(h(), t().take(n - 1))
      case Cons(h, _) if n == 1 => cons(h(), empty)
      case _ => Empty
    }


  def drop(n: Int): Stream[A] =
    this match {
      case Cons(h, t) if (n > 0) => t().drop(n - 1)
      case _ => this
    }

  def takeWhile(p: A => Boolean): Stream[A] = this match {
    case Cons(h, t) if p(h()) => cons(h(), t().takeWhile(p))
    case _ => empty
  }

  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
    case Cons(h, t) => f(h(), t().foldRight(z)(f))
    case Empty => z
  }

  def forall(p: A => Boolean): Boolean =
    this.foldRight(true)((a, b) => p(a) && b)

  def takeWhile2(p: A => Boolean): Stream[A] =
    this.foldRight(empty[A])(
      (a, b) =>
        if (p(a))
          cons(a, b)
        else
          empty
    )

  def headOption: Option[A] = this match {
    case Cons(h, _) => Some(h())
    case Empty => None
  }

  def headOption2: Option[A] = this.foldRight[Option[A]](None)((a, b) => Some(a))

  def map[B](f: A => B): Stream[B] = this.foldRight(empty[B])((a, b) => cons(f(a), b))

  def filter(f: A => Boolean): Stream[A] = this.foldRight(empty[A])((a, b) => if (f(a)) cons(a, b) else b)

  def append[B >: A](s: => Stream[B]): Stream[B] = this.foldRight(s)((a, b) => cons(a, b))

  def flatMap[B](f: A => Stream[B]): Stream[B] = this.foldRight(empty[B])((a, b) => f(a).append(b))

  def map2[B](f: A => B): Stream[B] = unfold(this) {
    case Cons(h, t) => Some(f(h()), t())
    case _ => None
  }

  def take2(n: Int): Stream[A] = unfold((this, n)) {
    case (Cons(h, t), 1) => Some((h(), (empty, 0)))
    case (Cons(h, t), n) if n > 1 => Some((h(), (t(), n - 1)))
    case _ => None
  }

  def takeWhile3(f: A => Boolean): Stream[A] = unfold(this) {
    case Cons(h, t) if f(h()) => Some(h(), t().takeWhile2(f))
    case _ => None
  }

  def zipWith[B, C](s2: Stream[B])(f: (A, B) => C): Stream[C] = unfold((this, s2)) {
    case (Cons(h1, t1), Cons(h2, t2)) => Some(f(h1(), h2()), (t1(), t2()))
    case _ => None
  }

  def zipAll[B](s2: Stream[B]): Stream[(Option[A], Option[B])] = unfold((this, s2)) {
    case (Cons(h1, t1), Cons(h2, t2)) => Some((Some(h1()), Some(h2())), (t1(), t2()))
    case (Empty, Cons(h2, t2)) => Some((None, Some(h2())), (Empty, t2()))
    case (Cons(h1, t1), Empty) => Some((Some(h1()), None), (t1(), Empty))
    case _ => None
  }

  def startWith[A](s: Stream[A]): Boolean = (this, s) match {
    case (Cons(h1, t1), Cons(h2, t2)) => h1() == h2() && t1().startWith(t2())
    case (Cons(h1, t1), Empty) => true
    case (Empty, Cons(h2, t2)) => false
    case _ => false
  }

  def startsWith2[A](s: Stream[A]): Boolean =
    zipAll(s).takeWhile(!_._2.isEmpty).forall {
      case (h,h2) => h == h2
    }

  def tails: Stream[Stream[A]] = unfold(this) {
    case Cons(h, t) => Some(cons(h(), t()), t())
    case _ => None
  }
}

case object Empty extends Stream[Nothing]

case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def constants[A](a: A): Stream[A] = cons(a, constants(a))

  def from(n: Int): Stream[Int] = cons(n, from(n + 1))

  def fibs: Stream[Int] = {
    def calc(a: Int, b: Int): Stream[Int] = cons(a, calc(b, a + b))

    calc(0, 1)
  }

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] =
    f(z) match {
      case Some((a, s)) => cons(a, unfold(s)(f))
      case None => empty
    }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty)
      empty
    else
      cons(as.head, apply(as.tail: _*))

  def fibs2: Stream[Int] = unfold((0, 1)) { case (f0, f1) => Some((f0, (f1, f0 + f1))) }

  def from2(n: Int): Stream[Int] = unfold(0)(s => Some(s, s + 1))

  def constant2(n: Int): Stream[Int] = unfold(n)(s => Some(s, s))
}

object StreamTest extends App {
  println(Stream(1, 2, 3, 4, 5).toList)
  println(Stream(1, 3, 5, 7, 9).take(3).toList)
  println(Stream(1, 3, 5, 7, 9).drop(3).toList)
  println(Stream(1, 3, 5, 7, 9).takeWhile(i => i < 7).toList)
  println(Stream(1, 2, 3, 4, 5).foldRight(0)((a, b) => a + b))
  println(Stream(1, 2, 3, 4, 5).forall(_ > 0))
  println(Stream(1, 2, 3, 4, 5).forall(_ < 3))
  println(Stream(1, 3, 5, 7, 9).takeWhile(i => i < 7).toList)
  println(Stream(1, 3, 5, 7, 9, 5).takeWhile(i => i < 7).toList)
  println(Stream(1, 3, 5, 7, 9).headOption)
  println(Stream(1, 3, 5, 7, 9).headOption2)
  println(Empty.headOption2)
  println(Stream(1, 3, 5, 7, 9).map((a: Int) => a + 1).toList())
  println(Stream(1, 3, 5, 7, 9, 5).filter((a: Int) => a > 3).toList())
  println(Stream(1, 2, 3).append(Stream(4, 5)).toList())
  println(Stream(1, 2, 3).flatMap(a => cons(a, cons(a, empty))).toList())
  println(constants(5).take(10).toList())
  println(from(0).take(10).toList())
  println(fibs.take(10).toList())
  println(unfold(0)(s => Some(s, s + 1)).take(5).toList())
  println(fibs2.take(10).toList())
  println(from2(10).take(5).toList())
  println(constant2(10).take(5).toList())
  println(Stream(1, 2, 4).map2(x => x + 2).toList())
  println(Stream(1, 3, 5, 7, 9).take2(3).toList)
  println(Stream(1, 3, 5, 7, 9).takeWhile3(i => i < 7).toList)
  println(Stream(1, 2, 3).zipWith(Stream(1, 2, 3))((a, b) => a + b).toList())
  println(Stream(1, 2, 3).zipAll(Stream(1, 2, 3, 4, 5)).toList())
  println(Stream(1, 2, 3).startWith(Stream(1, 2)))
  println(Stream(1, 2, 3).startWith(Stream(1, 3)))
  println(Stream(1, 2, 3).tails.map(t => t.toList())toList())
}
