package com.spring.springselenium.StepDefinitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.springselenium.Configuraion.annotation.LazyAutowired;
import com.spring.springselenium.DatabaseUtils.DataBaseUtils;
import com.spring.springselenium.PageClass.Actions.Turnout_Search_PageActions;
import com.spring.springselenium.SeleniumUtils.SeleniumUtils;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Turnout_Search_PageStepDefinitions {
  private static final Logger LOGGER = LoggerFactory.getLogger(Turnout_Search_PageStepDefinitions.class);
  private static Map<Integer, ScenarioContext> contextMap = new HashMap<>();

  @LazyAutowired
  Turnout_Search_PageActions pageActions;

  @LazyAutowired
  ScenarioContext scenario;
  @LazyAutowired
  private DataBaseUtils dbUtils;

  @LazyAutowired
  private SeleniumUtils util;

  static String GIS_Status;

  static String delete_Reportname;

  static String saveReport_name;

  static String prefix_2;
  static String trackName_2;
  static String trackState_2;
  static String trackType_2;
  static String trackStatus_2;
  static String trackState_1;
  static String trackType_1;
  static String trackStatus_1;

  static String prefix1;
  static String prefix2;
  static String trackName1;
  static String trackName2;

  static String trackStatus1;
  static String trackStatus2;

  static String trackType1;
  static String trackType2;

  static String trackState1;
  static String trackState2;

  static double from_MilePost;
  static double to_MilePost;
  static String milePost;

  @LazyAutowired
  private WebDriver driver;

  @Autowired
  ScenarioContext scenarioContext;

  @LazyAutowired
  private TakesScreenshot takescreenshot;

  @PostConstruct
  private void init() {
    contextMap.put(driver.hashCode(), scenarioContext);
  }

  /* Track Prefix Search Functionality */
  @Given("For Prefix Search Functionality, I use following values fetched from DB for Prefix {string} value search criteria")
  public void select_values_in_prefix_dropDown(String val_type, DataTable dataTable) throws Throwable {
    String sqlQuery =
        "SELECT MP_PREFIX_I FROM (SELECT DISTINCT MP_PREFIX_I FROM LINES_OWN.V_TURNOUT WHERE TO_NUMBER(BEGIN_ENG_MP_NUM_I) <=0.9 AND MP_PREFIX_I IS NOT NULL)  ORDER BY DBMS_RANDOM.RANDOM FETCH FIRST 2 ROWS ONLY";
    List<Map<String, Object>> testdata = dbUtils.FetchDatabaseRecords(sqlQuery);
    if (testdata.size() == 0) {
      LOGGER.info("No Database results found for selected criteria");
      Assume.assumeFalse(testdata.size() == 0);

    } else {
      prefix1 = (String) testdata.get(0).get("MP_PREFIX_I");
      prefix2 = (String) testdata.get(1).get("MP_PREFIX_I");
      if (val_type.equals("single")) {
        LOGGER.info("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + prefix1);
        pageActions.clear_Asset_status_sele();
        pageActions.select_prefixDropdown_val(prefix1, "dummy");
        pageActions.enter_val_in_ToMilePost(0.8);
        LOGGER.info("Entered values in respective fields");
      } else if (val_type.equals("multiple")) {
        scenario.getScenario()
            .log("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + prefix1 + "," + prefix2);
        pageActions.clear_Asset_status_sele();
        pageActions.select_prefixDropdown_val(prefix1, prefix2);
        pageActions.enter_val_in_ToMilePost(0.8);
        LOGGER.info("Entered values in respective fields");
      }
    }
  }

  @When("Press the search button")
  public void Press_search_singlevalue() throws InterruptedException {
    pageActions.clickon_search();
    LOGGER.info("Performed Search Operation");
  }

  /* Track Status Search Functionality */
  @Given("For Track Status Search Functionality, I use following values fetched from DB for track status {string} value search criteria")
  public void select_values_in_TrackStatus_dropDown(String val_type, DataTable dataTable) throws Throwable {
    String sqlQuery =
        "SELECT TRACK_STATUS_X FROM (SELECT DISTINCT TRACK_STATUS_X FROM LINES_OWN.V_TURNOUT WHERE TO_NUMBER(BEGIN_ENG_MP_NUM_I) <=0.9 AND TRACK_STATUS_X IS NOT NULL)  ORDER BY DBMS_RANDOM.RANDOM FETCH FIRST 2 ROWS ONLY";
    List<Map<String, Object>> testdata = dbUtils.FetchDatabaseRecords(sqlQuery);
    if (testdata.size() == 0) {
      LOGGER.info("No Database results found for selected criteria");
      Assume.assumeFalse(testdata.size() == 0);
    } else {
      trackStatus1 = (String) testdata.get(0).get("TRACK_STATUS_X");
      trackStatus2 = (String) testdata.get(1).get("TRACK_STATUS_X");
      if (val_type.equals("single")) {
        LOGGER.info("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + trackStatus1);
        pageActions.clear_Asset_status_sele();
        pageActions.select_trackStatusxDropdown_val(trackStatus1, "dummy");
        pageActions.enter_val_in_ToMilePost(0.8);
        LOGGER.info("Entered values in respective fields");
      } else if (val_type.equals("multiple")) {
        LOGGER.info(
            "Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + trackStatus1 + "," + trackStatus2);
        pageActions.clear_Asset_status_sele();
        pageActions.select_trackStatusxDropdown_val(trackStatus1, trackStatus2);
        pageActions.enter_val_in_ToMilePost(0.8);
        LOGGER.info("Entered values in respective fields");
      }
    }
  }

  /* Track Type Search Functionality */
  @Given("For Track Type Search Functionality, I use following values fetched from DB for track status {string} value search criteria")
  public void select_values_in_TrackType_dropDown(String val_type, DataTable dataTable) throws Throwable {
    String sqlQuery =
        "SELECT TRACK_TYPE_X FROM (SELECT DISTINCT TRACK_TYPE_X FROM LINES_OWN.V_TURNOUT WHERE TO_NUMBER(BEGIN_ENG_MP_NUM_I) <=0.9 AND TRACK_TYPE_X IS NOT NULL)  ORDER BY DBMS_RANDOM.RANDOM FETCH FIRST 2 ROWS ONLY";
    List<Map<String, Object>> testdata = dbUtils.FetchDatabaseRecords(sqlQuery);
    if (testdata.size() == 0) {
      LOGGER.info("No Database results found for selected criteria");
      Assume.assumeFalse(testdata.size() == 0);
    } else {
      trackType1 = (String) testdata.get(0).get("TRACK_TYPE_X");
      trackType2 = (String) testdata.get(1).get("TRACK_TYPE_X");
      if (val_type.equals("single")) {
        LOGGER.info("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + trackType1);
        pageActions.clear_Asset_status_sele();
        pageActions.select_trackTypeDropdown_val(trackType1, "dummy");
        pageActions.enter_val_in_ToMilePost(0.8);
        LOGGER.info("Entered values in respective fields");
      } else if (val_type.equals("multiple")) {
        scenario.getScenario()
            .log("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + trackType1 + "," + trackType2);
        pageActions.clear_Asset_status_sele();
        pageActions.select_trackTypeDropdown_val(trackType1, trackType2);
        pageActions.enter_val_in_ToMilePost(0.8);
        LOGGER.info("Entered values in respective fields");
      }
    }
  }

  /* Track State Search Functionality */
  @Given("For Track State Search Functionality, I use following values fetched from DB for track state {string} value search criteria")
  public void select_values_in_TrackState_dropDown(String val_type, DataTable dataTable) throws Throwable {
    String sqlQuery =
        "SELECT * FROM  (SELECT DISTINCT TRACK_STATE_X FROM LINES_OWN.V_TURNOUT WHERE TRACK_STATE_X IS NOT NULL AND TO_NUMBER(BEGIN_ENG_MP_NUM_I) <=0.9) ORDER BY DBMS_RANDOM.RANDOM FETCH FIRST 2 ROWS ONLY";
    List<Map<String, Object>> testdata = dbUtils.FetchDatabaseRecords(sqlQuery);
    if (testdata.size() == 0) {
      LOGGER.info("No Database results found for selected criteria");
      Assume.assumeFalse(testdata.size() == 0);

    } else {
      if (testdata.get(0).get("TRACK_STATE_X").equals("Out of Service")) {
        trackState1 = "Out Of Service";
      } else {
        trackState1 = (String) testdata.get(0).get("TRACK_STATE_X");
      }
      if (testdata.get(1).get("TRACK_STATE_X").equals("Out of Service")) {
        trackState2 = "Out Of Service";
      } else {
        trackState2 = (String) testdata.get(1).get("TRACK_STATE_X");
      }
      if (val_type.equals("single")) {
        LOGGER.info("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + trackState1);
        pageActions.clear_Asset_status_sele();
        pageActions.select_trackStateDropdown_val(trackState1, "dummy");
        pageActions.enter_val_in_ToMilePost(0.8);
        LOGGER.info("Entered values in respective fields");
      } else if (val_type.equals("multiple")) {
        scenario.getScenario()
            .log("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + trackState1 + "," + trackState2);
        pageActions.clear_Asset_status_sele();
        pageActions.select_trackStateDropdown_val(trackState1, trackState2);
        pageActions.enter_val_in_ToMilePost(0.8);
        LOGGER.info("Entered values in respective fields");
      }
    }
  }

  /* MilePost Search Functionality */
  @Given("For {string} Search Functionality, I enter {string} and {string} values in the respecrive input fileds")
  public void enter_valuein_fromMilePost(String colname, String frm_milePost, String to_milePost) {
    milePost = colname;
    if (colname.equals("From Milepost")) {
      from_MilePost = Double.parseDouble(frm_milePost);
      pageActions.enterValue_MilePost(colname, from_MilePost);
    } else if (colname.equals("To Milepost")) {
      to_MilePost = Double.parseDouble(to_milePost);
      pageActions.enterValue_MilePost(colname, to_MilePost);
    } else if (colname.equals("Milepost")) {
      from_MilePost = Double.parseDouble(frm_milePost);
      to_MilePost = Double.parseDouble(to_milePost);
      pageActions.enterValue_MilePost(colname, from_MilePost, to_MilePost);
    }

  }

  @Then("Milepost search results grid should show selected value of search criteria under {string} Column")
  public void validate_milePost(String colName) throws Throwable {
    Set<String> milepost_values = null;;
    boolean nosearch_results = pageActions.Search_results_displayed();
    List<String> colnames = new ArrayList<>(Arrays.asList(colName));
    pageActions.add_colums_to_result_table(colnames);
    if (!nosearch_results) {
      milepost_values = pageActions.fetch_data_from_resultstable(colName);
      for (String milepost : milepost_values) {
        if (milePost.equals("From Milepost")) {
          Assert.assertTrue(Double.parseDouble(milepost) >= from_MilePost);
        } else if (milePost.equals("To Milepost")) {
          Assert.assertTrue(Double.parseDouble(milepost) <= to_MilePost);
        } else if (milePost.equals("Milepost")) {
          Assert.assertTrue(Double.parseDouble(milepost) >= from_MilePost && Double.parseDouble(milepost) <= to_MilePost);
        }
      }

    } else {
      LOGGER.info("No search Records are found for Selected Criteria");
      LOGGER.info("No search Records are found for Selected Criteria");
    }
  }

  @Then("Search results grid should show selected {string} {string} values under Respective Column")
  public void validate_prefix_searchResults(String val_type, String colName) throws Throwable {
    Set<String> result_grid_values = null;;
    boolean nosearch_results = pageActions.Search_results_displayed();
    List<String> colnames = new ArrayList<>(Arrays.asList(colName));
    pageActions.add_colums_to_result_table(colnames);
    if (!nosearch_results) {
      if (colName.equals("Prefix")) {
        result_grid_values = pageActions.fetch_data_from_resultstable(colName);
      } else if (colName.equals("Track Name") || colName.equals("Track Status") || colName.equals("Track Type") || colName.equals("Track State")) {
        result_grid_values = pageActions.fetch_data_from_resultstable_pagination(colName);
      }
      LOGGER.info("Search Results Grid displayed values as:" + result_grid_values);

      if (val_type.equals("single")) {
        switch (colName) {
          case "Prefix":
            Assertions.assertEquals(new HashSet<>(Arrays.asList(prefix1)), result_grid_values);
            break;
          case "Track Name":
            Assertions.assertEquals(new HashSet<>(Arrays.asList(trackName1)), result_grid_values);
            break;
          case "Track Status":
            Assertions.assertEquals(new HashSet<>(Arrays.asList(trackStatus1)), result_grid_values);
            break;
          case "Track Type":
            Assertions.assertEquals(new HashSet<>(Arrays.asList(trackType1)), result_grid_values);
            break;
          case "Track State":
            if (trackState1.equals("Active")) {
              Assertions.assertEquals(new HashSet<>(Arrays.asList(trackState1)), result_grid_values);
            } else if (trackState1.equals("Out Of Service")) {
              Assertions.assertEquals(new HashSet<>(Arrays.asList("Out of Service")), result_grid_values);
            }
            break;
        }
        LOGGER.info("Single value Assertion passed");

      } else if (val_type.equals("multiple")) {
        switch (colName) {
          case "Prefix":
            Assertions.assertEquals(new HashSet<>(Arrays.asList(prefix1, prefix2)), result_grid_values);
            break;
          case "Track Name":
            Assertions.assertEquals(new HashSet<>(Arrays.asList(trackName1, trackName2)), result_grid_values);
            break;
          case "Track Status":
            Assertions.assertEquals(new HashSet<>(Arrays.asList(trackStatus1, trackStatus2)), result_grid_values);
            break;
          case "Track Type":
            Assertions.assertEquals(new HashSet<>(Arrays.asList(trackType1, trackType2)), result_grid_values);
            break;
          case "Track State":
            if (trackState1.equals("Out Of Service")) {
              Assertions.assertEquals(new HashSet<>(Arrays.asList(trackState2, "Out of Service")), result_grid_values);
            } else if (trackState2.equals("Out Of Service")) {
              Assertions.assertEquals(new HashSet<>(Arrays.asList(trackState1, "Out of Service")), result_grid_values);
            }

            Assertions.assertEquals(new HashSet<>(Arrays.asList("Active", "Out of Service")), result_grid_values);
            break;
        }
        LOGGER.info("Multi value Assertion passed");
      }

    } else {
      LOGGER.info("No search Records are found for Selected Criteria");
      LOGGER.info("No search Records are found for Selected Criteria");
    }
  }

  /* Active Track Attributes1 Search Functionality */
  @Given("For {string} Track Search Functionality,I use following values fetched from DB as my search criteria")
  public void select_ActiveTrack_Attributes(String track_status, DataTable dataTable) throws Throwable {
    String sqlQuery = "SELECT TRACK_STATE_X ,TRACK_TYPE_X ,TRACK_STATUS_X FROM LINES_OWN.V_TURNOUT "
        + "WHERE TO_NUMBER(BEGIN_ENG_MP_NUM_I) <=0.9 AND TRACK_STATE_X ='" + track_status
        + "' AND TRACK_STATUS_X IS NOT NULL AND TRACK_TYPE_X IS NOT NULL " + "ORDER BY DBMS_RANDOM.RANDOM FETCH FIRST 1 ROWS ONLY";
    List<Map<String, Object>> testdata = dbUtils.FetchDatabaseRecords(sqlQuery);
    if (testdata.size() == 0) {
      LOGGER.info("No Database results found for selected criteria");
      Assume.assumeFalse(testdata.size() == 0);
    } else {
      if (testdata.get(0).get("TRACK_STATE_X").equals("Out of Service")) {
        trackState_1 = "Out Of Service";
      } else {
        trackState_1 = (String) testdata.get(0).get("TRACK_STATE_X");
      }
      trackType_1 = (String) testdata.get(0).get("TRACK_TYPE_X");
      trackStatus_1 = (String) testdata.get(0).get("TRACK_STATUS_X");

      LOGGER.info("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + trackState_1);
      pageActions.clear_Asset_status_sele();
      pageActions.select_trackStateDropdown_val(trackState_1, "dummy");
      LOGGER.info("Entered values in respective fields");

      LOGGER.info("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(1) + ":" + trackType_1);
      pageActions.select_trackTypeDropdown_val(trackType_1, "dummy");
      LOGGER.info("Entered values in respective fields");

      LOGGER.info("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(2) + ":" + trackStatus_1);
      pageActions.select_trackStatusxDropdown_val(trackStatus_1, "dummy");
      LOGGER.info("Entered values in respective fields");
      pageActions.enter_val_in_ToMilePost(0.8);
    }
  }

  @Then("{string} Track search results grid should show values of selected criteria Under below columns Respectively")
  public void validate_activetrackattributes_searchResults(String state, DataTable dataTable) throws Throwable {
    List<String> colvalues = null;
    boolean nosearch_results = pageActions.Search_results_displayed();
    List<String> colnames = new ArrayList<>(dataTable.transpose().asList(String.class));
    if (state.equals("Active")) {
      trackState_1 = state;
      colvalues = new ArrayList<>(Arrays.asList(prefix_2, trackName_2, trackState_1, trackType_1, trackStatus_1));
    }
    if (state.equals("Out of Service")) {
      trackState_1 = state;
      colvalues = new ArrayList<>(Arrays.asList(prefix_2, trackName_2, trackState_1, trackType_1, trackStatus_1));
    }
    pageActions.add_colums_to_result_table(colnames);
    if (!nosearch_results) {
      Boolean asser_result = pageActions.fetch_data_from_resultstable_pagination_colGroup(colnames, colvalues);
      Assert.assertTrue(asser_result);
    } else {
      LOGGER.info("No search Records are found for Selected Criteria");
      LOGGER.info("No search Records are found for Selected Criteria");
    }
  }

  /* GIS Conflated Status Attributes Search Functionality */
  @Given("For Prefix Search Functionality, I use following values fetched from DB as my search criteria")
  public void select_GIS_Conflated_Status_Attribute(DataTable dataTable) throws Throwable {
    String sqlQuery =
        "SELECT DISTINCT GIS_STATUS FROM LINES_OWN.V_TURNOUT WHERE GIS_STATUS IN ('PENDING','CONFLATED') ORDER BY DBMS_RANDOM.RANDOM FETCH FIRST 1 ROWS ONLY";
    List<Map<String, Object>> testdata = dbUtils.FetchDatabaseRecords(sqlQuery);
    if (testdata.size() == 0) {
      LOGGER.info("No Database results found for selected criteria");
      Assume.assumeFalse(testdata.size() == 0);

    } else {
      if (testdata.get(0).get("GIS_STATUS").equals("CONFLATED")) {
        GIS_Status = "Validated";
      }
      if (testdata.get(0).get("GIS_STATUS").equals("PENDING")) {
        GIS_Status = "Pending";
      }

      LOGGER.info("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + GIS_Status);
      pageActions.select_GISStatusDropdown_val(GIS_Status);
      if (GIS_Status.equals("Validated")) {
        pageActions.enter_val_in_ToMilePost(1.0);
      }
    }
  }

  @Then("Search results grid should show selected {string} value under Respective Column")
  public void validate_GISStatusattribute_searchResults(String gis_status) throws Throwable {
    boolean nosearch_results = pageActions.Search_results_displayed();
    List<String> colvalues = Arrays.asList(gis_status);
    pageActions.add_colums_to_result_table(colvalues);
    if (!nosearch_results) {
      Set<String> GIS_Results = pageActions.fetch_data_from_resultstable(colvalues.get(0));
      LOGGER.info("Value in result table" + GIS_Results);
      if (GIS_Status.equals("Pending")) {
        Assertions.assertTrue(GIS_Results.containsAll(new HashSet<>(Arrays.asList(GIS_Status.toUpperCase()))));
      } else if (GIS_Status.equals("Validated")) {
        Assertions.assertTrue(new HashSet<>(Arrays.asList("CONFLATED", "REMOVED")).containsAll(GIS_Results));
      }
    } else {
      LOGGER.info("No search Records are found for Selected Criteria");
      LOGGER.info("No search Records are found for Selected Criteria");
    }
  }



  /* All Track Attributes1 Search Functionality */
  @Given("For Track Attributes Search Functionality,I use following values fetched from DB as my search criteria")
  public void select_AllTrack_Attributes(DataTable dataTable) throws Throwable {
    String sqlQuery = "SELECT MP_PREFIX_I ,TRACK_NAME_C ,TRACK_STATE_X ,TRACK_TYPE_X ,TRACK_STATUS_X FROM LINES_OWN.V_TURNOUT "
        + "WHERE TRACK_STATE_X IS NOT NULL AND TRACK_STATUS_X IS NOT NULL AND TRACK_TYPE_X IS NOT NULL AND MP_PREFIX_I IS NOT NULL AND TRACK_NAME_X  IS NOT NULL "
        + "ORDER BY DBMS_RANDOM.RANDOM FETCH FIRST 1 ROWS ONLY";
    List<Map<String, Object>> testdata = dbUtils.FetchDatabaseRecords(sqlQuery);

    if (testdata.size() == 0) {
      LOGGER.info("No Database results found for selected criteria");
      Assume.assumeFalse(testdata.size() == 0);

    } else {
      prefix_2 = (String) testdata.get(0).get("MP_PREFIX_I");
      trackName_2 = (String) testdata.get(0).get("TRACK_NAME_C");
      trackType_2 = (String) testdata.get(0).get("TRACK_TYPE_X");
      if (testdata.get(0).get("TRACK_STATE_X").equals("Out of Service")) {
        trackState_2 = "Out Of Service";
      } else {
        trackState_2 = (String) testdata.get(0).get("TRACK_STATE_X");
      }
      trackStatus_2 = (String) testdata.get(0).get("TRACK_STATUS_X");
      LOGGER.info("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(0) + ":" + prefix_2);
      pageActions.select_prefixDropdown_val(prefix_2, "dummy");
      LOGGER.info("Entered values in respective fields");

      LOGGER.info("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(1) + ":" + trackName_2);
      pageActions.select_trackNamexDropdown_val(trackName_2, "dummy");
      LOGGER.info("Entered values in respective fields");

      LOGGER.info("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(2) + ":" + trackState_2);
      pageActions.select_trackStateDropdown_val(trackState_2, "dummy");
      LOGGER.info("Entered values in respective fields");

      LOGGER.info("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(3) + ":" + trackType_2);
      pageActions.select_trackTypeDropdown_val(trackType_2, "dummy");
      LOGGER.info("Entered values in respective fields");


      LOGGER.info("Test Data Fetched from Database is : " + dataTable.transpose().asList(String.class).get(4) + ":" + trackStatus_2);
      pageActions.select_trackStatusxDropdown_val(trackStatus_2, "dummy");
      // pageActions.enter_val_in_ToMilePost(20);
      LOGGER.info("Entered values in respective fields");
    }
  }

  @Then("Track Attributes search results grid should show values of selected criteria Under below columns Respectively")
  public void validate_trackattributes_searchResults(DataTable dataTable) throws Throwable {
    boolean nosearch_results = pageActions.Search_results_displayed();
    List<String> colnames = new ArrayList<>(dataTable.transpose().asList(String.class));
    if (trackState_2.equals("Out Of Service")) {
      trackState_2 = "Out of Service";
    }
    List<String> colvalues = new ArrayList<>(Arrays.asList(prefix_2, trackName_2, trackState_2, trackType_2, trackStatus_2));
    pageActions.add_colums_to_result_table(colnames);
    if (!nosearch_results) {
      Boolean asser_result = pageActions.fetch_data_from_resultstable_pagination_colGroup(colnames, colvalues);
      Assert.assertTrue(asser_result);
    } else {
      LOGGER.info("No search Records are found for Selected Criteria");
      LOGGER.info("No search Records are found for Selected Criteria");
    }
  }

  /* Clear Filter Functionality */
  @Given("For Clear Filter Functionality ,I use following values fetched from DB as my search criteria")
  public void for_clear_filter_functionality_i_select_values(DataTable dataTable) throws Throwable {
    select_ActiveTrack_Attributes("Active", dataTable);
    contextMap.get(driver.hashCode()).scenario.attach(takescreenshot.getScreenshotAs(OutputType.BYTES), "image/png", "screenShot");
  }

  @Then("Current Filters should show the values of search criteria with corresponding column attributes")
  public void current_filters_should_show_the_values(DataTable dataTable) throws Throwable {
    List<String> asList = dataTable.transpose().asList(String.class);
    String results = pageActions.validate_current_Filter_ClearFilter();
    LOGGER.info("Search Results displayed values are:" + results);
    Assert.assertTrue(results.contains(asList.get(0) + "=" + trackState_1));
    LOGGER.info("Track_State Field Assertion passed");
    Assert.assertTrue(results.contains(asList.get(1) + "=" + trackType_1));
    LOGGER.info("Track_Type Field Assertion passed");
    Assert.assertTrue(results.contains(asList.get(2) + "=" + trackStatus_1));
    LOGGER.info("Track_Status Field Assertion passed");
  }

  @Then("Search results should display records of selected criteria but not {string}")
  public void validate_filtered_searchResults(String norecords) throws InterruptedException {
    Assert.assertFalse(pageActions.Search_results_displayed());
    LOGGER.info("Search results are displayed for selected criteria");
  }

  @When("I hit  the Clear Filter button")
  public void i_hit_the_clear_filter_button() throws Throwable {
    pageActions.click_clearFilter();
    LOGGER.info("Performed Clear filter Operation");
  }

  @Then("Current Filter should display default value {string}")
  public void current_filter_should_display_default_value_something(String statusString) throws Throwable {
    String results = pageActions.validate_current_Filter_Functionality();
    LOGGER.info(results + "displayed in current filters");
    Assert.assertTrue(results.contains(statusString));
    LOGGER.info("Clear filter assertion passed ");
  }

  @Then("Search results should display {string}")
  public void validate_clearfilter_searchResults(String norecords) throws InterruptedException {
    Assert.assertEquals(norecords, pageActions.Fetch_no_such_records());
    LOGGER.info(norecords + "displayed after clear filter operation");
  }


  /* Save Report Functionality */
  @Given("For Save Report Functionality, I use following values fetched from DB as my search criteria")
  public void select_valuesin_for_saveReport(DataTable dataTable) throws Throwable {
    select_ActiveTrack_Attributes("Active", dataTable);
  }

  @Then("Search Results should be displayed and add below columns to the result table")
  public void search_results_should_be_displayed(DataTable dataTable) throws Throwable {
    pageActions.clickon_search();
    boolean nosearch_results = pageActions.Search_results_displayed();
    if (!nosearch_results) {
      pageActions.add_colums_to_result_table(dataTable.transpose().asList(String.class));
      LOGGER.info("Searh results are Displayed ");
    } else {
      LOGGER.info("No search Records are found for Selected Criteria");
      LOGGER.info("No search Records are found for Selected Criteria");
    }
  }

  @When("Click Save Report button from actions menu,Enter Report name as {string} and press the Save button")
  public void press_save_report_button(String reportName) throws Throwable {
    saveReport_name = reportName;
    pageActions.saveReport(reportName);
    LOGGER.info("Reported saved with name :" + reportName);

  }

  @Then("{string} message should be displayed on the UI page")
  public void Validation_of_Success_message(String expt_message) throws Throwable {
    String act_message = pageActions.reportSaved_label();
    Assert.assertEquals(expt_message, act_message);
    LOGGER.info(act_message + "message displayed on the UI Page");

  }

  @Then("Saved report should we avaialable in Saved Report dropdown")
  public void check_reportin_savedReports_drpdwn() {
    pageActions.check_reports_in_savedreports(saveReport_name);
  }

  @Then("Current Filters should display default value {string}")
  public void current_filters_should_display_default_value_something(String expt_filter_text) throws Throwable {
    String act_filter_text = pageActions.validate_current_Filter_Functionality();
    Assert.assertEquals(expt_filter_text, act_filter_text);
    LOGGER.info("Current filters displayed the value" + act_filter_text);
  }

  @And("Search results should be Cleared and display {string}in results table")
  public void search_results_should_be_cleared_and_display_somethingin_results_table(String norecords) throws Throwable {
    boolean nosearch_results = pageActions.Search_results_displayed();
    if (!nosearch_results) {
      util.waitByTime(2000);
      LOGGER.info("Searh results are Displayed ");
    } else {
      Assert.assertEquals(pageActions.noRecordsFound(), norecords);
      LOGGER.info("No search Records are found for Selected Criteria");
      LOGGER.info("No search Records Assertion passed");
    }

  }

  @When("I select {string} under Saved Reports dropdown")
  public void select_saved_report_from_dropdown(String reporttitle) throws Throwable {
    pageActions.select_report_from_saved_list(reporttitle);


  }

  @Then("Save Report Functionality Search Results should show values of selected criteria Under respective columns")
  public void search_results_should_show_values(DataTable dataTable) throws Throwable {
    validate_activetrackattributes_searchResults("Active", dataTable);
  }


  /* Delete Report Functionality */

  @Given("For Delete Report Functionality, I use following values fetched from DB as my search criteria")
  public void Select_values_for_search_criteria(DataTable dataTable) throws Throwable {
    select_ActiveTrack_Attributes("Active", dataTable);

  }

  @Then("Save the Search results with Report name {string}")
  public void save_the_search_results_with_report_name(String reportName) throws Throwable {
    delete_Reportname = reportName;
    pageActions.saveReport(reportName);
    LOGGER.info("Search Results saved with name :" + delete_Reportname);
    pageActions.check_reports_in_savedreports(reportName);
    LOGGER.info(delete_Reportname + ":Report Displayed in the saved reports Dropdown");

  }

  @When("I click on Delete Shared Report option from the Actions menu")
  public void i_click_on_delete_shared_report_option_from_the_actions_menu() throws Throwable {
    pageActions.click_on_Delete_shared_report_link(delete_Reportname);
    LOGGER.info("Clicked on the Delete report option from action menu");
  }

  @Then("Delete Shared Report Window Should show the Saved Report {string}")
  public void delete_shared_report_window_should_show_the_saved_report(String reportName) throws Throwable {
    pageActions.check_reports_in_deletedreports_list(reportName);
    LOGGER.info(reportName + ": Report displayed in delete report list");
  }

  @When("I click on the Delete Icon for the report to be deleted")
  public void click_on_delete_report_icon() throws Throwable {
    pageActions.click_on_Report_deleteButton(delete_Reportname);
    LOGGER.info("Clicked the Delete Report Button");
    pageActions.click_closebtn();
    LOGGER.info("Clicked on Close button delete report window");
  }

  @Then("Selected Report should be deleted ,Deleted Report should not be displayed Saved Reports dropdown.")
  public void selected_report_should_be_deleted() throws Throwable {
    pageActions.check_reports_in_savedreports(delete_Reportname);
    LOGGER.info(delete_Reportname + ":Report not available in saved reports dropdown");
  }



  /* Export Report Functionality */

  @Given("For Export Functionality,I use following values fetched from DB as my search criteria")
  public void enter_vlaues_forExport_functionality(DataTable dataTable) throws Throwable {
    select_ActiveTrack_Attributes("Active", dataTable);
  }

  @When("I Press the Export button on the UI page")
  public void press_export_btn() throws Throwable {
    pageActions.press_exportButton();

  }

  @Then("Search results should be exported to a file and Saved in root directory")
  public void check_Report_exported() throws Throwable {
    pageActions.Check_report_Exported();

  }

  @And("Validate the Exported results against the search Criteria Under Respective Columns Names")
  public void validate_the_exported_results(DataTable dataTable) throws Throwable {
    boolean nosearch_results = pageActions.Search_results_displayed();
    if (!nosearch_results) {
      List<String> colnames = dataTable.transpose().asList(String.class);
      Set<String> act_result = pageActions.validate_exported_results(colnames);
      LOGGER.info("Search Results displayed as:" + act_result);
      Assert.assertTrue(act_result.containsAll(Arrays.asList(trackState_1, trackType_1, trackStatus_1)));
      util.waitByTime(2000);
      LOGGER.info("Searh results are validated ");

    } else {
      LOGGER.info("No search Records are found for Selected Criteria");
      LOGGER.info("No search Records are found for Selected Criteria");
    }
  }

  /* Pagination Functionality */

  @Given("For Pagination Links functionality, I use following values fetched from DB as my search criteria")
  public void select_values_for_pagination_validation(DataTable dataTable) throws Throwable {
    select_ActiveTrack_Attributes("Active", dataTable);

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

}


