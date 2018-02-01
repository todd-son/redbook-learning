package com.kakao.todd.ch03

object Generics {
  def main(args: Array[String]): Unit = {
    val cCon1 = NonVarContainer(new Child("todd"))
    //    val pCon1: NonVarContainer[Parent] = cCon

    val cCon2 = CoVarContainer(new Child("todd"))
    val pCon2: CoVarContainer[Parent] = cCon2
  }
}

case class NonVarContainer[A](data: A)

case class CoVarContainer[+A](data: A)

class Parent(val name: String)

class Child(override val name: String) extends Parent(name)
