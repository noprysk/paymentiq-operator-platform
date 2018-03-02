package com.paymentiq.operator.platform.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

import java.util.stream.Collectors;

public class BadRequestParamsApiException extends BaseApiException {

   public BadRequestParamsApiException(String userId, Errors errors) {
       super(userId,
             HttpStatus.BAD_REQUEST.value(),
             errors.getFieldErrors().stream()
                      .map(DefaultMessageSourceResolvable::getDefaultMessage)
                      .collect(Collectors.joining(" ")));
   }
}
