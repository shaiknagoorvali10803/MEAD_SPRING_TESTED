package com.spring.springselenium.StepDefinitions;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.springselenium.Configuraion.annotation.LazyAutowired;
import com.spring.springselenium.DatabaseUtils.DataBaseUtils;
import com.spring.springselenium.PageClass.Actions.Radio_Search_PageActions;
import com.spring.springselenium.SeleniumUtils.SeleniumUtils;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Radio_Search_PageStepDefinitions {

  private static final Logger LOGGER = LoggerFactory.getLogger(Radio_Search_PageStepDefinitions.class);
  private static Map<Integer, ScenarioContext> contextMap = new HashMap<>();
  @LazyAutowired
  Radio_Search_PageActions pageActions;
  @LazyAutowired
  private WebDriver driver;
  @LazyAutowired
  private SeleniumUtils utils;
  @LazyAutowired
  private DataBaseUtils dataBaseUtils;
  @Autowired
  ScenarioContext scenarioContext;
  static String comman_invstatus;


  static String status;
  static String radio_type;
  static String racfid;
  static String power;
  static String assign_dept;
  static String radio_shop;
  static String ship_from_date;
  static String ship_to_date;
  static String dept1;
  static String dept2;
  static String assign_RACF;
  static String racfid_name;
  static String Reportname = "";
  static String Inventory_status;
  static String po_from_date;
  static String po_to_date;
  static String dbUrl;
  static String username;
  static String password;
  static String inventory_status1;
  static String inventory_status2;
  static List<String> model;

  @PostConstruct
  private void init() {
    contextMap.put(driver.hashCode(), scenarioContext);
  }

  @Given("For Active Portable Radio Search Functionality,I use following values fetched from DB as my search criteria")
  public void Active_porable_radio_selection(DataTable dataTable) throws InterruptedException, SQLException, IOException, InvalidFormatException {
    String sqlQuery =
            "select v.ASSET_STATUS_STATE_X,v.TYPE_X from LINES_OWN.V_RADIO v WHERE v.ASSET_STATUS_STATE_X ='Active'AND v.TYPE_X ='Portable Radio' ORDER BY DBMS_RANDOM.VALUE FETCH FIRST 1 ROWS ONLY ";
    List<Map<String, Object>> testdata = dataBaseUtils.FetchDatabaseRecords(sqlQuery);
    if (testdata.size() == 0) {
      contextMap.get(driver.hashCode()).getScenario().log("No Database results found for selected criteria");
      Assume.assumeFalse(testdata.size() == 0);
    } else {
      status = (String) testdata.get(0).get("ASSET_STATUS_STATE_X");
      radio_type = (String) testdata.get(0).get("TYPE_X");
      contextMap.get(driver.hashCode()).getScenario()
              .log("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + status + ","
                      + dataTable.transpose().asList(String.class).get(1) + ":" + radio_type);
      pageActions.clickon_active_selection(status);
      pageActions.clickon_Portable_radio_selection(radio_type);
      contextMap.get(driver.hashCode()).getScenario().log("Entered values in respective fields");
    }
  }

  @Then("Active Portable Radio Search Results should show values of selected criteria Under columns {string} and {string} Respectively")
  public void Active_portable_radio_records_validate(String Status, String Radio_Type) throws InterruptedException {
    List<String> colnames = new ArrayList<>(Arrays.asList(Status, Radio_Type));
    pageActions.add_colums_to_result_table(colnames);
    boolean nosearch_results = pageActions.Search_results_displayed();
    if (!nosearch_results) {
      Set<String> staus_col_results = pageActions.validate_results(Status);
      contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed status type as:" + staus_col_results);
      Assertions.assertEquals(staus_col_results, new HashSet<>(Arrays.asList(status)));
      contextMap.get(driver.hashCode()).getScenario().log("Status Field Assertion passed");

      Set<String> radio_type_col_results = pageActions.validate_results(Radio_Type);
      contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed radio type as:" + radio_type_col_results);
      Assertions.assertEquals(radio_type_col_results, new HashSet<>(Arrays.asList(radio_type)));
      contextMap.get(driver.hashCode()).getScenario().log("Radio Type Field Assertion passed");
    } else {
      LOGGER.info("No search Records are found for Selected Criteria");
      contextMap.get(driver.hashCode()).getScenario().log("No search Records are found for Selected Criteria");
    }

  }

  @Given("For Active Radios Search Functionality,I use following values fetched from DB between given {string} and {string} as my search criteria")
  public void enter_values_for_Active_Radios_Functionality(String ship_fromdate, String ship_todate, DataTable dataTable)
          throws InterruptedException, InvalidFormatException, IOException, SQLException {
    ship_from_date = ship_fromdate;
    ship_to_date = ship_todate;
    String sql_from_date = ship_from_date.split("/")[2] + "-" + ship_from_date.split("/")[1] + "-" + ship_from_date.split("/")[1];
    String sql_to_date = ship_to_date.split("/")[2] + "-" + ship_to_date.split("/")[1] + "-" + ship_to_date.split("/")[1];
    String sqlQuery =
            " SELECT DISTINCT ASSET_STATUS_STATE_X,POWER_M ,ASSIGNED_DEPARTMENT_X ,RADIO_SHOP_X,SHIP_D from LINES_OWN.V_RADIO WHERE  ASSIGNED_DEPARTMENT_X ='Engineering' AND POWER_M =50 AND SHIP_D BETWEEN TIMESTAMP '"
                    + sql_from_date + " 00:00:00.000000' AND TIMESTAMP '" + sql_to_date
                    + " 00:00:00.000000' order by DBMS_RANDOM.RANDOM FETCH FIRST 1 ROWS ONLY";

    List<Map<String, Object>> testdata = dataBaseUtils.FetchDatabaseRecords(sqlQuery);
    if (testdata.size() == 0) {
      contextMap.get(driver.hashCode()).getScenario().log("No Database results found for selected criteria");
      Assume.assumeFalse(testdata.size() == 0);

    } else {
      status = (String) testdata.get(0).get("ASSET_STATUS_STATE_X");
      power = testdata.get(0).get("POWER_M").toString();
      assign_dept = (String) testdata.get(0).get("ASSIGNED_DEPARTMENT_X");
      radio_shop = (String) testdata.get(0).get("RADIO_SHOP_X");

      contextMap.get(driver.hashCode()).getScenario()
              .log("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + status + ","
                      + dataTable.transpose().asList(String.class).get(1) + ":" + power + "," + dataTable.transpose().asList(String.class).get(2) + ":"
                      + assign_dept + "," + dataTable.transpose().asList(String.class).get(3) + ":" + radio_shop + ","
                      + dataTable.transpose().asList(String.class).get(4) + ":" + ship_from_date + "," + dataTable.transpose().asList(String.class).get(5)
                      + ":" + ship_to_date);
      pageActions.enter_active_selection(status);
      pageActions.enter_power_field(power);
      pageActions.enter_assigndept_selection(assign_dept);
      pageActions.enter_radipshop_selection(radio_shop);
      pageActions.enter_values_in_ship_calender(ship_from_date, ship_to_date);
      contextMap.get(driver.hashCode()).getScenario().log("Entered values in respective fields");
    }

  }

  @Then("Active Radio Search results should show values of selected criteria Under following Columns Respectively.")
  public void validating_search_results_ActiveRadios(DataTable dataTable) throws InterruptedException, ParseException {
    List<String> colnames = dataTable.transpose().asList(String.class);
    boolean nosearch_results = pageActions.Search_results_displayed();
    if (!nosearch_results) {
      System.out.println("validation started");
      pageActions.add_colums_to_result_table(colnames);
      Set<String> status_val = pageActions.validate_results(colnames.get(0));
      contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed status type as:" + status_val);
      Assertions.assertEquals(status_val, new HashSet<>(Arrays.asList(status)));
      contextMap.get(driver.hashCode()).getScenario().log("Status Field Assertion passed");

      Set<String> power_val = pageActions.validate_results(colnames.get(1));
      contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed Power value as:" + power_val);
      Assertions.assertEquals(power_val, new HashSet<>(Arrays.asList(power)));
      contextMap.get(driver.hashCode()).getScenario().log("Power Field Assertion passed");

      Set<String> dept_val = pageActions.validate_results(colnames.get(2));
      contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed Department value as:" + dept_val);
      Assertions.assertEquals(dept_val, new HashSet<>(Arrays.asList(assign_dept)));
      contextMap.get(driver.hashCode()).getScenario().log("Assign Dept Field Assertion passed");

      Set<String> radioshop_val = pageActions.validate_results(colnames.get(3));
      contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed Radio Shop value as:" + radioshop_val);
      Assertions.assertEquals(radioshop_val, new HashSet<>(Arrays.asList(radio_shop)));
      contextMap.get(driver.hashCode()).getScenario().log("Radio Shop Field Assertion passed");

      Set<String> PO_dataes = pageActions.validate_results(colnames.get(4));
      utils.waitByTime(1000);
      for (String act_date : PO_dataes) {
        boolean compareDates = pageActions.compareDates(ship_from_date, ship_to_date, act_date);
        contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed PO Dates as:" + act_date);
        Assert.assertTrue(compareDates);
        contextMap.get(driver.hashCode()).getScenario().log("PO Dates Field Assertion passed");
      }
      LOGGER.info("Searh results are validated ");
    } else {
      contextMap.get(driver.hashCode()).getScenario().log("No search Records are found for Selected Criteria");
      LOGGER.info("No search Records are found for Selected Criteria");
    }
  }

  @Given("For Assigned Department Search Functionality, I use following value fetched from DB for {string} Department search criteria")
  public void Assign_dept_singleValue_sele(String department, DataTable dataTable) throws InterruptedException, IOException {
    String sqlQuery =
            "SELECT * FROM  (SELECT DISTINCT ASSIGNED_DEPARTMENT_X FROM LINES_OWN.V_RADIO  WHERE ASSIGNED_DEPARTMENT_X IS NOT NULL) ORDER BY DBMS_RANDOM.RANDOM FETCH FIRST 2 ROWS ONLY";
    List<Map<String, Object>> testdata = dataBaseUtils.FetchDatabaseRecords(sqlQuery);

    if (testdata.size() == 0) {
      contextMap.get(driver.hashCode()).getScenario().log("No Database results found for selected criteria");
      Assume.assumeFalse(testdata.size() == 0);

    } else {
      dept1 = (String) testdata.get(0).get("ASSIGNED_DEPARTMENT_X");
      dept2 = (String) testdata.get(1).get("ASSIGNED_DEPARTMENT_X");
      if (department.equals("single")) {
        contextMap.get(driver.hashCode()).getScenario()
                .log("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + dept1);
        pageActions.clickon_assig_dept_drp_dwn(dept1, "dummy");
        LOGGER.info("entered single dept value " + dept1 + "for search");
        contextMap.get(driver.hashCode()).getScenario().log("Entered values in respective fields");
      } else if (department.equals("multi")) {
        contextMap.get(driver.hashCode()).getScenario()
                .log("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + dept1 + "," + dept2);
        pageActions.clickon_assig_dept_drp_dwn(dept1, dept2);
        contextMap.get(driver.hashCode()).getScenario().log("Entered values in respective fields");
      }
    }
  }

  @When("Press the search button")
  public void Press_search_singlevalue() throws InterruptedException {
    pageActions.clickon_search();
    contextMap.get(driver.hashCode()).getScenario().log("Performed Search Operation");
    LOGGER.info("performed search operation");
  }

  @Then("Assigned Department Search Results should show values of selected criteria  for {string} deaprtment under {string} Column")
  public void validate_results_singleDepartment(String department, String ColumnName) throws InterruptedException {
    boolean nosearch_results = pageActions.Search_results_displayed();
    if (!nosearch_results) {
      Set<String> dept_values = pageActions.fetch_data_from_resultstable(ColumnName);
      contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed Department  values as:" + dept_values);
      if (department.equals("single")) {
        Assertions.assertEquals(dept_values, new HashSet<>(Arrays.asList(dept1)));
        contextMap.get(driver.hashCode()).getScenario().log("Single Department Assertion passed");
      } else if (department.equals("multi")) {
        Assertions.assertEquals(dept_values, new HashSet<>(Arrays.asList(dept1, dept2)));
        contextMap.get(driver.hashCode()).getScenario().log("Multi Department Assertion passed");
      }

    } else {
      contextMap.get(driver.hashCode()).getScenario().log("No search Records are found for Selected Criteria");
      LOGGER.info("No search Records are found for Selected Criteria");
    }

  }

  @Given("For Clear Filter Functionality ,I use following values fetched from DB as my search criteria")
  public void for_clear_filter_functionality_i_select_values(DataTable dataTable) throws Throwable {
    String sqlQuery =
            "select ASSET_STATUS_STATE_X,TYPE_X ,INVENTORY_STATUS_X FROM LINES_OWN.V_RADIO WHERE ASSET_STATUS_STATE_X IS NOT NULL AND TYPE_X  IS NOT NULL AND INVENTORY_STATUS_X  IS NOT NULL ORDER BY DBMS_RANDOM.VALUE FETCH FIRST 1 ROWS ONLY";
    List<Map<String, Object>> testdata = dataBaseUtils.FetchDatabaseRecords(sqlQuery);
    if (testdata.size() == 0) {
      contextMap.get(driver.hashCode()).getScenario().log("No Database results found for selected criteria");
      Assume.assumeFalse(testdata.size() == 0);

    } else {
      status = (String) testdata.get(0).get("ASSET_STATUS_STATE_X");
      radio_type = (String) testdata.get(0).get("TYPE_X");
      comman_invstatus = (String) testdata.get(0).get("INVENTORY_STATUS_X");
      contextMap.get(driver.hashCode()).getScenario()
              .log("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + status + ","
                      + dataTable.transpose().asList(String.class).get(1) + ":" + radio_type + "," + dataTable.transpose().asList(String.class).get(2) + ":"
                      + comman_invstatus);
      pageActions.clickon_active_selection(status);
      pageActions.clickon_Portable_radio_selection(radio_type);
      pageActions.clickon_inventory_status_drp_dwn(comman_invstatus);
      contextMap.get(driver.hashCode()).getScenario().log("Entered values in respective fields");

    }
  }

  @Then("Current Filters should show the values of search criteria with corresponding column attributes")
  public void current_filters_should_show_the_values(DataTable dataTable) throws Throwable {
    List<String> asList = dataTable.transpose().asList(String.class);
    String results = pageActions.validate_current_Filter_ClearFilter();
    contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed values are:" + results);
    Assert.assertTrue(results.contains(asList.get(0) + "=" + status));
    contextMap.get(driver.hashCode()).getScenario().log("Status Field Assertion passed");
    Assert.assertTrue(results.contains(asList.get(1) + "=" + radio_type));
    contextMap.get(driver.hashCode()).getScenario().log("Radio Type Field Assertion passed");
    Assert.assertTrue(results.contains(asList.get(2) + "=" + comman_invstatus));
    contextMap.get(driver.hashCode()).getScenario().log("Inventory status Field Assertion passed");
  }

  @Then("Search results should display records of selected criteria but not {string}")
  public void validate_filtered_searchResults(String norecords) throws InterruptedException {
    Assert.assertFalse(pageActions.Search_results_displayed());
    contextMap.get(driver.hashCode()).getScenario().log("Search results are displayed for selected criteria");
  }

  @When("I hit  the Clear Filter button")
  public void i_hit_the_clear_filter_button() throws Throwable {
    pageActions.click_clearFilter();
    contextMap.get(driver.hashCode()).getScenario().log("Performed Clear filter Operation");

  }

  @Then("Current Filter should display default value {string}")
  public void current_filter_should_display_default_value_something(String statusString) throws Throwable {
    String results = pageActions.validate_current_Filter_Functionality();
    contextMap.get(driver.hashCode()).getScenario().log(results + "displayed in current filters");
    Assert.assertTrue(results.contains(statusString));
    contextMap.get(driver.hashCode()).getScenario().log("Clear filter assertion passed ");
  }

  @Then("Search results should display {string}")
  public void validate_clearfilter_searchResults(String norecords) throws InterruptedException {
    Assert.assertEquals(norecords, pageActions.Fetch_no_such_records());
    contextMap.get(driver.hashCode()).getScenario().log(norecords + "displayed after clear filter operation");
  }


  @Given("For Delete Report Functionality, I use following values fetched from DB as my search criteria")
  public void Select_values_for_search_criteria(DataTable dataTable) throws Throwable {
    for_clear_filter_functionality_i_select_values(dataTable);

  }

  @Then("Save the Search results with Report name {string}")
  public void save_the_search_results_with_report_name(String reportName) throws Throwable {
    Reportname = reportName;
    pageActions.saveReport(reportName);
    contextMap.get(driver.hashCode()).getScenario().log("Search Results saved with name :" + Reportname);
    pageActions.check_reports_in_savedreports(reportName);
    contextMap.get(driver.hashCode()).getScenario().log(Reportname + ":Report Displayed in the saved reports Dropdown");

  }

  @When("I click on Delete Shared Report option from the Actions menu")
  public void i_click_on_delete_shared_report_option_from_the_actions_menu() throws Throwable {
    pageActions.click_on_Delete_shared_report_link(Reportname);
    contextMap.get(driver.hashCode()).getScenario().log("Clicked on the Delete report option from action menu");
  }

  @Then("Delete Shared Report Window Should show the Saved Report {string}")
  public void delete_shared_report_window_should_show_the_saved_report(String reportName) throws Throwable {
    pageActions.check_reports_in_deletedreports_list(reportName);
    contextMap.get(driver.hashCode()).getScenario().log(reportName + ": Report displayed in delete report list");
  }

  @When("I click on the Delete Icon for the report to be deleted")
  public void click_on_delete_report_icon() throws Throwable {
    pageActions.click_on_Report_deleteButton(Reportname);
    contextMap.get(driver.hashCode()).getScenario().log("Clicked the Delete Report Button");
    pageActions.click_closebtn();
    contextMap.get(driver.hashCode()).getScenario().log("Clicked on Close button delete report window");
  }

  @Then("Selected Report should be deleted ,Deleted Report should not be displayed Saved Reports dropdown.")
  public void selected_report_should_be_deleted() throws Throwable {
    pageActions.check_reports_in_savedreports(Reportname);
    contextMap.get(driver.hashCode()).getScenario().log(Reportname + ":Report not available in saved reports dropdown");
  }


  @Given("For OSS Mobile Radios Export Functionality,I use following values fetched from DB between given {string} and {string} as my search criteria")
  public void enter_values_for_Export_Functionality(String po_fromdate, String po_todate, DataTable dataTable)
          throws InterruptedException, IOException {
    po_from_date = po_fromdate;
    po_to_date = po_todate;
    String sql_from_date = po_from_date.split("/")[2] + "-" + po_from_date.split("/")[1] + "-" + po_from_date.split("/")[1];
    String sql_to_date = po_to_date.split("/")[2] + "-" + po_to_date.split("/")[1] + "-" + po_to_date.split("/")[1];
    String sqlQuery =
            " SELECT DISTINCT ASSET_STATUS_STATE_X,TYPE_X,INVENTORY_STATUS_X,PURCHASE_D from LINES_OWN.V_RADIO WHERE  TYPE_X ='Mobile Radio' AND ASSET_STATUS_STATE_X ='Out Of Service' AND INVENTORY_STATUS_X IS NOT NULL AND PURCHASE_D BETWEEN TIMESTAMP '"
                    + sql_from_date + " 00:00:00.000000' AND TIMESTAMP '" + sql_to_date
                    + " 00:00:00.000000' ORDER BY DBMS_RANDOM.RANDOM FETCH FIRST 1 ROW ONLY";

    List<Map<String, Object>> testdata = dataBaseUtils.FetchDatabaseRecords(sqlQuery);
    if (testdata.size() == 0) {
      contextMap.get(driver.hashCode()).getScenario().log("No Database results found for selected criteria");
      Assume.assumeFalse(testdata.size() == 0);

    } else {
      status = (String) testdata.get(0).get("ASSET_STATUS_STATE_X");
      radio_type = (String) testdata.get(0).get("TYPE_X");
      Inventory_status = (String) testdata.get(0).get("INVENTORY_STATUS_X");

      contextMap.get(driver.hashCode()).getScenario()
              .log("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + status + ","
                      + dataTable.transpose().asList(String.class).get(1) + ":" + radio_type + "," + dataTable.transpose().asList(String.class).get(2) + ":"
                      + Inventory_status + "," + dataTable.transpose().asList(String.class).get(3) + ":" + po_from_date + ","
                      + dataTable.transpose().asList(String.class).get(4) + ":" + po_to_date);
      pageActions.clickon_OutofService_selection(status);
      pageActions.clickon_Mobile_radio_selection(radio_type);
      pageActions.clickon_inventory_status_drp_dwn(Inventory_status);
      pageActions.enter_values_in_Purchase_Order_calender(po_from_date, po_to_date);

    }
  }

  @Then("Mobile Radios Exported results should show values of selected criteria Under following Columns Respectively.")
  public void validating_search_results_OOS_MobileRadios(DataTable dataTable)
          throws InterruptedException, ParseException, IOException, InvalidFormatException {

    boolean nosearch_results = pageActions.Search_results_displayed();
    if (!nosearch_results) {

      List<String> colnames = dataTable.transpose().asList(String.class);
      pageActions.clickon_search();
      pageActions.add_colums_to_result_table(colnames);
      Set<String> act_result = pageActions.validate_exported_results(colnames);
      contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed as:" + act_result);
      Assert.assertTrue(act_result.containsAll(Arrays.asList(status, radio_type, Inventory_status)));
      contextMap.get(driver.hashCode()).getScenario().log("Results are vlidated");
      utils.waitByTime(2000);
      LOGGER.info("Searh results are validated ");

    } else {
      contextMap.get(driver.hashCode()).getScenario().log("No search Records are found for Selected Criteria");
      LOGGER.info("No search Records are found for Selected Criteria");
    }

  }

  @Given("For Active Radios Export Functionality,I use following values fetched from DB between given {string} and {string} as my search criteria")
  public void enter_values_for_Active_Radios_Search(String ship_fromdate, String ship_todate, DataTable dataTable)
          throws InterruptedException, IOException {
    ship_from_date = ship_fromdate;
    ship_to_date = ship_todate;
    String sql_from_date = ship_from_date.split("/")[2] + "-" + ship_from_date.split("/")[1] + "-" + ship_from_date.split("/")[1];
    String sql_to_date = ship_to_date.split("/")[2] + "-" + ship_to_date.split("/")[1] + "-" + ship_to_date.split("/")[1];
    String sqlQuery =
            " SELECT DISTINCT ASSET_STATUS_STATE_X,POWER_M ,ASSIGNED_DEPARTMENT_X ,RADIO_SHOP_X,SHIP_D from LINES_OWN.V_RADIO WHERE  ASSIGNED_DEPARTMENT_X ='Engineering' AND POWER_M =50 AND SHIP_D BETWEEN TIMESTAMP '"
                    + sql_from_date + " 00:00:00.000000' AND TIMESTAMP '" + sql_to_date
                    + " 00:00:00.000000' order by DBMS_RANDOM.RANDOM FETCH FIRST 1 ROWS ONLY";

    List<Map<String, Object>> testdata = dataBaseUtils.FetchDatabaseRecords(sqlQuery);

    if (testdata.size() == 0) {
      contextMap.get(driver.hashCode()).getScenario().log("No Database results found for selected criteria");
      Assume.assumeFalse(testdata.size() == 0);

    } else {
      status = (String) testdata.get(0).get("ASSET_STATUS_STATE_X");
      power = testdata.get(0).get("POWER_M").toString();
      assign_dept = (String) testdata.get(0).get("ASSIGNED_DEPARTMENT_X");
      radio_shop = (String) testdata.get(0).get("RADIO_SHOP_X");

      contextMap.get(driver.hashCode()).getScenario()
              .log("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + status + ","
                      + dataTable.transpose().asList(String.class).get(1) + ":" + power + "," + dataTable.transpose().asList(String.class).get(2) + ":"
                      + assign_dept + "," + dataTable.transpose().asList(String.class).get(3) + ":" + radio_shop + ","
                      + dataTable.transpose().asList(String.class).get(4) + ":" + ship_from_date + "," + dataTable.transpose().asList(String.class).get(5)
                      + ":" + ship_to_date);
      pageActions.enter_active_selection(status);
      pageActions.enter_power_field(power);
      pageActions.enter_assigndept_selection(assign_dept);
      pageActions.enter_radipshop_selection(radio_shop);
      pageActions.enter_values_in_ship_calender(ship_from_date, ship_to_date);

    }

  }

  @Then("Active Radios Exported results results should show values of selected criteria Under following Columns Respectively.")
  public void validating_search_results_for_ActiveRadios(DataTable dataTable)
          throws InterruptedException, ParseException, IOException, InvalidFormatException {

    boolean nosearch_results = pageActions.Search_results_displayed();
    if (!nosearch_results) {
      List<String> colnames = dataTable.transpose().asList(String.class);
      pageActions.clickon_search();
      pageActions.add_colums_to_result_table(colnames);
      Set<String> act_result = pageActions.validate_exported_results(colnames);
      contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed as:" + act_result);
      Assert.assertTrue(act_result.containsAll(Arrays.asList(status, power, assign_dept, radio_shop)));
      utils.waitByTime(2000);
      LOGGER.info("Searh results are validated ");

    } else {
      contextMap.get(driver.hashCode()).getScenario().log("No search Records are found for Selected Criteria");
      LOGGER.info("No search Records are found for Selected Criteria");
    }
  }


  @Given("For Inventory Status Search Functionality, I use following value fetched from DB for {string} Inventory search criteria")
  public void inventory_status_singleValue_sele(String inventory, DataTable dataTable) throws InterruptedException, IOException {
    String sqlQuery =
            "SELECT * FROM  (SELECT DISTINCT INVENTORY_STATUS_X FROM LINES_OWN.V_RADIO  WHERE INVENTORY_STATUS_X IS NOT NULL) ORDER BY DBMS_RANDOM.RANDOM FETCH FIRST 2 ROWS ONLY";
    List<Map<String, Object>> testdata = dataBaseUtils.FetchDatabaseRecords(sqlQuery);

    if (testdata.size() == 0) {
      contextMap.get(driver.hashCode()).getScenario().log("No Database results found for selected criteria");
      Assume.assumeFalse(testdata.size() == 0);

    } else {
      inventory_status1 = (String) testdata.get(0).get("INVENTORY_STATUS_X");
      inventory_status2 = (String) testdata.get(1).get("INVENTORY_STATUS_X");
      if (inventory.equals("single")) {
        contextMap.get(driver.hashCode()).getScenario()
                .log("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + inventory_status1);
        pageActions.clear_status_filter();
        pageActions.clickon_inventory_status_drp_dwn(inventory_status1, "dummy");
      } else if (inventory.equals("multi")) {
        contextMap.get(driver.hashCode()).getScenario().log("Test Data Fetched from Database is : "
                + dataTable.transpose().asList(String.class).get(0) + ":" + inventory_status1 + "," + inventory_status2);
        pageActions.clear_status_filter();
        pageActions.clickon_inventory_status_drp_dwn(inventory_status1, inventory_status2);
      }

    }
  }

  @Then("Inventory Status Search Results should show values of selected criteria  for {string} inventory under {string} Column")
  public void validate_results_Inventory_Status(String inventory, String ColumnName) throws InterruptedException {

    boolean nosearch_results = pageActions.Search_results_displayed();
    if (!nosearch_results) {
      Set<String> invent_Values = pageActions.fetch_data_from_resultstable(ColumnName);
      if (inventory.equals("single")) {
        contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed status type as:" + invent_Values);
        Assertions.assertEquals(invent_Values, new HashSet<>(Arrays.asList(inventory_status1)));
        contextMap.get(driver.hashCode()).getScenario().log("Inventory status Field Assertion passed");
      } else if (inventory.equals("multi")) {
        contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed status type as:" + invent_Values);
        Assertions.assertEquals(invent_Values, new HashSet<>(Arrays.asList(inventory_status1, inventory_status2)));
        contextMap.get(driver.hashCode()).getScenario().log("Inventory status Field Assertion passed");

      }
    } else {
      contextMap.get(driver.hashCode()).getScenario().log("No search Records are found for Selected Criteria");
      LOGGER.info("No search Records are found for Selected Criteria");
    }

  }

  @Given("For Mobile Radios Search Functionality,I use following values fetched from DB between given {string} and {string} as my search criteria")
  public void enter_values_for_OOS_Mobile_Radios_Search(String po_fromdate, String po_todate, DataTable dataTable)
          throws InterruptedException, SQLException, IOException, InvalidFormatException {
    po_from_date = po_fromdate;
    po_to_date = po_todate;
    String sql_from_date = po_from_date.split("/")[2] + "-" + po_from_date.split("/")[1] + "-" + po_from_date.split("/")[1];
    String sql_to_date = po_to_date.split("/")[2] + "-" + po_to_date.split("/")[1] + "-" + po_to_date.split("/")[1];
    String sqlQuery =
            " SELECT DISTINCT ASSET_STATUS_STATE_X,TYPE_X,INVENTORY_STATUS_X,PURCHASE_D from LINES_OWN.V_RADIO WHERE  TYPE_X ='Mobile Radio' AND ASSET_STATUS_STATE_X ='Out Of Service' AND INVENTORY_STATUS_X IS NOT NULL AND PURCHASE_D BETWEEN TIMESTAMP '"
                    + sql_from_date + " 00:00:00.000000' AND TIMESTAMP '" + sql_to_date
                    + " 00:00:00.000000' ORDER BY DBMS_RANDOM.RANDOM FETCH FIRST 1 ROW ONLY";

    List<Map<String, Object>> testdata = dataBaseUtils.FetchDatabaseRecords(sqlQuery);

    if (testdata.size() == 0) {
      contextMap.get(driver.hashCode()).getScenario().log("No Database results found for selected criteria");
      Assume.assumeFalse(testdata.size() == 0);

    } else {
      status = (String) testdata.get(0).get("ASSET_STATUS_STATE_X");
      radio_type = (String) testdata.get(0).get("TYPE_X");
      Inventory_status = (String) testdata.get(0).get("INVENTORY_STATUS_X");

      contextMap.get(driver.hashCode()).getScenario()
              .log("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + status + ","
                      + dataTable.transpose().asList(String.class).get(1) + ":" + radio_type + "," + dataTable.transpose().asList(String.class).get(2) + ":"
                      + Inventory_status + "," + dataTable.transpose().asList(String.class).get(3) + ":" + po_from_date + ","
                      + dataTable.transpose().asList(String.class).get(4) + ":" + po_to_date);

      pageActions.clickon_OutofService_selection(status);
      pageActions.clickon_Mobile_radio_selection(radio_type);
      pageActions.clickon_inventory_status_drp_dwn(Inventory_status);
      pageActions.enter_values_in_Purchase_Order_calender(po_from_date, po_to_date);

    }
  }

  @Then("Mobile Radios Search results should show values of selected criteria Under following Columns Respectively.")
  public void validating_search_results(DataTable dataTable1) throws InterruptedException, ParseException {
    List<String> colnames = dataTable1.transpose().asList(String.class);
    pageActions.clickon_search();

    boolean nosearch_results = pageActions.Search_results_displayed();
    if (!nosearch_results) {

      pageActions.add_colums_to_result_table(colnames);
      Set<String> status_val = pageActions.validate_results(colnames.get(0));
      contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed status type as:" + status_val);
      utils.waitByTime(1000);
      Assertions.assertEquals(status_val, new HashSet<>(Arrays.asList(status)));
      contextMap.get(driver.hashCode()).getScenario().log("Status Field Assertion passed");

      Set<String> radio_val = pageActions.validate_results(colnames.get(1));
      contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed radio type as:" + radio_val);
      utils.waitByTime(1000);
      Assertions.assertEquals(radio_val, new HashSet<>(Arrays.asList(radio_type)));
      contextMap.get(driver.hashCode()).getScenario().log("Radio type Field Assertion passed");

      Set<String> inventory_val = pageActions.validate_results(colnames.get(2));
      utils.waitByTime(1000);
      contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed Inventory value as:" + inventory_val);
      Assertions.assertEquals(inventory_val, new HashSet<>(Arrays.asList(Inventory_status)));
      contextMap.get(driver.hashCode()).getScenario().log("Inventory status Field Assertion passed");

      Set<String> PO_dataes = pageActions.validate_results(colnames.get(3));
      contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed PO Dates as:" + PO_dataes);
      utils.waitByTime(1000);
      for (String act_date : PO_dataes) {
        boolean compareDates = pageActions.compareDates(po_from_date, po_to_date, act_date);
        Assert.assertTrue(compareDates);
        contextMap.get(driver.hashCode()).getScenario().log("PO Dates Assertion passed");

      }
      LOGGER.info("Searh results are validated ");
    } else {
      contextMap.get(driver.hashCode()).getScenario().log("No search Records are found for Selected Criteria");
      LOGGER.info("No search Records are found for Selected Criteria");
    }

  }


  @Given("For Pagination Links functionality, I use following values fetched from DB as my search criteria")
  public void select_values_for_pagination_validation(DataTable dataTable) throws InterruptedException, IOException {
    String sqlQuery =
            "SELECT * FROM  (select  DISTINCT MODEL_X from LINES_OWN.V_RADIO  WHERE ASSET_STATUS_STATE_X ='Active' AND MODEL_X IS NOT NULL) ORDER BY DBMS_RANDOM.RANDOM FETCH FIRST 3 ROWS ONLY";
    List<Map<String, Object>> testdata = dataBaseUtils.FetchDatabaseRecords(sqlQuery);

    if (testdata.size() == 0) {
      contextMap.get(driver.hashCode()).getScenario().log("No Database results found for selected criteria");
      Assume.assumeFalse(testdata.size() == 0);

    } else {
      model = new ArrayList<>();
      model.add((String) testdata.get(0).get("MODEL_X"));
      model.add((String) testdata.get(1).get("MODEL_X"));
      model.add((String) testdata.get(2).get("MODEL_X"));
      contextMap.get(driver.hashCode()).getScenario()
              .log("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + model);
      pageActions.select_values_for_pagination_validation(model);
      pageActions.clickon_search();

    }

  }

  @Then("Search results pagination links should work for navigating to the different pages")
  public void pagination_links_navigation() throws InterruptedException {
    pageActions.pagination_links_navigation();

  }

  @Then("Number of records in Search results table should match with no.of records in dropdown")
  public void validation_of_noofrecords(DataTable dataTable) throws InterruptedException {
    List<String> asList = dataTable.transpose().asList(String.class);
    pageActions.noofrecords_drpdwn_validation(asList);


  }

  @Given("For Save Report Functionality, I use following values fetched from DB as my search criteria")
  public void select_valuesin_search_criteria(DataTable dataTable) throws Throwable {
    for_clear_filter_functionality_i_select_values(dataTable);

  }

  @Then("Search Results should be displayed and Add given columns to the results table")
  public void search_results_should_be_displayed(DataTable dataTable) throws Throwable {
    pageActions.clickon_search();
    boolean nosearch_results = pageActions.Search_results_displayed();
    if (!nosearch_results) {
      utils.waitByTime(2000);
      pageActions.add_colums_to_result_table(dataTable.transpose().asList(String.class));
      LOGGER.info("Searh results are Displayed ");
    } else {
      contextMap.get(driver.hashCode()).getScenario().log("No search Records are found for Selected Criteria");
      LOGGER.info("No search Records are found for Selected Criteria");
    }
  }

  @When("Click Save Report button from actions menu,Enter Report name as {string} and press the Save button")
  public void press_save_report_button(String reportName) throws Throwable {
    pageActions.saveReport(reportName);
    contextMap.get(driver.hashCode()).getScenario().log("Reported saved with name :" + reportName);

  }

  @Then("{string} message should be displayed on the UI page")
  public void Validation_of_Success_message(String expt_message) throws Throwable {
    String act_message = pageActions.reportSaved_label();
    Assert.assertEquals(expt_message, act_message);
    contextMap.get(driver.hashCode()).getScenario().log(act_message + "message displayed on the UI Page");

  }

  @Then("Current Filters should display default value {string}")
  public void current_filters_should_display_default_value_something(String expt_filter_text) throws Throwable {
    String act_filter_text = pageActions.validate_current_Filter_Functionality();
    Assert.assertEquals(expt_filter_text, act_filter_text);
    contextMap.get(driver.hashCode()).getScenario().log("Current filters displayed the value" + act_filter_text);

  }

  @And("Search results should be Cleared and display {string}in results table")
  public void search_results_should_be_cleared_and_display_somethingin_results_table(String norecords) throws Throwable {
    boolean nosearch_results = pageActions.Search_results_displayed();
    if (!nosearch_results) {
      utils.waitByTime(2000);
      LOGGER.info("Searh results are Displayed ");
    } else {

      Assert.assertEquals(pageActions.noRecordsFound(), norecords);
      LOGGER.info("No search Records are found for Selected Criteria");
      contextMap.get(driver.hashCode()).getScenario().log("No search Records Assertion passed");
    }

  }

  @When("I select {string} under Saved Reports dropdown")
  public void select_saved_report_from_dropdown(String reporttitle) throws Throwable {
    pageActions.select_report_from_saved_list(reporttitle);


  }

  @Then("Save Report Functionality Search Results should show values of selected criteria Under respective columns")
  public void search_results_should_show_values(DataTable dataTable) throws Throwable {

    List<String> col_names = dataTable.transpose().asList(String.class);
    boolean nosearch_results = pageActions.Search_results_displayed();
    if (!nosearch_results) {
      pageActions.add_colums_to_result_table(col_names);
      utils.waitByTime(2000);
      Set<String> staus_col_results = pageActions.validate_results(dataTable.transpose().asList(String.class).get(0));
      contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed status type as:" + staus_col_results);
      Assertions.assertEquals(staus_col_results, new HashSet<>(Arrays.asList(status)));
      contextMap.get(driver.hashCode()).getScenario().log("status Field Assertion passed");

      Set<String> radio_type_col_results = pageActions.validate_results(dataTable.transpose().asList(String.class).get(1));
      contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed radio type as:" + radio_type_col_results);
      Assertions.assertEquals(radio_type_col_results, new HashSet<>(Arrays.asList(radio_type)));
      contextMap.get(driver.hashCode()).getScenario().log("Radio Type Field Assertion passed");

      Set<String> inv_col_results = pageActions.validate_results(dataTable.transpose().asList(String.class).get(2));
      contextMap.get(driver.hashCode()).getScenario().log("Search Results displayed Inventory Status as:" + inv_col_results);
      Assertions.assertEquals(inv_col_results, new HashSet<>(Arrays.asList(comman_invstatus)));
      contextMap.get(driver.hashCode()).getScenario().log("Inventory Status Field Assertion passed");
      utils.waitByTime(2000);
      LOGGER.info("Searh results are validated ");
    } else {
      contextMap.get(driver.hashCode()).getScenario().log("No search Records are found for Selected Criteria");
      LOGGER.info("No search Records are found for Selected Criteria");
    }

  }

  @Then("Search Results should be displayed with given column data")
  public void search_results_shareReport(DataTable dataTable) throws Throwable {
    pageActions.clickon_search();
    search_results_should_show_values(dataTable);
  }

  @When("Save the Report with name as {string} then {string} message should be displayed")
  public void press_save_report_button(String reportName, String message) throws Throwable {
    boolean nosearch_results = pageActions.Search_results_displayed();
    if (!nosearch_results) {
      pageActions.saveReport(reportName);
      contextMap.get(driver.hashCode()).getScenario().log("Report saved with name: " + reportName);
      String act_message = pageActions.reportSaved_label();
      Assert.assertEquals(message, act_message);
      contextMap.get(driver.hashCode()).getScenario().log(act_message + "message displayed on the UI Page");
      LOGGER.info("Searh results are validated ");
    } else {
      contextMap.get(driver.hashCode()).getScenario().log("No search Records are found for Selected Criteria");
      LOGGER.info("No search Records are found for Selected Criteria");
    }
  }
}
