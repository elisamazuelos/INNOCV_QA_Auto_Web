package pages;

import static common.Tools.javascriptClick;
import static common.Tools.scrollToElement;

import common.Contact;
import java.util.Arrays;
import java.util.Collections;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactPage {

  private final WebDriver driver;
  private final WebDriverWait driverWait;
  Contact contact;

  public ContactPage(WebDriver driver, WebDriverWait driverWait, Contact contact) {
    this.driver = driver;
    this.driverWait = driverWait;
    this.contact = contact;
  }

  public void savePhoneNumber() {
    By phoneXpath = By.xpath("//*[contains(text(), '(+34)')]");
    driverWait.until(ExpectedConditions.visibilityOfElementLocated(phoneXpath));
    WebElement phone = driver.findElement(phoneXpath);
    String phoneNumber = phone.getText().replace("(+34) ", "");
    contact.setPhoneNumber(phoneNumber);
  }

  public int numberOfWordOccurrencesInPage(String word) {
    WebElement page = driver.findElement(By.id("root"));
    String textInPage = page.getText().replaceAll("\\p{Punct}", "");
    return Collections.frequency(Arrays.asList(textInPage.split(" ")), word);
  }

  public void clickOnSendForm() {
    By sendFormXpath = By.xpath("//*[text()='Enviar formulario']");
    driverWait.until(ExpectedConditions.elementToBeClickable(sendFormXpath));
    WebElement sendFormButton = driver.findElement(sendFormXpath);
    javascriptClick(driver, sendFormButton);
  }

  public Boolean requiredRedFieldPresent() {
    WebElement requiredFieldMessage;
    boolean present;
    try {
      requiredFieldMessage = driver.findElement(By.xpath("//*[text()='Campo requerido']"));
      String hexColor = Color.fromString(requiredFieldMessage.getCssValue("color")).asHex();
      present = hexColor.equals("#f44336");
    } catch (NoSuchElementException e) {
      present = false;
    }
    return present;
  }

  public void goToNewsSection() {
    WebElement newsSection = driver.findElement(By.xpath("//*[text()='noticias']"));
    scrollToElement(driver, newsSection);
  }

}
