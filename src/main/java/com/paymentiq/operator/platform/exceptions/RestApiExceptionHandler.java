package com.paymentiq.operator.platform.exceptions;

import com.paymentiq.operator.platform.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler({
            BadRequestParamsApiException.class, UserNotFoundApiException.class,
            UserDebitLimitApiException.class, TransactionNotFoundApiException.class})
    public ResponseEntity<ErrorResponseDTO> handleRestApiException(BaseApiException ex) {

        return ResponseEntity.ok(
                new ErrorResponseDTO(ex.getUserId(), ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponseDTO> handleGeneralRestApiException(Exception ex) {

        return ResponseEntity.ok(
                new ErrorResponseDTO("", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }
}
