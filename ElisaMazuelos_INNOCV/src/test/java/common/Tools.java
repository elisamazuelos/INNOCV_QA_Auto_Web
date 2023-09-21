package common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Tools {

  public static void javascriptClick(WebDriver driver, WebElement element) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
  }

  public static void scrollToElement(WebDriver driver, WebElement element) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
  }

}
