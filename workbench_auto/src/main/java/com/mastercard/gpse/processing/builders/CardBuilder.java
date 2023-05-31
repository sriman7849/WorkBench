package com.mastercard.gpse.processing.builders;

import java.util.ArrayList;
import java.util.List;

public class CardBuilder {

    // Path/Query Parameters
    private String clientID;
    private String accountID;
    private String requestID;

    // Response Body Fields
    private String cardID;
    private String CBSNumber;
    private String cardPAN;
    private String dateOpen;
    private String cardExpiryDate;
    private String cardholderID;
    private String accountContractNumber;
    private String productName;
    private String cardContractNumber;

    // Request Body Fields
    private Card card;
    private ArrayList<CardCustomDataList> cardCustomDataList;

    private PlasticsList plasticsList;

    public CardBuilder() {
    }

    public PlasticsList getPlasticsList() {
        return plasticsList;
    }

    public void setPlasticsList(PlasticsList plasticsList) {
        this.plasticsList = plasticsList;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getCBSNumber() {
        return CBSNumber;
    }

    public void setCBSNumber(String CBSNumber) {
        this.CBSNumber = CBSNumber;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public ArrayList<CardCustomDataList> getCardCustomDataList() {
        return cardCustomDataList;
    }

    public void setCardCustomDataList(ArrayList<CardCustomDataList> cardCustomDataList) {
        this.cardCustomDataList = cardCustomDataList;
    }

    public String getDateOpen() {
        return dateOpen;
    }

    public void setDateOpen(String dateOpen) {
        this.dateOpen = dateOpen;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getCardholderID() {
        return cardholderID;
    }

    public void setCardholderID(String cardholderID) {
        this.cardholderID = cardholderID;
    }

    public String getAccountContractNumber() {
        return accountContractNumber;
    }

    public void setAccountContractNumber(String accountContractNumber) {
        this.accountContractNumber = accountContractNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCardContractNumber() {
        return cardContractNumber;
    }

    public void setCardContractNumber(String cardContractNumber) {
        this.cardContractNumber = cardContractNumber;
    }

    public class Card {
        private String cardContractNumber;
        private String accountContractNumber;
        private String contractName;
        private String productCode;
        private EmbossedData embossedData;
        private String cardStatus;

        public Card() {
        }

        public String getCardContractNumber() {
            return cardContractNumber;
        }

        public void setCardContractNumber(String cardContractNumber) {
            this.cardContractNumber = cardContractNumber;
        }

        public String getAccountContractNumber() {
            return accountContractNumber;
        }

        public void setAccountContractNumber(String accountContractNumber) {
            this.accountContractNumber = accountContractNumber;
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

        public String getCardStatus() {
            return cardStatus;
        }

        public void setCardStatus(String cardStatus) {
            this.cardStatus = cardStatus;
        }

        public EmbossedData getEmbossedData() {
            return embossedData;
        }

        public void setEmbossedData(EmbossedData embossedData) {
            this.embossedData = embossedData;
        }

        public class EmbossedData {
            private String embossedCompanyName;
            private String embossedFirstName;
            private String embossedLastName;
            private String embossedTitle;

            public EmbossedData() {
            }

            public String getEmbossedCompanyName() {
                return embossedCompanyName;
            }

            public void setEmbossedCompanyName(String embossedCompanyName) {
                this.embossedCompanyName = embossedCompanyName;
            }

            public String getEmbossedFirstName() {
                return embossedFirstName;
            }

            public void setEmbossedFirstName(String embossedFirstName) {
                this.embossedFirstName = embossedFirstName;
            }

            public String getEmbossedLastName() {
                return embossedLastName;
            }

            public void setEmbossedLastName(String embossedLastName) {
                this.embossedLastName = embossedLastName;
            }

            public String getEmbossedTitle() {
                return embossedTitle;
            }

            public void setEmbossedTitle(String embossedTitle) {
                this.embossedTitle = embossedTitle;
            }
        }
    }

    public static class CardCustomDataList {
        private String removeTag;
        private String tagContainer;
        private String tagName;
        private String tagValue;

        public CardCustomDataList() {
        }

        public String getRemoveTag() {
            return removeTag;
        }

        public void setRemoveTag(String removeTag) {
            this.removeTag = removeTag;
        }

        public String getTagContainer() {
            return tagContainer;
        }

        public void setTagContainer(String tagContainer) {
            this.tagContainer = tagContainer;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public String getTagValue() {
            return tagValue;
        }

        public void setTagValue(String tagValue) {
            this.tagValue = tagValue;
        }
    }
    public static class PlasticsList{

        private String id;
        private String cardID;
        private String cardPAN;
        private String embossedName;
        private String sequenceNumber;
        private String chipScheme;
        private String status;
        private String cardExpiryDate;
        private String productionCode;
        private String productionEvent;
        private String productionType;
        private String productionReason;
        private String productionDate;
        private String effectiveDate;
        private String persoFileName;
        private String orderSource;
        private String orderTarget;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCardID() {
            return cardID;
        }

        public void setCardID(String cardID) {
            this.cardID = cardID;
        }

        public String getCardPAN() {
            return cardPAN;
        }

        public void setCardPAN(String cardPAN) {
            this.cardPAN = cardPAN;
        }

        public String getEmbossedName() {
            return embossedName;
        }

        public void setEmbossedName(String embossedName) {
            this.embossedName = embossedName;
        }

        public String getSequenceNumber() {
            return sequenceNumber;
        }

        public void setSequenceNumber(String sequenceNumber) {
            this.sequenceNumber = sequenceNumber;
        }

        public String getChipScheme() {
            return chipScheme;
        }

        public void setChipScheme(String chipScheme) {
            this.chipScheme = chipScheme;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCardExpiryDate() {
            return cardExpiryDate;
        }

        public void setCardExpiryDate(String cardExpiryDate) {
            this.cardExpiryDate = cardExpiryDate;
        }

        public String getProductionCode() {
            return productionCode;
        }

        public void setProductionCode(String productionCode) {
            this.productionCode = productionCode;
        }

        public String getProductionEvent() {
            return productionEvent;
        }

        public void setProductionEvent(String productionEvent) {
            this.productionEvent = productionEvent;
        }

        public String getProductionType() {
            return productionType;
        }

        public void setProductionType(String productionType) {
            this.productionType = productionType;
        }

        public String getProductionReason() {
            return productionReason;
        }

        public void setProductionReason(String productionReason) {
            this.productionReason = productionReason;
        }

        public String getProductionDate() {
            return productionDate;
        }

        public void setProductionDate(String productionDate) {
            this.productionDate = productionDate;
        }

        public String getEffectiveDate() {
            return effectiveDate;
        }

        public void setEffectiveDate(String effectiveDate) {
            this.effectiveDate = effectiveDate;
        }

        public String getPersoFileName() {
            return persoFileName;
        }

        public void setPersoFileName(String persoFileName) {
            this.persoFileName = persoFileName;
        }

        public String getOrderSource() {
            return orderSource;
        }

        public void setOrderSource(String orderSource) {
            this.orderSource = orderSource;
        }

        public String getOrderTarget() {
            return orderTarget;
        }

        public void setOrderTarget(String orderTarget) {
            this.orderTarget = orderTarget;
        }

    }

}
