package com.payments.repayments.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.payments.repayments.calculation.AnnuityCalculation;
import com.payments.repayments.calculation.InterestCalculation;
import com.payments.repayments.controller.RepaymentController;
import com.payments.repayments.model.LoanRequest;
import com.payments.repayments.model.RepaymentPlan;
import com.payments.repayments.utils.RepaymentUtils;
import com.payments.repayments.utils.RoundOffUtils;

import exception.ApiException;
import exception.ExceptionConstants;

@Service
public class RepaymentService {

   private final static Logger log = Logger.getLogger(RepaymentService.class.getName());

   @Autowired
   AnnuityCalculation annuityCalculation;

   @Autowired
   InterestCalculation interestCalculation;

   /**
    * This method calculates payment schedule , annuity , outstanding amount , principal and interest amount for an annuity loan.
    * @param loanRequest
    * @return List<RepaymentPlan>
    */

   public List<RepaymentPlan> getReplaymentSchedule(LoanRequest loanRequest){

      log.info("Validating given inputs...");
      if(!validateInputs(loanRequest))
         throw new ApiException(ExceptionConstants.INVALID_INPUTS, HttpStatus.BAD_REQUEST);

      log.info("Inputs validated, starting to calculate repayment schedule...");

      int loanDuration = loanRequest.getDuration();
      List<RepaymentPlan> repaymentPlans = new ArrayList<>();

      double initialOutStandingAmount = loanRequest.getLoanAmount();

      double annuity = annuityCalculation.caluclateAnnuity(initialOutStandingAmount, loanRequest.getNominalRate()/
                  RepaymentUtils.NUMBER_OF_MONTHS_IN_YEAR, loanRequest.getDuration());
      
      annuity = RoundOffUtils.roundToNDecimalPlace(annuity, RepaymentUtils.ROUND_OFF_PLACE);


      for(int i=1; i <= loanDuration; i++) {
         RepaymentPlan RepaymentPlan = new RepaymentPlan();

         double interestAmount = interestCalculation.calculateInerestPerPeriod(initialOutStandingAmount, loanRequest.getNominalRate());


         interestAmount = RoundOffUtils.roundToNDecimalPlace(interestAmount, RepaymentUtils.ROUND_OFF_PLACE);

         if( interestAmount > initialOutStandingAmount ) {
            interestAmount = initialOutStandingAmount;

         }

         double principalAmount = annuity - interestAmount;
         principalAmount = RoundOffUtils.roundToNDecimalPlace(principalAmount, RepaymentUtils.ROUND_OFF_PLACE);

         // During last month principal amount may need to be corrected , since one cannot repay more principal than actual principal amount.

         if(principalAmount > initialOutStandingAmount) {
            principalAmount = initialOutStandingAmount;
            annuity = principalAmount + interestAmount;
         }

         RepaymentPlan.setPrincipal(principalAmount);
         RepaymentPlan.setInitialOutstandingPrincipal(initialOutStandingAmount);

         double remainingAmount = initialOutStandingAmount - principalAmount;
         remainingAmount= RoundOffUtils.roundToNDecimalPlace(remainingAmount, RepaymentUtils.ROUND_OFF_PLACE);

         RepaymentPlan.setInterest(interestAmount);
         RepaymentPlan.setBorrowerPaymentAmount(annuity);
         RepaymentPlan.setRemainingOutstandingPrincipal(remainingAmount);

         Calendar cal = Calendar.getInstance();
         cal.setTime(loanRequest.getStartDate());
         cal.add(Calendar.MONTH, i-1);

         RepaymentPlan.setDate(cal.getTime());

         //add to repayment schedule list
         repaymentPlans.add(RepaymentPlan);

         initialOutStandingAmount =remainingAmount;

      }
      return repaymentPlans;
   }

   private boolean validateInputs(final LoanRequest loanRequest)
   {
       if(loanRequest.getLoanAmount() <= 0 || loanRequest.getDuration() <= 0 || loanRequest.getNominalRate() <= 0 || loanRequest.getStartDate().after(new Date()))
         return false;

      return true;
   }

}