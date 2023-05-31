package com.mastercard.gpse.processing.workflowsapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.gpse.processing.builders.*;
import com.mastercard.gpse.processing.configuration.ApiConfig;
import com.mastercard.gpse.processing.constants.BankConstants.INBANKConstants;
import com.mastercard.gpse.processing.constants.GlobalConstants;
import com.mastercard.gpse.processing.managers.SSHTunnel;
import com.mastercard.gpse.processing.utils.JsonUtils;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WBPreRequisite extends ApiConfig {

    private static final Logger logger = (Logger) LogManager.getLogger(WBPreRequisite.class);
    public static Map<String, Object> GlobalWBTestData = new HashMap<String, Object>();
    private ClientBuilder clientBuilder = new ClientBuilder();
    private AccountBuilder accountBuilder = new AccountBuilder();
    private CardBuilder cardBuilder = new CardBuilder();

    /**
     * Create Pre Requisite Data
     */
    public void createPreRequisite() {

        new SSHTunnel().sendKeepAliveMsg();
        createClientPreRequisite();

        new SSHTunnel().sendKeepAliveMsg();
        createAccountPreRequisite();

        new SSHTunnel().sendKeepAliveMsg();
        createCardForSpecifiedClientPreRequisite();

        new SSHTunnel().sendKeepAliveMsg();
        activateCard();

        new SSHTunnel().sendKeepAliveMsg();
        getCardPlastics();

        new SSHTunnel().sendKeepAliveMsg();
        getClientDetails();

        new SSHTunnel().sendKeepAliveMsg();
        getCardsClientDetails();

        new SSHTunnel().sendKeepAliveMsg();
        getContractFinancials();

        new SSHTunnel().sendKeepAliveMsg();
        debitContract();
    }

    /**
     * Create Client
     */
    public void createClientPreRequisite() {
        String payload = new CreateClientAPI().getModifiedPayload();
        try {
            clientBuilder = new ObjectMapper().readValue(payload, ClientBuilder.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Response response = new CreateClientAPI().createClient(payload);
        clientBuilder.setRequestID(JsonUtils.getValueFromResponse(response, "requestID"));
        clientBuilder.setClientID(JsonUtils.getValueFromResponse(response, "responseData.clientID"));
        GlobalWBTestData.put(ClientBuilder.class.getSimpleName(), clientBuilder);
    }

    /**
     * Create Account
     */
    public void createAccountPreRequisite() {
        String payload = new CreateAccountAPI().getModifiedPayload();
        try {
            accountBuilder = new ObjectMapper().readValue(payload, AccountBuilder.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Response response = new CreateAccountAPI().createAccount(payload);
        accountBuilder.setRequestID(JsonUtils.getValueFromResponse(response, "requestID"));
        accountBuilder.setAccountID(JsonUtils.getValueFromResponse(response, "responseData.accountID"));
        GlobalWBTestData.put(AccountBuilder.class.getSimpleName(), accountBuilder);
    }

    /**
     * Create Card for Specified Client
     */
    public void createCardForSpecifiedClientPreRequisite() {
        String payload = new CreateCardForSpecifiedClientAPI().getModifiedPayload();
        try {
            cardBuilder = new ObjectMapper().readValue(payload, CardBuilder.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Response response = new CreateCardForSpecifiedClientAPI().createCardForSpecifiedClient(clientBuilder.getClientID(), accountBuilder.getAccountID(), payload);
        cardBuilder.setRequestID(JsonUtils.getValueFromResponse(response, "requestID"));
        cardBuilder.setCardID(JsonUtils.getValueFromResponse(response, "responseData.cardID"));
        GlobalWBTestData.put(CardBuilder.class.getSimpleName(), cardBuilder);
    }

    /**
     * Activate Card
     */
    public void activateCard() {
        String payload = "";
        new CreateCardForSpecifiedClientAPI().activateCard(payload, cardBuilder.getCardID());
    }

    /**
     * get Card Plastics
     */
    public void getCardPlastics() {
        Response response = new CreateCardForSpecifiedClientAPI().getCardPlastics(cardBuilder.getCardID());
        CardBuilder.PlasticsList plasticsList = new CardBuilder.PlasticsList();

        plasticsList.setCardPAN(JsonUtils.getValueFromResponse(response, "responseData.plasticsList[0].cardPAN"));
        plasticsList.setId(JsonUtils.getValueFromResponse(response, "responseData.plasticsList[0].id"));
        plasticsList.setCardID(JsonUtils.getValueFromResponse(response, "responseData.plasticsList[0].cardID"));
        plasticsList.setEmbossedName(JsonUtils.getValueFromResponse(response, "responseData.plasticsList[0].embossedName"));
        plasticsList.setSequenceNumber(JsonUtils.getValueFromResponse(response, "responseData.plasticsList[0].sequenceNumber"));
        plasticsList.setChipScheme(JsonUtils.getValueFromResponse(response, "responseData.plasticsList[0].chipScheme"));
        plasticsList.setStatus(JsonUtils.getValueFromResponse(response, "responseData.plasticsList[0].status"));
        plasticsList.setCardExpiryDate(JsonUtils.getValueFromResponse(response, "responseData.plasticsList[0].cardExpiryDate"));
        plasticsList.setProductionCode(JsonUtils.getValueFromResponse(response, "responseData.plasticsList[0].productionCode"));
        plasticsList.setProductionEvent(JsonUtils.getValueFromResponse(response, "responseData.plasticsList[0].productionEvent"));
        plasticsList.setProductionType(JsonUtils.getValueFromResponse(response, "responseData.plasticsList[0].productionType"));
        plasticsList.setProductionReason(JsonUtils.getValueFromResponse(response, "responseData.plasticsList[0].productionReason"));
        plasticsList.setProductionDate(JsonUtils.getValueFromResponse(response, "responseData.plasticsList[0].productionDate"));
        plasticsList.setEffectiveDate(JsonUtils.getValueFromResponse(response, "responseData.plasticsList[0].effectiveDate"));
        plasticsList.setPersoFileName(JsonUtils.getValueFromResponse(response, "responseData.plasticsList[0].persoFileName"));
        plasticsList.setOrderSource(JsonUtils.getValueFromResponse(response, "responseData.plasticsList[0].orderSource"));
        plasticsList.setOrderTarget(JsonUtils.getValueFromResponse(response, "responseData.plasticsList[0].orderTarget"));
        cardBuilder.setPlasticsList(plasticsList);
    }

    /**
     * get Contract Financials
     */
    public void getContractFinancials() {
        Response response = new CreateAccountAPI().getContractFinancials(accountBuilder.getAccountID());
        AccountBuilder.ContractFinancials contractFinancials = new AccountBuilder.ContractFinancials();
        contractFinancials.setContractID(JsonUtils.getValueFromResponse(response, "responseData.contractFinancials.contractID"));
        contractFinancials.setCurrency(JsonUtils.getValueFromResponse(response, "responseData.contractFinancials.currency"));
        contractFinancials.setAvailableAmount(JsonUtils.getValueFromResponse(response, "responseData.contractFinancials.availableAmount"));
        contractFinancials.setBalance(JsonUtils.getValueFromResponse(response, "responseData.contractFinancials.balance"));
        contractFinancials.setBlockedAmount(JsonUtils.getValueFromResponse(response, "responseData.contractFinancials.blockedAmount"));
        contractFinancials.setAmendmentDate(JsonUtils.getValueFromResponse(response, "responseData.contractFinancials.amendmentDate"));
        contractFinancials.setMainContractID(JsonUtils.getValueFromResponse(response, "responseData.contractFinancials.mainContractID"));
        contractFinancials.setMainContractNumber(JsonUtils.getValueFromResponse(response, "responseData.contractFinancials.mainContractNumber"));
        //contractFinancials.setParentContractID(JsonUtils.getValueFromResponse(response, "responseData.contractFinancials.parentContractID"));
        //contractFinancials.setParentContractNumber(JsonUtils.getValueFromResponse(response, "responseData.contractFinancials.parentContractNumber"));
        contractFinancials.setTotalDueAmount(JsonUtils.getValueFromResponse(response, "responseData.contractFinancials.totalDueAmount"));
        contractFinancials.setPastDueAmount(JsonUtils.getValueFromResponse(response, "responseData.contractFinancials.pastDueAmount"));
        contractFinancials.setOverlimitAmount(JsonUtils.getValueFromResponse(response, "responseData.contractFinancials.overlimitAmount"));
        contractFinancials.setDisputeAmount(JsonUtils.getValueFromResponse(response, "responseData.contractFinancials.disputeAmount"));
        accountBuilder.setContractFinancials(contractFinancials);
    }

    /**
     * get Client Details
     */
    public void getClientDetails() {
        Response response = new CreateClientAPI().getClientDetails(clientBuilder.getClientID(), INBANKConstants.INB);
        clientBuilder.setClientNumber(JsonUtils.getValueFromResponse(response, "responseData.client.clientNumber"));
        clientBuilder.setClientPersonalData(clientBuilder.getClientPersonalData());
    }

    /**
     * get Cards By Client Details
     */
    public void getCardsClientDetails() {
        Response response = new CreateClientAPI().getCardsByClient(clientBuilder.getClientID(), INBANKConstants.INB);
        cardBuilder.setCBSNumber(JsonUtils.getValueFromResponse(response, "responseData.cardsList[0].cbsNumber"));
        cardBuilder.setCardID(JsonUtils.getValueFromResponse(response, "responseData.cardsList[0].cardID"));
        cardBuilder.setCardContractNumber(JsonUtils.getValueFromResponse(response, "responseData.cardsList[0].cardContractNumber"));
        cardBuilder.setDateOpen(JsonUtils.getValueFromResponse(response, "responseData.cardsList[0].dateOpen"));
        cardBuilder.setCardExpiryDate(JsonUtils.getValueFromResponse(response, "responseData.cardsList[0].cardExpiryDate"));
        cardBuilder.setProductName(JsonUtils.getValueFromResponse(response, "responseData.cardsList[0].productName"));
        cardBuilder.setAccountContractNumber(JsonUtils.getValueFromResponse(response, "responseData.cardsList[0].accountContractNumber"));
        cardBuilder.setCardContractNumber(JsonUtils.getValueFromResponse(response, "responseData.cardsList[0].cardContractNumber"));
        cardBuilder.getCard().setContractName(JsonUtils.getValueFromResponse(response, "responseData.cardsList[0].contractName"));
        cardBuilder.getCard().getEmbossedData().setEmbossedTitle(JsonUtils.getValueFromResponse(response, "responseData.cardsList[0].embossedData.embossedTitle"));
        cardBuilder.getCard().getEmbossedData().setEmbossedFirstName(JsonUtils.getValueFromResponse(response, "responseData.cardsList[0].embossedData.embossedFirstName"));
        cardBuilder.getCard().getEmbossedData().setEmbossedLastName(JsonUtils.getValueFromResponse(response, "responseData.cardsList[0].embossedData.embossedLastName"));
        cardBuilder.getCard().getEmbossedData().setEmbossedCompanyName(JsonUtils.getValueFromResponse(response, "responseData.cardsList[0].embossedData.embossedCompanyName"));
        cardBuilder.getCard().setCardStatus(JsonUtils.getValueFromResponse(response, "responseData.cardsList[0].cardStatusData.statusDetails"));
    }

    /**
     * Debit Contract
     */
    public void debitContract() {
        TransactionBuilder transactionBuilder = null;
        String payload = new ContractAPI().getModifiedPayload();
        try {
            transactionBuilder = new ObjectMapper().readValue(payload, TransactionBuilder.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ContractAPI().debitContract(accountBuilder.getAccountID(), payload);
        GlobalWBTestData.put(GlobalConstants.DEBIT_CONTRACT, transactionBuilder);
    }
}