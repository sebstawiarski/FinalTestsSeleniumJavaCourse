package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.tests.BaseTest;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {

        int randomNumber = (int) (Math.random()*100000);
        String email = "tester" + randomNumber + "@tester.pl";

        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        String lastName = "Stawiarski";

        driver.findElement(By.name("firstname")).sendKeys("Sebastian");
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("+48 333 444 555");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("Qwerty1!");
        driver.findElement(By.name("confirmpassword")).sendKeys("Qwerty1!");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));

        Assert.assertTrue(heading.getText().contains(lastName));
        Assert.assertEquals(heading.getText(),"Hi, Sebastian Stawiarski");
    }
}
