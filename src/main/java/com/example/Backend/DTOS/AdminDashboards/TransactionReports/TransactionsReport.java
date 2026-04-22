package com.example.Backend.DTOS.AdminDashboards.TransactionReports;

import com.example.Backend.DTOS.AdminDashboards.Earning;

import java.util.List;

public class TransactionsReport {
    private List<Earning> earnings;
    private List<TransactionReportList> transactions;

    public TransactionsReport(List<Earning> earnings, List<TransactionReportList> transactions) {
        this.earnings = earnings;
        this.transactions = transactions;
    }

    public List<Earning> getEarnings() {
        return earnings;
    }

    public void setEarnings(List<Earning> earnings) {
        this.earnings = earnings;
    }

    public List<TransactionReportList> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionReportList> transactions) {
        this.transactions = transactions;
    }
}
