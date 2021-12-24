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

public class AnnuityCalculationTest {

   @Autowired
   AnnuityCalculation annuityCalculation;

   @Test
   public void caluclateAnnuity() {
      double loanAmount = 5000d;
      int numberOfPeriods = 24;
      double interestPerPeriod = (5.0/12);
      double annuityAmount = annuityCalculation.caluclateAnnuity(loanAmount, interestPerPeriod, numberOfPeriods);
      annuityAmount = new BigDecimal(annuityAmount).setScale(2,RoundingMode.HALF_UP).doubleValue();
      assertTrue(annuityAmount==219.36);
   }

}
