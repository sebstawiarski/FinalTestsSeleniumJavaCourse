package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

import java.util.List;
import java.util.stream.Collectors;


public class HotelSearchTest extends BaseTest {

    @Test
    public void hotelSearchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("20/02/2023", "23/02/2023");
        hotelSearchPage.setTravellers(1,2);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);

        List<String> hotelNames = resultsPage.getHotelNames();

        Assert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower",hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana",hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth",hotelNames.get(3));
    }
    @Test
    public void searchHotelWithoutCityNameTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDates("20/02/2023", "23/02/2023");
        hotelSearchPage.setTravellers(0,2);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);

        Assert.assertTrue(resultsPage.resultHeading.isDisplayed());
        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");
    }

    //blank registration and do the assertion
    @Test
    public void signUpWithoutInfoTest() {

        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        List<String> reqInfo = driver.findElements(By.xpath("//div[contains(@class,'alert alert-danger')]//p"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        reqInfo.forEach(System.out::println);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(reqInfo.contains("The Email field is required."));
        softAssert.assertTrue(reqInfo.contains("The Password field is required."));
        softAssert.assertTrue(reqInfo.contains("The Password field is required."));
        softAssert.assertTrue(reqInfo.contains("The First name field is required."));
        softAssert.assertTrue(reqInfo.contains("The Last Name field is required."));
    }

    //create account with wrong email
    @Test
    public void invalidEmailTest() {

        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();


        driver.findElement(By.name("firstname")).sendKeys("Sebastian");
        driver.findElement(By.name("lastname")).sendKeys("Stawiarski");
        driver.findElement(By.name("phone")).sendKeys("+48 333 444 555");
        driver.findElement(By.name("email")).sendKeys("tester.pl");
        driver.findElement(By.name("password")).sendKeys("Qwerty1!");
        driver.findElement(By.name("confirmpassword")).sendKeys("Qwerty1!");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        String invalidEmail = driver.findElement(By.xpath("//div[contains(@class,'alert alert-danger')]//p")).getText();

        Assert.assertTrue(invalidEmail.contains("The Email field must contain a valid email address."));
    }
}
