package com.kakao.todd.ch06

import java.util.Date

import com.kakao.todd.ch06.model.Amount

import scala.util.{Failure, Success, Try}

class Account2(val no: String, val name: String, val dateOfOpening: Date = new Date(), val balance: Balance = Balance()) {
  def debit(a: Amount): Try[Account2] = {
    if (balance.amount < a)
      Failure(new Exception("Insufficient balance in account"))

    Success(new Account2(no, name, dateOfOpening, Balance(balance.amount - a)))
  }

  def credit(a: Amount) = new Account2(no, name, dateOfOpening, Balance(balance.amount + a))
}

object Account2Test {
  val a = new Account2("a1", "John")

  val b = a.credit(100)
  val c = b.debit(20)

  println(c.get.balance)
}

