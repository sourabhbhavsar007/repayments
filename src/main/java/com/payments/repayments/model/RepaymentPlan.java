package com.payments.repayments.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * This Model class represents borrower's repayment plan.
 */
@Data
public class RepaymentPlan {

   @ApiModelProperty(notes = "Amount borrower need to day each month",required=true,dataType="Double")
   Double borrowerPaymentAmount;

   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
   @ApiModelProperty(notes = "Payment date",dataType="date" , example="2018-02-01T00:00:01Z")
   Date date;

   @ApiModelProperty(notes = "Initial Outstanding Principal Amount",dataType="Double")
   Double initialOutstandingPrincipal;

   @ApiModelProperty(notes = "Interest Amount payable",required=true,dataType="Double")
   Double interest;
   @ApiModelProperty(notes = "Principal Amount Payable",required=true,dataType="Double")
   Double principal;

   @ApiModelProperty(notes = "Remaining Principal Amount",required=true,dataType="Double")
   Double remainingOutstandingPrincipal;
}
