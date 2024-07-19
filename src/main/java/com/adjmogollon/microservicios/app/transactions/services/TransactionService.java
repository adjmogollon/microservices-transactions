package com.adjmogollon.microservicios.app.transactions.services;

import java.util.List;

import com.adjmogollon.microservicios.app.transactions.entity.Transaction;

public interface TransactionService {
    List<Transaction> findAll();
    Transaction findById(Long id);
    Transaction save(Transaction transaction);
    void deleteById(Long id);
}