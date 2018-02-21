package com.kakao.todd.ch06

import java.util.Date

import com.kakao.todd.ch06.model.Amount

import scala.util.{Failure, Success, Try}

case class Account3(no: String, name: String, dateOfOpening: Date = new Date(), balance: Balance = Balance())

object AccountService {
  def debit(a: Account3, amount: Amount): Try[Account3] = {
    if (a.balance.amount < amount)
      Failure(new Exception("Insufficient balance in account"))
    else
      Success(a.copy(balance = Balance(a.balance.amount + amount)))
  }

  def credit(a: Account3, amount: Amount): Try[Account3] =
    Success(a.copy(balance = Balance(a.balance.amount + amount)))
}

object Account3Test extends App {
  val a = Account3("a1", "John")
  val b = AccountService.credit(a, 100).get

  println(b.balance)

  AccountService.debit(b, 10).foreach(a => auditLog(s"Account ${a.no} debit ${a.balance}"))

  def auditLog(log: String) = println(log)
}

