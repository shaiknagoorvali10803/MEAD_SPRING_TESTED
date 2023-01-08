package com.spring.springselenium.PageClass.Objects;

import java.util.List;

import com.spring.springselenium.Configuraion.annotation.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
@Page
public class Radio_Search_PageObjects {

  @FindBy(how = How.XPATH, using = "//i[@class='single-clear-icon csx-common_remove_circle']")
  public WebElement status_filter_clear;
  @FindBy(how = How.XPATH, using = "//div[@id='multiStatusCodes']//span[@class='ui-icon ui-icon-triangle-1-s']")
  public WebElement status_drp_dwn;
  @FindBy(how = How.XPATH, using = "//div[@id='radioTypeCodes']//span[@class='ui-icon ui-icon-triangle-1-s']")
  public WebElement radio_drp_dwn;
  @FindBy(how = How.ID, using = "autoUserSearch_input")
  public WebElement racf_text_box;
  @FindBy(how = How.XPATH, using = "//span[normalize-space()='Customized columns']")
  public WebElement Customized_column;
  @FindBy(how = How.XPATH, using = "//span[normalize-space()='Apply Changes']")
  public WebElement apply_chnages;
  @FindBy(how = How.XPATH, using = "(//button[contains(@id,'j_idt')]//span[normalize-space()='Search'])[1]")
  public WebElement search_button;
  @FindBy(how = How.XPATH, using = "(//div[@class='ui-datatable-tablewrapper'])[1]//descendant::th//span[@class='ui-column-title']")
  public List<WebElement> result_table_hdr;
  @FindBy(how = How.XPATH, using = "// td[normalize-space()='No records found.']")
  public WebElement nosuchrecords;
  @FindBy(how = How.XPATH, using = "//li[@class='ui-autocomplete-item ui-autocomplete-list-item ui-corner-all ui-state-highlight']")
  public WebElement racf_auto_complete;
  @FindBy(how = How.XPATH, using = "//div[@class='x-loader x-loader-train']")
  public WebElement loader;
  @FindBy(how = How.XPATH, using = "//tbody[@id='radioResultForm:radioDataTable_data']//td")
  public List<WebElement> nosuchrecords1;


  @FindBy(how = How.XPATH, using = "//div[@id='multiDeptCodes']//span[@class='ui-icon ui-icon-triangle-1-s']")
  public WebElement dept_drp_dwn;
  @FindBy(how = How.XPATH,
      using = "(//label[contains(text(),'Power (in Watts)')]//parent::td//following-sibling::td//input[contains(@id,'j_idt')])[1]")
  public WebElement power_text;
  @FindBy(how = How.XPATH, using = "//div[@id='multiRadioShopCodes']//span[@class='ui-icon ui-icon-triangle-1-s']")
  public WebElement radio_shop_drp_dwn;
  @FindBy(how = How.ID, using = "radioShipDateFrom_input")
  public WebElement ship_from_date;
  @FindBy(how = How.ID, using = "radioShipDateTo_input")
  public WebElement ship_to_date;
  @FindBy(how = How.XPATH, using = "//span[normalize-space()='Apply Changes']")
  public WebElement apply_changes;


  @FindBy(how = How.XPATH, using = "//div[@id='multiDeptCodes']//span[@class='ui-icon ui-icon-triangle-1-s']")
  public WebElement assgin_dept_drp_dwn;
  @FindBy(how = How.XPATH, using = "//label[@id='multiDeptCodes_label']")
  public WebElement assgin_dept_drp_dwn_value;
  @FindBy(how = How.XPATH, using = "//select[@name='radioResultForm:radioDataTable_rppDD']")
  public WebElement result_table_pag_drop_dwn;
  @FindBy(how = How.XPATH, using = "//a[@class='ui-paginator-first ui-state-default ui-corner-all']")
  public WebElement result_table_first_page;
  @FindBy(how = How.XPATH, using = "//a[@class='ui-paginator-last ui-state-default ui-corner-all']")
  public WebElement result_table_last_page;
  @FindBy(how = How.XPATH, using = "//div[@id='radioResultForm:radioDataTable_paginator_bottom']//span[@class='ui-paginator-current']")
  public WebElement result_table_page_count;
  @FindBy(how = How.XPATH,
      using = "//span[normalize-space()='Assigned Department']//ancestor::thead//following-sibling::tbody//descendant::td[2]//span")
  public WebElement result_table_dept_validation;
  @FindBy(how = How.ID, using = "autoUserSearch_input")
  public WebElement RACF_search_box;



  @FindBy(how = How.XPATH, using = "//span[@class='ui-icon ui-icon-triangle-1-e']")
  public WebElement currentfilter_drp_dwn_click;
  @FindBy(how = How.XPATH, using = "//div[normalize-space()='Current Filters']//span[@class='ui-icon ui-icon-triangle-1-s']")
  public WebElement currentfilter_drp_dwn_unclick;
  @FindBy(how = How.XPATH, using = "//label[@id='currentRadioAttFilter']")
  public WebElement currentfilter_text;
  @FindBy(how = How.XPATH, using = "//span[normalize-space()='Clear Filters']")
  public WebElement clearfilter_btn;
  @FindBy(how = How.XPATH, using = "//div[@id='multiInvCodes']//span[@class='ui-icon ui-icon-triangle-1-s']")
  public WebElement inventory_status_drp_dwn;



  @FindBy(how = How.ID, using = "radioPoDateFrom_input")
  public WebElement po_from_date;
  @FindBy(how = How.ID, using = "radioPoDateTo_input")
  public WebElement po_to_date;


  @FindBy(how = How.XPATH, using = "//div[@id='multiModelCodes']//span[@class='ui-icon ui-icon-triangle-1-s']")
  public WebElement model_type_drpdwn;
  @FindBy(how = How.NAME, using = "radioResultForm:radioDataTable_rppDD")
  public WebElement noof_records_drpdwn;
  @FindBy(how = How.XPATH, using = "//a[@class='ui-paginator-last ui-state-default ui-corner-all']")
  public WebElement last_page_link;
  @FindBy(how = How.XPATH, using = "//a[@class='ui-paginator-first ui-state-default ui-corner-all']")
  public WebElement first_page_link;
  @FindBy(how = How.XPATH, using = "//div[@id='radioResultForm:radioDataTable_paginator_bottom']//span[@class='ui-paginator-current']")
  public WebElement page_no_text;
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
  @FindBy(how = How.XPATH, using = "//div[@id='radioResultForm:radioResultFormMsg_container']")
  public WebElement email_sent_text;



  /* Delete Report Functionality */
  @FindBy(how = How.XPATH, using = "//div[@id='personalReports']//span[@class='ui-icon ui-icon-triangle-1-s ui-c']")
  public WebElement saved_reports_drp_dwn;
  @FindBy(how = How.XPATH, using = "//div[@id='personalReports_panel']//div[@class='ui-selectonemenu-items-wrapper']//li[not(@data-label ='&nbsp;')]")
  public List<WebElement> saved_reports_list;
  @FindBy(how = How.XPATH, using = "//span[@class='ui-menuitem-text'][normalize-space()='Delete Saved Report']")
  public WebElement delete_shared_report_link;
  @FindBy(how = How.XPATH, using = "//tbody[@id='editSaveReportTable_data']//td")
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
