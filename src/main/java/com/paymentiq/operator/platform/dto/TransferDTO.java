package com.paymentiq.operator.platform.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TransferDTO {

    private String userId;
    private boolean success;
    private String txId;
    private String merchantTxId;
}