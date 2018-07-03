package com.kakao.todd.ch04

import scala.util.Try

object Sample03_Either {
  def main(args: Array[String]): Unit = {
    println(safeDiv(2, 1))
    println(safeDiv(2, 0))

    safeDiv(3, 1) match {
      case Left(x) => println(x)
      case Right(x) => println(x)
    }

    println(Try(2 / 0))
  }

  def safeDiv(x: Int, y: Int): Either[Exception, Int] =
    try {
      Right(x / y)
    } catch {
      case e: Exception => Left(e)
    }
}


