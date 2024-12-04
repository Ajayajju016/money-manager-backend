package com.money.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.money.manager.model.Transaction;
import com.money.manager.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    // Retrieve all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Add a new transaction
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Delete a transaction by ID
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    // Retrieve a transaction by ID
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    // Update an existing transaction
    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);

        if (optionalTransaction.isPresent()) {
            Transaction existingTransaction = optionalTransaction.get();
            existingTransaction.setName(updatedTransaction.getName());
            existingTransaction.setAmount(updatedTransaction.getAmount());
            existingTransaction.setTransactionDate(updatedTransaction.getTransactionDate());
            existingTransaction.setStatus(updatedTransaction.getStatus());
            existingTransaction.setPurpose(updatedTransaction.getPurpose());
            existingTransaction.setMode(updatedTransaction.getMode());
            return transactionRepository.save(existingTransaction);
        } else {
            throw new RuntimeException("Transaction not found with ID: " + id);
        }
    }
}