package com.payments.repayments.service;

import static org.junit.Assert.assertTrue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.payments.repayments.model.LoanRequest;
import com.payments.repayments.model.RepaymentPlan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepaymentServiceTest {


   @Autowired
   RepaymentService repaymentService ;

   @Test
   public void testRepaymentService() throws ParseException {

      LoanRequest loanRequest = new LoanRequest();
      loanRequest.setDuration(24);
      loanRequest.setLoanAmount(5000d);
      loanRequest.setNominalRate(5.0);
      SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy'T'00:00:00'Z'");
      String dateString = "05-01-2019T00:00:00Z";
      Date startDate = format.parse(dateString);

      loanRequest.setStartDate(startDate);
      List<RepaymentPlan> repaymentPlanList =	repaymentService.getReplaymentSchedule(loanRequest);
      assertTrue(repaymentPlanList.size() == 24);
      RepaymentPlan repaymentResponse = repaymentPlanList.get(0);
      assertTrue(repaymentResponse.getInitialOutstandingPrincipal() == 5000);
      assertTrue(repaymentResponse.getPrincipal() == 198.53);
      assertTrue(repaymentResponse.getRemainingOutstandingPrincipal() == 4801.47);

      RepaymentPlan paymentResponseNextMonth = repaymentPlanList.get(2);
      Date nextDate = format.parse("05-03-2019T00:00:00Z");
      assertTrue(paymentResponseNextMonth.getDate().compareTo(nextDate) == 0);
      RepaymentPlan paymentResponseLast = repaymentPlanList.get(23);
      assertTrue(paymentResponseLast.getRemainingOutstandingPrincipal() == 0);
   }

}
