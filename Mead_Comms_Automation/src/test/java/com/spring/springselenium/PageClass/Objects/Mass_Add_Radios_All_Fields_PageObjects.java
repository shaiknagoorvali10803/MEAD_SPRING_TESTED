package com.spring.springselenium.PageClass.Objects;

import java.util.List;

import com.spring.springselenium.Configuraion.annotation.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@Page
public class Mass_Add_Radios_All_Fields_PageObjects {
  @FindBy(how = How.XPATH, using = "//span[normalize-space()='Actions']")
  public WebElement action_btn;
  @FindBy(how = How.XPATH, using = "//span[normalize-space()='Mass Add']")
  public WebElement Mass_add_btn;
  @FindBy(how = How.XPATH, using = "//div[@class='app-page-name']")
  public WebElement Mass_add_page_title;
  @FindBy(how = How.XPATH,
      using = "(//label[contains(text(),'Number of Radios to be added:')]//parent::td//following-sibling::td//input[contains(@id,'j_idt')])[1]")
  public WebElement no_radio_txt;
  @FindBy(how = How.XPATH, using = "(//label[contains(text(),'Radio Type:')]//parent::td//following-sibling::td//label[contains(@id,'j_idt')])[1]")
  public WebElement radio_type_drp_dwn;
  @FindBy(how = How.XPATH, using = "(//label[contains(text(),'Status:')]//parent::td//following-sibling::td//div[contains(@id,'j_idt')])[1]")
  public WebElement status_drp_dwn;
  @FindBy(how = How.XPATH,
      using = "(//label[contains(text(),'Purchase Order Number:')]//parent::td//following-sibling::td//input[contains(@id,'j_idt')])[1]")
  public WebElement PO_number;
  @FindBy(how = How.XPATH,
      using = "(//label[contains(text(),'Inventory Status:')]//parent::td//following-sibling::td//div[contains(@id,'j_idt')])[1]")
  public WebElement invstatus_drp_dwn;
  @FindBy(how = How.XPATH,
      using = "(//label[contains(text(),'Shipment Tracking Number:')]//parent::td//following-sibling::td//input[contains(@id,'j_idt')])[1]")
  public WebElement ship_track_number;
  @FindBy(how = How.XPATH,
      using = "(//label[contains(text(),'Max Project ID:')]//parent::td//following-sibling::td//input[contains(@id,'j_idt')])[1]")
  public WebElement Max_prj_id;
  @FindBy(how = How.XPATH, using = "//div[@id='radioForm:tabview:manufacData']//span[@class='ui-icon ui-icon-triangle-1-s ui-c']")
  public WebElement manuf_drp_dwn;
  @FindBy(how = How.XPATH, using = "//div[@id='radioForm:tabview:modelData']//span[@class='ui-icon ui-icon-triangle-1-s ui-c']")
  public WebElement model_drp_dwn;
  @FindBy(how = How.XPATH, using = "(//label[contains(text(),'Receive Date:')]//parent::td//following-sibling::td//input[contains(@id,'j_idt')])[1]")
  public WebElement received_date;
  @FindBy(how = How.XPATH, using = "//input[@id='radioForm:tabview:firmwareVersion_input']")
  public WebElement firmware_ver;
  @FindBy(how = How.XPATH,
      using = "(//label[contains(text(),'Purchase Order Date:')]//parent::td//following-sibling::td//input[contains(@id,'j_idt')])[1]")
  public WebElement PO_date;
  @FindBy(how = How.XPATH,
      using = "(//label[contains(text(),'Assigned Department:')]//parent::td//following-sibling::td//div[contains(@id,'j_idt')])[1]")
  public WebElement assigndept_drp_dwn;
  @FindBy(how = How.XPATH, using = "(//label[contains(text(),'Ship Date:')]//parent::td//following-sibling::td//input[contains(@id,'j_idt')])[1]")
  public WebElement ship_date;
  @FindBy(how = How.XPATH, using = "//div[@id='radioForm:tabview:radioShopC']//span[@class='ui-icon ui-icon-triangle-1-s ui-c']")
  public WebElement radio_shop_drp_dwn;
  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Save')]//parent::button[contains(@id,'j_idt')]")
  public WebElement save_btn;
  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Cancel')]//parent::button[contains(@id,'j_idt')]")
  public WebElement cancel_btn;
  @FindBy(how = How.XPATH, using = "//div[@class='app-page-name']")
  public WebElement radio_page_title;
  @FindBy(how = How.XPATH, using = "(//div[@class='ui-radiobutton-box ui-widget ui-corner-all ui-state-default'])[2]")
  public WebElement nxn_id;
  @FindBy(how = How.XPATH, using = "//div[contains(text(),'Radio InlineEdit')]")
  public WebElement radio_inlineEdit;
  @FindBy(how = How.XPATH, using = "//thead[@id='radioInlineEditForm:radioDataTable_head']//span")
  public List<WebElement> inlineEdit_table_hdr;
  @FindBy(how = How.XPATH, using = "//div[@class='x-loader x-loader-train']")
  public WebElement loader;



  /* Inventory status field vlidation */

  @FindBy(how = How.XPATH, using = "//div[@id='radioForm:tabview:massAddInvStatus']//descendant::span")
  public WebElement invstatus_drp_dwn1;
  @FindBy(how = How.XPATH, using = "(//label[contains(text(),'Status:')]//parent::td//following-sibling::td//label[contains(@id,'j_idt')])[1]")
  public WebElement status_label;


  /* RACF Field Validation */

  @FindBy(how = How.XPATH, using = "//input[@id='radioForm:tabview:assignedTo']")
  public WebElement RACF_field;
  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Search Person Using Name/RACF')] ")
  public WebElement RACF_pop_title;
  @FindBy(how = How.XPATH, using = "//input[@id='radioForm:tabview:racf_input']")
  public WebElement RACF_window_search;
  @FindBy(how = How.XPATH, using = "//li[@class='ui-autocomplete-item ui-autocomplete-list-item ui-corner-all ui-state-highlight']")
  public WebElement RACF_search_autocomplete;
  @FindBy(how = How.XPATH, using = "//span[contains(text(),'OK')]")
  public WebElement RACF_window_search_okbtn;
  @FindBy(how = How.XPATH, using = "//span[text()='Search Person Using Name/RACF']//parent::div//following-sibling::a[@aria-label='Close']")
  public WebElement RACF_pop_cls_btn;



  /* Vehicle Field Validation */

  @FindBy(how = How.XPATH, using = "//li[@data-label='Active']")
  public WebElement active_status_sel;
  @FindBy(how = How.XPATH, using = "//li[@data-label='Mobile Radio']")
  public WebElement mobile_radio_sel;
  @FindBy(how = How.XPATH, using = "//label[normalize-space()='Assign To:']")
  public WebElement Assign_to;
  @FindBy(how = How.XPATH, using = "//label[normalize-space()='Vehicle']")
  public WebElement vechicle_field_label;
  @FindBy(how = How.XPATH, using = "//label[normalize-space()='Vehicle']//preceding-sibling::div//div[contains(@class,'ui-radiobutton-box')]")
  public WebElement vechicle_field_radio_btn;
  @FindBy(how = How.XPATH, using = "//div[@id='radioForm:tabview:assignToLabel']//label[contains(@id,'j_idt')]")
  public WebElement vechicle_input_label;
  @FindBy(how = How.XPATH, using = "//div[@id='radioForm:tabview:assignToValue']//input[contains(@id,'j_idt')]")
  public WebElement vechicle_input_txt_box;



  /* Missing mandatory fields Validation */
  @FindBy(how = How.XPATH, using = "(//label[contains(text(),'Radio Type:')]//parent::td//following-sibling::td//label[contains(@id,'j_idt')])[1]")
  public WebElement radiotype_label;
  @FindBy(how = How.XPATH, using = "(//span[@class='ui-radiobutton-icon ui-icon ui-icon-blank ui-c'])")
  public List<WebElement> NXNID_sel;
  @FindBy(how = How.XPATH,
      using = "(//label[contains(text(),'Inventory Status:')]//parent::td//following-sibling::td//div[contains(@id,'j_idt')])[1]")
  public WebElement invstatus_label1;
  @FindBy(how = How.XPATH, using = "//label[@id='radioForm:tabview:manufacData_label']")
  public WebElement manufacture_label;
  @FindBy(how = How.XPATH, using = "//label[@id='radioForm:tabview:modelData_label']")
  public WebElement model_label;


}
