package com.mastercard.gpse.processing.builders;

public class ClientBuilder {

    // Path/Query Parameters
    private String requestID;

    // Response fields
    private String clientID;

    // Request Body Fields
    private String clientType;
    private String dateExpiry;

    private String clientNumber;
    private ClientBaseAddressData clientBaseAddressData;
    private ClientCompanyData clientCompanyData;
    private ClientContactData clientContactData;
    private ClientIdentificationData clientIdentificationData;
    private ClientPersonalData clientPersonalData;
    private EmbossedData EmbossedData;
    private String originalMessage;

    public ClientBuilder() {
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getOriginalMessage() {
        return originalMessage;
    }

    public void setOriginalMessage(String originalMessage) {
        this.originalMessage = originalMessage;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getDateExpiry() {
        return dateExpiry;
    }

    public void setDateExpiry(String dateExpiry) {
        this.dateExpiry = dateExpiry;
    }

    public ClientBaseAddressData getClientBaseAddressData() {
        return clientBaseAddressData;
    }

    public void setClientBaseAddressData(ClientBaseAddressData clientBaseAddressData) {
        this.clientBaseAddressData = clientBaseAddressData;
    }

    public ClientCompanyData getClientCompanyData() {
        return clientCompanyData;
    }

    public void setClientCompanyData(ClientCompanyData clientCompanyData) {
        this.clientCompanyData = clientCompanyData;
    }

    public ClientContactData getClientContactData() {
        return clientContactData;
    }

    public void setClientContactData(ClientContactData clientContactData) {
        this.clientContactData = clientContactData;
    }

    public ClientIdentificationData getClientIdentificationData() {
        return clientIdentificationData;
    }

    public void setClientIdentificationData(ClientIdentificationData clientIdentificationData) {
        this.clientIdentificationData = clientIdentificationData;
    }

    public ClientPersonalData getClientPersonalData() {
        return clientPersonalData;
    }

    public void setClientPersonalData(ClientPersonalData clientPersonalData) {
        this.clientPersonalData = clientPersonalData;
    }

    public ClientBuilder.EmbossedData getEmbossedData() {
        return EmbossedData;
    }

    public void setEmbossedData(ClientBuilder.EmbossedData embossedData) {
        EmbossedData = embossedData;
    }

    public class ClientBaseAddressData {

        private String addressLine1;
        private String addressLine2;
        private String addressLine3;
        private String addressLine4;
        private String city;
        private String deliveryType;
        private String postalCode;
        private String state;
        private String url;

        public ClientBaseAddressData() {
        }

        public String getAddressLine1() {
            return addressLine1;
        }

        public void setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
        }

        public String getAddressLine2() {
            return addressLine2;
        }

        public void setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
        }

        public String getAddressLine3() {
            return addressLine3;
        }

        public void setAddressLine3(String addressLine3) {
            this.addressLine3 = addressLine3;
        }

        public String getAddressLine4() {
            return addressLine4;
        }

        public void setAddressLine4(String addressLine4) {
            this.addressLine4 = addressLine4;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDeliveryType() {
            return deliveryType;
        }

        public void setDeliveryType(String deliveryType) {
            this.deliveryType = deliveryType;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class ClientCompanyData {

        private String companyDepartment;
        private String companyName;
        private String companyTradeName;
        private String position;

        public ClientCompanyData() {
        }

        public String getCompanyDepartment() {
            return companyDepartment;
        }

        public void setCompanyDepartment(String companyDepartment) {
            this.companyDepartment = companyDepartment;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyTradeName() {
            return companyTradeName;
        }

        public void setCompanyTradeName(String companyTradeName) {
            this.companyTradeName = companyTradeName;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }
    }

    public class ClientContactData {
        private String email;

        public ClientContactData() {
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public class ClientIdentificationData {
        private String identificationDocumentDetails;
        private String identificationDocumentNumber;
        private String identificationDocumentType;
        private String socialNumber;
        private String taxPosition;
        private String taxpayerIdentifier;

        public ClientIdentificationData() {
        }

        public String getIdentificationDocumentDetails() {
            return identificationDocumentDetails;
        }

        public void setIdentificationDocumentDetails(String identificationDocumentDetails) {
            this.identificationDocumentDetails = identificationDocumentDetails;
        }

        public String getIdentificationDocumentNumber() {
            return identificationDocumentNumber;
        }

        public void setIdentificationDocumentNumber(String identificationDocumentNumber) {
            this.identificationDocumentNumber = identificationDocumentNumber;
        }

        public String getIdentificationDocumentType() {
            return identificationDocumentType;
        }

        public void setIdentificationDocumentType(String identificationDocumentType) {
            this.identificationDocumentType = identificationDocumentType;
        }

        public String getSocialNumber() {
            return socialNumber;
        }

        public void setSocialNumber(String socialNumber) {
            this.socialNumber = socialNumber;
        }

        public String getTaxPosition() {
            return taxPosition;
        }

        public void setTaxPosition(String taxPosition) {
            this.taxPosition = taxPosition;
        }

        public String getTaxpayerIdentifier() {
            return taxpayerIdentifier;
        }

        public void setTaxpayerIdentifier(String taxpayerIdentifier) {
            this.taxpayerIdentifier = taxpayerIdentifier;
        }
    }

    public class ClientPersonalData {
        private String birthDate;
        private String birthName;
        private String birthPlace;
        private String citizenship;
        private String countryCode;
        private String firstName;
        private String gender;
        private String lastName;
        private String middleName;
        private String secretPhrase;
        private String shortName;
        private String suffix;

        public ClientPersonalData() {
        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public String getBirthName() {
            return birthName;
        }

        public void setBirthName(String birthName) {
            this.birthName = birthName;
        }

        public String getBirthPlace() {
            return birthPlace;
        }

        public void setBirthPlace(String birthPlace) {
            this.birthPlace = birthPlace;
        }

        public String getCitizenship() {
            return citizenship;
        }

        public void setCitizenship(String citizenship) {
            this.citizenship = citizenship;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getSecretPhrase() {
            return secretPhrase;
        }

        public void setSecretPhrase(String secretPhrase) {
            this.secretPhrase = secretPhrase;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }
    }

    public class EmbossedData {
        private String embossedCompanyName;
        private String embossedFirstName;
        private String embossedLastName;

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
    }

}



