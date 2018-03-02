package com.paymentiq.operator.platform.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class TransferInfo {

    @NotBlank(message = "userId is required.")
    private String userId;

    private String authCode;

    @NotNull(message = "txAmount is required.")
    private double txAmount;

    @NotBlank(message = "txAmountCy is required.")
    @Length(min = 3, max = 3, message = "txAmountCy size should be 3 letters")
    private String txAmountCy;

    @NotBlank(message = "txPspAmount is required.")
    private String txPspAmount;

    @NotBlank(message = "txPspAmountCy is required.")
    private String txPspAmountCy;

    @NotBlank(message = "fee is required.")
    private String fee;

    @NotBlank(message = "feeCy is required.")
    private String feeCy;

    @NotBlank(message = "txId is required.")
    private String txId;

    @NotNull(message = "txTypeId is required.")
    private int txTypeId;

    @NotBlank(message = "txName is required.")
    private String txName;

    @NotBlank(message = "provider is required.")
    private String provider;

    @NotBlank(message = "txRefId is required.")
    private String txRefId;

    private String pspService;
    private String originTxId;
    private UUID accountId;
    private String accountHolder;
    private String maskedAccount;
    private String pspFee;
    private String pspFeeCy;
    private String pspFeeBase;
    private String pspFeeBaseCy;
    private String pspRefId;
    private String pspStatusMessage;
    private Object attributes;
}