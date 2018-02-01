package com.kakao.todd.ch03

object FunList {

  def main(args: Array[String]): Unit = {
    val list1 = List("a", "b")
    println(list1)
  }

}

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  def apply[A](as: A*): List[A] =
    if (as.isEmpty)
      Nil
    else
      Cons(as.head, apply(as.tail: _*))
}
