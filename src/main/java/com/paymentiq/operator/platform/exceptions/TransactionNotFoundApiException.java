package com.paymentiq.operator.platform.exceptions;

import org.springframework.http.HttpStatus;

public class TransactionNotFoundApiException extends BaseApiException {

   public TransactionNotFoundApiException(String userId) {
       super(userId, HttpStatus.NOT_FOUND.value(), "Unknown transaction.");
   }
}
