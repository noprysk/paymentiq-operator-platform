package com.paymentiq.operator.platform.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
public class VerifyUserInfo {

    @NotBlank(message = "sessionId is required.")
    private String sessionId;

    @NotBlank(message = "userId is required.")
    private String userId;
}
