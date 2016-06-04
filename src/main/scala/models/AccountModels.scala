package models

import java.util.{Date, Calendar}

import scala.util.{Try, Success, Failure}

/**
 * Created by devesh on 10/15/15.
 */
object AccountModels {
  type Amount = BigDecimal
  def today =  Calendar.getInstance.getTime
  case class Balance(amount: Amount = 0)

  sealed trait Account{
    def no:String
    def name: String
    def dateOfOpen: Option[Date]
    def dateOfClose: Option[Date]
    def balance : Balance
  }

  final case class CheckingAccount (
        no:String,
        name: String,
        dateOfOpen: Option[Date],
        dateOfClose: Option[Date]= None,
        balance:Balance = Balance(0)
        ) extends Account

  final case class SavingsAccount (
       no:String,
       name: String,
       dateOfOpen: Option[Date],
       dateOfClose: Option[Date]= None,
       balance:Balance = Balance(0),
       rateOfInterest: Amount
       ) extends Account

  object Account {
    def checkingAccount(no: String, name: String, openDate:Option[Date],
                        closeDate:Option[Date], balance: Balance):Try[Account]={
      val od = openDate.getOrElse(today)
      val cd = closeDate.getOrElse(today)

      if(cd before od) {
        Failure(new Exception(s"Close date [$cd] cannnot be before open date [$od]"))
      }else{
        Success(CheckingAccount(no, name, openDate, closeDate, balance))
      }
    }

    def savingsAccount(no: String, name: String, openDate:Option[Date], closeDate:Option[Date],
                       balance: Balance, rateOfInterest: Amount):Try[Account]={
      val od = openDate.getOrElse(today)
      val cd = closeDate.getOrElse(today)

      if(cd before od) {
        Failure(new Exception(s"Close date [$cd] cannnot be before open date [$od]"))
      }else if( rateOfInterest <= BigDecimal(0)){
        Failure(new Exception(s"Rate of Interest [$rateOfInterest] cannot be less than zero"))
      }
      else{
        Success(SavingsAccount(no, name, openDate, closeDate, balance, rateOfInterest))
      }
    }
  }
}
