package com.payments.repayments.calculation;


import org.springframework.stereotype.Component;

/**
 * This class is responsible for calculating Annuity amount for annuity loans.
 *
 */
@Component
public class AnnuityCalculation {
   /**
    * This method calculates Annuity based on present value , interest period and number of periods. and approximates upto 2 decimal places.
    * @param presentValue
    * @param interestPerPeriod
    * @param numberOfPeriods
    * @return
    */
   public Double caluclateAnnuity(double presentValue,double interestPerPeriod,int numberOfPeriods) {
      double annuity = 0d;
      interestPerPeriod = (interestPerPeriod / 100);
      annuity =  ( (interestPerPeriod)*presentValue) / (1 - Math.pow((1+interestPerPeriod), -numberOfPeriods));
      return annuity;
   }

}
