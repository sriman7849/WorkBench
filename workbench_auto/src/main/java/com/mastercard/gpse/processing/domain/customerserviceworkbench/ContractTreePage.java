package com.mastercard.gpse.processing.domain.customerserviceworkbench;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Map;

public class ContractTreePage {

    private static final Logger logger = (Logger) LogManager.getLogger(ContractTreePage.class);

    private String institution;
    private String product;
    private String contractName;
    private String parentContract;
    private String billingContract;
    private String bankCode;
    private String bankAccountNumber;
    private String openingDate;
    private String availableProductionAction;
    private String status;
    private String lastActionDate;
    private String lastActionStatus;
    private String statusReason;
    private String contractID;
    private String expirationDate;
    private String title;
    private String firstName;
    private String lastName;
    private String companyName;
    private String cardNumber;
    private String cardName;
    private String sequenceNumber;
    private String otbAmount;
    private String otbCurrency;
    private String statusCode;


    public ContractTreePage getContract(Map<String,String> map){
        ContractTreePage contract = new ContractTreePage();
        contract.setInstitution(map.get("Institution"));
        contract.setProduct(map.get("Product"));
        contract.setContractName(map.get("ContractName"));
        contract.setParentContract(map.get("ParentContract"));
        contract.setBillingContract(map.get("BillingContract"));
        contract.setBankCode(map.get("BankCode"));
        contract.setBankAccountNumber(map.get("BankAccountNumber"));
        contract.setOpeningDate(map.get("OpeningDate"));
        contract.setAvailableProductionAction(map.get("AvailableProductionAction"));
        contract.setStatus(map.get("Status"));
        contract.setLastActionDate(map.get("LastActionDate"));
        contract.setLastActionStatus(map.get("LastActionStatus"));
        contract.setStatusReason(map.get("StatusReason"));
        contract.setContractID(map.get("ContractID"));
        contract.setExpirationDate(map.get("ExpirationDate"));
        contract.setTitle(map.get("Title"));
        contract.setFirstName(map.get("FirstName"));
        contract.setLastName(map.get("LastName"));
        contract.setCompanyName(map.get("CompanyName"));
        return contract;
    }

    public ContractTreePage getCardData(Map <String,String>map)
    {
        ContractTreePage cardData=new ContractTreePage();
        cardData.setCardNumber(map.get("CardNumber"));
        cardData.setCardName(map.get("CardName"));
        cardData.setProduct(map.get("Product"));
        cardData.setExpirationDate(map.get("ExpirationDate"));
        cardData.setOtbAmount(map.get("OtbAmount"));
        cardData.setOtbCurrency(map.get("OtbCurrency"));
        cardData.setBankCode(map.get("BankCode"));
        cardData.setBankAccountNumber(map.get("BankAccountNumber"));
        cardData.setSequenceNumber(map.get("SequenceNumber"));
        cardData.setTitle(map.get("Title"));
        cardData.setFirstName(map.get("FirstName"));
        cardData.setLastName(map.get("LastName"));
        cardData.setCompanyName(map.get("CompanyName"));
        cardData.setStatus(map.get("Status"));
        cardData.setStatusCode(map.get("StatusCode"));

        return cardData;
    }

    public String getOtbCurrency() {
        return otbCurrency;
    }

    public void setOtbCurrency(String otbCurrency) {
        this.otbCurrency = otbCurrency;
    }

    public String getStatusCode() { return statusCode; }

    public void setStatusCode(String statusCode) {this.statusCode=statusCode;}

    public String getCardNumber() { return cardNumber; }

    public void setCardNumber(String cardNumber) {this.cardNumber=cardNumber;}

    public String getOtbAmount() {return otbAmount;};

    public void setOtbAmount(String otbAmount) {this.otbAmount=otbAmount;}

    public String getCardName() {return cardName;}

    public void setCardName(String cardName) {this.cardName=cardName;}

    public String getSequenceNumber() {return sequenceNumber;}

    public void setSequenceNumber(String sequenceNumber) {this.sequenceNumber=sequenceNumber;}

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getParentContract() {
        return parentContract;
    }

    public void setParentContract(String parentContract) {
        this.parentContract = parentContract;
    }

    public String getBillingContract() {
        return billingContract;
    }

    public void setBillingContract(String billingContract) {
        this.billingContract = billingContract;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public String getAvailableProductionAction() {
        return availableProductionAction;
    }

    public void setAvailableProductionAction(String availableProductionAction) {
        this.availableProductionAction = availableProductionAction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastActionDate() {
        return lastActionDate;
    }

    public void setLastActionDate(String lastActionDate) {
        this.lastActionDate = lastActionDate;
    }

    public String getLastActionStatus() {
        return lastActionStatus;
    }

    public void setLastActionStatus(String lastActionStatus) {
        this.lastActionStatus = lastActionStatus;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public String getContractID() {
        return contractID;
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
