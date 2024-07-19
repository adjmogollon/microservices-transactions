package com.adjmogollon.microservicios.app.transactions.repository;

import org.springframework.data.repository.CrudRepository;

import com.adjmogollon.microservicios.app.transactions.entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}