package com.paymentiq.operator.platform.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class AuthorizeInfo {

    @NotBlank(message = "userId is required.")
    private String userId;

    @NotNull(message = "txAmount is required.")
    private double txAmount;

    @NotBlank(message = "txAmountCy is required.")
    @Length(min = 3, max = 3, message = "txAmountCy should be 3 letters")
    private String txAmountCy;

    @NotBlank(message = "txId is required.")
    private String txId;

    @NotNull(message = "txTypeId is required.")
    private int txTypeId;

    @NotBlank(message = "txName is required.")
    private String txName;

    @NotBlank(message = "provider is required.")
    private String provider;

    private String pspService;
    private String originTxId;
    private UUID accountId;
    private String accountHolder;
    private String maskedAccount;
    private String pspFee;
    private String pspFeeCy;
    private String pspFeeBase;
    private String pspFeeBaseCy;
    private String attributes;
}