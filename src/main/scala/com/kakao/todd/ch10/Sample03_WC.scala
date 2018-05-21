package com.kakao.todd.ch10

class Sample03_WC {

  val wcMonoid = new Monoid[WC] {
    override def op(a1: WC, a2: WC): WC = ???

    override def zero: WC = Stub("")
  }
}

sealed trait WC
case class Stub(chars: String) extends WC
case class Part(lStub: String, words: Int, rSub: String) extends WC
