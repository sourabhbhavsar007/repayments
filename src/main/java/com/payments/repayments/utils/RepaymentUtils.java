package com.payments.repayments.utils;

/**
 * This interface defines constants used in repayment schedule calculation.
 */
public interface RepaymentUtils {
   /*
    * This variable defines number of days in a Months. For the simplicity it is decided to take 30 days per month.
    */
   int  NUMBER_OF_DAYS_IN_MONTH = 30;
   /*
    * This variable defines number of days in a year . For the simplicity it is decided to take 360 days per year.
    */
   int  NUMBER_OF_DAYS_IN_YEAR  = 360;
   /*
    * This variable defines number of months in a year.
    */
   int  NUMBER_OF_MONTHS_IN_YEAR = 12;

   /*
    * This variable defines round off places after decimal.
    */
   int  ROUND_OFF_PLACE = 2;
}