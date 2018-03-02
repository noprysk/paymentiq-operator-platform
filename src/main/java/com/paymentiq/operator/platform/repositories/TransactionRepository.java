package com.paymentiq.operator.platform.repositories;

import com.paymentiq.operator.platform.domain.Transaction;
import com.paymentiq.operator.platform.domain.TransactionType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {

    private static List<Transaction> transactions = new ArrayList<>();

    public void create(String transactionId, String userId, double amount, TransactionType type) {
        Transaction transaction = Transaction.builder()
                .transactionId(transactionId)
                .userId(userId)
                .amount(amount)
                .type(type).build();

        transactions.add(transaction);
    }

    public void update(String transactionId, String userId, TransactionType type) {

        transactions.stream()
                .filter(t -> t.getTransactionId().equals(transactionId) && t.getUserId().equals(userId))
                .forEach(t -> t.setType(type));
    }

    public void cancel(String transactionId) {
        for (Transaction item: transactions) {
            if(item.getTransactionId().equals(transactionId) && item.getType() == TransactionType.PENDING) {

                item.setType(TransactionType.CANCELED);
            }
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Transaction> getPendingTransactionsByUser(String userId) {
        return transactions.stream()
                .filter(t -> t.getUserId().equals(userId) && t.getType() == TransactionType.PENDING)
                .collect(Collectors.toList());
    }

    public Optional<Transaction> getPendingTransactionsById(String id) {
        return transactions.stream()
                .filter(t -> t.getTransactionId().equals(id) && t.getType() == TransactionType.PENDING)
                .findFirst();
    }
}
