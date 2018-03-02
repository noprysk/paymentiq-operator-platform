package com.paymentiq.operator.platform.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundApiException extends BaseApiException {

   public UserNotFoundApiException(String userId) {
        super(userId, HttpStatus.NOT_FOUND.value(), "Unknown user.");
   }
}
