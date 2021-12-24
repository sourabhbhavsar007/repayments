package com.payments.repayments.advice;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.payments.repayments.controller.RepaymentController;
import com.payments.repayments.utils.RepaymentUtils;

import exception.ApiException;
import exception.ExceptionConstants;

/**
 * This class is responsible for handling all un-handled runtime exceptions and log the exception.
 */

@ControllerAdvice
public class ExceptionHandlingAdvice {

   private final static Logger log = Logger.getLogger(ExceptionHandlingAdvice.class.getName());

   @ExceptionHandler(RuntimeException.class)
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ResponseBody
   public ApiException genericException(RuntimeException re) {
      log.severe(re.getMessage());
      ApiException apiException = new ApiException(ExceptionConstants.SERVER_ERROR + " : " + re.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      return apiException;

   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ResponseBody
   public ApiException inputValidationFailed(MethodArgumentNotValidException ex) {
      log.severe(ex.getMessage());
      ApiException apiException = new ApiException(ExceptionConstants.INVALID_INPUTS + " : " + ex.getMessage(), HttpStatus.BAD_REQUEST);
      return apiException;
   }

}
