package com.kakao.todd.ch08

import org.scalacheck.{Gen, Prop}

object Sample01_ScalaCheck extends App {
  val intList = Gen.listOf(Gen.choose(0, 100))

  val prop =
    Prop.forAll(intList)(ns => ns.reverse.reverse == ns) &&
    Prop.forAll(intList)(ns => ns.headOption == ns.reverse.lastOption)

  val failingProp =
    Prop.forAll(intList)(ns => ns.reverse == ns)

  prop.check
  failingProp.check
}
