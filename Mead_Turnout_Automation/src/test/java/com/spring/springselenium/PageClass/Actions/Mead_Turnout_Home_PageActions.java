package com.spring.springselenium.PageClass.Actions;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.spring.springselenium.Configuraion.annotation.LazyAutowired;
import com.spring.springselenium.Configuraion.annotation.Page;
import com.spring.springselenium.PageClass.Objects.Mead_Turnout_Home_PageObjects;
import com.spring.springselenium.SeleniumUtils.SeleniumUtils;
import com.spring.springselenium.StepDefinitions.ScenarioContext;

@Page
public class Mead_Turnout_Home_PageActions {
  private static final Logger LOGGER = LoggerFactory.getLogger(Mead_Turnout_Home_PageActions.class);
  private static Map<Integer, ScenarioContext> contextMap = new HashMap<>();

  @LazyAutowired
  private Mead_Turnout_Home_PageObjects pageObjects;

  @LazyAutowired
  private WebDriver driver;

  private WebDriverWait wait;
  @LazyAutowired
  private SeleniumUtils utils;

  @LazyAutowired
  private JavascriptExecutor executor;

  @Value("${app_username}")
  private String username;

  @Value("${app_password}")
  private String password;

  @Autowired
  ScenarioContext scenarioContext;

  @LazyAutowired
  private TakesScreenshot takescreenshot;

  @PostConstruct
  public void setup() {
    PageFactory.initElements(driver, pageObjects);
    contextMap.put(driver.hashCode(), scenarioContext);
    wait = new WebDriverWait(driver, Duration.ofSeconds(100));

  }
  public String mead_homepage_title() throws InterruptedException, IOException {
    wait.until(ExpectedConditions.visibilityOf(pageObjects.username));
    addScreenShot();
    utils.setValueToElement(driver, pageObjects.username, username);
    wait.until(ExpectedConditions.visibilityOf(pageObjects.next));
    utils.clickElementByWebElement(driver, pageObjects.next);
    utils.waitByTime(1000);
    try {
      WebElement error = driver.findElement(By.xpath("//div[contains(@class,'o-form-error-container')]"));
      if (error.isDisplayed()) {
        utils.waitByTime(2000);
        utils.clickElementByWebElement(driver, pageObjects.next);
      }
    } catch (Exception e) {
      System.out.println("no error displayed at login screen");
    }
    try {
      utils.waitByTime(2000);
      WebElement password_conformation = driver.findElement(By.xpath("//div[@data-se='okta_password']"));
      if (password_conformation.isDisplayed()) {
        utils.waitByTime(2000);
        utils.clickElementByWebElement(driver, password_conformation);
      }

    } catch (Exception e) {
      System.out.println("No password conformation option displayed on UI");
    }
    wait.until(ExpectedConditions.visibilityOf(pageObjects.password));
    addScreenShot();
    utils.setValueToElement(driver, pageObjects.password, password);
    wait.until(ExpectedConditions.visibilityOf(pageObjects.verify));
    utils.clickElementByWebElement(driver, pageObjects.verify);
    String act_title = null;
    try {
      act_title = utils.getValueByElement(driver, pageObjects.mead_page_title);
    } catch (Exception e) {
      act_title = utils.getValueByElement(driver, pageObjects.mead_page_title);
    }
    return act_title;
  }

  public void navigate_MOWPage() throws InterruptedException {
    utils.clickElementByWebElement(driver, pageObjects.mow_btn_click);
    utils.waitByTime(1000);
  }

  public String Turnout_Asset_PageTitle_Validation() {
    utils.waitTillLoadingCompletes(pageObjects.loader);
    utils.waitByTime(5000);
    addScreenShot();
    utils.mouseOverElement(pageObjects.asset_btn_link);
    wait.until(ExpectedConditions.visibilityOf(pageObjects.turnout_btn_click));
    utils.clickElementByWebElement(driver, pageObjects.turnout_btn_click);
    utils.waitUntilDomLoad();
    String act_title = utils.getValueByElement(driver, pageObjects.turnout_page_title);
    addScreenShot();
    return act_title;
  }

  public void addScreenShot() {
    if (!contextMap.get(driver.hashCode()).getScenario().isFailed()) {
      contextMap.get(driver.hashCode()).getScenario().attach(takescreenshot.getScreenshotAs(OutputType.BYTES), "image/png", "screenShot");
    }

  }

  public void addLog(String text) {
    if (!contextMap.get(driver.hashCode()).getScenario().isFailed()) {
      contextMap.get(driver.hashCode()).getScenario().log(text);
    }

  }

  public void addLog(List<String> values) {
    if (!contextMap.get(driver.hashCode()).getScenario().isFailed()) {
      contextMap.get(driver.hashCode()).getScenario().log(values.toString());
    }

  }
}
