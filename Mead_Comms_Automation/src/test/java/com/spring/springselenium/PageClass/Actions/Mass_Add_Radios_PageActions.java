package com.spring.springselenium.PageClass.Actions;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import javax.annotation.PostConstruct;

import com.spring.springselenium.Configuraion.annotation.LazyAutowired;
import com.spring.springselenium.Configuraion.annotation.Page;
import com.spring.springselenium.DatabaseUtils.DataBaseUtils;
import com.spring.springselenium.PageClass.Objects.Mass_Add_Radios_All_Fields_PageObjects;

import com.spring.springselenium.SeleniumUtils.SeleniumUtils;
import com.spring.springselenium.StepDefinitions.ScenarioContext;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Page
public class Mass_Add_Radios_PageActions {
  @LazyAutowired
  private Mass_Add_Radios_All_Fields_PageObjects pageObjects;
  private static Map<Integer, ScenarioContext> contextMap = new HashMap<>();
  @LazyAutowired
  private WebDriver driver;
  private WebDriverWait wait;
  @LazyAutowired
  private SeleniumUtils utils;
  @Autowired
  private DataBaseUtils dataBaseUtils;
  @Autowired
  ScenarioContext scenarioContext;
  @LazyAutowired
  private TakesScreenshot takescreenshot;
  @LazyAutowired
  private JavascriptExecutor executor;
  Set<String> All_radioAdd_expt_Values;
  Set<String> mandatory_radioAdd_expt_Values;

  static String ponumber_created;
  public static final int DRIVER_WAIT_TIME_IN_SECS = 10;
  public static final int thread_sleep = 300;
  static String racf_id;
  static String vehicle_Number;
  static String radio_type;
  static String radio_nos;
  static String dbUrl;
  static String username;
  static String password;
  static String ponumber;
  static String shiptrachno;
  static String maxprojectid;
  static String firmwareversion;
  static String podate;
  static String shipdate;
  static String recdate;

  @PostConstruct
  public void init() {
    PageFactory.initElements(driver, pageObjects);
    contextMap.put(driver.hashCode(), scenarioContext);
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
    wait = new WebDriverWait(driver, Duration.ofSeconds(180));
      }

  public void clickon_Mass_Add_radios_selection() throws InterruptedException {
    clickElementByWebElement(driver, pageObjects.action_btn);
    clickElementByWebElement(driver, pageObjects.Mass_add_btn);
  }

  public String check_page_title() {
    String act_title = utils.getValueByElement(driver, pageObjects.Mass_add_page_title);
    return act_title;
  }

  public Set<String> enter_allfields(String radiotype, String numberofradios, String racfid, String vehicleno) throws InterruptedException {
    ponumber = "PO";
    shiptrachno = "ST";
    maxprojectid = "PI";
    firmwareversion = "FW";
    radio_type = radiotype;
    radio_nos = numberofradios;
    final Date now = new Date();
    podate = new SimpleDateFormat("MM/dd/yyyy").format(now);
    shipdate = new SimpleDateFormat("MM/dd/yyyy").format(now);
    recdate = new SimpleDateFormat("MM/dd/yyyy").format(now);
    final String dateAndTimeString = new SimpleDateFormat("yyhhmmss").format(now);
    final String po_number_ = new SimpleDateFormat("yyyyddhhmmss").format(now);
    enter_no_radios(numberofradios);
    String select_RadioType = Select_RadioType(radiotype);
    if (select_RadioType.equals("Portable Radio")) {
      verify_RACFfield();
      clickon_RACFfield();
      verify_RACFpopup(racfid);
    } else if (select_RadioType.equals("Mobile Radio")) {
      clickon_vehicle_radiobtn();
      inputbyJsExecutor(driver, pageObjects.vechicle_input_txt_box, vehicleno);
    }
    String status = select_status();
    select_nxnid_no();
    enter_PONUmber(ponumber, po_number_);
    enter_PONUmber(ponumber, po_number_);
    String select_inven_status = select_inven_status();
    enter_ship_trakno(shiptrachno, dateAndTimeString);
    enter_maxprjId(maxprojectid, dateAndTimeString);
    String select_manufacture = select_manufacture();
    String select_model = select_model();
    enter_recDate(recdate);
    enter_firmware(firmwareversion, dateAndTimeString);
    enter_PoDate(podate);
    select_department();
    enter_shipDate(shipdate);
    select_RadioShop();
    ponumber_created = ponumber + po_number_;
    All_radioAdd_expt_Values = new HashSet<>(Arrays.asList(numberofradios, select_manufacture, status, select_inven_status, select_RadioType,
        select_model, ponumber + po_number_, podate));
    return All_radioAdd_expt_Values;
  }

  public void save_functionality() throws InterruptedException {
    Thread.sleep(1500);
    clickElementByWebElement(driver, pageObjects.save_btn);
  }

  public String inlineEdit_page_Validation() throws InterruptedException {
    String act_title = null;
    Thread.sleep(2000);
    try {
      act_title = utils.getValueByElement(driver, pageObjects.radio_inlineEdit);
    } catch (Exception e) {
      act_title = utils.getValueByElement(driver, pageObjects.radio_inlineEdit);
    }

    return act_title;
  }

  /* Radio Mass Add Inventory status field validation */

  public void select_status_fields(String status) {
    select_status(status);
  }

  public List<String> validateStatus() {
    List<String> drp_dwn_values = new ArrayList<>();
    String attribute = null;
    attribute = driver.findElement(By.xpath("//div[@id='radioForm:tabview:massAddInvStatus']//div")).getAttribute("id");
    clickElementByWebElement(driver, pageObjects.invstatus_drp_dwn1);
    for (WebElement webElement : driver
        .findElements(By.xpath("//li[contains(@id,'" + attribute + "') and not (@data-label='&nbsp;')]"))) {
      drp_dwn_values.add(webElement.getText());
    }
    utils.changefocus();;
    return drp_dwn_values;
  }


  /* Radio Mass Add Cancel field Validation */
  public Set<String> enter_mandatory_fields(String radiotype, String numberofradios, String racfid, String vehicleno) throws InterruptedException {
    ponumber = "PO";
    radio_type = radiotype;
    radio_nos = numberofradios;
    final Date now = new Date();
    podate = new SimpleDateFormat("MM/dd/yyyy").format(now);
    recdate = new SimpleDateFormat("MM/dd/yyyy").format(now);
    final String po_number_ = new SimpleDateFormat("yyyyddhhmmss").format(now);
    enter_no_radios(numberofradios);
    String select_RadioType = Select_RadioType(radiotype);
    if (select_RadioType.equals("Portable Radio")) {
      verify_RACFfield();
      clickon_RACFfield();
      verify_RACFpopup(racfid);
    } else if (select_RadioType.equals("Mobile Radio")) {
      clickon_vehicle_radiobtn();
      inputbyJsExecutor(driver, pageObjects.vechicle_input_txt_box, vehicleno);
    }
    String status = select_status();
    select_nxnid_no();
    enter_PONUmber(ponumber, po_number_);
    String select_inven_status = select_inven_status();
    String select_manufacture = select_manufacture();
    String select_model = select_model();
    enter_recDate(recdate);
    enter_PoDate(podate);
    ponumber_created = ponumber + po_number_;

    mandatory_radioAdd_expt_Values = new HashSet<>(Arrays.asList(numberofradios, select_manufacture, status, select_inven_status, select_RadioType,
        select_model, ponumber + po_number_, podate));
    return mandatory_radioAdd_expt_Values;

  }

  public void pressCancelBtn() {
    clickElementByWebElement(driver, pageObjects.cancel_btn);
  }

  public String navigate_RadioSearch() {
    String act_title = utils.getValueByElement(driver, pageObjects.radio_page_title);
    return act_title;
  }

  /* Radio Mass Add RACF field Validation */
  public void select_portableradio(String radiotype) throws InterruptedException {
    radio_type = radiotype;
    radio_nos = "5";
    Select_RadioType(radiotype);
    contextMap.get(driver.hashCode()).getScenario().log(radiotype + ": is selected from the radiotype dropdown");
  }

  public boolean verify_RACFfield() {
    boolean displayed = false;
    try {
      displayed = pageObjects.RACF_field.isDisplayed();
      contextMap.get(driver.hashCode()).getScenario().log("Assign_RACF id field is displayed");
    } catch (Exception e) {
      contextMap.get(driver.hashCode()).getScenario().log("Assign_RACF id field is not displayed");
    }
    return displayed;
  }

  public void clickon_RACFfield() {
    clickElementByWebElement(driver, pageObjects.RACF_field);
  }

  public String verify_RACFpopup(String racfid) throws InterruptedException {
    String act_title = utils.getValueByElement(driver, pageObjects.RACF_pop_title);
    Thread.sleep(1500);
    utils.setValueToElement(driver, pageObjects.RACF_window_search, racfid);
    try {
      wait.until(ExpectedConditions.visibilityOf(pageObjects.RACF_search_autocomplete));
      clickElementByWebElement(driver, pageObjects.RACF_search_autocomplete);
    } catch (TimeoutException e) {
      driver.navigate().refresh();
      enter_no_radios(radio_nos);
      Select_RadioType(radio_type);
      clickon_RACFfield();
      utils.setValueToElement(driver, pageObjects.RACF_window_search, racfid);
      clickElementByWebElement(driver, pageObjects.RACF_search_autocomplete);
    }

    Thread.sleep(1000);
    clickElementByWebElement(driver, pageObjects.RACF_window_search_okbtn);
    return act_title;
  }


  /* Radio Mass Add Vehicle field Validation */
  public void select_mobileradio(String radiotype) throws InterruptedException {
    Select_RadioType(radiotype);
    contextMap.get(driver.hashCode()).getScenario().log(radiotype + ": is selected from the radiotype dropdown");
    verify_AssignTo_Field();
  }

  public boolean verify_AssignTo_Field() {
    boolean displayed = false;
    try {
      displayed = pageObjects.Assign_to.isDisplayed();
      contextMap.get(driver.hashCode()).getScenario().log("Assigned to Vehicle field is displayed");
    } catch (Exception e) {
      contextMap.get(driver.hashCode()).getScenario().log("Assigned to Vehicle field is not displayed");
    }
    return displayed;
  }



  public void clickon_vehicle_radiobtn() {
    clickElementByWebElement(driver, pageObjects.vechicle_field_radio_btn);
  }

  public boolean verify_vehicle_txtbox(String vehicleno) {
    boolean displayed = false;
    try {
      displayed = pageObjects.vechicle_input_txt_box.isDisplayed();
      System.out.println("Vehicle input textbox is displayed on UI");
      inputbyJsExecutor(driver, pageObjects.vechicle_input_txt_box, vehicleno);
    } catch (Exception e) {
      System.out.println("Vehicle inpt textbox is not displayed on UI");
    }
    return displayed;

  }


  /* Radio Mass Add Fields Validation */

  public boolean validates_text_fields() {
    boolean txt_filed_check = false;
    Assert.assertTrue(element_clickable(driver, pageObjects.no_radio_txt));
    Assert.assertTrue(element_clickable(driver, pageObjects.PO_number));
    Assert.assertTrue(element_clickable(driver, pageObjects.ship_track_number));
    Assert.assertTrue(element_clickable(driver, pageObjects.Max_prj_id));
    txt_filed_check = true;
    return txt_filed_check;
  }


  public boolean validates_drpdwn_fields() {
    boolean drpdwn_filed_check = false;
    Assert.assertTrue(element_clickable(driver, pageObjects.model_drp_dwn));
    utils.changefocus();;
    Assert.assertTrue(element_clickable(driver, pageObjects.radio_type_drp_dwn));
    utils.changefocus();;
    Assert.assertTrue(element_clickable(driver, pageObjects.status_drp_dwn));
    utils.changefocus();;
    Assert.assertTrue(element_clickable(driver, pageObjects.invstatus_drp_dwn));
    utils.changefocus();;
    Assert.assertTrue(element_clickable(driver, pageObjects.manuf_drp_dwn));
    utils.changefocus();;
    Assert.assertTrue(element_clickable(driver, pageObjects.assigndept_drp_dwn));
    utils.changefocus();;
    Assert.assertTrue(element_clickable(driver, pageObjects.radio_shop_drp_dwn));
    utils.changefocus();;
    drpdwn_filed_check = true;
    return drpdwn_filed_check;
  }

  public boolean validates_date_fields() {
    boolean date_filed_check = false;

    Assert.assertTrue(element_clickable(driver, pageObjects.ship_date));
    utils.changefocus();;
    Assert.assertTrue(element_clickable(driver, pageObjects.received_date));
    utils.changefocus();;
    Assert.assertTrue(element_clickable(driver, pageObjects.PO_date));
    utils.changefocus();;
    date_filed_check = true;
    return date_filed_check;
  }


  /* Radio Mass Add missing Mandatory Fields */

  public void enter_mandatory_fields(String MissingField) throws InterruptedException {
    ponumber = "PO";
    final Date now = new Date();
    final String dateAndTimeString = new SimpleDateFormat("yyyyhhmmss").format(now);
    podate = new SimpleDateFormat("MM/dd/yyyy").format(now);
    shipdate = new SimpleDateFormat("MM/dd/yyyy").format(now);
    recdate = new SimpleDateFormat("MM/dd/yyyy").format(now);

    if (!MissingField.equals("NoOfRadios")) {
      enter_no_radios("5");
    }
    if (!MissingField.equals("RadioType")) {
      Select_RadioType("Base Radio");
    }

    if (MissingField.equals("Status")) {
      String attribute = pageObjects.status_drp_dwn.getAttribute("id");
      clickElementByWebElement(driver, pageObjects.status_drp_dwn);
      final String status_type = "//li[contains(@id,'" + attribute + "') and @data-label='Select One']";
      clickElementByXPath(driver, status_type);
      utils.changefocus();;
    } else {
      select_status();
    }
    if (!MissingField.equals("NXNID")) {
      select_nxnid_no();
    }
    if (!MissingField.equals("PONumber")) {
      enter_PONUmber(ponumber, dateAndTimeString);
    }
    if (!MissingField.equals("InvenToryStatus")) {
      select_inven_status();
    }
    if (!MissingField.equals("Manufacturer")) {
      select_manufacture();
    }

    if (!MissingField.equals("Model")) {
      select_model();
    }

    if (!MissingField.equals("ReceivedDate")) {
      enter_recDate(recdate);

    }
    if (!MissingField.equals("PODate")) {
      enter_PoDate(podate);
    }

  }

  public String check_error_msg() throws InterruptedException {
    clickElementByWebElement(driver, pageObjects.save_btn);
    Thread.sleep(2000);
    boolean no_radio_sel = pageObjects.no_radio_txt.getAttribute("value").equals("");
    boolean no_status_sel = pageObjects.status_label.getText().equals("Select One");
    boolean no_radiotype_sel = pageObjects.radiotype_label.getText().equals("Select One");
    boolean no_NXNID_sel = pageObjects.NXNID_sel.size() >= 2;
    boolean no_PONo_sel = pageObjects.PO_number.getAttribute("value").equals("");
    boolean no_manufacture_sel = pageObjects.manufacture_label.getText().equals("Select One");
    boolean no_model_sel = pageObjects.model_label.getText().equals("Select One");
    boolean no_recDate_sel = pageObjects.received_date.getAttribute("value").equals("");
    boolean no_PODate_sel = pageObjects.PO_date.getAttribute("value").equals("");

    try {
      boolean no_invstatus_sel = pageObjects.invstatus_label1.getText().equals(" ");
      if (no_invstatus_sel) {
        contextMap.get(driver.hashCode()).getScenario().log("Inventory status field not selected");
      }
    } catch (Exception e) {
      boolean no_invstatus_sel = pageObjects.invstatus_label1.getText().equals(" ");
      if (no_invstatus_sel) {
        contextMap.get(driver.hashCode()).getScenario().log("Inventory status field not selected");
      }
    }
    if (no_radio_sel) {
      contextMap.get(driver.hashCode()).getScenario().log("No of Radios Field is empty");
    }
    if (no_status_sel) {
      contextMap.get(driver.hashCode()).getScenario().log("No status type was selected");
    }
    if (no_radiotype_sel) {
      contextMap.get(driver.hashCode()).getScenario().log("No Radio type was selected");
    }
    if (no_NXNID_sel) {
      contextMap.get(driver.hashCode()).getScenario().log("NXN ID field is not selected");
    }
    if (no_PONo_sel) {
      contextMap.get(driver.hashCode()).getScenario().log("PO Number not selected");
    }
    if (no_manufacture_sel) {
      contextMap.get(driver.hashCode()).getScenario().log("Manufacture not selected");
    }
    if (no_model_sel) {
      contextMap.get(driver.hashCode()).getScenario().log("Model not selected");
    }
    if (no_recDate_sel) {
      contextMap.get(driver.hashCode()).getScenario().log("Received date not selected");
    }
    if (no_PODate_sel) {
      contextMap.get(driver.hashCode()).getScenario().log("PO date is not selected");
    }
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='ui-messages-error-summary']"))));
    WebElement error_msg = driver.findElement(By.xpath("//span[@class='ui-messages-error-summary']"));
    return error_msg.getText();
  }


  public Set<String> inlineEdit_column_values() {
    Set<String> table_val = new HashSet<>();
    List<String> hdr_names = new ArrayList<>();
    for (WebElement element : pageObjects.inlineEdit_table_hdr) {
      hdr_names.add(element.getText());
    }

    for (String hdr_name : hdr_names) {
      if (hdr_name.equals("Radio Number")) {
        List<WebElement> findElements = driver
            .findElements(By.xpath("//tbody[@id='radioInlineEditForm:radioDataTable_data']//td[" + (hdr_names.indexOf(hdr_name) + 1) + "]"));
        table_val.add(String.valueOf(findElements.size()));
      }
      if (hdr_name.equals("Manufacturer") || hdr_name.equals("Status") || hdr_name.equals("Inventory Status")) {
        List<WebElement> findElements = driver
            .findElements(By.xpath("//tbody[@id='radioInlineEditForm:radioDataTable_data']//td[" + (hdr_names.indexOf(hdr_name) + 1) + "]"));
        for (WebElement webElement : findElements) {
          table_val.add(webElement.getText());
        }
      }

      if (hdr_name.equals("Radio Type") || hdr_name.equals("Model") || hdr_name.equals("PO Number") || hdr_name.equals("PO Date")) {
        List<WebElement> findElements =
            driver.findElements(By.xpath("//tbody[@id='radioInlineEditForm:radioDataTable_data']//td["
                + (hdr_names.indexOf(hdr_name) + 1) + "]//div[@class='ui-cell-editor-output']"));
        for (WebElement webElement : findElements) {
          table_val.add(webElement.getText());
        }
      }

    }

    return table_val;
  }

  public Set<String> inlineEdit_col_DBValidation() {
    Set<String> table_val = new HashSet<>();
    List<String> hdr_names = new ArrayList<>();
    for (WebElement element : pageObjects.inlineEdit_table_hdr) {
      hdr_names.add(element.getText());
    }
    for (String hdr_name : hdr_names) {
      if (hdr_name.equals("Radio Number")) {
        List<WebElement> findElements1 = driver
            .findElements(By.xpath("//tbody[@id='radioInlineEditForm:radioDataTable_data']//td[" + (hdr_names.indexOf(hdr_name) + 1) + "]"));
        for (WebElement webElement : findElements1) {
          table_val.add(webElement.getText());
        }
      }
      if (hdr_name.equals("Manufacturer") || hdr_name.equals("Status") || hdr_name.equals("Inventory Status")) {
        List<WebElement> findElements3 = driver
            .findElements(By.xpath("//tbody[@id='radioInlineEditForm:radioDataTable_data']//td[" + (hdr_names.indexOf(hdr_name) + 1) + "]"));
        for (WebElement webElement : findElements3) {
          table_val.add(webElement.getText());
        }
      }
      if (hdr_name.equals("Radio Type") || hdr_name.equals("Model") || hdr_name.equals("PO Number")) {
        List<WebElement> findElements2 =
            driver.findElements(By.xpath("//tbody[@id='radioInlineEditForm:radioDataTable_data']//td["
                + (hdr_names.indexOf(hdr_name) + 1) + "]//div[@class='ui-cell-editor-output']"));
        for (WebElement webElement : findElements2) {
          table_val.add(webElement.getText());
        }
      }

    }
    return table_val;
  }
  public Set<String> fetcht_DB_createdRadios() throws IOException {
  String sql =
        "SELECT HISTORY_KEY_I,TYPE_X ,MANUFACTURER_X,MODEL_X ,PURCHASE_ORDER_I ,ASSET_STATUS_STATE_X,INVENTORY_STATUS_X FROM LINES_OWN.V_RADIO WHERE PURCHASE_ORDER_I ='"
            + ponumber_created + "'";
   List<Map<String, Object>> oracleConnectionRowRetrieve = dataBaseUtils.FetchDatabaseRecords(sql);
    Set<String> dBValues = new HashSet<>();
    for (Map<String, Object> map : oracleConnectionRowRetrieve) {
      Iterator<Object> iterator = map.values().iterator();
      while (iterator.hasNext()) {
        String string = (String) iterator.next();
        dBValues.add(string);
      }

    }
    return dBValues;
  }

  public void enter_no_radios(String numberofradios) {
    /* noofRadio */
    inputbyJsExecutor(driver, pageObjects.no_radio_txt, numberofradios);

  }

  public String Select_RadioType(String radiotype) throws InterruptedException {
    /* Radio Type */
    String sel_attribute = null;

    try {
      clickElementByWebElement(driver, pageObjects.radio_type_drp_dwn);
      final String Radio_type = "//li[@data-label='" + radiotype + "']";
      clickElementByXPath(driver, Radio_type);
      sel_attribute = radiotype;
    } catch (Exception e) {
      utils.changefocus();
      clickElementByWebElement(driver, pageObjects.radio_type_drp_dwn);
      final String Radio_type = "//li[@data-label='" + radiotype + "']";
      clickElementByXPath(driver, Radio_type);
      sel_attribute = radiotype;
    }
    utils.changefocus();;
    return sel_attribute;

  }

  public String select_status() {
    /* Status */
    String sel_attribute = null;
    clickElementByWebElement(driver, pageObjects.status_drp_dwn);
    String attribute = driver
        .findElement(By.xpath("//label[contains(text(),'Status:')]//parent::td//following-sibling::td//div")).getAttribute("id");
    List<WebElement> statusList =
        driver.findElements(By.xpath("//ul[contains(@id,'" + attribute + "')]//li[not(contains(@data-label,'Select One'))]"));
    sel_attribute = SelectRandom(statusList);
    final String inventory_status = "//ul[contains(@id,'" + attribute + "')]//li[@data-label='" + sel_attribute + "']";
    clickElementByXPath(driver, inventory_status);
    utils.changefocus();;
    return sel_attribute;
  }

  public String select_status(String status) {
    /* Status */
    String ele_attribute = null;
    clickElementByWebElement(driver, pageObjects.status_drp_dwn);
    String attribute = driver
        .findElement(By.xpath("//label[contains(text(),'Status:')]//parent::td//following-sibling::td//div")).getAttribute("id");
    final String inventory_status = "//ul[contains(@id,'" + attribute + "')]//li[@data-label='" + status + "']";
    clickElementByXPath(driver, inventory_status);
    utils.changefocus();;
    return ele_attribute;
  }

  public void select_nxnid_no() {

    /* NXN-ID/Yes */
    clickElementByWebElement(driver, pageObjects.nxn_id);
    utils.changefocus();;

  }

  public void enter_PONUmber(String ponumber, String dateAndTimeString) {
    /* PO Number */
    inputbyJsExecutor(driver, pageObjects.PO_number, ponumber + dateAndTimeString);
    utils.changefocus();;
  }

  public String select_inven_status() {
    /* Inventory status */
    String ele_attribute = null;
    List<String> dropDownVal = new ArrayList();
    try {
      clickElementByWebElement(driver, pageObjects.invstatus_drp_dwn);
      String attribute =
          driver.findElement(By.xpath("//div[@id='radioForm:tabview:massAddInvStatus']//div")).getAttribute("id");
      List<WebElement> inventoryList =
          driver.findElements(By.xpath("//ul[contains(@id,'" + attribute + "')]//li[not(contains(@data-label,'Select One'))]"));
      for (WebElement webElement : inventoryList) {
        dropDownVal.add(webElement.getText());
      }
      Random random = new Random();
      int randomElementIndex = random.nextInt(dropDownVal.size());
      if (randomElementIndex == 0) {
        randomElementIndex = 1;
      }
      ele_attribute = dropDownVal.get(randomElementIndex);
      final String inventory_status = "//li[@data-label='" + ele_attribute + "']";
      clickElementByXPath(driver, inventory_status);

    } catch (Exception e) {
      utils.changefocus();;
      clickElementByWebElement(driver, pageObjects.invstatus_drp_dwn);
      String attribute =
          driver.findElement(By.xpath("//div[@id='radioForm:tabview:massAddInvStatus']//div")).getAttribute("id");
      List<WebElement> inventoryList =
          driver.findElements(By.xpath("//ul[contains(@id,'" + attribute + "')]//li[not(contains(@data-label,'Select One'))]"));
      for (WebElement webElement : inventoryList) {
        dropDownVal.add(webElement.getText());
      }
      Random random = new Random();
      int randomElementIndex = random.nextInt(dropDownVal.size());
      if (randomElementIndex == 0) {
        randomElementIndex = 1;
      }
      ele_attribute = dropDownVal.get(randomElementIndex);
      final String inventory_status = "//li[@data-label='" + ele_attribute + "']";
      clickElementByXPath(driver, inventory_status);
    }
    utils.changefocus();;
    return ele_attribute;
  }


  public void enter_ship_trakno(String shiptrachno, String dateAndTimeString) {
    /* Shipment Tracking Number */
    inputbyJsExecutor(driver, pageObjects.ship_track_number, shiptrachno + dateAndTimeString);
    utils.changefocus();;
  }

  public void enter_maxprjId(String maxprojectid, String dateAndTimeString) {
    /* MAX Project ID */
    inputbyJsExecutor(driver, pageObjects.Max_prj_id, maxprojectid + dateAndTimeString);
    utils.changefocus();;
  }

  public String select_manufacture() {
    /* Manufacturer */
    String sel_attribute = null;
    clickElementByWebElement(driver, pageObjects.manuf_drp_dwn);
    try {
      List<WebElement> manufactureList = driver
          .findElements(By.xpath("//ul[contains(@id,'radioForm:tabview:manufacData_items')]//li[not(contains(@data-label,'Select One'))]"));
      sel_attribute = SelectRandom(manufactureList);
      final String manuf_name = "//ul[contains(@id,'radioForm:tabview:manufacData_items')]//li[@data-label='" + sel_attribute + "']";
      clickElementByXPath(driver, manuf_name);

    } catch (Exception e) {
      utils.changefocus();;
      List<WebElement> manufactureList = driver
          .findElements(By.xpath("//ul[contains(@id,'radioForm:tabview:manufacData_items')]//li[not(contains(@data-label,'Select One'))]"));
      sel_attribute = SelectRandom(manufactureList);
      final String manuf_name = "//ul[contains(@id,'radioForm:tabview:manufacData_items')]//li[@data-label='" + sel_attribute + "']";
      clickElementByXPath(driver, manuf_name);

    }
    utils.changefocus();;
    return sel_attribute;
  }

  public String SelectRandom(List<WebElement> dropDownElements) {
    int inventoryListSize = dropDownElements.size();
    Random random = new Random();
    int randomElementIndex = random.nextInt(inventoryListSize);
    dropDownElements.get(randomElementIndex).getText();
    String selected_val = dropDownElements.get(randomElementIndex).getText();
    return selected_val;
  }

  public String select_model() throws InterruptedException {
    /* model */
    String sel_attribute = null;
    try {
      clickElementByWebElement(driver, pageObjects.model_drp_dwn);
      List<WebElement> modelList = driver
          .findElements(By.xpath("//ul[contains(@id,'radioForm:tabview:modelData_items')]//li[not(contains(@data-label,'Select One'))]"));
      sel_attribute = SelectRandom(modelList);
      final String model_name = "//ul[contains(@id,'radioForm:tabview:modelData_items')]//li[@data-label='" + sel_attribute + "']";
      clickElementByXPath(driver, model_name);
    } catch (Exception e) {
      utils.changefocus();;
      clickElementByWebElement(driver, pageObjects.model_drp_dwn);
      List<WebElement> modelList = driver
          .findElements(By.xpath("//ul[contains(@id,'radioForm:tabview:modelData_items')]//li[not(contains(@data-label,'Select One'))]"));
      sel_attribute = SelectRandom(modelList);
      final String model_name = "//ul[contains(@id,'radioForm:tabview:modelData_items')]//li[@data-label='" + sel_attribute + "']";
      clickElementByXPath(driver, model_name);
    }

    utils.changefocus();;
    return sel_attribute;

  }

  public void enter_recDate(String recdate) {
    /* Received Date */

    inputbyJsExecutor(driver, pageObjects.received_date, recdate);
    utils.changefocus();;
  }

  public void enter_firmware(String firmwareversion, String dateAndTimeString) {
    /* firmware Version */
    inputbyJsExecutor(driver, pageObjects.firmware_ver, firmwareversion + dateAndTimeString);
    utils.changefocus();;
  }

  public void enter_PoDate(String podate) {
    /* PO Date */
    inputbyJsExecutor(driver, pageObjects.PO_date, podate);
    utils.changefocus();;
  }


  public String select_department() {
    /* Assigned Department */
    String sel_attribute = null;
    try {
      clickElementByWebElement(driver, pageObjects.assigndept_drp_dwn);
      String attribute = driver
          .findElement(By.xpath("//label[contains(text(),'Assigned Department:')]//parent::td//following-sibling::td//div")).getAttribute("id");
      List<WebElement> departmentList =
          driver.findElements(By.xpath("//ul[contains(@id,'" + attribute + "')]//li[not(contains(@data-label,'Select One'))]"));
      sel_attribute = SelectRandom(departmentList);
      final String assign_dept = "//ul[contains(@id,'" + attribute + "')]//li[@data-label='" + sel_attribute + "']";
      clickElementByXPath(driver, assign_dept);

    } catch (Exception e) {
      utils.changefocus();;
      clickElementByWebElement(driver, pageObjects.assigndept_drp_dwn);
      String attribute = driver
          .findElement(By.xpath("//label[contains(text(),'Assigned Department:')]//parent::td//following-sibling::td//div")).getAttribute("id");
      List<WebElement> departmentList =
          driver.findElements(By.xpath("//ul[contains(@id,'" + attribute + "')]//li[not(contains(@data-label,'Select One'))]"));
      sel_attribute = SelectRandom(departmentList);
      final String assign_dept = "//ul[contains(@id,'" + attribute + "')]//li[@data-label='" + sel_attribute + "']";
      clickElementByXPath(driver, assign_dept);
    }
    utils.changefocus();;
    return sel_attribute;
  }


  public void enter_shipDate(String shipdate) {
    /* Ship Date */
    inputbyJsExecutor(driver, pageObjects.ship_date, shipdate);
    utils.changefocus();;

  }

  public String select_RadioShop() throws InterruptedException {
    /* Radio_Shop */
    String sel_attribute = null;

    try {
      clickElementByWebElement(driver, pageObjects.radio_shop_drp_dwn);
      List<WebElement> radioshopList = driver
          .findElements(By.xpath("//ul[contains(@id,'radioForm:tabview:radioShopC_items')]//li[not(contains(@data-label,'Select One'))]"));
      sel_attribute = SelectRandom(radioshopList);
      final String radioshop_name = "//ul[contains(@id,'radioForm:tabview:radioShopC_items')]//li[@data-label='" + sel_attribute + "']";
      clickElementByXPath(driver, radioshop_name);

    } catch (Exception e) {
      utils.changefocus();;
      clickElementByWebElement(driver, pageObjects.radio_shop_drp_dwn);
      List<WebElement> radioshopList = driver
          .findElements(By.xpath("//ul[contains(@id,'radioForm:tabview:radioShopC_items')]//li[not(contains(@data-label,'Select One'))]"));
      sel_attribute = SelectRandom(radioshopList);
      final String radioshop_name = "//ul[contains(@id,'radioForm:tabview:radioShopC_items')]//li[@data-label='" + sel_attribute + "']";
      clickElementByXPath(driver, radioshop_name);
    }
    utils.changefocus();;
    return sel_attribute;
  }



  /* Utility Methods */
  /**
   * -----Change the focus from drop down and other fields--------------------------------------
   */
  public void changefocus(WebDriver driver) {
    Actions actions = new Actions(driver);
    actions.moveByOffset(20, 8).click().build().perform();
  }

  /**
   * -----Fetch values from results table based on col name without duplicates----------------------
   */

  public Set<String> table_column_values(WebDriver driver, List<WebElement> element, String colName)

  {
    final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    wait.until(ExpectedConditions.visibilityOfAllElements(element));
    List<WebElement> findElements = element;
    List<String> colname = new ArrayList<>();
    for (WebElement webElement : findElements) {
      colname.add(webElement.getText());
    }
    int colno = colname.indexOf(colName) + 1;
    final String col_search_name =
        "//span[normalize-space()='" + colName + "']//ancestor::thead//following-sibling::tbody//descendant::td[" + colno + "]//span";
    List<WebElement> col_result_data = driver.findElements(By.xpath(col_search_name));
    Set<String> result_values = new HashSet<>();
    for (WebElement webElement : col_result_data) {
      result_values.add(webElement.getText());
    }
    return result_values;
  }

  /**
   * -----JS Method for scroll to element and enter the value ---------
   */

  public void inputbyJsExecutor(WebDriver driver, WebElement element, Object value) {
    final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    wait.until(ExpectedConditions.visibilityOf(element));
    executor.executeScript("arguments[0].scrollIntoView(true);", element);
    try {
      Thread.sleep(thread_sleep);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    if (wait.until(ExpectedConditions.elementToBeClickable(element)) != null) {
      executor.executeScript("arguments[0].value='" + value + "';", element);
    }
    try {
      Thread.sleep(thread_sleep);
    } catch (InterruptedException e) {

      e.printStackTrace();
    }
  }

  /**
   * -----double click operation ---------
   */
  public void doubleclick_element(WebDriver driver, String xpath, String colName) {
    List<WebElement> findElements = driver.findElements(By.xpath(xpath));
    for (WebElement webElement : findElements) {
      if (webElement.getText().equals(colName)) {
        Actions actions = new Actions(driver);
        actions.doubleClick(webElement).perform();
      }
    }

  }


  /**
   * -----to Check element enabled and clickable ---------
   */

  public Boolean element_clickable(final WebDriver driver, final WebElement webElement) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      if (driverWait.until(ExpectedConditions.elementToBeClickable(webElement)) != null) {
        webElement.click();
        return webElement.isEnabled();
      }
    } catch (TimeoutException | NoSuchElementException | ElementNotInteractableException ei) {
      System.out.println("element is not clickable & disabled");
    }
    return false;
  }


  /**
   * -----clickElementByWebElement with after delay ---------
   */

  public void clickElementByWebElement(final WebDriver driver, final WebElement element) {
    try {
      final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
      wait.until(ExpectedConditions.visibilityOf(element));
      if (wait.until(ExpectedConditions.elementToBeClickable(element)) != null) {
        element.click();
      }
      try {
        Thread.sleep(thread_sleep);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } catch (TimeoutException | NoSuchElementException ele) {
      fail();
    }
  }

  /**
   * -----clickElementByXpath with after delay ---------
   */

  public void clickElementByXPath(final WebDriver driver, final String xPath) {
    try {
      final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
      if (wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath))) != null) {
        driver.findElement(By.xpath(xPath)).click();
      }
      try {
        Thread.sleep(thread_sleep);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } catch (TimeoutException | NoSuchElementException ele) {
      throw ele;
    }
  }

}
