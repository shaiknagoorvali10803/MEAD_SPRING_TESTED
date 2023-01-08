package com.spring.springselenium.StepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.springselenium.Configuraion.annotation.LazyAutowired;
import com.spring.springselenium.PageClass.Actions.Mead_Turnout_Home_PageActions;
import com.spring.springselenium.SeleniumUtils.SeleniumUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest
public class Mead_Turnout_Home_PageStepDefinitions {

  private static final Logger LOGGER = LoggerFactory.getLogger(Mead_Turnout_Home_PageStepDefinitions.class);
  private static Map<Integer, ScenarioContext> contextMap = new HashMap<>();

  @LazyAutowired
  private WebDriver driver;
  @LazyAutowired
  private Mead_Turnout_Home_PageActions pageActions;

  @LazyAutowired
  private SeleniumUtils utils;

  @Autowired
  ScenarioContext scenarioContext;

  @Value("${application_url}")
  private String url;

  @LazyAutowired
  private TakesScreenshot takescreenshot;

  @PostConstruct
  private void init() {
    contextMap.put(driver.hashCode(), scenarioContext);
  }

  @Given("I am on a {string} browser")
  public void determineBrowserType(final String browserType) {
    switch (browserType) {
      case "mobile":
        LOGGER.info("resizing window size for mobile");
        utils.resizeWindowForMobile(driver);
        break;
      case "tablet":
        LOGGER.info("resizing window size for tablet");
        utils.resizeWindowForTablet(driver);
        break;
      case "desktop":
        LOGGER.info("resizing window size for desktop");
      default:
        utils.maximizeWindow(driver);
        break;
    }
  }

  @When("I navigate to Mead Application")
  public void navigateToMeadWebsite() {
    driver.get(url);
    pageActions.addScreenShot();

  }

  @Then("I should be able to see the Title of the Page as {string}")
  public void check_title_of_meadpage(String expt_title) throws InterruptedException, IOException {
    String act_title = pageActions.mead_homepage_title();
    Assertions.assertEquals(expt_title, act_title);
  }

  @When("I navigate to Turnout Asset Page")
  public void navigate_to_TurnoutSearchpage() throws InterruptedException {
    pageActions.navigate_MOWPage();
    pageActions.addScreenShot();
  }

  @Then("I should be able to see the Subtitle of the page as {string}")
  public void check_title_of_turnoutsearchpage(String expt_title) throws InterruptedException {
    String act_title = pageActions.Turnout_Asset_PageTitle_Validation();
    Assertions.assertEquals(expt_title, act_title);
  }
}
