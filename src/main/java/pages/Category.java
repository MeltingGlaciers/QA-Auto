package pages;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Category {



    public static String getName(WebDriverWait wait){

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//h1[@class='title']"))).getText();
    }

    public static int getGoodsCount(WebDriverWait wait){

        return Integer.parseInt(
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//span[@class='count ng-star-inserted']"))).getText());

    }

    public static boolean isCategory(WebDriverWait wait){

        try{
            wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.xpath("//h1[@class='title']")));
            return true;
        }
        catch (TimeoutException te){
            return false;
        }

    }


}
