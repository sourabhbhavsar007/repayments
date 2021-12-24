package com.payments.repayments.calculation;

import org.springframework.stereotype.Component;

import com.payments.repayments.utils.RepaymentUtils;

/**
 * This class is responsible for calculating Interest based on borrowed amount.
 *
 */
@Component
public class InterestCalculation {
   /**
    * This method calculates interest amount in Euro based on current loan amount and yearly nominal interest rate its calculates monthly interest amount.
    * @param loanAmount
    * @param nominalInterestRate
    * @return
    */

   public double calculateInerestPerPeriod(double loanAmount,double nominalInterestRate ) {
      double interestAmount =  (loanAmount * RepaymentUtils.NUMBER_OF_DAYS_IN_MONTH*nominalInterestRate) / (RepaymentUtils.NUMBER_OF_DAYS_IN_YEAR*100);
      return interestAmount;

   }

}