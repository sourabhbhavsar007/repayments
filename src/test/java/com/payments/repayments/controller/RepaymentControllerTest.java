package com.payments.repayments.controller;

import static org.junit.Assert.assertTrue;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import com.payments.repayments.RepaymentsApplication;
import com.payments.repayments.model.LoanRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RepaymentsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepaymentControllerTest {
   
   @LocalServerPort
   private int port;

   @Test
   public void paymentScheduleTest() {
      LoanRequest loanRequest = new LoanRequest();
      loanRequest.setDuration(24);
      loanRequest.setLoanAmount(5000d);
      loanRequest.setNominalRate(5.0);
      loanRequest.setStartDate(new Date());
      RestTemplate restTemplate = new RestTemplate();
      HttpHeaders httpHeader = new HttpHeaders();
      HttpEntity<LoanRequest> entity = new HttpEntity<LoanRequest>(loanRequest, httpHeader);

      ResponseEntity<List> response = restTemplate.exchange(createURLWithPort("/repaymentschedule"),
            HttpMethod.POST, entity, List.class);

      HttpStatus status = response.getStatusCode();
      assertTrue(status.value()==200);
      List bodyList = response.getBody();
      assertTrue(bodyList.size()==24);

   }

   private String createURLWithPort(String uri) {
      return "http://localhost:" + port + uri;
   }

}