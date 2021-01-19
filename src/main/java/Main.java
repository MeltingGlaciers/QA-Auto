import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.assertj.core.*;
import pages.Category;
import pages.Index;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class Main {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.get("https://www.mvideo.ru");


        String[] arr = Index.getAllCategories(wait);
        driver.navigate().refresh();

        for (String el : arr){
            Index.clickOnHeaderByText(el,wait);
            if (Category.isCategory(wait)) {
                assertThat(Category.getName(wait)).contains(el);
                assertThat(Category.getGoodsCount(wait)).isNotEqualTo(0);
            }
            driver.navigate().back();
        }






    }

}
