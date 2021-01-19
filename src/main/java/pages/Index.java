package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Index {

    public static void clickOnHeaderByText(String text, WebDriverWait wait){

        String locator = String.format("//a[contains(text(),'%s')]",text);

        while (true) {

            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator))).click();
                break;
            } catch (Exception e){
                if (wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.xpath("//a[@data-direction='next']"))).
                        getAttribute("class").contains("hidden"))
                    break;
                else
                    wait.until(
                            ExpectedConditions.presenceOfElementLocated(
                                    By.xpath("//a[@data-direction='next']"))).click();
            }

        }

    }


    /**
     *
     * По сути, добавляем новые элементы из шапки сайта и щелкаем на стрелочку, пока она не пропадет.
     * Можно было бы начинать добавлять новые элементы с конца, чтобы не проверять уже добавленные элементы по несколько раз,
     * а заканчивать цикл при нахождении первого повтора. Но мне лень :)
     *
     * @param wait
     * @return
     */
    public static String[] getAllCategories(WebDriverWait wait){

        Set<String> set = new HashSet<>();

        while (true){
            wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.xpath("//a[@class='header-collection__item']"))).
                    forEach(el -> set.add(el.getAttribute("text")));

            if (wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//a[@data-direction='next']"))).
                    getAttribute("class").contains("hidden")) break;

            wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//a[@data-direction='next']"))).click();
        }

        return set.toArray(new String[0]);


    }


}
