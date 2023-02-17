package pl.seleniumdemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private WebDriver driver;

    private static final Logger Logger = LogManager.getLogger();

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

    }


    public void setCity(String cityName) {
        Logger.info("Setting city: " + cityName);
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void setDates(String checkin, String checkout) {
        Logger.info("Setting dates check-in: " + checkin + " check-out: " + checkout);
        checkInInput.sendKeys(checkin);
        checkOutInput.sendKeys(checkout);
        Logger.info("Setting dates done");
    }

    public void setTravellers(int adultsToAdd, int childToAdd) {
        Logger.info("Adding adults: " + adultsToAdd + " and kids: " +childToAdd);
        travellersInput.click();
        addTraveller(adultPlusBtn, adultsToAdd);
        addTraveller(childPlusBtn, childToAdd);
        Logger.info("Adding travellers done");
    }

    private void addTraveller (WebElement travellerBtn, int numberOfTravellers) {
        for (int i = 0; i < numberOfTravellers; i++) {
            travellerBtn.click();
        }
    }
        public void performSearch() {
            Logger.info("Performing search");
            searchButton.click();
            Logger.info("Performing search done");
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

