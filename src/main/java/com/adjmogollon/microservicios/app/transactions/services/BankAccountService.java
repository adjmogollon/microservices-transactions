package com.adjmogollon.microservicios.app.transactions.services;

import java.util.List;

import com.adjmogollon.microservicios.app.transactions.entity.BankAccount;

public interface BankAccountService {
    List<BankAccount> findAll();
    BankAccount findById(Long id);
    BankAccount save(BankAccount bankAccount);
    void deleteById(Long id);
    void deactivateById(Long id);
	boolean existsByAccount(String accountNumber);
	BankAccount findByAccount(String account); 
}