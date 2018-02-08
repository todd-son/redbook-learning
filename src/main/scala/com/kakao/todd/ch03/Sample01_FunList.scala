package com.kakao.todd.ch03

object FunList {
  def main(args: Array[String]): Unit = {
    val list1 = List("a", "b")
    println(list1)
    println(list1.size)
    println(list1.tail)
  }
}

sealed trait List[+A] {
  def size =  {
    def size(l: List[A]): Int = l match {
      case Nil => 0
      case Cons(h, t) => 1 + size(t)
    }

    size(this)
  }

  def tail() = this match {
    case Nil => Nil
    case Cons(h, t) => t
  }

}

case object Nil extends List[Nothing]

case class Cons[+A](h: A, t: List[A]) extends List[A]

object List {
  def apply[A](as: A*): List[A] =
    if (as.isEmpty)
      Nil
    else
      Cons(as.head, apply(as.tail: _*))
}
