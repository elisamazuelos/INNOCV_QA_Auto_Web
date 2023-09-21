package pages;

import static common.Tools.javascriptClick;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

  private final WebDriver driver;
  private final WebDriverWait driverWait;

  public MainPage(WebDriver driver, WebDriverWait driverWait) {
    this.driver = driver;
    this.driverWait = driverWait;
  }

  public void goToInnoCVPage() {
    driver.get("https://www.innocv.com/");
    driver.manage().window().maximize();
    driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("que-hacemos")));
  }

  public void acceptCookies() {
    try {
      WebElement acceptCookiesButton = driver.findElement(By.id("rcc-confirm-button"));
      acceptCookiesButton.click();
    } catch (NoSuchElementException e) {
      System.out.println("Cookies were already accepted");
    }
  }

  public void goToContactUs() {
    WebElement contactUsButton = driver.findElement(By.xpath("//a[@href='/contacto']"));
    contactUsButton.click();
  }

  public void goToTermsAndConditions() {
    By termsAndConditionsXpath = By.xpath("//a[@href='aviso-legal']");
    driverWait.until(ExpectedConditions.elementToBeClickable(termsAndConditionsXpath));
    WebElement termsAndConditionsButton = driver.findElement(termsAndConditionsXpath);
    javascriptClick(driver, termsAndConditionsButton);
  }

}
