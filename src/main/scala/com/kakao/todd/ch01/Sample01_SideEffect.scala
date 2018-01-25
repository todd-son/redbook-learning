package com.kakao.todd.ch01

trait CreditCard {
  def charge(price: Int)
}

class Coffee {
  val price = 1200
}

case class Charge(cc: CreditCard, price: Int)


// side effect program
// credit card is hard to test
class Cafe_SideEffect {
  def buyCoffee(cc: CreditCard): Coffee = {
    val coffee = new Coffee()
    cc.charge(coffee.price) // side effect
    coffee
  }
}

// separation of concern
// coffee, charge
class Cafe {
  def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
    val coffee = new Coffee()
    (coffee, Charge(cc, coffee.price))
  }
}



