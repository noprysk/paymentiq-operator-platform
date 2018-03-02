package com.paymentiq.operator.platform.services;

import com.paymentiq.operator.platform.domain.Transaction;
import com.paymentiq.operator.platform.dto.*;

import java.util.List;

public interface TransactionService {

    AuthorizeDTO authorize(AuthorizeInfo info);
    TransferDTO transfer(TransferInfo info);
    CancelDTO cancel(CancelInfo info);

    List<Transaction> getTransactions();
}
