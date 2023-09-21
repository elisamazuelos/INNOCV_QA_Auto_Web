package pages;

import common.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TermsAndConditionsPage {

  private final WebDriver driver;
  private final WebDriverWait driverWait;
  Contact contact;

  public TermsAndConditionsPage(WebDriver driver, WebDriverWait driverWait, Contact contact) {
    this.driver = driver;
    this.driverWait = driverWait;
    this.contact = contact;
  }

  public boolean phoneNumberPresentInText() {
    driverWait.until(
        ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Aviso Legal']")));
    WebElement content = driver.findElement(By.id("root"));
    String savedPhoneNumber = contact.getPhoneNumber();
    return content.getText().contains(savedPhoneNumber);
  }

}
