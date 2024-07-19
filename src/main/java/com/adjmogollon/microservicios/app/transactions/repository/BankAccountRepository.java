package com.adjmogollon.microservicios.app.transactions.repository;

import org.springframework.data.repository.CrudRepository;

import com.adjmogollon.microservicios.app.transactions.entity.BankAccount;

public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {
    boolean existsByAccount(String accountNumber);
    BankAccount findByAccount(String account);

}
