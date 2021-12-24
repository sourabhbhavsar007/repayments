package com.payments.repayments.model;

import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * This is a Loan class which corresponds to the request we get to generate repayment plan.
 */
@Data
public class LoanRequest
{
   @ApiModelProperty(notes = "Annuity loan Amount", required = true, dataType = "Double")
   @NotNull(message = "Loan amount cannot be null.")
   Double loanAmount;

   @ApiModelProperty(notes = "Nominal interest rate per year", required = true, dataType = "Double")
   @NotNull(message = "Nominal interest rate cannot be null.")
   Double nominalRate;

   @ApiModelProperty(notes = "Duration of loan in months", required = true, dataType = "Integer")
   @Min(1)
   int duration;

   @ApiModelProperty(notes = "Payment start date ", required = true, dataType = "Date", example = "2018-01-01T00:00:01Z")
   @NotNull(message = "Start date cannot be null and must be in proper date format.")
   Date startDate;

}
