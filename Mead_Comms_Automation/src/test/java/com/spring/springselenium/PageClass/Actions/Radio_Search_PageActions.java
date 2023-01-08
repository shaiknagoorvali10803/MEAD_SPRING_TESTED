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
import com.spring.springselenium.XLUtils.XLUtility_xls;
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
  @LazyAutowired
  private XLUtility_xls xlUtility_xls;

  public static final int DRIVER_WAIT_TIME_IN_SECS = 120;
  public static final int thread_sleep = 800;

  @PostConstruct
  public void setup() {
    PageFactory.initElements(driver, pageObjects);
    contextMap.put(driver.hashCode(), scenarioContext);
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
  }

  public void clickon_active_selection(String active) {
    utils.clickElementByWebElement(driver, pageObjects.status_filter_clear);
    utils.clickElementByWebElement(driver, pageObjects.status_drp_dwn);
    final String active_sele =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
            + active + "')]";
    utils.clickElementByXPath(driver, active_sele);
    utils.changefocus();;
  }

  public void clickon_Portable_radio_selection(String Portable_radio) {
    utils.clickElementByWebElement(driver, pageObjects.radio_drp_dwn);
    final String portable_radio_sel = "//div[@id='radioTypeCodes_panel']//label[contains(text(),'" + Portable_radio + "')]";
    utils.clickElementByXPath(driver, portable_radio_sel);
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
    utils.JsClickByElement(pageObjects.racf_auto_complete);
    utils.changefocus();;
    return racf_text;
  }

  public void add_colums_to_result_table(List<String> colnames) throws InterruptedException {
    utils.clickElementByWebElement(driver, pageObjects.Customized_column);
    final String available_col = "//ul[@aria-label='Available']//li";
    Iterator<String> iterator = colnames.iterator();
    while (iterator.hasNext()) {
      utils.doubleclick_element(driver, available_col, iterator.next());
    }
    utils.clickElementByWebElement(driver, pageObjects.apply_chnages);
  }

  public void clickon_search() {
    utils.waitTillLoadingCompletes(pageObjects.loader);
    utils.clickElementByWebElement(driver, pageObjects.search_button);
    utils.waitTillLoadingCompletes(pageObjects.loader);

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
    utils.clickElementByWebElement(driver, pageObjects.status_filter_clear);
    utils.clickElementByWebElement(driver, pageObjects.status_drp_dwn);
    final String active_sel =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
            + active + "')]";
    utils.clickElementByXPath(driver, active_sel);
    utils.changefocus();;
  }

  public void enter_power_field(String power) {
    utils.setValueToElement(driver, pageObjects.power_text, power);
    utils.changefocus();;
  }

  public void enter_assigndept_selection(String dept) {
    utils.clickElementByWebElement(driver, pageObjects.dept_drp_dwn);
    final String assgn_dept_value =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
            + dept + "')]";
    utils.clickElementByXPath(driver, assgn_dept_value);
    utils.changefocus();;
  }

  public void enter_radipshop_selection(String radioshop) {
    utils.clickElementByWebElement(driver, pageObjects.radio_shop_drp_dwn);
    final String radio_shop_value =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
            + radioshop + "')]";
    utils.clickElementByXPath(driver, radio_shop_value);
    utils.changefocus();;
  }

  public void enter_values_in_ship_calender(String shipfrmdate, String shiptodate) {
    utils.inputbyJsExecutor(driver, pageObjects.ship_from_date, shipfrmdate);
    utils.inputbyJsExecutor(driver, pageObjects.ship_to_date, shiptodate);
  }

  public Set<String> validate_result_for_assign_dept(String colName) {
    Set<String> dept_values = table_column_values(driver, pageObjects.result_table_hdr, colName);
    return dept_values;
  }

  public void clickon_assig_dept_drp_dwn(String dept1, String dept2) {
    wait.until(ExpectedConditions.visibilityOf(pageObjects.assgin_dept_drp_dwn));
    utils.clickElementByWebElement(driver, pageObjects.assgin_dept_drp_dwn);
    final String assgn_dept1_value =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
            + dept1 + "')]";
    utils.clickElementByXPath(driver, assgn_dept1_value);
    if (!dept2.equals("dummy")) {
      final String assgn_dept2_value =
          "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
              + dept2 + "')]";
      utils.clickElementByXPath(driver, assgn_dept2_value);
    }
    utils.changefocus();;
  }

  public Set<String> fetch_data_from_resultstable(String colName) {
    utils.clickElementByWebElement(driver, driver
        .findElement(By.xpath("//thead[@id='radioResultForm:radioDataTable_head']//descendant::span[normalize-space()='" + colName + "']")));
    utils.waitTillLoadingCompletes(pageObjects.loader);
    Set<String> dept_values = table_column_values(driver, pageObjects.result_table_hdr, colName);
    utils.waitTillLoadingCompletes(pageObjects.loader);
    utils.clickElementByWebElement(driver, driver
        .findElement(By.xpath("//thead[@id='radioResultForm:radioDataTable_head']//descendant::span[normalize-space()='" + colName + "']")));
    utils.waitTillLoadingCompletes(pageObjects.loader);
    dept_values.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
    utils.waitTillLoadingCompletes(pageObjects.loader);
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
    utils.JsClickByElement(pageObjects.racf_auto_complete);
    utils.changefocus();;

  }

  public Set<String> validate_result_for_assign_RACF(String colName) throws InterruptedException {
    utils.clickElementByWebElement(driver, pageObjects.Customized_column);
    final String available_col = "//ul[@aria-label='Available']//li";
    utils.doubleclick_element(driver, available_col, colName);
    utils.clickElementByWebElement(driver, pageObjects.apply_chnages);
    utils.waitTillLoadingCompletes(pageObjects.loader);
    Set<String> racf_values = table_column_values(driver, pageObjects.result_table_hdr, colName);
    return racf_values;
  }

  public String validate_current_Filter_ClearFilter() {
    utils.clickElementByWebElement(driver, pageObjects.currentfilter_drp_dwn_click);
    String text_value = utils.getValueByElement(driver, pageObjects.currentfilter_text);
    utils.clickElementByWebElement(driver, pageObjects.currentfilter_drp_dwn_unclick);
    return text_value;
  }

  public String validate_current_Filter_Functionality() {
    utils.clickElementByWebElement(driver, pageObjects.currentfilter_drp_dwn_click);
    String text_value = utils.getValueByElement(driver, pageObjects.currentfilter_text).trim();
    String[] results = text_value.split("------------------------------------------------");
    utils.clickElementByWebElement(driver, pageObjects.currentfilter_drp_dwn_unclick);
    return results[0].trim();
  }

  public String Fetch_no_such_records() {
    String act_text = utils.getValueByElement(driver, pageObjects.nosuchrecords);
    return act_text;

  }


  public void click_clearFilter() {
    utils.clickElementByWebElement(driver, pageObjects.clearfilter_btn);
    utils.waitTillLoadingCompletes(pageObjects.loader);
    utils.changefocus();;
  }

  public void saveReport(String reportName) throws InterruptedException {
    utils.waitByTime(1000);
    utils.clickElementByWebElement(driver, pageObjects.action_btn);
    utils.clickElementByWebElement(driver, pageObjects.save_report_btn);
    utils.setValueToElement(driver, pageObjects.report_name, reportName);
    utils.clickElementByWebElement(driver, pageObjects.save_btn);
  }

  public void check_reports_in_savedreports(String reportName) {
    utils.clickElementByWebElement(driver, pageObjects.saved_reports_drp_dwn);
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
    utils.clickElementByWebElement(driver, pageObjects.action_btn);
    utils.clickElementByWebElement(driver, pageObjects.delete_shared_report_link);
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
    utils.clickElementByXPath(driver, report_sele);
  }

  public void click_closebtn() {
    utils.clickElementByWebElement(driver, pageObjects.close_window);
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
    utils.clickElementByWebElement(driver, pageObjects.status_filter_clear);
    utils.clickElementByWebElement(driver, pageObjects.status_drp_dwn);
    final String outofservice_sele =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
            + outofserice + "')]";
    utils.clickElementByXPath(driver, outofservice_sele);
    utils.changefocus();;

  }

  public void clickon_Mobile_radio_selection(String Mobile_radio) {
    utils.clickElementByWebElement(driver, pageObjects.radio_drp_dwn);
    final String mobile_radio_sel = "//div[@id='radioTypeCodes_panel']//label[contains(text(),'" + Mobile_radio + "')]";
    utils.clickElementByXPath(driver, mobile_radio_sel);
    utils.changefocus();;
  }

  public void clear_status_filter() {
    utils.clickElementByWebElement(driver, pageObjects.status_filter_clear);
  }

  public void clickon_inventory_status_drp_dwn(String Inventory_status) {
    utils.clickElementByWebElement(driver, pageObjects.inventory_status_drp_dwn);
    final String inventory_status_value =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
            + Inventory_status + "')]";
    utils.clickElementByXPath(driver, inventory_status_value);
    utils.changefocus();;
  }


  public void enter_values_in_Purchase_Order_calender(String pofrmdate, String potodate) {
    utils.inputbyJsExecutor(driver, pageObjects.po_from_date, pofrmdate);
    utils.inputbyJsExecutor(driver, pageObjects.po_to_date, potodate);

  }

  public Set<String> validate_exported_results(List<String> colnames) throws InterruptedException {
    contextMap.get(driver.hashCode()).getScenario().log(System.getProperty("user.dir"));
    utils.deleteExistingFile("Radio Details");
    utils.waitByTime(5000);
    contextMap.get(driver.hashCode()).getScenario().log("Existing File is deleted in the root directory");
    utils.clickElementByWebElement(driver, pageObjects.export_btn);
    boolean fileDownloaded = utils.isFileDownloaded("Radio Details");
    if (fileDownloaded) {
      contextMap.get(driver.hashCode()).getScenario().log("Exported file downloaded");
    }
    utils.waitByTime(5000);
    String reportname = utils.getFilename("Radio");
    contextMap.get(driver.hashCode()).getScenario().log("report name is :" + reportname);
    contextMap.get(driver.hashCode()).getScenario().log("Exported file downloaded with name" + reportname);
    List<Map<String, String>> results = null;
    String latestFilename = utils.getTheNewestFile(System.getProperty("user.dir"), "xls");
    try {
      results = xlUtility_xls.getData(System.getProperty("user.dir") + File.separator + "/" + latestFilename + "", "Radio Details");
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
    utils.clickElementByWebElement(driver, pageObjects.inventory_status_drp_dwn);
    final String inventory_status1_value =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
            + inventory_status1 + "')]";
    utils.clickElementByXPath(driver, inventory_status1_value);
    if (!inventory_status2.equals("dummy")) {
      final String inventory_status2_value =
          "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
              + inventory_status2 + "')]";
      utils.clickElementByXPath(driver, inventory_status2_value);
    }
    utils.changefocus();;

  }

  public void select_values_for_pagination_validation(List<String> modelNames) {
    utils.clickElementByWebElement(driver, pageObjects.status_filter_clear);
    utils.clickElementByWebElement(driver, pageObjects.status_drp_dwn);
    final String active_sele =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'Active')]";
    utils.clickElementByXPath(driver, active_sele);
    utils.changefocus();;
    utils.clickElementByWebElement(driver, pageObjects.model_type_drpdwn);
    for (String model : modelNames) {
      final String model_sel =
          "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[contains(text(),'"
              + model + "')]";
      utils.clickElementByXPath(driver, model_sel);
    }
    utils.changefocus();;
  }

  public void pagination_links_navigation() throws InterruptedException {
    utils.waitTillLoadingCompletes(pageObjects.loader);
    String first_pageText = "";
    String page_Text = utils.getValueByElement(driver, pageObjects.page_no_text);
    String[] split = page_Text.split(" ");
    if (Integer.parseInt(split[3]) >= 2) {

      /* last page check */
      utils.clickElementByWebElement(driver, pageObjects.last_page_link);
      contextMap.get(driver.hashCode()).getScenario().log("Last Page link functionality Validated");
      utils.waitTillLoadingCompletes(pageObjects.loader);
      String last_page_Text = utils.getValueByElement(driver, pageObjects.page_no_text);
      utils.waitTillLoadingCompletes(pageObjects.loader);
      Assert.assertEquals(current_pageno(last_page_Text), retrive_pageno(last_page_Text));

      /* First page check */
      utils.clickElementByWebElement(driver, pageObjects.first_page_link);
      contextMap.get(driver.hashCode()).getScenario().log("First Page link functionality Validated");
      utils.waitTillLoadingCompletes(pageObjects.loader);
      first_pageText = utils.getValueByElement(driver, pageObjects.page_no_text);
      Assert.assertEquals(current_pageno(first_pageText), 1);

      /* pagination check */
      String total_records = utils.getValueByElement(driver, pageObjects.total_records);
      if (Integer.parseInt(total_records.split(" ")[2]) >= 500) {
        select_Records(driver, pageObjects.noof_records_drpdwn, "300");
        utils.waitTillLoadingCompletes(pageObjects.loader);
        first_pageText = utils.getValueByElement(driver, pageObjects.page_no_text);
      }
      for (int i = 1; i <= retrive_pageno(first_pageText); i++) {
        driver.findElement(By.xpath("//a[normalize-space()='" + i + "']")).click();
        utils.waitTillLoadingCompletes(pageObjects.loader);
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
      utils.clickElementByWebElement(driver, pageObjects.first_page_link);
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
    utils.waitTillLoadingCompletes(pageObjects.loader);
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
    utils.clickElementByWebElement(driver, pageObjects.saved_reports_drp_dwn);
    utils.waitTillLoadingCompletes(pageObjects.loader);
    final String report_name_xpath =
        "//li[@class='ui-selectonemenu-item ui-selectonemenu-list-item ui-corner-all' and @data-label='" + reportname + "']";
    driver.findElement(By.xpath(report_name_xpath)).click();
    wait.until(ExpectedConditions.invisibilityOf(pageObjects.loader));
  }



  public String click_on_share_report() {
    utils.clickElementByWebElement(driver, pageObjects.action_btn);
    utils.clickElementByWebElement(driver, pageObjects.share_btn);
    String share_window_text = utils.getValueByElement(driver, pageObjects.share_window);
    return share_window_text;

  }

  public void share_Report_Racf(String reportName, List<String> RACF) throws InterruptedException {
    utils.clickElementByWebElement(driver, pageObjects.sel_report_drp_dwn);
    final String report_name_xpath = "// ul[@id='emailToForm:searchByMany_items']//li[@data-label='" + reportName + "']";
    utils.clickElementByXPath(driver, report_name_xpath);
    utils.clickElementByWebElement(driver, pageObjects.To_email);

    for (String racfid : RACF) {
      utils.setValueToElement(driver, pageObjects.RACF_search_text, racfid);
      utils.clickElementByWebElement(driver, pageObjects.RACF_search_btn);
      utils.waitByTime(1000);
      utils.waitTillLoadingCompletes(pageObjects.loader);
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
        utils.waitByTime(1000);
        utils.clickElementByWebElement(driver, pageObjects.RACF_search_res_sel);
      } else {
        contextMap.get(driver.hashCode()).getScenario().log("enter valid RACFID");
      }

    }
    int no_of_email = pageObjects.no_emails.size();
    contextMap.get(driver.hashCode()).getScenario().log("no.of receipient selected are  :" + no_of_email);
    utils.clickElementByWebElement(driver, pageObjects.RACF_search_finished);
  }

  public void share_Report_tome(String comment) {
    utils.clickElementByWebElement(driver, pageObjects.To_me);
    utils.waitTillLoadingCompletes(pageObjects.loader);
    utils.setValueToElement(driver, pageObjects.email_comment, comment);
    utils.clickElementByWebElement(driver, pageObjects.email_send_btn);

  }

  public String validate_emilaSent_message() throws InterruptedException {
    utils.waitByTime(500);
    utils.waitTillLoadingCompletes(pageObjects.loader);
    String success_messString = utils.getValueByElement(driver, pageObjects.email_sent_text);
    contextMap.get(driver.hashCode()).getScenario().log(success_messString + "displayed on the UI Page");
    return success_messString;
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
   * -----Verify results has no records ---------
   */

  public boolean searchResults_table(WebDriver driver, List<WebElement> nosuchrecords1) {
    utils.waitTillLoadingCompletes(pageObjects.loader);
    boolean flag = false;
    if (nosuchrecords1.size() <= 1) {
      flag = true;
    }
    return flag;
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
