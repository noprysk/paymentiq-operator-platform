package com.paymentiq.operator.platform.services;

import com.paymentiq.operator.platform.domain.Transaction;
import com.paymentiq.operator.platform.domain.TransactionType;
import com.paymentiq.operator.platform.dto.*;
import com.paymentiq.operator.platform.exceptions.InconsistentDataApiException;
import com.paymentiq.operator.platform.exceptions.TransactionNotFoundApiException;
import com.paymentiq.operator.platform.exceptions.UserDebitLimitApiException;
import com.paymentiq.operator.platform.repositories.TransactionRepository;
import com.paymentiq.operator.platform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionServiceImpl extends BaseService implements TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public AuthorizeDTO authorize(AuthorizeInfo info) {
        VerifyUserDTO verifyUser = checkUser(userRepository, info.getUserId(), info.getTxAmountCy());

        if (info.getTxAmount() < 0) {
            List<Transaction> transactions =
                    transactionRepository.getPendingTransactionsByUser(info.getUserId());

            double debitSum = transactions.stream()
                    .filter(t -> t.getAmount() < 0)
                    .mapToDouble(Transaction::getAmount)
                    .sum();

            if (verifyUser.getBalance() + (debitSum + info.getTxAmount()) < 0) {
                transactionRepository.create(info.getTxId(), info.getUserId(),
                        info.getTxAmount(), TransactionType.REJECTED);

                throw new UserDebitLimitApiException(info.getUserId());
            }
        }

        transactionRepository.create(
                info.getTxId(),
                info.getUserId(),
                info.getTxAmount(),
                TransactionType.PENDING);

        return AuthorizeDTO.builder()
                .userId(info.getUserId())
                .success(true)
                .authCode(UUID.randomUUID().toString()).build();
    }

    @Override
    public TransferDTO transfer(TransferInfo info) {
        checkUser(userRepository, info.getUserId(), info.getTxAmountCy());

        checkTransactionAvailability(info.getTxId(), info.getUserId(), info.getTxAmount());

        // TODO: transfer operation should be done in scope of transaction

        transactionRepository.update(
                info.getTxId(),
                info.getUserId(),
                TransactionType.APPROVED);

        userRepository.updateUserBalance(
                info.getUserId(),
                info.getTxAmount());

        return TransferDTO.builder()
                .userId(info.getUserId())
                .success(true)
                .txId(info.getTxId())
                .merchantTxId(UUID.randomUUID().toString()).build();
    }

    @Override
    public CancelDTO cancel(CancelInfo info) {
        checkUser(userRepository, info.getUserId(), info.getTxCurrency());

        checkTransactionAvailability(info.getTxId(), info.getUserId(), info.getTxAmount());

        transactionRepository.cancel(info.getTxId());

        return CancelDTO.builder()
                .userId(info.getUserId())
                .success(true).build();
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.getTransactions();
    }

    private void checkTransactionAvailability(String transactionId, String userId, double amount) {
        Optional<Transaction> transaction =
                transactionRepository.getPendingTransactionsById(transactionId);

        Transaction transData =
                transaction.orElseThrow(() -> new TransactionNotFoundApiException(userId));

        if (transData.getAmount() != amount) {
            throw new InconsistentDataApiException(userId);
        }
    }
}
