package com.payments.repayments.calculation;

import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class InterestCalculationTest {

   @Autowired
   InterestCalculation interestCalculation;

   @Test
   public void testcalculateInerestPerPeriod() {
      double loanAmount = 5000;
      double nominalInterestRate = 5;
      loanAmount = interestCalculation.calculateInerestPerPeriod(loanAmount, nominalInterestRate);
      loanAmount = new BigDecimal(loanAmount).setScale(2,RoundingMode.HALF_UP).doubleValue();
      assertTrue(loanAmount==20.83);
   }
}
