package com.mastercard.gpse.processing.builders;

public class AccountBuilder {

    // Path/Query parameters
    private String requestID;

    // Response fields
    private String accountID;

    // Request body fields
    private String clientID;
    private Account account;
    private String bankCode;

    private ContractFinancials contractFinancials;

    public AccountBuilder() {
    }

    public ContractFinancials getContractFinancials() {
        return contractFinancials;
    }

    public void setContractFinancials(ContractFinancials contractFinancials) {
        this.contractFinancials = contractFinancials;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public class Account {
        private String accountContractNumber;
        private String cbsNumber;
        private String contractName;
        private String productCode;

        public Account() {
        }

        public String getAccountContractNumber() {
            return accountContractNumber;
        }

        public void setAccountContractNumber(String accountContractNumber) {
            this.accountContractNumber = accountContractNumber;
        }

        public String getCbsNumber() {
            return cbsNumber;
        }

        public void setCbsNumber(String cbsNumber) {
            this.cbsNumber = cbsNumber;
        }

        public String getContractName() {
            return contractName;
        }

        public void setContractName(String contractName) {
            this.contractName = contractName;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }
    }

    public static class ContractFinancials {
        private String contractID;
        private String currency;
        private String availableAmount;
        private String balance;
        private String blockedAmount;
        private String amendmentDate;
        private String clientID;
        private String mainContractID;
        private String mainContractNumber;
        private String parentContractID;
        private String parentContractNumber;
        private String contractNumber;
        private String totalDueAmount;
        private String pastDueAmount;
        private String overlimitAmount;
        private String disputeAmount;

        public String getContractNumber() {
            return contractNumber;
        }

        public void setContractNumber(String contractNumber) {
            this.contractNumber = contractNumber;
        }

        public String getDisputeAmount() {
            return disputeAmount;
        }

        public void setDisputeAmount(String disputeAmount) {
            this.disputeAmount = disputeAmount;
        }

        public String getTotalDueAmount() {
            return totalDueAmount;
        }

        public void setTotalDueAmount(String totalDueAmount) {
            this.totalDueAmount = totalDueAmount;
        }

        public String getPastDueAmount() {
            return pastDueAmount;
        }

        public void setPastDueAmount(String pastDueAmount) {
            this.pastDueAmount = pastDueAmount;
        }

        public String getOverlimitAmount() {
            return overlimitAmount;
        }

        public void setOverlimitAmount(String overlimitAmount) {
            this.overlimitAmount = overlimitAmount;
        }

        public String getContractID() {
            return contractID;
        }

        public void setContractID(String contractID) {
            this.contractID = contractID;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getAvailableAmount() {
            return availableAmount;
        }

        public void setAvailableAmount(String availableAmount) {
            this.availableAmount = availableAmount;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getBlockedAmount() {
            return blockedAmount;
        }

        public void setBlockedAmount(String blockedAmount) {
            this.blockedAmount = blockedAmount;
        }

        public String getAmendmentDate() {
            return amendmentDate;
        }

        public void setAmendmentDate(String amendmentDate) {
            this.amendmentDate = amendmentDate;
        }

        public String getClientID() {
            return clientID;
        }

        public void setClientID(String clientID) {
            this.clientID = clientID;
        }

        public String getMainContractID() {
            return mainContractID;
        }

        public void setMainContractID(String mainContractID) {
            this.mainContractID = mainContractID;
        }

        public String getMainContractNumber() {
            return mainContractNumber;
        }

        public void setMainContractNumber(String mainContractNumber) {
            this.mainContractNumber = mainContractNumber;
        }

        public String getParentContractID() {
            return parentContractID;
        }

        public void setParentContractID(String parentContractID) {
            this.parentContractID = parentContractID;
        }

        public String getParentContractNumber() {
            return parentContractNumber;
        }

        public void setParentContractNumber(String parentContractNumber) {
            this.parentContractNumber = parentContractNumber;
        }
    }
}
