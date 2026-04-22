package com.example.Backend.DTOS.Transactions;

public class TransactionView {
    private TransactionDetail transactionDetail;
    private ProviderInfo providerInfo;
    private SubscriptionPlan subscriptionPlan;

    public TransactionView(TransactionDetail transactionDetail, ProviderInfo providerInfo, SubscriptionPlan subscriptionPlan) {
        this.transactionDetail = transactionDetail;
        this.providerInfo = providerInfo;
        this.subscriptionPlan = subscriptionPlan;
    }

    public TransactionDetail getTransactionDetail() {
        return transactionDetail;
    }

    public void setTransactionDetail(TransactionDetail transactionDetail) {
        this.transactionDetail = transactionDetail;
    }

    public ProviderInfo getProviderInfo() {
        return providerInfo;
    }

    public void setProviderInfo(ProviderInfo providerInfo) {
        this.providerInfo = providerInfo;
    }

    public SubscriptionPlan getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }
}
