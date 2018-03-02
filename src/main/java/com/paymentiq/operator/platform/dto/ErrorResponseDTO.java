package com.paymentiq.operator.platform.dto;

import lombok.Getter;

@Getter
public class ErrorResponseDTO {
    private String userId;
    private boolean success;
    private int errCode;
    private String errMsg;

    public ErrorResponseDTO(String userId, int errCode, String errMsg) {
        this.userId = userId;
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.success = false;
    }
}
