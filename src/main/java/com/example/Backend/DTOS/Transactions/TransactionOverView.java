package com.example.Backend.DTOS.Transactions;

import java.util.List;

public class TransactionOverView {
    private long totalTransaction;
    private double totalRevenue;
    private long completed;
    private long pending;
    private List<TransactionList> transactions;
    public long getTotalTransaction() {
        return totalTransaction;
    }

    public void setTotalTransaction(long totalTransaction) {
        this.totalTransaction = totalTransaction;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public long getCompleted() {
        return completed;
    }

    public void setCompleted(long completed) {
        this.completed = completed;
    }

    public long getPending() {
        return pending;
    }

    public void setPending(long pending) {
        this.pending = pending;
    }

    public List<TransactionList> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionList> transactions) {
        this.transactions = transactions;
    }
}
