package com.paymentiq.operator.platform.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Transaction {

    private String transactionId;
    private String userId;
    private double amount;
    private TransactionType type;
}
