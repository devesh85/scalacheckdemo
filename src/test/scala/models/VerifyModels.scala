package models


import java.util.Date

import models.AccountModels.{Account, Balance, Amount, today}
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll


import scala.util.Success


/**
 * Created by devesh on 10/15/15.
 */
object VerifyModels extends Properties("Account"){

   property("Amount type") = forAll{ l: Amount =>
      val balance = new Balance(l)
      balance.amount == l
   }

  property("Create checking account, and make a deposit")= forAll{
    (no: String, name: String, openDate:Option[Date],
      closeDate:Option[Date], l: Amount)=>
      {
      val balance = new Balance(l)
      val acc = Account.checkingAccount(no, name, openDate, closeDate, balance)

      val Success(amount) = for{
        checkingAcc <- acc
      }yield checkingAcc.balance

      val od = openDate.getOrElse(today)
      val cd = closeDate.getOrElse(today)

      //Open date should be before close date
      od before cd

      //Balance amount should be greater than zero
      balance.amount >= 0

      //Amount deposited should be the same as he balance in the account
      amount == balance
    }

  }


}
