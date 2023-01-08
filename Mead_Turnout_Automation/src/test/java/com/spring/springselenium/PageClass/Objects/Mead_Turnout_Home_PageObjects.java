package com.spring.springselenium.PageClass.Objects;


import com.spring.springselenium.Configuraion.annotation.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@Page
public class Mead_Turnout_Home_PageObjects {

  @FindBy(how = How.ID, using = "input28")
  public WebElement username;
  @FindBy(how = How.XPATH, using = "//input[@class='button button-primary']")
  public WebElement next;
  @FindBy(how = How.XPATH, using = "//input[@class='password-with-toggle']")
  public WebElement password;
  @FindBy(how = How.XPATH, using = "//input[@class='button button-primary']")
  public WebElement verify;

  @FindBy(how = How.XPATH, using = "//div[@class='app-full-name']//span")
  public WebElement mead_page_title;
  @FindBy(how = How.XPATH, using = "//div[text()='Maintenance of Way']")
  public WebElement mow_btn_click;
  @FindBy(how = How.XPATH, using = "//span[normalize-space()='Asset']")
  public WebElement asset_btn_link;

  @FindBy(how = How.XPATH, using = "//span[normalize-space()='Turnout']")
  public WebElement turnout_btn_click;
  @FindBy(how = How.XPATH, using = "//div[contains(text(),'Turnout Search')]")
  public WebElement turnout_page_title;
  @FindBy(how = How.XPATH, using = "//div[@class='x-loader x-loader-train']")
  public WebElement loader;

}


