package com.adjmogollon.microservicios.app.transactions.controllers;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adjmogollon.microservicios.app.transactions.dto.TransactionRequest;
import com.adjmogollon.microservicios.app.transactions.entity.BankAccount;
import com.adjmogollon.microservicios.app.transactions.entity.Transaction;
import com.adjmogollon.microservicios.app.transactions.services.BankAccountService;
import com.adjmogollon.microservicios.app.transactions.services.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private static TransactionService transactionService;
    private static BankAccountService bankAccountService;

    public TransactionController(TransactionService transactionService, BankAccountService bankAccountService) {
        TransactionController.transactionService = transactionService;
        TransactionController.bankAccountService = bankAccountService;
    }

    @PostMapping("/credit")
    public ResponseEntity<?> credit(@RequestBody TransactionRequest request) {
        BankAccount bankAccount = bankAccountService.findByAccount(request.getAccount());
        if (bankAccount == null) {
            return ResponseEntity.badRequest().body("Cuenta bancaria no existe");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setType("CREDIT");
        transaction.setTransactionDate(new Date());
        transaction.setBankAccount(bankAccount);

        transactionService.save(transaction);

        // Actualizar el balance en la base de datos
        bankAccount.setAmount(bankAccount.getAmount() + request.getAmount());
        bankAccountService.save(bankAccount);

        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/debit")
    public ResponseEntity<?> debit(@RequestBody TransactionRequest request) {
        BankAccount bankAccount = bankAccountService.findByAccount(request.getAccount());
        if (bankAccount == null) {
            return ResponseEntity.badRequest().body("Cuenta bancaria no existe");
        }

        if (bankAccount.getAmount() < request.getAmount()) {
            return ResponseEntity.badRequest().body("Fondos Insuficientes");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setType("DEBIT");
        transaction.setTransactionDate(new Date());
        transaction.setBankAccount(bankAccount);

        transactionService.save(transaction);

        // Actualizar el balance en la base de datos
        bankAccount.setAmount(bankAccount.getAmount() - request.getAmount());
        bankAccountService.save(bankAccount);

        return ResponseEntity.ok(transaction);
    }
}