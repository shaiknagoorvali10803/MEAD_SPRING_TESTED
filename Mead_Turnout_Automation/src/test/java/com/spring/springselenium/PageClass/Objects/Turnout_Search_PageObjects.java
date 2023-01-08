package com.spring.springselenium.PageClass.Objects;

import java.util.List;


import com.spring.springselenium.Configuraion.annotation.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
@Page
public class Turnout_Search_PageObjects {

  @FindBy(how = How.XPATH, using = "// i[@class='single-clear-icon csx-common_remove_circle']")
  public WebElement asset_status_clear_btn;

  @FindBy(how = How.XPATH, using = "//div[@id='turnoutClearPrefixBtn']//i")
  public WebElement prefix_filter_clear;
  @FindBy(how = How.XPATH, using = "//div[@id='multiprefix']//span[@class='ui-icon ui-icon-triangle-1-s']")
  public WebElement prefix_drp_dwn;


  @FindBy(how = How.XPATH, using = "//div[@id='turnoutClearTrackNameBtn']//i")
  public WebElement trackname_filter_clear;
  @FindBy(how = How.XPATH, using = "//div[@id='multiTrackNames']//span[@class='ui-icon ui-icon-triangle-1-s']")
  public WebElement trackname_drp_dwn;



  @FindBy(how = How.XPATH,
      using = "//table[@id='SearchFilerPanel']//descendant::label[normalize-space()='From Milepost']//parent::td//following-sibling::td//input")
  public WebElement frommilepost_input;

  @FindBy(how = How.XPATH,
      using = "//table[@id='SearchFilerPanel']//descendant::label[normalize-space()='To Milepost']//parent::td//following-sibling::td//input")
  public WebElement tomilepost_input;



  @FindBy(how = How.XPATH, using = "//div[@id='turnoutClearTrackStatusBtn']//i")
  public WebElement trackstatus_filter_clear;
  @FindBy(how = How.XPATH, using = "//div[@id='multiTrackStatus']//span[@class='ui-icon ui-icon-triangle-1-s']")
  public WebElement trackstatus_drp_dwn;


  @FindBy(how = How.XPATH, using = "//div[@id='turnoutClearTrackTypeBtn']//i")
  public WebElement tracktype_filter_clear;
  @FindBy(how = How.XPATH, using = "//div[@id='multiTrackTypes']//span[@class='ui-icon ui-icon-triangle-1-s']")
  public WebElement tracktype_drp_dwn;


  @FindBy(how = How.XPATH, using = "//div[@id='turnoutClearTrackStateBtn']//i")
  public WebElement trackstate_filter_clear;
  @FindBy(how = How.XPATH, using = "//div[@id='multiTrackStates']")
  public WebElement trackstate_drp_dwn;

  @FindBy(how = How.XPATH, using = "//div[@id='GISChangeStatus']//span[@class='ui-icon ui-icon-triangle-1-s ui-c']")
  public WebElement GISStatus_drp_dwn;


  @FindBy(how = How.XPATH, using = "//div[@id='turnoutClearAssetStatusBtn']//i[@class='single-clear-icon csx-common_remove_circle']")
  public WebElement track_Asset_status_cler_btn;


  @FindBy(how = How.XPATH, using = "//span[normalize-space()='Customized columns']")
  public WebElement Customized_column;
  @FindBy(how = How.XPATH, using = "//span[normalize-space()='Apply Changes']")
  public WebElement apply_changes;
  @FindBy(how = How.XPATH, using = "(//button[contains(@id,'j_idt')]//span[normalize-space()='Search'])[1]")
  public WebElement search_button;
  @FindBy(how = How.XPATH, using = "(//div[@class='ui-datatable-tablewrapper'])[1]//descendant::th//span[@class='ui-column-title']")
  public List<WebElement> result_table_hdr;
  @FindBy(how = How.XPATH, using = "// td[normalize-space()='No records found.']")
  public WebElement nosuchrecords;
  @FindBy(how = How.XPATH, using = "//div[@class='x-loader x-loader-train']")
  public WebElement loader;
  @FindBy(how = How.XPATH, using = "//tbody[@id='turnoutResultForm:turnoutDataTable_data']//td")
  public List<WebElement> nosuchrecords1;



  @FindBy(how = How.XPATH, using = "//select[@name='turnoutResultForm:turnoutDataTable_rppDD']")
  public WebElement noof_records_drpdwn;
  @FindBy(how = How.XPATH, using = "//a[@class='ui-paginator-first ui-state-default ui-corner-all']")
  public WebElement first_page_link;
  @FindBy(how = How.XPATH, using = "//a[@class='ui-paginator-last ui-state-default ui-corner-all']")
  public WebElement last_page_link;
  @FindBy(how = How.XPATH, using = "//div[@id='turnoutResultForm:turnoutDataTable_paginator_bottom']//span[@class='ui-paginator-current']")
  public WebElement page_no_text;
  @FindBy(how = How.XPATH, using = "//td[contains(text(),'Found Records')]")
  public WebElement found_records;


  @FindBy(how = How.XPATH, using = "//span[@class='ui-icon ui-icon-triangle-1-e']")
  public WebElement currentfilter_drp_dwn_click;
  @FindBy(how = How.XPATH, using = "//div[normalize-space()='Current Filters']//span[@class='ui-icon ui-icon-triangle-1-s']")
  public WebElement currentfilter_drp_dwn_unclick;
  @FindBy(how = How.XPATH, using = "//label[@id='currentCommonFilter']")
  public WebElement currentcommonfilter_text;
  @FindBy(how = How.XPATH, using = "//label[@id='currentTurnoutAttFilter']")
  public WebElement currentturnoutfilter_text;


  @FindBy(how = How.XPATH, using = "//span[normalize-space()='Clear Filters']")
  public WebElement clearfilter_btn;


  @FindBy(how = How.XPATH, using = "//td[contains(text(),'Found Records')]")
  public WebElement total_records;



  /* Share Report Functionality */
  @FindBy(how = How.XPATH, using = "//span[normalize-space()='Actions']")
  public WebElement action_btn;
  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Save Report')]")
  public WebElement save_report_btn;
  @FindBy(how = How.XPATH, using = "//input[@id='reportName']")
  public WebElement report_name;
  @FindBy(how = How.XPATH, using = "//span[normalize-space()='Save']")
  public WebElement save_btn;
  @FindBy(how = How.XPATH, using = "//span[@class='ui-menuitem-text'][normalize-space()='Share Report']")
  public WebElement share_btn;
  @FindBy(how = How.XPATH, using = "//span[@class='ui-dialog-title' and text()='Share Report']")
  public WebElement share_window;
  @FindBy(how = How.XPATH, using = "//div[@id='emailToForm:searchByMany']//span[@class='ui-icon ui-icon-triangle-1-s ui-c']")
  public WebElement sel_report_drp_dwn;
  @FindBy(how = How.XPATH, using = "//span[normalize-space()='To...']")
  public WebElement To_email;
  @FindBy(how = How.XPATH, using = "//input[contains(@id,'emailToForm:j_idt')]")
  public WebElement RACF_search_text;
  @FindBy(how = How.XPATH, using = "//button[contains(@id,'emailToForm:j_idt')]//span[@class='ui-button-text ui-c'][normalize-space()='Search']")
  public WebElement RACF_search_btn;

  @FindBy(how = How.XPATH, using = "//span[@class='ui-messages-error-summary']")
  public WebElement no_user_found;
  @FindBy(how = How.XPATH, using = "//span[@class='ui-messages-info-summary']")
  public WebElement user_found;
  @FindBy(how = How.XPATH, using = "//img[contains(@id,'emailToForm:searchResultsTable:0:j_idt')]")
  public WebElement RACF_search_res_sel;
  @FindBy(how = How.XPATH, using = "//div[@id='emailToForm:recipientsList_content']//span[@class='ui-button-text ui-c']")
  public List<WebElement> no_emails;
  @FindBy(how = How.XPATH, using = "//span[normalize-space()='Finished']")
  public WebElement RACF_search_finished;
  @FindBy(how = How.XPATH, using = "//span[normalize-space()='To me']")
  public WebElement To_me;
  @FindBy(how = How.XPATH, using = "//textarea[contains(@id,'emailToForm:j_idt')]")
  public WebElement email_comment;
  @FindBy(how = How.XPATH, using = "//span[normalize-space()='Send']")
  public WebElement email_send_btn;
  @FindBy(how = How.XPATH, using = "//div[@id='turnoutResultForm:turnoutResultFormMsg_container']")
  public WebElement email_sent_text;



  /* Delete Report Functionality */
  @FindBy(how = How.XPATH, using = "//div[@id='personalReports']//span[@class='ui-icon ui-icon-triangle-1-s ui-c']")
  public WebElement saved_reports_drp_dwn;
  @FindBy(how = How.XPATH, using = "//div[@id='personalReports_panel']//div[@class='ui-selectonemenu-items-wrapper']//li[not(@data-label ='&nbsp;')]")
  public List<WebElement> saved_reports_list;
  @FindBy(how = How.XPATH, using = "//span[@class='ui-menuitem-text'][normalize-space()='Delete Save Report']")
  public WebElement delete_shared_report_link;
  @FindBy(how = How.XPATH, using = "//tbody[@id='editSaveReportTable_data']//td[text()]")
  public WebElement delete_shared_report_list;
  @FindBy(how = How.XPATH, using = "//span[text()='Delete Saved Report']//following-sibling::a[@aria-label='Close']")
  public WebElement close_window;


  /* Export Report Functionality */


  @FindBy(how = How.XPATH, using = "//span[contains(text(),'Export')]")
  public WebElement export_btn;



  /* Save Report Functionality */

  @FindBy(how = How.ID, using = "savedreport_container")
  public WebElement saved_label;
  @FindBy(how = How.ID, using = "radioResultForm:radioResultFormMsg_container")
  public WebElement saved_label1;


}
