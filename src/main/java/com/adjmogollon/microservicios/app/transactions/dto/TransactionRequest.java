package com.adjmogollon.microservicios.app.transactions.dto;

public class TransactionRequest {
	private String account;
	private Double amount;
    
    // Getters y setters

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

  
}
