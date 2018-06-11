package com.kakao.todd.ch02.exercise

object Exer02_IsSorted {
  def main(args: Array[String]) {
    println(isSorted(Array(3,1,2),(a: Int, b: Int) => a < b))
    println(isSorted(Array(1,2,3),(a: Int, b: Int) => a < b))

  }

  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    (0 to as.length - 2).forall(i =>
      if (i < 1)
        true
      else
        ordered(as(i - 1), as(i)))
  }
}
