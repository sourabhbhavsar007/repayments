package com.payments.repayments.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class provides statis utilities to rounding off amounts.
 */
public class RoundOffUtils {
   /**
    * This method rounds data to given number of  decimal place.
    * @param value
    * @param scale
    * @return
    */
   public static double roundToNDecimalPlace(double value,int scale) {
      return new BigDecimal(value).setScale(scale, RoundingMode.HALF_UP).doubleValue();
   }
}
