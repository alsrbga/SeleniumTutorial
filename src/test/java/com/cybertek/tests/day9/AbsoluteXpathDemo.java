package com.cybertek.tests.day9;

import com.cybertek.utilities.BrowserFactory;
import com.cybertek.utilities.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AbsoluteXpathDemo {
    //We use only inside this class, that's why it's private
    private WebDriver driver;
    String fullNameLocator = "html/body/div/div[2]/div/div/form/div/div[1]/input[@name='full_name']";
    String emailLocator = "html/body/div/div[2]/div/div/form/div/div[1]/input[@name='email']";
    String signUpButton = "html/body/div/div[2]/div/div/form/button";

    @BeforeMethod
    public void setUp() {
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/sign_up");

    }

    @Test
    public void test1() {
        //Find input box to enter name
        driver.findElement(By.xpath(fullNameLocator)).sendKeys("Java GURU");
        //find input box to enter email
        driver.findElement(By.xpath(emailLocator)).sendKeys("tomsmith@email.com");
        SeleniumUtils.waitPlease(2);
        //find button to click sign up
        driver.findElement(By.xpath(signUpButton)).click();
        SeleniumUtils.waitPlease(2);
        //expected message
        String expectedMessage = "Internal Server Error";
        //actual will be coming from page
        String actualMessage = driver.findElement(By.xpath("/html/body/h1")).getText();
        /*
         * Verify that expected and actual message is the same
         * if no, it will stop test and throw exception
         * you will see in the console log what's exactly didn't match
         */
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void negativeEmailTest() {
        String expectingLink = driver.getCurrentUrl();
        driver.findElement(By.xpath(fullNameLocator)).sendKeys("Tom Smith");
        driver.findElement(By.xpath(emailLocator)).sendKeys("email");
        driver.findElement(By.xpath(signUpButton)).click();
        SeleniumUtils.waitPlease(2);
        String actualLink = driver.getCurrentUrl();
        Assert.assertEquals(actualLink, expectingLink);

    }


    @AfterMethod // it will run after every @test
    public void teardown() {
        driver.quit();

    }
}
