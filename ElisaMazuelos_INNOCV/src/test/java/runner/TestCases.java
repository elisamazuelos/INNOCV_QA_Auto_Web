package runner;

import common.Contact;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ContactPage;
import pages.MainPage;
import pages.TermsAndConditionsPage;

public class TestCases {

  private WebDriver driver;
  MainPage mainPage;
  ContactPage contactPage;
  TermsAndConditionsPage termsAndConditionsPage;

  @BeforeAll
  static void setUpClass() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  void setUpTest() {
    driver = new FirefoxDriver();
    WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    Contact contact = new Contact();
    mainPage = new MainPage(driver, driverWait);
    contactPage = new ContactPage(driver, driverWait, contact);
    termsAndConditionsPage = new TermsAndConditionsPage(driver, driverWait, contact);
  }

  @AfterEach
  void teardown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  @DisplayName("Test 1: phone number")
  void testCaseOne() {
    mainPage.goToInnoCVPage();
    mainPage.acceptCookies();
    mainPage.goToContactUs();
    contactPage.savePhoneNumber();
    driver.navigate().back();
    mainPage.goToTermsAndConditions();
    Assertions.assertTrue(termsAndConditionsPage.phoneNumberPresentInText());
  }

  @Test
  @DisplayName("Test 2: Faraday")
  void testCaseTwo() {
    mainPage.goToInnoCVPage();
    mainPage.acceptCookies();
    mainPage.goToContactUs();
    int numberOfOccurrences = contactPage.numberOfWordOccurrencesInPage("Faraday");
    System.out.println(numberOfOccurrences);
  }

  @Test
  @DisplayName("Test 3: campo requerido")
  void testCaseThree() {
    mainPage.goToInnoCVPage();
    mainPage.acceptCookies();
    mainPage.goToContactUs();
    contactPage.clickOnSendForm();
    Assertions.assertTrue(contactPage.requiredRedFieldPresent());
  }

  @Test
  @DisplayName("Test 4: sección de noticias")
  /* Nota: la sección de noticias aparece vacía, por lo que sólo he hecho scroll hasta la sección. */
  void testCaseFour() {
    mainPage.goToInnoCVPage();
    mainPage.acceptCookies();
    mainPage.goToContactUs();
    contactPage.goToNewsSection();
  }

}
