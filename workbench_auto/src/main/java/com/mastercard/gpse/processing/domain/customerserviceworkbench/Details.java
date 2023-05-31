package com.mastercard.gpse.processing.domain.customerserviceworkbench;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Map;

public class Details {
    private static final Logger logger = (Logger) LogManager.getLogger(Details.class);

    private String branch;
    private String institution;
    private String clientType;
    private String serviceGroup;
    private String riskSegment;
    private String migrationFlag;
    private String fullName;
    private String shortName;
    private String salutationSuffix;
    private String gender;
    private String firstName;
    private String middleName;
    private String lastName;
    private String birthDate;
    private String birthPlace;
    private String birthName;
    private String language;
    private String citizenship;
    private String maritalStatus;
    private String taxBracket;
    private String taxpayerID;
    private String dateExpire;
    private String numberOfDependentPersons;
    private String loyaltyID;
    private String nationality;
    private String homePhone;
    private String mobilePhone;
    private String homeFax;
    private String eMail;
    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String companyName;
    private String trademark;
    private String department;
    private String Position;
    private String businessPhone;
    private String Title;
    private String embosslastName;
    private String embossfirstName;
    private String embossCompanyName;
    private String identityCardType;
    private String identityCardNumber;
    private String clientNumber;
    private String secretPhrase;
    private String socialSecurityNumber;
    private String registrationDate;
    private String lastActionStatus;
    private String lastActionDate;
    private String Ready;

    public Details getDetails(Map<String, String> map){
        Details details = new Details();
        details.setInstitution(map.get("Institution"));
        details.setClientType(map.get("ClientType"));
        details.setShortName(map.get("ShortName"));
        details.setFullName(map.get("FullName"));
        details.setFirstName(map.get("FirstName"));
        details.setLastName(map.get("LastName"));
        details.setMiddleName(map.get("MiddleName"));
        details.setGender(map.get("Gender"));
        details.setBirthDate(map.get("BirthDate"));
        details.setBirthPlace(map.get("BirthPlace"));
        details.setBirthName(map.get("BirthName"));
        details.setCitizenship(map.get("Citizenship"));
        details.setTaxBracket(map.get("TaxBracket"));
        details.setTaxpayerID(map.get("TaxpayerId"));
        details.setDateExpire(map.get("DateExpire"));
        details.setHomePhone(map.get("HomePhone"));
        details.setHomeFax(map.get("HomeFax"));
        details.setMobilePhone(map.get("MobilePhone"));
        details.seteMail(map.get("Email"));
        details.setCountry(map.get("Country"));
        details.setState(map.get("State"));
        details.setCity(map.get("City"));
        details.setZipCode(map.get("ZipCode"));
        details.setAddressLine1(map.get("AddressLine1"));
        details.setCompanyName(map.get("CompanyName"));
        details.setDepartment(map.get("Department"));
        details.setTrademark(map.get("Trademark"));
        details.setPosition(map.get("Position"));
        details.setBusinessPhone(map.get("BusinessPhone"));
        details.setEmbossCompanyName(map.get("EmbossingCompanyName"));
        details.setEmbossfirstName(map.get("EmbossingFirstName"));
        details.setEmbosslastName(map.get("EmbossingLastName"));
        details.setIdentityCardNumber(map.get("IdentityCardNumber"));
        details.setIdentityCardType(map.get("IdentityCardType"));
        details.setClientNumber(map.get("ClientNumber"));
        details.setSecretPhrase(map.get("SecretPhrase"));
        details.setSocialSecurityNumber(map.get("SocialSecurityNumber"));
        details.setRegistrationDate(map.get("RegistrationDate"));
        details.setReady(map.get("Ready"));
        details.setLastActionStatus(map.get("LastActionStatus"));
        return details;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getServiceGroup() {
        return serviceGroup;
    }

    public void setServiceGroup(String serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    public String getRiskSegment() {
        return riskSegment;
    }

    public void setRiskSegment(String riskSegment) {
        this.riskSegment = riskSegment;
    }

    public String getMigrationFlag() {
        return migrationFlag;
    }

    public void setMigrationFlag(String migrationFlag) {
        this.migrationFlag = migrationFlag;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSalutationSuffix() {
        return salutationSuffix;
    }

    public void setSalutationSuffix(String salutationSuffix) {
        this.salutationSuffix = salutationSuffix;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getBirthName() {
        return birthName;
    }

    public void setBirthName(String birthName) {
        this.birthName = birthName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getTaxBracket() {
        return taxBracket;
    }

    public void setTaxBracket(String taxBracket) {
        this.taxBracket = taxBracket;
    }

    public String getTaxpayerID() {
        return taxpayerID;
    }

    public void setTaxpayerID(String taxpayerID) {
        this.taxpayerID = taxpayerID;
    }

    public String getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(String dateExpire) {
        this.dateExpire = dateExpire;
    }

    public String getNumberOfDependentPersons() {
        return numberOfDependentPersons;
    }

    public void setNumberOfDependentPersons(String numberOfDependentPersons) {
        this.numberOfDependentPersons = numberOfDependentPersons;
    }

    public String getLoyaltyID() {
        return loyaltyID;
    }

    public void setLoyaltyID(String loyaltyID) {
        this.loyaltyID = loyaltyID;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getHomeFax() {
        return homeFax;
    }

    public void setHomeFax(String homeFax) {
        this.homeFax = homeFax;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getEmbosslastName() {
        return embosslastName;
    }

    public void setEmbosslastName(String embosslastName) {
        this.embosslastName = embosslastName;
    }

    public String getEmbossfirstName() {
        return embossfirstName;
    }

    public void setEmbossfirstName(String embossfirstName) {
        this.embossfirstName = embossfirstName;
    }

    public String getEmbossCompanyName() {
        return embossCompanyName;
    }

    public void setEmbossCompanyName(String embossCompanyName) {
        this.embossCompanyName = embossCompanyName;
    }

    public String getIdentityCardType() {
        return identityCardType;
    }

    public void setIdentityCardType(String identityCardType) {
        this.identityCardType = identityCardType;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getSecretPhrase() {
        return secretPhrase;
    }

    public void setSecretPhrase(String secretPhrase) {
        this.secretPhrase = secretPhrase;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getLastActionStatus() {
        return lastActionStatus;
    }

    public void setLastActionStatus(String lastActionStatus) {
        this.lastActionStatus = lastActionStatus;
    }

    public String getLastActionDate() {
        return lastActionDate;
    }

    public void setLastActionDate(String lastActionDate) {
        this.lastActionDate = lastActionDate;
    }

    public String getReady() {
        return Ready;
    }

    public void setReady(String ready) {
        Ready = ready;
    }
}
