package com.paymentiq.operator.platform.exceptions;

import org.springframework.http.HttpStatus;

public class UserDebitLimitApiException extends BaseApiException {

   public UserDebitLimitApiException(String userId) {
       super(userId, HttpStatus.FORBIDDEN.value(), "Not enough user balance.");
   }
}
