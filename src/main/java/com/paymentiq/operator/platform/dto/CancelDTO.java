package com.paymentiq.operator.platform.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CancelDTO {

    private String userId;
    private boolean success;
}