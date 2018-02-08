package com.kakao.todd.ch03

object TreeTest {
  def main(args: Array[String]) {
    val tree = Branch(Branch(Leaf("A"), Leaf("B")), Leaf("C"))

    println(tree)
    println(tree.size())
  }
}

sealed trait Tree[+A] {
  def size() = {
    def size(t: Tree[A]): Int = t match {
      case Leaf(_) => 1
      case Branch(l, r) => size(l) + size(r)
    }

    size(this)
  }
}

case class Leaf[A](value: A) extends Tree[A]

case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]




