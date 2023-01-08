package com.spring.springselenium.PageClass.Actions;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;


import com.spring.springselenium.Configuraion.annotation.LazyAutowired;
import com.spring.springselenium.Configuraion.annotation.Page;
import com.spring.springselenium.PageClass.Objects.Radio_Search_PageObjects;
import com.spring.springselenium.SeleniumUtils.SeleniumUtils;
import com.spring.springselenium.StepDefinitions.ScenarioContext;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;


@Page
public class Radio_Search_PageActions {
  private static Map<Integer, ScenarioContext> contextMap = new HashMap<>();

  @LazyAutowired
  private Radio_Search_PageObjects pageObjects;
  @LazyAutowired
  private WebDriver driver;
  private WebDriverWait wait;
  @LazyAutowired
  private SeleniumUtils utils;
  @Autowired
  ScenarioContext scenarioContext;

  @LazyAutowired
  private JavascriptExecutor executor;

  public static final int DRIVER_WAIT_TIME_IN_SECS = 60;
  public static final int thread_sleep = 800;

  @PostConstruct
  public void setup() {
    PageFactory.initElements(driver, pageObjects);
    contextMap.put(driver.hashCode(), scenarioContext);
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
  }

  public void clickon_active_selection(String active) {
    clickElementByWebElement(driver, pageObjects.status_filter_clear);
    clickElementByWebElement(driver, pageObjects.status_drp_dwn);
    final String active_sele =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
            + active + "')]";
    clickElementByXPath(driver, active_sele);
    utils.changefocus();;
  }

  public void clickon_Portable_radio_selection(String Portable_radio) {
    clickElementByWebElement(driver, pageObjects.radio_drp_dwn);
    final String portable_radio_sel = "//div[@id='radioTypeCodes_panel']//label[contains(text(),'" + Portable_radio + "')]";
    clickElementByXPath(driver, portable_radio_sel);
    utils.changefocus();;
  }

  public String enter_value_in_RACF_textbox(String racfid) throws InterruptedException {
    utils.setValueToElement(driver, pageObjects.racf_text_box, racfid);
    try {
      wait.until(ExpectedConditions.visibilityOf(pageObjects.racf_auto_complete));
    } catch (TimeoutException e) {
      driver.navigate().refresh();
      utils.setValueToElement(driver, pageObjects.racf_text_box, racfid);
    }
    String racf_text = utils.getValueByElement(driver, pageObjects.racf_auto_complete);
    JsClickByElement(driver, pageObjects.racf_auto_complete);
    utils.changefocus();;
    return racf_text;
  }

  public void add_colums_to_result_table(List<String> colnames) {
    clickElementByWebElement(driver, pageObjects.Customized_column);
    final String available_col = "//ul[@aria-label='Available']//li";
    Iterator<String> iterator = colnames.iterator();
    while (iterator.hasNext()) {
      doubleclick_element(driver, available_col, iterator.next());
    }
    clickElementByWebElement(driver, pageObjects.apply_chnages);
  }

  public void clickon_search() {
    if (pageObjects.loader.isDisplayed()) {
      wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
    }
    clickElementByWebElement(driver, pageObjects.search_button);
    wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));

  }

  public boolean Search_results_displayed() {
    return searchResults_table(driver, pageObjects.nosuchrecords1);
  }

  public Set<String> validate_results(String colName) {
    Set<String> result_values = null;
    try {
      result_values = table_column_values(driver, pageObjects.result_table_hdr, colName);
    } catch (Exception e) {
      result_values = table_column_values(driver, pageObjects.result_table_hdr, colName);
    }
    return result_values;
  }

  public void enter_active_selection(String active) {
    clickElementByWebElement(driver, pageObjects.status_filter_clear);
    clickElementByWebElement(driver, pageObjects.status_drp_dwn);
    final String active_sel =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
            + active + "')]";
    clickElementByXPath(driver, active_sel);
    utils.changefocus();;
  }

  public void enter_power_field(String power) {
    utils.setValueToElement(driver, pageObjects.power_text, power);
    utils.changefocus();;
  }

  public void enter_assigndept_selection(String dept) {
    clickElementByWebElement(driver, pageObjects.dept_drp_dwn);
    final String assgn_dept_value =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
            + dept + "')]";
    clickElementByXPath(driver, assgn_dept_value);
    utils.changefocus();;
  }

  public void enter_radipshop_selection(String radioshop) {
    clickElementByWebElement(driver, pageObjects.radio_shop_drp_dwn);
    final String radio_shop_value =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
            + radioshop + "')]";
    clickElementByXPath(driver, radio_shop_value);
    utils.changefocus();;
  }

  public void enter_values_in_ship_calender(String shipfrmdate, String shiptodate) {
    inputbyJsExecutor(driver, pageObjects.ship_from_date, shipfrmdate);
    inputbyJsExecutor(driver, pageObjects.ship_to_date, shiptodate);
  }

  public Set<String> validate_result_for_assign_dept(String colName) {
    Set<String> dept_values = table_column_values(driver, pageObjects.result_table_hdr, colName);
    return dept_values;
  }

  public void clickon_assig_dept_drp_dwn(String dept1, String dept2) {
    wait.until(ExpectedConditions.visibilityOf(pageObjects.assgin_dept_drp_dwn));
    clickElementByWebElement(driver, pageObjects.assgin_dept_drp_dwn);
    final String assgn_dept1_value =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
            + dept1 + "')]";
    clickElementByXPath(driver, assgn_dept1_value);
    if (!dept2.equals("dummy")) {
      final String assgn_dept2_value =
          "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
              + dept2 + "')]";
      clickElementByXPath(driver, assgn_dept2_value);
    }
    utils.changefocus();;
  }

  public Set<String> fetch_data_from_resultstable(String colName) {
    clickElementByWebElement(driver, driver
        .findElement(By.xpath("//thead[@id='radioResultForm:radioDataTable_head']//descendant::span[normalize-space()='" + colName + "']")));
    wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
    Set<String> dept_values = table_column_values(driver, pageObjects.result_table_hdr, colName);
    wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
    clickElementByWebElement(driver, driver
        .findElement(By.xpath("//thead[@id='radioResultForm:radioDataTable_head']//descendant::span[normalize-space()='" + colName + "']")));
    wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
    dept_values.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
    wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
    return dept_values;
  }

  public void enter_valuein_RACF_textbox(String racfid) throws InterruptedException {
    utils.setValueToElement(driver, pageObjects.racf_text_box, racfid);
    try {
      wait.until(ExpectedConditions.visibilityOf(pageObjects.racf_auto_complete));
    } catch (TimeoutException e) {
      driver.navigate().refresh();
      utils.setValueToElement(driver, pageObjects.racf_text_box, racfid);
    }
    JsClickByElement(driver, pageObjects.racf_auto_complete);
    utils.changefocus();;

  }

  public Set<String> validate_result_for_assign_RACF(String colName) {
    clickElementByWebElement(driver, pageObjects.Customized_column);
    final String available_col = "//ul[@aria-label='Available']//li";
    doubleclick_element(driver, available_col, colName);
    clickElementByWebElement(driver, pageObjects.apply_chnages);
    wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
    Set<String> racf_values = table_column_values(driver, pageObjects.result_table_hdr, colName);
    return racf_values;
  }

  public String validate_current_Filter_ClearFilter() {
    clickElementByWebElement(driver, pageObjects.currentfilter_drp_dwn_click);
    String text_value = utils.getValueByElement(driver, pageObjects.currentfilter_text);
    clickElementByWebElement(driver, pageObjects.currentfilter_drp_dwn_unclick);
    return text_value;
  }

  public String validate_current_Filter_Functionality() {
    clickElementByWebElement(driver, pageObjects.currentfilter_drp_dwn_click);
    String text_value = utils.getValueByElement(driver, pageObjects.currentfilter_text).trim();
    String[] results = text_value.split("------------------------------------------------");
    clickElementByWebElement(driver, pageObjects.currentfilter_drp_dwn_unclick);
    return results[0].trim();
  }

  public String Fetch_no_such_records() {
    String act_text = utils.getValueByElement(driver, pageObjects.nosuchrecords);
    return act_text;

  }


  public void click_clearFilter() {
    clickElementByWebElement(driver, pageObjects.clearfilter_btn);
    wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
    utils.changefocus();;
  }

  public void saveReport(String reportName) throws InterruptedException {
    Thread.sleep(1000);
    clickElementByWebElement(driver, pageObjects.action_btn);
    clickElementByWebElement(driver, pageObjects.save_report_btn);
    utils.setValueToElement(driver, pageObjects.report_name, reportName);
    clickElementByWebElement(driver, pageObjects.save_btn);
  }

  public void check_reports_in_savedreports(String reportName) {
    clickElementByWebElement(driver, pageObjects.saved_reports_drp_dwn);
    boolean flag = false;
    List<WebElement> saved_reports_list = pageObjects.saved_reports_list;
    if (saved_reports_list.size() >= 1) {
      for (WebElement webElement : pageObjects.saved_reports_list) {
        try {
          if (webElement.getText().equals(reportName)) {
            contextMap.get(driver.hashCode()).getScenario().log("report saved successfully");
            flag = true;
          }
        } catch (Exception e) {
          continue;
        }
      }
    }

    if (flag == false) {
      contextMap.get(driver.hashCode()).getScenario().log("report deleted successfully");
    }
  }

  public void click_on_Delete_shared_report_link(String reportName) {
    clickElementByWebElement(driver, pageObjects.action_btn);
    clickElementByWebElement(driver, pageObjects.delete_shared_report_link);
  }

  public void check_reports_in_deletedreports_list(String reportName) {
    boolean flag = false;
    wait.until(ExpectedConditions.visibilityOfAllElements(pageObjects.delete_shared_report_list));
    for (WebElement webElement : pageObjects.saved_reports_list) {
      try {
        if (webElement.getText().equals(reportName)) {
          contextMap.get(driver.hashCode()).getScenario().log("Report Available for Delete");
          flag = true;
        }
      } catch (Exception e) {
        continue;
      }
    }
    if (flag == false) {
      contextMap.get(driver.hashCode()).getScenario().log("report deleted successfully");
    }
  }

  public void click_on_Report_deleteButton(String reportName) {
    final String report_sele = "//tbody[@id='editSaveReportTable_data']//td[normalize-space()='" + reportName + "']//following-sibling::td//button";
    clickElementByXPath(driver, report_sele);
  }

  public void click_closebtn() {
    clickElementByWebElement(driver, pageObjects.close_window);
  }

  public boolean checkPopup() {
    boolean flag = false;
    try {
      driver.switchTo().alert();
      contextMap.get(driver.hashCode()).getScenario().log("'YES/NO' POP-UP Displayed");
      flag = true;
    } catch (Exception e) {
      flag = false;
      contextMap.get(driver.hashCode()).getScenario().log("'YES/NO' POP-UP Not Displayed");
    }
    return flag;
  }


  public void clickon_OutofService_selection(String outofserice) {
    clickElementByWebElement(driver, pageObjects.status_filter_clear);
    clickElementByWebElement(driver, pageObjects.status_drp_dwn);
    final String outofservice_sele =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
            + outofserice + "')]";
    clickElementByXPath(driver, outofservice_sele);
    utils.changefocus();;

  }

  public void clickon_Mobile_radio_selection(String Mobile_radio) {
    clickElementByWebElement(driver, pageObjects.radio_drp_dwn);
    final String mobile_radio_sel = "//div[@id='radioTypeCodes_panel']//label[contains(text(),'" + Mobile_radio + "')]";
    clickElementByXPath(driver, mobile_radio_sel);
    utils.changefocus();;
  }

  public void clear_status_filter() {
    clickElementByWebElement(driver, pageObjects.status_filter_clear);
  }

  public void clickon_inventory_status_drp_dwn(String Inventory_status) {
    clickElementByWebElement(driver, pageObjects.inventory_status_drp_dwn);
    final String inventory_status_value =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
            + Inventory_status + "')]";
    clickElementByXPath(driver, inventory_status_value);
    utils.changefocus();;
  }


  public void enter_values_in_Purchase_Order_calender(String pofrmdate, String potodate) {
    inputbyJsExecutor(driver, pageObjects.po_from_date, pofrmdate);
    inputbyJsExecutor(driver, pageObjects.po_to_date, potodate);

  }

  public Set<String> validate_exported_results(List<String> colnames) throws InterruptedException {
    contextMap.get(driver.hashCode()).getScenario().log(System.getProperty("user.dir"));
    utils.deleteExistingFile("Radio Details");
    Thread.sleep(5000);
    contextMap.get(driver.hashCode()).getScenario().log("Existing File is deleted in the root directory");
    clickElementByWebElement(driver, pageObjects.export_btn);
    boolean fileDownloaded = utils.isFileDownloaded("Radio Details");
    if (fileDownloaded) {
      contextMap.get(driver.hashCode()).getScenario().log("Exported file downloaded");
    }
    Thread.sleep(5000);
    String reportname = utils.getFilename("Radio");
    contextMap.get(driver.hashCode()).getScenario().log("report name is :" + reportname);
    contextMap.get(driver.hashCode()).getScenario().log("Exported file downloaded with name" + reportname);
    List<Map<String, String>> results = null;
    String latestFilename = utils.getTheNewestFile(System.getProperty("user.dir"), "xls");
    try {
      results = getData(System.getProperty("user.dir") + File.separator + "/" + latestFilename + "", "Radio Details");
    } catch (InvalidFormatException e) {

      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Set<String> col_results = new HashSet<>();
    for (String colname : colnames) {
      for (int i = 0; i < results.size() - 1; i++) {
        col_results.add(results.get(i).get(colname));
      }
    }
    return col_results;
  }

  public void clickon_inventory_status_drp_dwn(String inventory_status1, String inventory_status2) {
    clickElementByWebElement(driver, pageObjects.inventory_status_drp_dwn);
    final String inventory_status1_value =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
            + inventory_status1 + "')]";
    clickElementByXPath(driver, inventory_status1_value);
    if (!inventory_status2.equals("dummy")) {
      final String inventory_status2_value =
          "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
              + inventory_status2 + "')]";
      clickElementByXPath(driver, inventory_status2_value);
    }
    utils.changefocus();;

  }

  public void select_values_for_pagination_validation(List<String> modelNames) {
    clickElementByWebElement(driver, pageObjects.status_filter_clear);
    clickElementByWebElement(driver, pageObjects.status_drp_dwn);
    final String active_sele =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'Active')]";
    clickElementByXPath(driver, active_sele);
    utils.changefocus();;
    clickElementByWebElement(driver, pageObjects.model_type_drpdwn);
    for (String model : modelNames) {
      final String model_sel =
          "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
              + model + "')]";
      clickElementByXPath(driver, model_sel);
    }
    utils.changefocus();;
  }

  public void pagination_links_navigation() throws InterruptedException {
    wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
    String first_pageText = "";
    String page_Text = utils.getValueByElement(driver, pageObjects.page_no_text);
    String[] split = page_Text.split(" ");
    if (Integer.parseInt(split[3]) >= 2) {

      /* last page check */
      clickElementByWebElement(driver, pageObjects.last_page_link);
      contextMap.get(driver.hashCode()).getScenario().log("Last Page link functionality Validated");
      wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
      String last_page_Text = utils.getValueByElement(driver, pageObjects.page_no_text);
      wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
      Assert.assertEquals(current_pageno(last_page_Text), retrive_pageno(last_page_Text));

      /* First page check */
      clickElementByWebElement(driver, pageObjects.first_page_link);
      contextMap.get(driver.hashCode()).getScenario().log("First Page link functionality Validated");
      wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
      first_pageText = utils.getValueByElement(driver, pageObjects.page_no_text);
      Assert.assertEquals(current_pageno(first_pageText), 1);

      /* pagination check */
      String total_records = utils.getValueByElement(driver, pageObjects.total_records);
      if (Integer.parseInt(total_records.split(" ")[2]) >= 500) {
        select_Records(driver, pageObjects.noof_records_drpdwn, "300");
        wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
        first_pageText = utils.getValueByElement(driver, pageObjects.page_no_text);
      }
      for (int i = 1; i <= retrive_pageno(first_pageText); i++) {
        driver.findElement(By.xpath("//a[normalize-space()='" + i + "']")).click();
        wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
        String pag_pageText = utils.getValueByElement(driver, pageObjects.page_no_text);
        contextMap.get(driver.hashCode()).getScenario().log("\"current page no is: " + i + " and expected page number is " + current_pageno(pag_pageText));
        Assert.assertEquals(current_pageno(pag_pageText), i);
      }
    } else {
      contextMap.get(driver.hashCode()).getScenario().log("no paginations found for selected search criteria");
    }

  }

  public void noofrecords_drpdwn_validation(List<String> noofrecords) throws InterruptedException {
    boolean nosearch_results = Search_results_displayed();
    if (!nosearch_results) {
      clickElementByWebElement(driver, pageObjects.first_page_link);
      for (String records : noofrecords) {
        select_Records(driver, pageObjects.noof_records_drpdwn, records);
        List<String> dept_names = table_column_value(driver, pageObjects.result_table_hdr, "Status");
        contextMap.get(driver.hashCode()).getScenario().log("Expected records per page are: " + records + " and actual records per page are :" + dept_names.size());
        if ((Integer.parseInt(records) < dept_names.size())) {
          Assert.assertEquals(Integer.parseInt(records), dept_names.size());
          contextMap.get(driver.hashCode()).getScenario().log("No.of records per page assertion passed for: " + records);
        }

      }
      contextMap.get(driver.hashCode()).getScenario().log("Searh results are validated ");
    } else {
      contextMap.get(driver.hashCode()).getScenario().log("No search Records are found for Selected Criteria");
    }


  }


  /* Utility methods */
  public void select_Records(WebDriver driver, WebElement element, String noofrecords) throws InterruptedException {
    Select select = new Select(element);
    select.selectByValue(noofrecords);
    wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
  }

  public int retrive_pageno(String pageString) {
    String[] split = pageString.split(" ");
    return Integer.parseInt(split[(split.length) - 1]);
  }

  public int current_pageno(String pageString) {
    String[] split = pageString.split(" ");
    return Integer.parseInt(split[1]);
  }

  public List<String> table_column_value(WebDriver driver, List<WebElement> element, String colName) {
    wait.until(ExpectedConditions.visibilityOfAllElements(element));
    List<WebElement> findElements = element;
    List<String> colname = new ArrayList<>();
    for (WebElement webElement : findElements) {
      colname.add(webElement.getText());
    }
    int colno = colname.indexOf(colName) + 1;
    final String result_dept_value =
        "//span[normalize-space()='" + colName + "']//ancestor::thead//following-sibling::tbody//descendant::td[" + colno + "]//span";
    List<WebElement> dept_names = driver.findElements(By.xpath(result_dept_value));
    List<String> col_values = new ArrayList<>();
    for (WebElement webElement : dept_names) {
      col_values.add(webElement.getText());
    }

    return col_values;
  }

  public String reportSaved_label() {
    String label1 = utils.getValueByElement(driver, pageObjects.saved_label);
    contextMap.get(driver.hashCode()).getScenario().log(label1 + "displayed in saved reports Dropdown ");
    return label1;

  }


  public String noRecordsFound() {
    String act_text = utils.getValueByElement(driver, pageObjects.nosuchrecords);
    return act_text;
  }

  public void select_report_from_saved_list(String reportname) {
    clickElementByWebElement(driver, pageObjects.saved_reports_drp_dwn);
    wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
    final String report_name_xpath =
        "//li[@class='ui-selectonemenu-item ui-selectonemenu-list-item ui-corner-all' and @data-label='" + reportname + "']";
    driver.findElement(By.xpath(report_name_xpath)).click();
    wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
  }



  public String click_on_share_report() {
    clickElementByWebElement(driver, pageObjects.action_btn);
    clickElementByWebElement(driver, pageObjects.share_btn);
    String share_window_text = utils.getValueByElement(driver, pageObjects.share_window);
    return share_window_text;

  }

  public void share_Report_Racf(String reportName, List<String> RACF) throws InterruptedException {
    clickElementByWebElement(driver, pageObjects.sel_report_drp_dwn);
    final String report_name_xpath = "// ul[@id='emailToForm:searchByMany_items']//li[@data-label='" + reportName + "']";
    clickElementByXPath(driver, report_name_xpath);
    clickElementByWebElement(driver, pageObjects.To_email);

    for (String racfid : RACF) {
      utils.setValueToElement(driver, pageObjects.RACF_search_text, racfid);
      clickElementByWebElement(driver, pageObjects.RACF_search_btn);
      Thread.sleep(1000);
      if (pageObjects.loader.isDisplayed()) {
        wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
      }
      String records = null;
      try {
        if (pageObjects.user_found.isDisplayed()) {
          contextMap.get(driver.hashCode()).getScenario().log("Report will is shared to " + racfid);
          records = utils.getValueByElement(driver, pageObjects.user_found);
        }
      } catch (Exception e) {
        contextMap.get(driver.hashCode()).getScenario().log("entered wrong racf");
        continue;
      }

      if (records.equals("1 user(s) found")) {
        Thread.sleep(1000);
        clickElementByWebElement(driver, pageObjects.RACF_search_res_sel);
      } else {
        contextMap.get(driver.hashCode()).getScenario().log("enter valid RACFID");
      }

    }
    int no_of_email = pageObjects.no_emails.size();
    contextMap.get(driver.hashCode()).getScenario().log("no.of receipient selected are  :" + no_of_email);
    clickElementByWebElement(driver, pageObjects.RACF_search_finished);
  }

  public void share_Report_tome(String comment) {
    clickElementByWebElement(driver, pageObjects.To_me);
    if (pageObjects.loader.isDisplayed()) {
      wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
    }
    utils.setValueToElement(driver, pageObjects.email_comment, comment);
    clickElementByWebElement(driver, pageObjects.email_send_btn);

  }

  public String validate_emilaSent_message() throws InterruptedException {
    Thread.sleep(500);
    if (pageObjects.loader.isDisplayed()) {
      wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
    }
    String success_messString = utils.getValueByElement(driver, pageObjects.email_sent_text);
    contextMap.get(driver.hashCode()).getScenario().log(success_messString + "displayed on the UI Page");
    return success_messString;
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
        try {
          Thread.sleep(thread_sleep + 500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

  }

  /**
   * -----Verify results has no records ---------
   */

  public boolean searchResults_table(WebDriver driver, List<WebElement> nosuchrecords1) {
    wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
    boolean flag = false;
    if (nosuchrecords1.size() <= 1) {
      flag = true;
    }
    return flag;
  }

  /**
   * -----JS Method for scroll to element and click ---------
   */

  public void JsClickByElement(final WebDriver driver, final WebElement webelement) {
    final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    wait.until(ExpectedConditions.visibilityOf(webelement));
    executor.executeScript("arguments[0].scrollIntoView(true);", webelement);
    try {
      Thread.sleep(thread_sleep);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    if (wait.until(ExpectedConditions.elementToBeClickable(webelement)) != null) {
      executor.executeScript("arguments[0].click();", webelement);
    }
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
      fail();
    }
  }


  /**
   * -----fetching the values from excel sheet ---------
   */

  public List<Map<String, String>> getData(String excelFilePath, String sheetName) throws InvalidFormatException, IOException {
    Sheet sheet = WorkbookFactory.create(new File(excelFilePath)).getSheet(sheetName);
    Row row;
    int totalRow = sheet.getPhysicalNumberOfRows();
    List<Map<String, String>> excelRows = new ArrayList<>();
    int totalColumn = sheet.getRow(0).getLastCellNum();
    int setCurrentRow = 1;
    for (int currentRow = setCurrentRow; currentRow <= totalRow; currentRow++) {
      row = sheet.getRow(sheet.getFirstRowNum() + currentRow);
      LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<>();
      for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
        columnMapdata.putAll(getCellValue(sheet, row, currentColumn));
      }
      excelRows.add(columnMapdata);
    }
    return excelRows;
  }

  private LinkedHashMap<String, String> getCellValue(Sheet sheet, Row row, int currentColumn) {
    LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<>();
    Cell cell;
    if (row == null) {
      if (sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getCellType() != CellType.BLANK) {
        String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn).getStringCellValue();
        columnMapdata.put(columnHeaderName, "");
      }
    } else {
      cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
      if (cell.getCellType() == CellType.STRING) {
        if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
            .getCellType() != CellType.BLANK) {
          String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
          columnMapdata.put(columnHeaderName, cell.getStringCellValue());
        }
      } else if (cell.getCellType() == CellType.NUMERIC) {
        if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
            .getCellType() != CellType.BLANK) {
          String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
          columnMapdata.put(columnHeaderName, NumberToTextConverter.toText(cell.getNumericCellValue()));
        }
      } else if (cell.getCellType() == CellType.BLANK) {
        if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
            .getCellType() != CellType.BLANK) {
          String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
          columnMapdata.put(columnHeaderName, "");
        }
      } else if (cell.getCellType() == CellType.BOOLEAN) {
        if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
            .getCellType() != CellType.BLANK) {
          String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
          columnMapdata.put(columnHeaderName, Boolean.toString(cell.getBooleanCellValue()));
        }
      } else if (cell.getCellType() == CellType.ERROR) {
        if (sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
            .getCellType() != CellType.BLANK) {
          String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex()).getStringCellValue();
          columnMapdata.put(columnHeaderName, Byte.toString(cell.getErrorCellValue()));
        }
      }
    }
    return columnMapdata;
  }

  public boolean compareDates(String fromDate, String toDate, String actDate) throws ParseException {
    boolean flag = false;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date from_date = sdf.parse(fromDate);
    Date to_date = sdf.parse(toDate);
    Date act_date = sdf.parse(actDate);
    if (from_date.before(act_date) && to_date.after(act_date)) {
      flag = true;

    }
    return flag;
  }



}
