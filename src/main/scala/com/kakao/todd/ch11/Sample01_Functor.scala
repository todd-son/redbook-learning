package com.kakao.todd.ch11

object Sample01_Functor extends App {

  val listFunctor = new Functor[List] {
    override def map[A, B](fa: List[A])(f: A => B): List[B] = fa.map(f(_))
  }

  val result = listFunctor.map(List(1, 2, 3))(x => "result: " + (x + 10))
  val result2 = listFunctor.distribute(List((1, "A"), (2, "B"), (3, "C")))
  val result3 = listFunctor.codistribute(
    Left(List("A", "B", "C"))
  )

  println(result)
  println(result2)
  println(result3)
}

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]

  def distribute[A, B](fab: F[(A, B)]): (F[A], F[B]) = (map(fab)(_._1), map(fab)(_._2))

  def codistribute[A, B](e: Either[F[A], F[B]]): F[Either[A, B]] = e match {
    case Left(fa) => map(fa)(Left(_))
    case Right(fb) => map(fb)(Right(_))
  }
}


