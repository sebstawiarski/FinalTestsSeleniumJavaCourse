import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.tests.BaseTest;

import java.util.List;
import java.util.stream.Collectors;

//hotel search without city name, and do the assertion for no reasults found

public class HomeWorkTest extends BaseTest {

    @Test
    public void searchHotelWithoutCityNameTest() {

        driver.findElement(By.name("checkin")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='17']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElements(By.xpath("//td[@class='day ' and text()='25']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();

        WebElement noResultFound = driver.findElement(By.xpath("//div[@class='itemscontainer']//h2"));


        Assert.assertTrue(noResultFound.isDisplayed());
        Assert.assertEquals(noResultFound.getText(), "No Results Found");
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
