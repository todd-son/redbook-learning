package com.kakao.todd.ch06

import java.util.Date

import com.kakao.todd.ch06.model.Amount

package object model {
  type Amount = BigDecimal
}

case class Balance(amount: Amount = 0)

class Account(val no: String, val name: String, val dateOfOpening: Date = new Date()) {
  var balance: Balance = Balance()

  def debit(a: Amount) = {
    if (balance.amount < a)
      throw new Exception("Insufficient balance in account")

    balance = Balance(balance.amount - a)
  }

  def credit(a: Amount) = balance = Balance(balance.amount + a)
}

object AccountTest extends App {
  val a = new Account("a1", "John")

  a.credit(100)
  a.debit(20)

  println(a.balance)
}


