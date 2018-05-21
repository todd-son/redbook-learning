package com.kakao.todd.ch07

import java.util.concurrent._

import com.kakao.todd.ch07.Par.Par

object ParSumTest extends App {
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case h :: t => h + sum(t)
  }

  // refining => divide and conquer for parallel
  def sum2(ints: List[Int]): Int =
    ints match {
      case Nil => 0
      case h :: Nil => h
      case s =>
        val (l, r) = s.splitAt(s.size / 2)
        sum2(l) + sum2(r)
    }

  // invent => parallelism structure
  def sum3(ints: List[Int]): Int =
    ints match {
      case Nil => 0
      case h :: Nil => h
      case s =>
        val (l, r) = s.splitAt(s.size / 2)
        val sumL = Par.unit(sum3(l))
        val sumR = Par.unit(sum3(r))
        Par.get(sumL) + Par.get(sumR)
    }

  // refining => + operation in parallel, convert infix operator
  def sum4(ints: List[Int]): Par[Int] =
    ints match {
      case Nil => Par.unit(0)
      case h :: Nil => Par.unit(h)
      case s =>
        val (l, r) = s.splitAt(s.size / 2)
        Par.map2(sum4(l), sum5(r))(_ + _)
    }

  // refining => fork is running in other thread, unit is running in same thread
  def sum5(ints: List[Int]): Par[Int] =
    ints match {
      case Nil => Par.unit(0)
      case h :: Nil => Par.unit(h)
      case s =>
        val (l, r) = s.splitAt(s.size / 2)
        Par.map2(Par.fork(sum5(l)), Par.fork(sum5(r)))(_ + _)
    }

  val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  println(sum(list))
  println(sum2(list))
  println(
    sum5(list)
    (
      Executors.newFixedThreadPool(10)
    ).get()
  )
}

//trait Par[+A] {
//
//}

case class UnitFuture[A](get: A) extends Future[A] {
  override def cancel(mayInterruptIfRunning: Boolean): Boolean = false

  override def isCancelled: Boolean = false

  override def isDone: Boolean = true

  override def get(timeout: Long, unit: TimeUnit): A = get
}

object Par {
  type Par[A] = ExecutorService => Future[A]

  def unit[A](a: => A): Par[A] = _ => UnitFuture(a)

  def get[A](a: Par[A]): A = ???

  def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] = es => {
    val af = a(es)
    val bf = b(es)
    UnitFuture(f(af.get, bf.get))
  }

  def fork[A](a: => Par[A]): Par[A] = es => es.submit(() => a(es).get())

}
