package com.paymentiq.operator.platform.exceptions;

import org.springframework.http.HttpStatus;

public class InconsistentDataApiException extends BaseApiException {

   public InconsistentDataApiException(String userId) {
       super(userId, HttpStatus.BAD_REQUEST.value(), "Inconsistent data.");
   }
}
