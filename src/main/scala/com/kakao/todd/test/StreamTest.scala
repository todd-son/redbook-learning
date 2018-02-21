package com.kakao.todd.test

object StreamTest extends App {

  val list = List("A", "B", "C", "D", "E", "F", "G").toStream

  val rs = list
    .map(i => {
      println("map " + i)
      i.toLowerCase
    })
    .filter(i => {
      println("filter " + i)
      i != "b"
    })
    .take(2)

  println(rs.toList)

  val f = () => {
    println("x")
    "hello"
  }

}


