package com.kakao.todd.ch03

object Generics {
  def main(args: Array[String]): Unit = {
    val cCon1 = NonVarContainer(new Dog("todd"))
    // coVariant
    // val pCon1: NonVarContainer[Animal] = cCon1

    val cCon2 = CoVarContainer(new Dog("todd"))
    val pCon2: CoVarContainer[Animal] = cCon2

    val cCon3 = new ContraVarContainer[Animal]
    val pCon3: ContraVarContainer[Dog] = cCon3
  }
}

case class NonVarContainer[A](data: A)

case class CoVarContainer[+A](data: A) {
  // compile error => Animal can't be Dog
//    def concat(i: A) = ???

  def get: A = data
}

class ContraVarContainer[-A] {
  def name(i : A) = ???

//  compile error =>
//  def get: A = ???
}

class Animal(val name: String)

class Dog(override val name: String) extends Animal(name)
