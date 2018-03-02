package com.paymentiq.operator.platform.exceptions;

import lombok.Getter;

@Getter
public class BaseApiException extends RuntimeException {

    private String userId;
    private int code;

    public BaseApiException(String userId, int code, String message) {
        super(message);
        this.userId = userId;
        this.code = code;
    }
}
