package com.payments.repayments.controller;

import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;
import javax.validation.Valid;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;
import com.payments.repayments.model.LoanRequest;
import com.payments.repayments.model.RepaymentPlan;
import com.payments.repayments.service.RepaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This is a controller class which accepts request body and routes incoming requests.
 */
@Api(consumes = "application/json", produces = "application/json")
@RestController
public class RepaymentController
{

   private final static Logger log = Logger.getLogger(RepaymentController.class.getName());

   @Autowired
   RepaymentService repaymentService;

   @ApiOperation(value = "Fetches list of repayment schedule for annuity loan", responseContainer = "List", response = RepaymentPlan.class)
   @ApiResponses(value = {
         @ApiResponse(code = 200, message = "Ok"),
         @ApiResponse(code = 500, message = "Internal Server Error"),
         @ApiResponse(code = 400, message = "Bad Request"),
         @ApiResponse(code = 404, message = "Not Found")
   })

   @PostMapping(value = "/repaymentschedule", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
   public List<RepaymentPlan> getPaymentInformation(@Valid @RequestBody LoanRequest loanRequest)
   {
      log.info("Request for repayment schedule calculation received...");

      return repaymentService.getReplaymentSchedule(loanRequest);
   }

}
