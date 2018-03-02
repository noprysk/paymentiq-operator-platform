package com.paymentiq.operator.platform.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class CancelInfo {

    @NotBlank(message = "userId is required.")
    private String userId;

    @NotBlank(message = "authCode is required.")
    private String authCode;

    @NotNull(message = "txAmount is required.")
    private double txAmount;

    @NotBlank(message = "txCurrency is required.")
    @Length(min = 3, max = 3, message = "txCurrency size should be 3 letters")
    private String txCurrency;

    @NotBlank(message = "txId is required.")
    private String txId;

    @NotNull(message = "txTypeId is required.")
    private int txTypeId;

    @NotBlank(message = "txName is required.")
    private String txName;

    @NotBlank(message = "provider is required.")
    private String provider;

    private String originTxId;
    private UUID accountId;
    private String maskedAccount;
    private String statusCode;
    private String pspStatusCode;
    private String pspFee;
    private String pspFeeCy;
    private String pspFeeBase;
    private String pspFeeBaseCy;
    private String pspRefId;
    private String pspStatusMessage;
    private Object attributes;
}