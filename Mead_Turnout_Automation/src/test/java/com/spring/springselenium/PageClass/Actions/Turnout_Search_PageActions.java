package com.spring.springselenium.PageClass.Actions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.springselenium.Configuraion.annotation.LazyAutowired;
import com.spring.springselenium.Configuraion.annotation.Page;
import com.spring.springselenium.PageClass.Objects.Turnout_Search_PageObjects;
import com.spring.springselenium.SeleniumUtils.SeleniumUtils;
import com.spring.springselenium.StepDefinitions.Mead_Turnout_Home_PageStepDefinitions;
import com.spring.springselenium.StepDefinitions.ScenarioContext;
import com.spring.springselenium.XLUtils.XLUtility_xls;


@Page
public class Turnout_Search_PageActions {
  private static final Logger LOGGER = LoggerFactory.getLogger(Mead_Turnout_Home_PageStepDefinitions.class);
  private static Map<Integer, ScenarioContext> contextMap = new HashMap<>();

  @Autowired
  ScenarioContext scenarioContext;

  @LazyAutowired
  private Turnout_Search_PageObjects pageObjects;

  @LazyAutowired
  private WebDriver driver;

  private WebDriverWait wait;
  @LazyAutowired
  private SeleniumUtils Utils;
  @LazyAutowired
  private JavascriptExecutor javascriptExecutor;
  @LazyAutowired
  private TakesScreenshot takescreenshot;
  @LazyAutowired
  private XLUtility_xls xlsUtility;

  public static final int DRIVER_WAIT_TIME_IN_SECS = 1000;
  public static final int thread_sleep = 800;

  @PostConstruct
  public void init() {
    PageFactory.initElements(driver, pageObjects);
    contextMap.put(driver.hashCode(), scenarioContext);
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    wait = new WebDriverWait(driver, Duration.ofSeconds(180));
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

  /* Selecting the value from Prefix dropdown */

  public void select_prefixDropdown_val(String prefix1, String prefix2) {
    addScreenShot();
    Utils.clickElementByWebElement(driver, pageObjects.prefix_drp_dwn);
    wait.until(ExpectedConditions.visibilityOf(pageObjects.prefix_drp_dwn));
    final String prefix_value1 =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[text()='"
            + prefix1 + "']";
    Utils.JsClickByXpath(prefix_value1);
    if (!prefix2.equals("dummy")) {
      final String prefix_value2 =
          "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[text()='"
              + prefix2 + "']";
      Utils.JsClickByXpath(prefix_value2);
    }
    Utils.changefocus();
  }

  /* Selecting the value from TrackName dropdown */

  public void select_trackNamexDropdown_val(String trackName1, String trackName2) {
    addScreenShot();
    Utils.clickElementByWebElement(driver, pageObjects.trackname_drp_dwn);
    wait.until(ExpectedConditions.visibilityOf(pageObjects.trackname_drp_dwn));
    final String trackName1_val = "//div[@id='multiTrackNames_panel']/descendant::label[text()='" + trackName1 + "']";
    Utils.JsClickByXpath(trackName1_val);
    if (!trackName1.equals(trackName2)) {
      if (!trackName2.equals("dummy")) {
        final String trackName2_val = "//div[@id='multiTrackNames_panel']/descendant::label[text()='" + trackName2 + "']";
        Utils.JsClickByXpath(trackName2_val);
      }
      Utils.changefocus();
    }

  }

  /* Selecting the value from TrackStatus dropdown */

  public void select_trackStatusxDropdown_val(String trackStatus1, String trackStatus2) {
    addScreenShot();
    Utils.clickElementByWebElement(driver, pageObjects.trackstatus_drp_dwn);
    final String trackStatus1_val =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[text()='"
            + trackStatus1 + "']";
    Utils.JsClickByXpath(trackStatus1_val);
    if (!trackStatus2.equals("dummy")) {
      final String trackStatus2_val =
          "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[text()='"
              + trackStatus2 + "']";
      Utils.JsClickByXpath(trackStatus2_val);
    }
    Utils.changefocus();
  }


  /* Selecting the value from TrackType dropdown */

  public void select_trackTypeDropdown_val(String trackType1, String trackType2) {
    addScreenShot();
    Utils.clickElementByWebElement(driver, pageObjects.tracktype_drp_dwn);
    final String trackType1_val =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[text()='"
            + trackType1 + "']";
    Utils.JsClickByXpath(trackType1_val);
    if (!trackType2.equals("dummy")) {
      final String trackType2_val =
          "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[text()='"
              + trackType2 + "']";
      Utils.JsClickByXpath(trackType2_val);
    }
    Utils.changefocus();
  }


  /* Selecting the value from TrackState dropdown */


  public void select_trackStateDropdown_val(String trackState1, String trackState2) {
    addScreenShot();
    Utils.clickElementByWebElement(driver, pageObjects.trackstate_drp_dwn);
    final String trackState1_val =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[text()='"
            + trackState1 + "']";
    Utils.JsClickByXpath(trackState1_val);
    if (!trackState2.equals("dummy")) {
      final String trackState2_val =
          "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[text()='"
              + trackState2 + "']";
      Utils.JsClickByXpath(trackState2_val);
    }
    Utils.changefocus();
  }



  /* Entering the values in MilePost Fields */

  public void enterValue_MilePost(String colname, double... milePost) {
    addScreenShot();
    if (colname.equals("From Milepost")) {
      Utils.inputbyJsExecutor(driver, pageObjects.frommilepost_input, milePost[0]);
      Utils.changefocus();
    } else if (colname.equals("To Milepost")) {
      Utils.inputbyJsExecutor(driver, pageObjects.tomilepost_input, milePost[0]);
      Utils.changefocus();
    } else if (colname.equals("Milepost")) {
      Utils.inputbyJsExecutor(driver, pageObjects.frommilepost_input, milePost[0]);
      Utils.changefocus();
      Utils.inputbyJsExecutor(driver, pageObjects.tomilepost_input, milePost[1]);
    }
    Utils.clickElementByWebElement(driver, pageObjects.trackstate_drp_dwn);
    final String trackState1_val =
        "//li[@class='ui-selectcheckboxmenu-item ui-selectcheckboxmenu-list-item ui-corner-all ui-selectcheckboxmenu-unchecked']//label[text()='Out Of Service']";
    Utils.JsClickByXpath(trackState1_val);
  }

  public void enter_val_in_FromMilePost(double val) {
    addScreenShot();
    Utils.inputbyJsExecutor(driver, pageObjects.frommilepost_input, val);
    Utils.changefocus();
  }

  public void enter_val_in_ToMilePost(double val) {
    addScreenShot();
    Utils.inputbyJsExecutor(driver, pageObjects.tomilepost_input, val);
    Utils.changefocus();
  }


  /* Selecting the value from GISStatus dropdown */

  public void select_GISStatusDropdown_val(String GIS_Status) {
    addScreenShot();
    Utils.clickElementByWebElement(driver, pageObjects.GISStatus_drp_dwn);
    final String GISStatus_val = "//ul[@id='GISChangeStatus_items']//li[text()='" + GIS_Status + "']";
    Utils.JsClickByXpath(GISStatus_val);
  }


  /* Clearing the selection of AssetStatus Dropdown */

  public void clear_Asset_status_sele() {
    addScreenShot();
    Utils.JsClickByElement(pageObjects.asset_status_clear_btn);
  }


  /* Adding Required column names from Available to Selected List in Customized Columns */

  public void add_colums_to_result_table(List<String> colnames) throws InterruptedException {
    Utils.clickElementByWebElement(driver, pageObjects.Customized_column);
    Utils.waitByTime(1000);
    driver.findElement(By.xpath("//span[normalize-space()='Remove All']")).click();
    Utils.waitByTime(1000);
    Utils.waitTillLoadingCompletes(pageObjects.loader);
    final String available_col = "//ul[@aria-label='Available']//li";
    Iterator<String> iterator = colnames.iterator();
    while (iterator.hasNext()) {
      Utils.doubleclick_element(driver, available_col, iterator.next());
    }
    Utils.clickElementByWebElement(driver, pageObjects.apply_changes);
    Utils.waitByTime(1000);
    Utils.waitTillLoadingCompletes(pageObjects.loader);
    addScreenShot();
  }


  /* Performing Search Functionlaity */

  public void clickon_search() {
    Utils.waitByTime(500);
    Utils.waitTillLoadingCompletes(pageObjects.loader);
    Utils.clickElementByWebElement(driver, pageObjects.search_button);
    Utils.waitByTime(500);
    Utils.waitTillLoadingCompletes(pageObjects.loader);
  }


  /* To Check Results Grid has Records? */

  public boolean Search_results_displayed() {
    return searchResults_table(driver, pageObjects.nosuchrecords1);
  }


  /* To fetch the Values of a Single Column from Results Grid with Sorting Functionality */

  public Set<String> fetch_data_from_resultstable(String colName) throws InterruptedException {
    Utils.clickElementByWebElement(driver, driver
        .findElement(By.xpath("//thead[@id='turnoutResultForm:turnoutDataTable_head']//descendant::span[normalize-space()='" + colName + "']")));
    Utils.waitByTime(1000);
    Utils.waitTillLoadingCompletes(pageObjects.loader);
    Set<String> dept_values = table_column_values(driver, pageObjects.result_table_hdr, colName);
    Utils.clickElementByWebElement(driver, driver
        .findElement(By.xpath("//thead[@id='turnoutResultForm:turnoutDataTable_head']//descendant::span[normalize-space()='" + colName + "']")));
    Utils.waitByTime(500);
    Utils.waitTillLoadingCompletes(pageObjects.loader);
    dept_values.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
    return dept_values;
  }



  /*
   * To fetch the Values of a Single Column from Results Grid without Sorting Functionality But with
   * Pagination
   */
  public Set<String> fetch_data_from_resultstable_pagination(String colName) throws InterruptedException {
    Set<String> result = new HashSet<>();
    String[] split = (pageObjects.found_records.getText()).split(" ");
    int total_records = Integer.parseInt(split[2]);
    if (total_records > 20) {
      select_Records(driver, pageObjects.noof_records_drpdwn, "60");
      if (pageObjects.page_no_text.isDisplayed()) {
        String pagination_text = pageObjects.page_no_text.getText();
        String page[] = pagination_text.split(" ");
        for (int i = 1; i <= Integer.parseInt(page[page.length - 1]); i++) {
          Utils.waitByTime(2000);
          String xpath = "//a[normalize-space()='" + i + "']";
          Utils.waitByTime(1000);
          Utils.waitTillLoadingCompletes(pageObjects.loader);
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
          Utils.clickElementByXPath(driver, xpath);
          Utils.waitByTime(1000);
          // System.err.println("pag number is :" + i);
          try {
            result.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
            Utils.waitByTime(1000);
          } catch (StaleElementReferenceException e) {
            result.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
          }
        }

      } else {
        try {
          result.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
          Utils.waitByTime(1000);
        } catch (StaleElementReferenceException e) {
          result.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
        }
      }
    }
    try {
      result.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
      Utils.waitByTime(1000);
    } catch (StaleElementReferenceException e) {
      result.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
    }
    return result;
  }


  /*
   * To fetch the Values of a Group of Columns from Results Grid without Sorting Functionality But
   * with Pagination
   */

  public Boolean fetch_data_from_resultstable_pagination_colGroup(List<String> colnames, List<String> colvalues) throws InterruptedException {
    boolean flag = false;
    String[] split = (pageObjects.found_records.getText()).split(" ");
    int total_records = Integer.parseInt(split[2]);
    if (total_records > 20) {
      select_Records(driver, pageObjects.noof_records_drpdwn, "60");
      if (pageObjects.page_no_text.isDisplayed()) {
        String pagination_text = pageObjects.page_no_text.getText();
        String page[] = pagination_text.split(" ");
        for (int i = 1; i <= Integer.parseInt(page[page.length - 1]); i++) {
          String xpath = "//a[normalize-space()='" + i + "']";
          Utils.waitByTime(1000);
          Utils.waitTillLoadingCompletes(pageObjects.loader);
          wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
          Utils.clickElementByXPath(driver, xpath);
          Utils.waitByTime(1000);
          fetch_result_griddata(colnames, colvalues);
        }
      } else {
        fetch_result_griddata(colnames, colvalues);
      }
    }
    fetch_result_griddata(colnames, colvalues);
    flag = true;
    return flag;
  }



  /*
   * To fetch and Validatiing the Values of a Group of Columns from Results Grid without Sorting
   * Functionality But with Pagination
   */

  public boolean fetch_result_griddata(List<String> colnames, List<String> colvalues) throws InterruptedException {
    boolean flag = false;
    Set<String> prefix = new HashSet<>();
    Set<String> track_name = new HashSet<>();
    Set<String> track_state = new HashSet<>();
    Set<String> track_type = new HashSet<>();
    Set<String> track_status = new HashSet<>();
    for (String colName : colnames) {
      if (colName.equals("Prefix")) {
        try {
          prefix.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
          Utils.waitByTime(1000);
        } catch (StaleElementReferenceException e) {
          prefix.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
        }
      } else if (colName.equals("Track Name")) {
        try {
          track_name.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
          Utils.waitByTime(1000);
        } catch (StaleElementReferenceException e) {
          track_name.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
        }

      } else if (colName.equals("Track State")) {
        try {
          track_state.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
          Utils.waitByTime(1000);
        } catch (StaleElementReferenceException e) {
          track_state.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
        }

      } else if (colName.equals("Track Type")) {
        try {
          track_type.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
          Utils.waitByTime(1000);
        } catch (StaleElementReferenceException e) {
          track_type.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
        }
      } else if (colName.equals("Track Status")) {
        try {
          track_status.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
          Utils.waitByTime(1000);
        } catch (StaleElementReferenceException e) {
          track_status.addAll(table_column_values(driver, pageObjects.result_table_hdr, colName));
        }
      }
    }
    if (colnames.contains("Prefix")) {
      Assertions.assertEquals(new HashSet<>(Arrays.asList(colvalues.get(0))), prefix);
    }
    if (colnames.contains("Track Name")) {
      Assertions.assertEquals(new HashSet<>(Arrays.asList(colvalues.get(1))), track_name);
    }
    Assertions.assertEquals(new HashSet<>(Arrays.asList(colvalues.get(2))), track_state);
    Assertions.assertEquals(new HashSet<>(Arrays.asList(colvalues.get(3))), track_type);
    Assertions.assertEquals(new HashSet<>(Arrays.asList(colvalues.get(4))), track_status);
    flag = true;
    return flag;
  }



  /* Fecthing the values from current Filter DropDown with multiple values */

  public String validate_current_Filter_ClearFilter() {
    Utils.clickElementByWebElement(driver, pageObjects.currentfilter_drp_dwn_click);
    String text_value = Utils.getValueByElement(driver, pageObjects.currentcommonfilter_text);
    Utils.clickElementByWebElement(driver, pageObjects.currentfilter_drp_dwn_unclick);
    return text_value;
  }


  /* Fecthing the values from current Filter DropDown for Default Selection */
  public String validate_current_Filter_Functionality() {
    Utils.clickElementByWebElement(driver, pageObjects.currentfilter_drp_dwn_click);
    String text_value = Utils.getValueByElement(driver, pageObjects.currentturnoutfilter_text).trim();
    String[] results = text_value.split("------------------------------------------------");
    Utils.clickElementByWebElement(driver, pageObjects.currentfilter_drp_dwn_unclick);
    return results[0].trim();
  }


  /* Returns the "No Such Records" from result Grid */
  public String Fetch_no_such_records() {
    String act_text = Utils.getValueByElement(driver, pageObjects.nosuchrecords);
    return act_text;
  }

  /* Click clear filter button */
  public void click_clearFilter() {
    Utils.clickElementByWebElement(driver, pageObjects.clearfilter_btn);
    Utils.waitTillLoadingCompletes(pageObjects.loader);
    Utils.changefocus();
  }

  /* Perform save Operation by entering report name */

  public void saveReport(String reportName) throws InterruptedException {
    Utils.waitByTime(1000);
    Utils.clickElementByWebElement(driver, pageObjects.action_btn);
    Utils.clickElementByWebElement(driver, pageObjects.save_report_btn);
    Utils.setValueToElement(driver, pageObjects.report_name, reportName);
    Utils.clickElementByWebElement(driver, pageObjects.save_btn);
  }


  /* Checking the saved report name in Saved Reports dropdown */
  public void check_reports_in_savedreports(String reportName) {
    Utils.clickElementByWebElement(driver, pageObjects.saved_reports_drp_dwn);
    boolean flag = false;
    List<WebElement> saved_reports_list = pageObjects.saved_reports_list;
    if (saved_reports_list.size() >= 1) {
      for (WebElement webElement : pageObjects.saved_reports_list) {
        try {
          if (webElement.getText().equals(reportName)) {
            LOGGER.info("report saved successfully");
            flag = true;
          }
        } catch (Exception e) {
          continue;
        }
      }
    }

    if (flag == false) {
      LOGGER.info("report deleted successfully");
    }
  }

  public String reportSaved_label() {
    String label1 = Utils.getValueByElement(driver, pageObjects.saved_label);
    LOGGER.info(label1 + "displayed in saved reports Dropdown ");
    return label1;
  }


  public String noRecordsFound() {
    String act_text = Utils.getValueByElement(driver, pageObjects.nosuchrecords);
    return act_text;
  }

  public void select_report_from_saved_list(String reportname) {
    Utils.clickElementByWebElement(driver, pageObjects.saved_reports_drp_dwn);
    Utils.waitTillLoadingCompletes(pageObjects.loader);
    final String report_name_xpath =
        "//li[@class='ui-selectonemenu-item ui-selectonemenu-list-item ui-corner-all' and @data-label='" + reportname + "']";
    driver.findElement(By.xpath(report_name_xpath)).click();
    Utils.waitTillLoadingCompletes(pageObjects.loader);
  }



  /* click on Delete Report link in action menu */

  public void click_on_Delete_shared_report_link(String reportName) {
    Utils.clickElementByWebElement(driver, pageObjects.action_btn);
    Utils.clickElementByWebElement(driver, pageObjects.delete_shared_report_link);
  }


  /* Checking Report name to be deleted in the available list */

  public void check_reports_in_deletedreports_list(String reportName) {
    boolean flag = false;
    wait.until(ExpectedConditions.visibilityOfAllElements(pageObjects.delete_shared_report_list));
    for (WebElement webElement : pageObjects.saved_reports_list) {
      try {
        if (webElement.getText().equals(reportName)) {
          LOGGER.info("Report Available for Delete");
          flag = true;
        }
      } catch (Exception e) {
        continue;
      }
    }
    if (flag == false) {
      LOGGER.info("report deleted successfully");
    }
  }

  /* Delete the Report with given name */
  public void click_on_Report_deleteButton(String reportName) {
    final String report_sele = "//tbody[@id='editSaveReportTable_data']//td[normalize-space()='" + reportName + "']//following-sibling::td//button";
    Utils.clickElementByXPath(driver, report_sele);
  }
  /* Click on close button of Delete report window */

  public void click_closebtn() {
    Utils.clickElementByWebElement(driver, pageObjects.close_window);
  }

  /* Click on Export button */

  public void press_exportButton() {
    LOGGER.info(System.getProperty("user.dir"));
    Utils.deleteExistingFile("Turnouts");
    Utils.waitByTime(3000);
    LOGGER.info("Existing File is deleted in the root directory");
    Utils.clickElementByWebElement(driver, pageObjects.export_btn);
  }

  /* To check saved report after pressing Export operation */

  public void Check_report_Exported() {
    boolean fileDownloaded = Utils.isFileDownloaded("Turnout");
    if (fileDownloaded) {
      LOGGER.info("Exported file downloaded");
    }
    Utils.waitByTime(2000);
    String reportname = Utils.getFilename("Turnout");
    LOGGER.info("report name is :" + reportname);
    LOGGER.info("Exported file downloaded with name" + reportname);
  }

  /* Validating the records in Exported Excel sheet */
  public Set<String> validate_exported_results(List<String> colnames) throws InterruptedException {
    List<Map<String, String>> results = null;
    String latestFilename = Utils.getTheNewestFile(System.getProperty("user.dir"), "xls");
    try {
      results = xlsUtility.getData(System.getProperty("user.dir") + File.separator + "/" + latestFilename + "", "turnoutDataTable");
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


  /* Checking the Functionality of Pagination links */

  public void pagination_links_navigation() throws InterruptedException {
    Utils.waitTillLoadingCompletes(pageObjects.loader);
    String first_pageText = "";
    String page_Text = Utils.getValueByElement(driver, pageObjects.page_no_text);
    String[] split = page_Text.split(" ");
    if (Integer.parseInt(split[3]) >= 2) {

      /* last page check */
      Utils.clickElementByWebElement(driver, pageObjects.last_page_link);
      LOGGER.info("Last Page link functionality Validated");
      Utils.waitTillLoadingCompletes(pageObjects.loader);
      String last_page_Text = Utils.getValueByElement(driver, pageObjects.page_no_text);
      Utils.waitTillLoadingCompletes(pageObjects.loader);
      Assert.assertEquals(current_pageno(last_page_Text), retrive_pageno(last_page_Text));

      /* First page check */
      Utils.clickElementByWebElement(driver, pageObjects.first_page_link);
      LOGGER.info("First Page link functionality Validated");
      Utils.waitTillLoadingCompletes(pageObjects.loader);
      first_pageText = Utils.getValueByElement(driver, pageObjects.page_no_text);
      Assert.assertEquals(current_pageno(first_pageText), 1);

      /* pagination check */
      String total_records = Utils.getValueByElement(driver, pageObjects.total_records);
      if (Integer.parseInt(total_records.split(" ")[2]) >= 25) {
        Utils.waitTillLoadingCompletes(pageObjects.loader);
        first_pageText = Utils.getValueByElement(driver, pageObjects.page_no_text);
      }
      for (int i = 1; i <= retrive_pageno(first_pageText); i++) {
        Utils.clickElementByWebElement(driver,By.xpath("//a[normalize-space()='" + i + "']"));
        Utils.waitTillLoadingCompletes(pageObjects.loader);
        String pag_pageText = Utils.getValueByElement(driver, pageObjects.page_no_text);
        LOGGER.info("current page no is: " + i + " and expected page number is " + current_pageno(pag_pageText));
        Assert.assertEquals(current_pageno(pag_pageText), i);
      }
    } else {
      LOGGER.info("no paginations found for selected search criteria");
    }

  }

  /* Validation of No.of records dropdown in Pagination */

  public void noofrecords_drpdwn_validation(List<String> noofrecords) throws InterruptedException {
    boolean nosearch_results = Search_results_displayed();
    if (!nosearch_results) {
      Utils.clickElementByWebElement(driver, pageObjects.first_page_link);
      Utils.waitTillLoadingCompletes(pageObjects.loader);
      for (String records : noofrecords) {
        select_Records(driver, pageObjects.noof_records_drpdwn, records);
        Utils.waitTillLoadingCompletes(pageObjects.loader);
        List<String> dept_names = table_column_value(driver, pageObjects.result_table_hdr, "Prefix");
        LOGGER.info("Expected records per page are: " + records + " and actual records per page are :" + dept_names.size());
        if ((Integer.parseInt(records) < dept_names.size())) {
          Assert.assertEquals(Integer.parseInt(records), dept_names.size());
          LOGGER.info("No.of records per page assertion passed for: " + records);
        }

      }
      LOGGER.info("Searh results are validated ");
    } else {
      LOGGER.info("No search Records are found for Selected Criteria");
    }
  }


  /* selecting the values from select drodown in as aprt of pagination */
  public void select_Records(WebDriver driver, WebElement element, String noofrecords) throws InterruptedException {
    Select select = new Select(element);
    select.selectByValue(noofrecords);
    Utils.waitTillLoadingCompletes(pageObjects.loader);
  }

  public int retrive_pageno(String pageString) {
    String[] split = pageString.split(" ");
    return Integer.parseInt(split[(split.length) - 1]);
  }

  /* Retrieving the current page numbers from pagination text */
  public int current_pageno(String pageString) {
    String[] split = pageString.split(" ");
    return Integer.parseInt(split[1]);
  }

  /* Fetchinh the values from result grid in the form of List based on column name */

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


  /* Click on Share port link in the action menu */
  public String click_on_share_report() {
    Utils.clickElementByWebElement(driver, pageObjects.action_btn);
    Utils.clickElementByWebElement(driver, pageObjects.share_btn);
    String share_window_text = Utils.getValueByElement(driver, pageObjects.share_window);
    return share_window_text;

  }

  /* Selecting the User based on RACF to share report */

  public void share_Report_Racf(String reportName, List<String> RACF) throws InterruptedException {
    Utils.clickElementByWebElement(driver, pageObjects.sel_report_drp_dwn);
    final String report_name_xpath = "// ul[@id='emailToForm:searchByMany_items']//li[@data-label='" + reportName + "']";
    Utils.clickElementByXPath(driver, report_name_xpath);
    Utils.clickElementByWebElement(driver, pageObjects.To_email);

    for (String racfid : RACF) {
      Utils.setValueToElement(driver, pageObjects.RACF_search_text, racfid);
      Utils.clickElementByWebElement(driver, pageObjects.RACF_search_btn);
      Utils.waitByTime(1000);
      Utils.waitTillLoadingCompletes(pageObjects.loader);
      String records = null;
      try {
        if (pageObjects.user_found.isDisplayed()) {
          LOGGER.info("Report will is shared to " + racfid);
          records = Utils.getValueByElement(driver, pageObjects.user_found);
        }
      } catch (Exception e) {
        LOGGER.info("entered wrong racf");
        continue;
      }

      if (records.equals("1 user(s) found")) {
        Utils.waitByTime(1000);
        Utils.clickElementByWebElement(driver, pageObjects.RACF_search_res_sel);
      } else {
        LOGGER.info("enter valid RACFID");
      }

    }
    int no_of_email = pageObjects.no_emails.size();
    LOGGER.info("no.of receipient selected are  :" + no_of_email);
    Utils.clickElementByWebElement(driver, pageObjects.RACF_search_finished);
  }

  /* Selecting the own username by clicking on ToMe Button */

  public void share_Report_tome(String comment) {
    Utils.clickElementByWebElement(driver, pageObjects.To_me);
    Utils.waitTillLoadingCompletes(pageObjects.loader);
    Utils.setValueToElement(driver, pageObjects.email_comment, comment);
    Utils.clickElementByWebElement(driver, pageObjects.email_send_btn);

  }

  /* Validating the Email sent Message */

  public String validate_emilaSent_message() throws InterruptedException {
    Utils.waitByTime(500);
    Utils.waitTillLoadingCompletes(pageObjects.loader);
    String success_messString = Utils.getValueByElement(driver, pageObjects.email_sent_text);
    LOGGER.info(success_messString + "displayed on the UI Page");
    return success_messString;
  }


  /* Utility Methods */
  /**
   * -----Fetch values from results table based on col name without duplicates----------------------
   */

  public Set<String> table_column_values(WebDriver driver, List<WebElement> element, String colName)

  {
    List<WebElement> col_result_data = new ArrayList<>();
    final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    wait.until(ExpectedConditions.visibilityOfAllElements(element));
    List<WebElement> findElements = element;
    List<String> colname = new ArrayList<>();
    for (WebElement webElement : findElements) {
      colname.add(webElement.getText());
    }
    int colno = colname.indexOf(colName) + 1;
    final String col_search_name =
        "//span[normalize-space()='" + colName + "']//ancestor::thead//following-sibling::tbody//descendant::td[" + colno + "]//span";
    col_result_data.addAll(driver.findElements(By.xpath(col_search_name)));

    if (colName.equals("MilePost")) {
      final String col_milepost =
          "//span[normalize-space()='" + colName + "']//ancestor::thead//following-sibling::tbody//descendant::td[" + colno + "]//a";
      col_result_data.addAll(driver.findElements(By.xpath(col_milepost)));
    }
    Set<String> result_values = new HashSet<>();
    for (WebElement webElement : col_result_data) {
      result_values.add(webElement.getText());
      // System.err.println(result_values);
    }
    return result_values;
  }

  /**
   * -----Verify results has no records ---------
   */

  public boolean searchResults_table(WebDriver driver, List<WebElement> nosuchrecords1) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    Utils.waitTillLoadingCompletes(pageObjects.loader);
    boolean flag = false;
    if (nosuchrecords1.size() <= 1) {
      flag = true;
    }
    return flag;
  }
}
