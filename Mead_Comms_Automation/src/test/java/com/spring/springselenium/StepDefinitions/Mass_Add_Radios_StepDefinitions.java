package com.spring.springselenium.StepDefinitions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.spring.springselenium.Configuraion.annotation.LazyAutowired;
import com.spring.springselenium.PageClass.Actions.Mass_Add_Radios_PageActions;
import com.spring.springselenium.PageClass.Actions.Mead_Comms_Home_PageActions;
import com.spring.springselenium.SeleniumUtils.SeleniumUtils;
import org.junit.Assert;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;


public class Mass_Add_Radios_StepDefinitions {
  private static Map<Integer, ScenarioContext> contextMap = new HashMap<>();
  @LazyAutowired
  private WebDriver driver;
  @LazyAutowired
  private SeleniumUtils utils;
  @LazyAutowired
  Mass_Add_Radios_PageActions pageActions;
  static Set<String> radioAdd_expt_Values;

  @Autowired
  ScenarioContext scenarioContext;

  static String url = null;
  @LazyAutowired
  private TakesScreenshot takescreenshot;

  @PostConstruct
  private void init() {
    contextMap.put(driver.hashCode(), scenarioContext);
  }

  /* Mass Add Radios with all fields Validation */

  @Given("I should navigate to Radio Mass Add Page by clicking Mass Radio option from Actions menu")
  public void navigate_to_radio_mass_add_page() throws Throwable {
    pageActions.clickon_Mass_Add_radios_selection();
    contextMap.get(driver.hashCode()).getScenario().log("Click on Mass Radio Selection ");

  }

  @When("I Navigated to {string} page")
  public void user_navigated_to_something_page(String expt_title) throws Throwable {
    Assert.assertEquals(expt_title, pageActions.check_page_title());
    contextMap.get(driver.hashCode()).getScenario().log("Navigated to Radio Mass add page ");

  }

  @Then("I Enter the Data in {string},{string},{string} and {string}.")
  public void i_enter_the_data_in_Allfields(String radiotype, String numberofradios, String racfid, String vechicleno) throws Throwable {
    radioAdd_expt_Values = pageActions.enter_allfields(radiotype, numberofradios, racfid, vechicleno);
    contextMap.get(driver.hashCode()).getScenario().log("Entered values in all the fields of UI Page");

  }

  @And("Press Save to Add the New Radios with All fields to the Inventory")
  public void press_save_to_add_the_new_radios_to_the_inventory() throws Throwable {
    pageActions.save_functionality();
    contextMap.get(driver.hashCode()).getScenario().log("Pressed Save button on UI Page for creating radios ");
    Thread.sleep(3000);
  }

  @Then("UI page should navigated to {string} page by displaying newly created Radios")
  public void ui_page_navigated_to_something_page_by_displaying_newly_created_radios(String expt_title) throws Throwable {
    String act_title = pageActions.inlineEdit_page_Validation();
    Assert.assertEquals(expt_title, act_title);
    contextMap.get(driver.hashCode()).getScenario().log("Displayed the message after creating radios" + pageActions.inlineEdit_page_Validation());
  }

  @And("Validate the created records in Radio InlineEdit UI page and inventory")
  public void validate_inlinedit_page_records() throws Throwable {
    /* Result table validation */
    contextMap.get(driver.hashCode()).getScenario().log("Values used for created for creation radios ares:" + radioAdd_expt_Values);
    Set<String> inlineEdit_column_values = pageActions.inlineEdit_column_values();
    contextMap.get(driver.hashCode()).getScenario().log("Values fetched from InlineEdit page afte creating Radios:" + inlineEdit_column_values);
    Assert.assertEquals(radioAdd_expt_Values, inlineEdit_column_values);

    /* Database Validation validation */

    Set<String> inlineEdit_col_values = pageActions.inlineEdit_col_DBValidation();
    Set<String> inlineEdit_DB_values = pageActions.fetcht_DB_createdRadios();
    Assert.assertEquals(inlineEdit_col_values, inlineEdit_DB_values);
    contextMap.get(driver.hashCode()).getScenario().log("Values fetched from DB:" + inlineEdit_DB_values);
    contextMap.get(driver.hashCode()).getScenario().log("Values fetched from inlineEdit for DB:" + inlineEdit_col_values);

  }

  /* Mass Add Radios UI page Inventory status Dropdown validation */

  @And("I Select the {string} staus from drop down")
  public void i_select_the_something_radio_from_drop_down(String status) {
    pageActions.select_status_fields(status);
    contextMap.get(driver.hashCode()).getScenario().log("Selected " + status + "from the dropdown ");
  }

  @Then("Inventory status drop down should show below options")
  public void inventory_status_drop_down_should_show_below_options(DataTable dataTable) throws Throwable {
    List<String> act = pageActions.validateStatus();
    contextMap.get(driver.hashCode()).getScenario().log("Inventory status values are " + act);
    Assert.assertEquals(act, dataTable.transpose().asList(String.class));
    // Assert.assertTrue(act.equals(dataTable.asList()));

  }

  @And("Press Cancel button on the UI page")
  public void press_cancel_btn() throws Throwable {
    pageActions.pressCancelBtn();
    contextMap.get(driver.hashCode()).getScenario().log("pressed cancel button");
  }

  @Then("User should Navigated to Radio Search page with Title {string}")
  public void ui_page_navigated_to_created_radios(String expt_title) throws Throwable {
    String act_title = pageActions.navigate_RadioSearch();
    Assert.assertEquals(expt_title, act_title);
    contextMap.get(driver.hashCode()).getScenario().log("UI page navigated to " + act_title + "page");
  }

  /* Mass Add Radios UI page Vehicle field validation */

  @And("I Select the {string} From Radio Type dropdown")
  public void select_RACF_dropdown(String radiotype) throws Throwable {
    if (radiotype.equals("Portable Radio")) {
      pageActions.select_portableradio(radiotype);
    } else if (radiotype.equals("Mobile Radio")) {
      pageActions.select_mobileradio(radiotype);
    }
  }

  @Then("{string} field with Vehicle radio button on UI Page should be displayed")
  public void verify_AssignTo_Field_on_ui_page(String assignTo) throws Throwable {
    boolean displayed = pageActions.verify_AssignTo_Field();
    if (displayed) {
      contextMap.get(driver.hashCode()).getScenario().log("assignTo field is displayed");
    }
  }

  @When("I clicked the vehicle Radio button")
  public void clickon_vehicle_Radio_button() throws Throwable {
    pageActions.clickon_vehicle_radiobtn();

  }

  @Then("{string} field should be displayed on UI page,enter {string} in input field")
  public void Vehicle_Field_validation(String expt_title, String vehicleno) throws Throwable {
    boolean displayed = pageActions.verify_vehicle_txtbox(vehicleno);
    if (displayed) {
      contextMap.get(driver.hashCode()).getScenario().log("Vehicle inpt textbox is diaplyed");
    }
  }



  /* Mass Add Radios UI page fields validation */

  @Then("Validate the following Editable text Fileds")
  public void validate_the_following_editable_text_fileds(DataTable dataTable1) throws Throwable {
    Assert.assertTrue(pageActions.validates_text_fields());
    contextMap.get(driver.hashCode()).getScenario().log("Text fields are validated");
  }

  @And("Validate the Following Drops Down fields")
  public void validate_the_following_drops_down_fields(DataTable dataTable2) throws Throwable {
    Assert.assertTrue(pageActions.validates_drpdwn_fields());
    contextMap.get(driver.hashCode()).getScenario().log("DropDown fields are validated");
  }

  @And("Validte the Following Date Fields")
  public void validte_the_following_date_fields(DataTable dataTable3) throws Throwable {
    Assert.assertTrue(pageActions.validates_date_fields());
    contextMap.get(driver.hashCode()).getScenario().log("Date fields are validated");
  }



  /* Mass Add Radios with Mandatory Fields */


  @Then("I Enter the Data in {string},{string},{string} and {string} in mandatory Fields")
  public void I_enter_data_in_MandatoryFields(String radiotype, String numberofradios, String racfid, String vehicleno) throws Throwable {
    radioAdd_expt_Values = pageActions.enter_mandatory_fields(radiotype, numberofradios, racfid, vehicleno);
    contextMap.get(driver.hashCode()).getScenario().log("Values entered in mandatory fields");

  }

  @And("Press Save to Add the New Radios to the Inventory")
  public void Save_radios_toInventory() throws Throwable {
    pageActions.save_functionality();
    contextMap.get(driver.hashCode()).getScenario().log("save button pressed");


  }

  /* Missing Mandatory Fields */

  @Given("For Mass Add Radios with Missing  Madatory Fields,i should navigate to Radio Mass Add Page by clicking Mass Radio option from Actions menu")
  public void navigate_to_radio_mass_add_page_for_missingfields_validation() throws Throwable {
    pageActions.clickon_Mass_Add_radios_selection();

  }

  @When("User Navigated to {string} page for Adding New Radios with Missing Mandatory Fields")
  public void i_navigated_to_RadioMassAdd_page(String expt_title) throws Throwable {
    Assert.assertEquals(expt_title, pageActions.check_page_title());
    contextMap.get(driver.hashCode()).getScenario().log("UI page navigated to " + pageActions.check_page_title());

  }

  @Then("I Enter the Data in with {string} Missed")
  public void i_enter_the_data_in_mandatotyfields_exceptonefield(String missingField) throws Throwable {
    pageActions.enter_mandatory_fields(missingField);
    contextMap.get(driver.hashCode()).getScenario().log("Entered data in all mandatory field missing one field");

  }

  @Then("System Should the Warning Message {string}")
  public void Ui_Page_displays_warning_message(String expt_err_msg) throws Throwable {
    String act_error_msg = pageActions.check_error_msg();
    Assert.assertEquals(expt_err_msg, act_error_msg);
    contextMap.get(driver.hashCode()).getScenario().log(act_error_msg + ":displayed on the UI Page");
  }

}
