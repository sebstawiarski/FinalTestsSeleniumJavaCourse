package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;

import java.util.List;
import java.util.stream.Collectors;


public class HotelSearchTest extends BaseTest {

    @Test
    public void hotelSearchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("20/02/2023", "23/02/2023");
        hotelSearchPage.setTravellers();
        hotelSearchPage.performSearch();



        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b"))
                .stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());

        hotelNames.forEach(System.out::println);

        Assert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower",hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana",hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth",hotelNames.get(3));
    }
}
