package com.mastercard.gpse.processing.utils;

import org.jboss.aerogear.security.otp.Totp;

public class TOTPGenerator {

    /**
     * Method is used to get the TOTP based on the security token
     *
     * @return OTP
     */
    public static String getTwoFactorCode(String secretKey) {
        //Replace with your security key copied from step 12
        Totp totp = new Totp(secretKey); // 2FA secret key
        String twoFactorCode = totp.now(); //Generated 2FA code here
        return twoFactorCode;
    }
//
//    public static String getTwoFactorCode(String roleKeyStr){
//        String twoFactorCode;
//        try {
//            Totp totp = new Totp(roleKeyStr);
//            twoFactorCode = totp.now();
//            logger.info("TwoFactorCode: "+twoFactorCode);
//            return twoFactorCode;
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            return null;
//        }
//    }
}
