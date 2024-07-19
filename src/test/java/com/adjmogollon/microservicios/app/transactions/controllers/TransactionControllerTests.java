package com.adjmogollon.microservicios.app.transactions.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.adjmogollon.microservicios.app.transactions.dto.TransactionRequest;
import com.adjmogollon.microservicios.app.transactions.entity.BankAccount;
import com.adjmogollon.microservicios.app.transactions.entity.Transaction;
import com.adjmogollon.microservicios.app.transactions.services.BankAccountService;
import com.adjmogollon.microservicios.app.transactions.services.TransactionService;

@SpringJUnitConfig
public class TransactionControllerTests {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @Mock
    private BankAccountService bankAccountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreditAccountNotFound() {
        TransactionRequest request = new TransactionRequest();
        request.setAccount("123456");
        request.setAmount(100.0);

        when(bankAccountService.findByAccount(eq("123456"))).thenReturn(null);

        ResponseEntity<?> response = transactionController.credit(request);

        assert(response.getStatusCodeValue() == 400);
        assert(response.getBody().equals("Cuenta bancaria no existe"));
    }


    @Test
    public void testDebitAccountNotFound() {
        TransactionRequest request = new TransactionRequest();
        request.setAccount("123456");
        request.setAmount(100.0);

        when(bankAccountService.findByAccount(eq("123456"))).thenReturn(null);

        ResponseEntity<?> response = transactionController.debit(request);

        assert(response.getStatusCodeValue() == 400);
        assert(response.getBody().equals("Cuenta bancaria no existe"));
    }

    
}