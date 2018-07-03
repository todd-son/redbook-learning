package com.kakao.todd.ch03

object TreeTest {
  def main(args: Array[String]) {
    val tree = Branch(Branch(Leaf("A"), Leaf("B")), Leaf("C"))

    println(tree)
    println(tree.size())

    val tree2 = Branch(Branch(Leaf(1), Leaf(6)), Leaf(3))

    println(Tree.maximum(tree2))

    val tree3 = Branch(Branch(Leaf(1), Branch(Leaf(6), Leaf(4))), Leaf(3))

    println(tree3.depth())

    val tree4 = Branch(Branch(Leaf(1), Branch(Leaf(6), Leaf(4))), Leaf(3))

    println(tree4.map((a : Int) => a + 1))
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

  def depth() = {
    def depth(t: Tree[A]): Int = t match {
      case Leaf(_) => 1
      case Branch(l, r) => 1 + depth(l).max(depth(r))
    }

    depth(this)
  }

  def map[B](f: A => B): Tree[B] = {
    def map(t: Tree[A])(f: A => B): Tree[B] = {
      t match {
        case Leaf(v) => Leaf(f(v))
        case Branch(l, r) => Branch(map(l)(f), map(r)(f))
      }
    }

    map(this)(f)
  }
}

case class Leaf[A](value: A) extends Tree[A]

case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {
  def maximum(t: Tree[Int]): Int = t match {
    case Leaf(v) => v
    case Branch(l, r) => maximum(l).max(maximum(r))
  }

  def fold[A, B](t: Tree[A], b: B)(f: A => B)(g: (B, B) => B) : B = t match {
    case Leaf(v) => f(v)
    case Branch(l, r) => g(fold(l, b)(f)(g), fold(r, b)(f)(g))

  }
}




