package com.paymentiq.operator.platform.controllers;

import com.paymentiq.operator.platform.domain.Transaction;
import com.paymentiq.operator.platform.dto.*;
import com.paymentiq.operator.platform.exceptions.BadRequestParamsApiException;
import com.paymentiq.operator.platform.services.TransactionService;
import com.paymentiq.operator.platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OperatorPlatformController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/verifyuser", consumes = "application/json", produces = "application/json")
    public ResponseEntity<VerifyUserDTO> verifyUser(@RequestBody @Valid VerifyUserInfo verifyUserInfo, Errors errors) {

        checkRequestParams(verifyUserInfo.getUserId(), errors);

        return ResponseEntity
                .ok(userService.verifyUser(verifyUserInfo));
    }

    @PostMapping(value = "/authorize", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthorizeDTO> authorize(@RequestBody @Valid AuthorizeInfo authorizeInfo, Errors errors) {

        checkRequestParams(authorizeInfo.getUserId(), errors);

        return ResponseEntity
                .ok(transactionService.authorize(authorizeInfo));
    }

    @PostMapping(value = "/transfer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TransferDTO> transfer(@RequestBody @Valid TransferInfo transferInfo, Errors errors) {

        checkRequestParams(transferInfo.getUserId(), errors);

        return ResponseEntity
                .ok(transactionService.transfer(transferInfo));
    }

    @PostMapping(value = "/cancel", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CancelDTO> cancel(@RequestBody @Valid CancelInfo cancelInfo, Errors errors) {

        checkRequestParams(cancelInfo.getUserId(), errors);

        return ResponseEntity
                .ok(transactionService.cancel(cancelInfo));
    }

    @GetMapping(value = "/transactions", produces = "application/json")
    public ResponseEntity<List<Transaction>> getTransactions() {

        return ResponseEntity
                .ok(transactionService.getTransactions());
    }

    private void checkRequestParams(String userId, Errors errors) {
        if(errors.hasErrors()) {
            throw new BadRequestParamsApiException(userId, errors);
        }
    }
}
