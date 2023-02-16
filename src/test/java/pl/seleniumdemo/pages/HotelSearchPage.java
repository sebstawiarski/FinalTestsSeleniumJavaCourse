package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelSearchPage {

    @FindBy(xpath = "//span[text()= 'Search by Hotel or City Name']")
    private WebElement searchHotelSpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement searchHotelInput;

    @FindBy(xpath = "//span[@class='select2-match' and text()='Dubai']")
    private WebElement hotelMatch;

    @FindBy(name = "checkin")
    private WebElement checkInInput;

    @FindBy(name = "checkout")
    private WebElement checkOutInput;

    @FindBy(id = "travellersInput")
    private WebElement travellersInput;
    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;

    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;

    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }





    public void setCity(String cityName) {
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        hotelMatch.click();
    }

    public void setDates(String checkin, String checkout) {
        checkInInput.sendKeys(checkin);
        checkOutInput.sendKeys(checkout);
    }

    public void setTravellers() {
        travellersInput.click();
        adultPlusBtn.click();
        childPlusBtn.click();
    }

    public void performSearch() {
        searchButton.click();
    }



     /*   driver.findElement(By.xpath("//span[text()= 'Search by Hotel or City Name']")).

    click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).

    sendKeys("Dubai");
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).

    click();
        driver.findElement(By.name("checkin")).

    sendKeys("20/02/2023");

         driver.findElement(By.id("travellersInput")).

    click();
        driver.findElement(By.id("adultPlusBtn")).

    click();
        driver.findElement(By.id("childPlusBtn")).

    click();
        driver.findElement(By.xpath("//button[text()=' Search']")).

    click();*/

}