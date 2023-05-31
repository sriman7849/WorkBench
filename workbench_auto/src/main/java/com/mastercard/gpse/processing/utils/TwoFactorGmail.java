package com.mastercard.gpse.processing.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TwoFactorGmail {

    public static void main(String[] args) throws InterruptedException {
        TwoFactorGmail twofa= new TwoFactorGmail();
        twofa.gmailSignIn();
    }

    public void gmailSignIn() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "c:\\driver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();


        driver.manage().window().maximize();

/*
        driver.get("https://www.gmail.com");
        Thread.sleep(5000);
        driver.findElement(By.id("identifierId")).sendKeys("kavyanjali.sweet@gmail.com");
        Thread.sleep(5000);
        driver.findElement(By.id("identifierNext")).click();
        Thread.sleep(5000);
        driver.findElement(By.name("password")).sendKeys("Prashika@x100");
        Thread.sleep(5000);
        driver.findElement(By.id("passwordNext")).click();
        Thread.sleep(5000);
// OTP value is returned from getTwoFactor method
        driver.findElement(By.id("totpPin")).sendKeys(TOTPGenerator.getTwoFactorCode());
        driver.findElement(By.id("totpNext")).click();
*/
        System.out.println(TOTPGenerator.getTwoFactorCode(""));

    }
}