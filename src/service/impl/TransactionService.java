package service.impl;

import util.transaction.Transaction;

public class TransactionService {
    Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
