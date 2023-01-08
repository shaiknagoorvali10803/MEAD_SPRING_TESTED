package com.spring.springselenium.SeleniumUtils;

import com.spring.springselenium.Configuraion.annotation.Page;
import com.spring.springselenium.Constants.CommonConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.joda.time.Days;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static com.spring.springselenium.Constants.CommonConstants.BEFORE_WAIT_FOR_ELEMENT_IN_BUTTON_CLICK;
import static com.spring.springselenium.Constants.CommonConstants.MM_DD_YYYY_WITH_SLASH;
import static org.junit.jupiter.api.Assertions.fail;

@Page
public class SeleniumUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(SeleniumUtils.class);

  private static final String SPINNER_XPATH = "//app-block-ui/div/p-blockui";
  public static final String ERROR_MSG = "Some error has occurred while performing operation::{}";
  public static final String IS_ENTERED = " is entered";
  public static final String AND_PASSWORD = " and Password: ";
  public static final String USERNAME = "Username: ";
  public static final int DRIVER_WAIT_TIME_IN_SECS = 120;
  private static final String DROPDOWN_ITEM_SELECTOR_IN_OVERLAY = "//ul/li[*]/span[text()='%s']";
  private static final String DROPDOWN_PARTIAL_MATCH_ITEM_SELECTOR_IN_OVERLAY = "//ul/li[*]/span[contains(text(),'%s')]";
  private static final String DROPDOWN_OVERLAY = "//ul";
  private static final String DROPDOWN_CLICKABLE_LABEL = "div/label";
  private static final String DEFAULT_RACFID = "t_client.crew.hr.admin@csx.local";
  private static final String DEFAULT_RACFID_PASSWORD = "*20q#ZUAa7qe1a9!";
  private static final String DEFAULT_SXLOGON_STAGING_URL = "https://crew-qa.go-dev.csx.com/#";
  public static final String LOGINPAGE_USERNAME_TEXTBOX_OBJECT_ID = "okta-signin-username";
  private static final String LOGINPAGE_PASSWORD_TEXTBOX_OBJECT_ID = "okta-signin-password";
  public static final String LOGINPAGE_LOGIN_BUTTON_OBJECT_ID = "okta-signin-submit";
  public static final String downloadPath = System.getProperty("user.dir");
  public static final String testUserName = "t_client.crew.hr.admin@csx.local";
  public static final String testPassWord = "*20q#ZUAa7qe1a9!";
  public static final int thread_sleep = 1000;

  @Autowired
  protected WebDriver driver;
  @Autowired
  protected JavascriptExecutor jsExecutor;
  private int defaultMaxTime = 120;
  private int maxSyncTime = defaultMaxTime;
  private boolean isCustomWait = false;

  /**
   * ---------------------------Maximize Window------------------------------------------
   */
  public void maximizeWindow(final WebDriver driver) {
    driver.manage().window().maximize();
  }

  /**
   * ---------------------------Resize window for Mobile------------------------------------------
   */
  public void resizeWindowForMobile(final WebDriver driver) {
    driver.manage().window().setSize(new Dimension(50, 750));
  }

  /**
   * ---------------------------Logon to non prod------------------------------------------
   */
  public void logonNonProd(WebDriver driver) {
    WebElement userName = driver.findElement(By.id(LOGINPAGE_USERNAME_TEXTBOX_OBJECT_ID));
    WebElement pwd = driver.findElement(By.id(LOGINPAGE_PASSWORD_TEXTBOX_OBJECT_ID));
    WebElement submit = driver.findElement(By.id(LOGINPAGE_LOGIN_BUTTON_OBJECT_ID));
    isElementDisplayed(driver, pwd);
    // SeleniumUtils.setValueToElement(driver, userName, testUserName);
    // SeleniumUtils.setValueToElement(driver, pwd, testPassWord);
    clickElementByWebElementWithOutSendKeys(driver, submit);
  }

  /**
   * ---------------------------Resize window for Tablet------------------------------------------
   */
  public  void resizeWindowForTablet(final WebDriver driver) {
    driver.manage().window().setSize(new Dimension(700, 750));
  }

  /**
   * ------------------------------- System Date in
   * customFormat--------------------------------------
   */
  public  String todayDate(String dateTimeFormat) {
    LocalDateTime dateTime = LocalDateTime.now();
    return dateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat)); // give any custom format
    // like "MM/dd/yyyy
    // | HH:mm"
  }


  /**
   * -------------------------------Add days to current date--------------------------------------
   */
  public  Date addDays(final int daysCount) {
    Date date = new Date();
    final Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, daysCount);
    date = cal.getTime();
    return date;
  }

  /**
   * ------------------------------- Add days to System Date in custom
   * Format--------------------------------------
   */
  public  String addDays(String dateTimeFormat, int days) {
    LocalDateTime dateTime = LocalDateTime.now().plusDays(days);
    return dateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat)); // give any custom format
    // like "MM/dd/yyyy
    // | HH:mm"
  }

  /**
   * ------------------------------- minus days to System Date in custom
   * Format--------------------------------------
   */
  public  String minusDays(String dateTimeFormat, int days) {
    LocalDateTime dateTime = LocalDateTime.now().minusDays(days);
    return dateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat)); // give any custom format
    // like "MM/dd/yyyy
    // | HH:mm"
  }

  /**
   * ------------------------------- Add hours to System Date in custom Format
   */
  public  String addHours(String dateTimeFormat, int hours) {
    LocalDateTime dateTime = LocalDateTime.now().plusHours(hours);
    return dateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat)); // give any custom format
    // like "MM/dd/yyyy
    // | HH:mm"
  }

  /**
   * ------------------------------- Add minutes to System Date in custom
   * Format--------------------------------------
   */
  public  String addMinutes(String dateTimeFormat, int minutes) {
    LocalDateTime dateTime = LocalDateTime.now().plusMinutes(minutes);
    return dateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat)); // give any custom format
    // like "MM/dd/yyyy
    // | HH:mm"
  }

  /**
   * ------------------------------- minus hours to System Date in custom
   * Format--------------------------------------
   */
  public  String minusHours(String dateTimeFormat, int hours) {
    LocalDateTime dateTime = LocalDateTime.now().minusHours(hours);
    return dateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat)); // give any custom format
    // like "MM/dd/yyyy
    // | HH:mm"
  }

  /**
   * ------------------------------- Verification of Webtable sort
   * ----------------------------------------------------
   */

  public  void sortingandCompareWebtableColumns(WebDriver driver, int colNum) {
    driver.findElement(By.xpath("//tr/th[" + colNum + "]")).click();
    List<WebElement> headerList = driver.findElements(By.xpath("//tr/td[" + colNum + "]"));
    List<String> originalList =
        headerList.stream().map(s -> s.findElement(By.xpath("//tr/td[" + colNum + "]")).getText()).collect(Collectors.toList());
    List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());
    Assertions.assertTrue(originalList.equals(sortedList));
  }

  /**
   * ------------------------------- Get the Webtable cell value
   * ----------------------------------------------------
   */

  public String getWebTableData(WebDriver driver, int rowNum, int colNum) {
    WebElement webElement = driver.findElement(By.xpath("//tr[" + rowNum + "]/td[" + colNum + "]"));
    return getValueByElement(driver, webElement);
  }

  /**
   * ------------------------------- File download confirmation
   * --------------------------------------
   */
  public  boolean isFileDownloaded(String partialFileName) {
    File dir = new File(downloadPath);
    File[] files = dir.listFiles();
    boolean flag = false;
    for (int i = 1; i < files.length; i++) {
      if (files[i].getName().contains(partialFileName)) {
        flag = true;
      }
    }
    return flag;
  }

  /**
   * ------------------------------- Get Newest File --------------------------------------
   */

  public  String getTheNewestFile(String filePath, String ext) {
    // File theNewestFile = null;
    File dir = new File(filePath);
    FileFilter fileFilter = new WildcardFileFilter("*." + ext);
    File[] files = dir.listFiles(fileFilter);
    String name = "";
    if (files.length > 0) {
      /** The newest file comes first **/
      Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
      // theNewestFile = files[0];
      name = files[0].getName();
    }
    return name;
  }

  /**
   * -------------------------------Delete file --------------------------------------
   */

  public  void deleteExistingFile(String partialFileName) {
    File dir = new File(downloadPath);
    File[] files = dir.listFiles();
    for (int i = 1; i < files.length; i++) {
      if (files[i].getName().contains(partialFileName)) {
        files[i].delete();
      }
    }
  }

  /**
   * -------------------------------Get file name --------------------------------------
   */
  public  String getFilename(String partialFileName) {
    File dir = new File(downloadPath);
    File[] files = dir.listFiles();
    String reportFileName = "";
    for (int i = 1; i < files.length; i++) {
      if (files[i].getName().contains(partialFileName)) {
        reportFileName = files[i].getName();
      }
    }
    return reportFileName;
  }

  /**
   * -----get the downloadded documnat name  ---------
   */

  public  String getDownloadedDocumentName(String downloadDir, String fileExtension) {
    String downloadedFileName = null;
    boolean valid = true;
    boolean found = false;

    // default timeout in seconds
    long timeOut = 60;
    try {
      // set the path
      Path downloadFolderPath = Paths.get(downloadDir);
      // create a listener
      WatchService watchService = FileSystems.getDefault().newWatchService();
      // add the listener to the path
      downloadFolderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
      long startTime = System.currentTimeMillis();
      do {
        WatchKey watchKey;
        watchKey = watchService.poll(timeOut, TimeUnit.SECONDS);
        long currentTime = (System.currentTimeMillis() - startTime) / 1000;
        // check if 60 seconds already passed
        if (currentTime > timeOut) {
          System.out.println("OPTA: Download operation timed out... Expected file was not downloaded");
          return downloadedFileName;
        }

        for (WatchEvent<?> event : watchKey.pollEvents()) {
          WatchEvent.Kind<?> kind = event.kind();
          if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
            String fileName = event.context().toString();
            // Checks if the file created is a PDF
            if (fileName.endsWith(fileExtension)) {
              downloadedFileName = fileName;
              found = true;
              break;
            }
          }
        }
        if (found) {
          return downloadedFileName;
        } else {
          currentTime = (System.currentTimeMillis() - startTime) / 1000;
          if (currentTime > timeOut) {
            System.out.println("Failed to download expected file");
            return downloadedFileName;
          }
          valid = watchKey.reset();
        }
      } while (valid);
    }

    catch (InterruptedException e) {
      System.out.println("OPTA: Interrupted error - " + e.getMessage());
      e.printStackTrace();
    } catch (NullPointerException e) {
      System.out.println("OPTA: Download operation timed out.. Expected file was not downloaded");
    } catch (Exception e) {
      System.out.println("OPTA: Error occurred - " + e.getMessage());
      e.printStackTrace();
    }
    return downloadedFileName;
  }


  /**
   * -------------------------------Create New Tab and Switch to new tab
   * --------------------------------------
   */
  public void switchNewTab(WebDriver driver) {
    jsExecutor.executeScript("window.open()");
    List<String> tabs = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(1));
  }

  /**
   * -------------------------------Get PDF Content --------------------------------------
   */
  public  String getPDFContent(WebDriver driver, String url) throws IOException {
    driver.get(url);
    URL pdfUrl = new URL(driver.getCurrentUrl());
    InputStream inputStream = pdfUrl.openStream();
    BufferedInputStream bufferedIPS = new BufferedInputStream(inputStream);
    PDDocument doc = PDDocument.load(bufferedIPS);
    String content = new PDFTextStripper().getText(doc);
    String pdfContent = content.toLowerCase();
    doc.close();
    return pdfContent;
  }

  /**
   * -------------------------------Simple Date Format--------------------------------------
   */
  public  String todayDate() {
    return new SimpleDateFormat("MM/dd/yyyy HH:mm").format(new Date());
  }

  /**
   * ------------------------------------------------------------------------------------------------------------------
   * Click on the WebElement With ID and handling WebDriverWait to handle NoSuchElementException
   * Passing Element as String
   */
  public  void clickElementByID(final WebDriver driver, final String elementID) {
    final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementID)));
    wait.until(ExpectedConditions.elementToBeClickable(By.id(elementID)));
    driver.findElement(By.id(elementID)).click();
    waitByTime(1000);
  }

  /**
   * ------------------------------------------------------------------------------------------------------------------
   * Click on the WebElement With XPATH and handling WebDriverWait to handle NoSuchElementException
   * Passing Element as String
   */
  public  void clickElementByXPath(final WebDriver driver, final String elementID) {
    try {
      final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementID)));
      wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementID)));
      driver.findElement(By.xpath(elementID)).click();
      waitByTime(1000);
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
  }

  /**
   * ------------------------------------------------------------------------------------------------------------------
   * Click on the WebElement With XPATH and handling WebDriverWait to handle NoSuchElementException
   * Passing Element as WebElement
   */
  public void clickElementByWebElement(final WebDriver driver, final WebElement elementID) {
    try {
      final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
      wait.until(ExpectedConditions.visibilityOf(elementID));
      wait.until(ExpectedConditions.elementToBeClickable(elementID));
      highlightElement(elementID);
      elementID.click();
      waitByTime(1000);
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
  }

  /**
   * ------------------------------------------------------------------------------------------------------------------
   * Click on the WebElement With XPATH and handling WebDriverWait to handle NoSuchElementException
   * Passing Element as WebElement
   */
  public void clickElementByWebElement(final WebDriver driver, final By locator) {
    try {
      final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
      wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
      wait.until(ExpectedConditions.elementToBeClickable(locator));
      WebElement element =driver.findElement(locator);
      highlightElement(element);
      element.click();
      waitByTime(1000);
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
  }

  public void clickElementbyWebElement(final WebDriver driver, final WebElement elementID) {
    try {
      final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
      wait.until(ExpectedConditions.visibilityOf(elementID));
      wait.until(ExpectedConditions.elementToBeClickable(elementID));
      highlightElement(elementID);
      elementID.sendKeys("");
      elementID.click();
      waitByTime(1000);
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    } catch (final Exception e) {
      // If clickElementbyWebElement method failed to click on the element with sendkeys then this catch block will be executed with calling
      // clickElementbyWebElementWithOutSendKeys method
      clickElementByWebElementWithOutSendKeys(driver, elementID);
    }
  }


  /**
   * ------------------------------------------------------------------------------------------------------------------
   * Click on the WebElement With XPATH and handling WebDriverWait to handle NoSuchElementException
   * Passing Element as WebElement This method doesn't use sendKeys
   */
  public void clickElementByWebElementWithOutSendKeys(final WebDriver driver, final WebElement elementID) {
    try {
      final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
      wait.until(ExpectedConditions.visibilityOf(elementID));
      wait.until(ExpectedConditions.elementToBeClickable(elementID));
      highlightElement(elementID);
      elementID.click();
      waitByTime(1000);
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
  }

  /**
   * ---------------------------------------------------------------------------------------------------
   * Submit Element by XPATH
   */
  public  void submitElementbyXPath(final WebDriver driver, final String elementID) {
    WebElement element=driver.findElement(By.xpath(elementID));
    highlightElement(element);
    jsExecutor.executeScript("arguments[0].click();", element);
    waitByTime(1000);
  }

  /**
   * ----------------------------------------------------------------------------------------------------
   * Scroll till Web Element
   */
  public  void scrollToWebElement(final WebDriver driver, final WebElement webelement) {
    highlightElement(webelement);
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", webelement);
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * Validate Element
   */
  public  void validateElement(final WebDriver driver, final String xpath,final String expectedResult) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
      final WebElement element = driver.findElement(By.xpath(xpath));
      highlightElement(element);
      Assertions.assertEquals(expectedResult.trim(), element.getText().trim());
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * Set value to Element
   */
  public  void setValueToElementByXpath(final WebDriver driver, final String xpath, final String inputValue) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
      final WebElement element = driver.findElement(By.xpath(xpath));
      highlightElement(element);
      element.clear();
      element.sendKeys(inputValue);
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
  }

  /**
   * ---------------------------------------------------------------------------------------------------------
   * Set Value to WebElement using XPATH and handling WebDriverWait to handle NoSuchElementException
   */
  public void setValueToElement(final WebDriver driver, final WebElement webElement, final String inputValue) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      driverWait.until(ExpectedConditions.visibilityOf(webElement));
      highlightElement(webElement);
      webElement.clear();
      webElement.sendKeys(inputValue);
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * get value by element
   */
  public String getValueByElement(final WebDriver driver, final WebElement webElement) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      if (driverWait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed()) {
        highlightElement(webElement);
        return webElement.getText().trim();
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return null;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * Is element not empty-
   */
  public boolean isElementNotEmpty(final WebDriver driver, final WebElement webElement, final String elementName) {
    return StringUtils.isNotEmpty(getValueByElement(driver, webElement));
  }

  /**
   * ---------------------------------------------------------------------------- Get Text Value of
   * the Input Element using XPATH and handling WebDriverWait to handle NoSuchElementException
   * //Handled the webelement from - ShipmentEnquiryObjectRepo and Actions class
   */
  public  String getInputElementValue(final WebDriver driver, final String xpath) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    WebElement webElement = driver.findElement(By.xpath(xpath));
    try {
      if (driverWait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed()) {
        highlightElement(webElement);
        return webElement.getAttribute("value").trim();
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return null;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * get Enable Disable by element
   */
  public  Boolean getEnableDisableByElement(final WebDriver driver, final WebElement webElement) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      if (driverWait.until(ExpectedConditions.visibilityOf(webElement)) != null) {
        highlightElement(webElement);
        return webElement.isEnabled();
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return false;
  }

  public  Boolean getEnableDisableByElement(final WebDriver driver, String xPath) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    WebElement webElement = driver.findElement(By.xpath(xPath));
    try {
      if (driverWait.until(ExpectedConditions.visibilityOf(webElement)) != null) {
        highlightElement(webElement);
        return webElement.isEnabled();
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return false;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * is Disable by element
   */
  public  Boolean isDisableByElement(final WebDriver driver, final WebElement webElement) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      if (driverWait.until(ExpectedConditions.visibilityOf(webElement)) != null) {
        highlightElement(webElement);
        final String attribute = webElement.getAttribute("disabled");
        return attribute != null && attribute.equalsIgnoreCase("true");
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return false;
  }

  /**
   * ----------------------------------------------------------------------------------------------
   * this method checks if the input's enabled status has changed by overriding the apply method and
   * applying the condition that we are looking for pass testIsEnabled true if checking whether the
   * input has become enabled pass testIsEnabled false if checking whether the input has become
   * disabled
   */
  public  Boolean isEnabledDisabledWaitingForChange(final WebDriver driver, final WebElement webElement, final Boolean testIsEnabled) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    Boolean isEnabled = null;
    try {
      isEnabled = driverWait.until(driver1 -> {
        final Boolean isElementEnabled = webElement.isEnabled();
        return testIsEnabled ? isElementEnabled : !isElementEnabled;
      });
    } catch (final org.openqa.selenium.TimeoutException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return isEnabled;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * is Spinner Enabled
   */
  public  Boolean isSpinnerEnabled(final WebDriver driver, final WebElement webElement) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      if (driverWait.until(ExpectedConditions.visibilityOf(webElement)) != null) {
        final String webElementCSS = getElementsCSS(driver, webElement);
        if (webElementCSS != null && !webElementCSS.isEmpty()) {
          return webElementCSS.contains("loadingSpinner");
        } else {
          return false;
        }
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return false;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * Is disabled by element CSS
   */
  public  Boolean isDisabledByElementCSS(final WebDriver driver, final WebElement webElement) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      if (driverWait.until(ExpectedConditions.visibilityOf(webElement)) != null) {
        highlightElement(webElement);
        final String webElementCSS = getElementsCSS(driver, webElement);
        if (webElementCSS != null && !webElementCSS.isEmpty()) {
          return webElementCSS.contains("ui-state-disabled");
        } else {
          return false;
        }
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return false;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * Mouse over Element
   */
  public  void mouseOverElement(final WebElement webElement) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      driverWait.until(ExpectedConditions.visibilityOf(webElement));
      highlightElement(webElement);
      final Actions actObj = new Actions(driver);
      actObj.moveToElement(webElement).build().perform();
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * Check & Uncheck box
   */
  public  void clickCheckedUnCheckedElement(final WebDriver driver, final WebElement webElement) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      driverWait.until(ExpectedConditions.visibilityOf(webElement));
      driverWait.until(ExpectedConditions.elementToBeClickable(webElement));
      highlightElement(webElement);
      final boolean chk = webElement.isEnabled();
      if (chk == true) {
        webElement.click();
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * Mouse over element select
   */
  public  void mouseOverElementSelect(final WebDriver driver, final WebElement webElement) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      driverWait.until(ExpectedConditions.visibilityOf(webElement)).isSelected();
      highlightElement(webElement);
      final Actions actObj = new Actions(driver);
      actObj.moveToElement(webElement).build().perform();
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
  }

  /**
   * Navigate to URL
   */
  public  void navigateToURL(final WebDriver driver, final String url) {
    driver.get(url);
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * Reload page
   */
  public  void reloadPage(final WebDriver driver) {
    driver.navigate().refresh();
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * Navigate to URL in new tab
   */
  public  void navigateToURLInNewTab(final WebDriver driver, final String url) {
    driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
    final ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(0));
    driver.get(url);
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * Is element present
   */
  public  boolean isElementPresent(final WebDriver driver, final String xPath) {
    try {
      return driver.findElements(By.xpath(xPath)).size() > 0;
    } catch (final org.openqa.selenium.NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
      return false;
    }
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * Is Anchor present
   */
  public  boolean isAnchorPresent(final WebDriver driver, final String text) {
    return isElementPresent(driver, "//a[contains(text(),'" + text + "')]");
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * get CSS classes by element
   */
  public  String getCssClassesByElement(final WebDriver driver, final WebElement webElement) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      if (driverWait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed()) {
        highlightElement(webElement);
        return webElement.getAttribute("class").trim();
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return null;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * Get CSS value by element
   */
  public  String getCSSValueByElement(final WebDriver driver, final WebElement webElement, final String cssAttributeName,
      final String elementName) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      if (driverWait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed()) {
        highlightElement(webElement);
        return webElement.getCssValue(cssAttributeName);
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return null;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * get data table size by xpath
   */
  public  int getDataTableSizeByXpath(final WebDriver driver, final String tableXPath, final String testCaseName) {
    final List<WebElement> rows = driver.findElements(By.xpath(tableXPath + "/tbody/tr"));
    return rows.size();
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * get mobile view card size by xpath
   */
  public  int getMobileViewCardsSizeByXpath(final WebDriver driver, final String cardXPath, final String testCaseName) {
    final List<WebElement> rows = driver.findElements(By.xpath(cardXPath));
    return rows.size();
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * To switch to a different window
   */
  public  void switchToOtherWindow(final WebDriver driver, final int windowNumber) {
    final List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(browserTabs.get(windowNumber));
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * is element displayed
   */
  public  boolean isElementDisplayed(final WebDriver driver, final WebElement webElement) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      if (driverWait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed()) {
        highlightElement(webElement);
        return webElement.isDisplayed();
      }

    } catch (final NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
      return false;
    }
    return true;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * is element not displayed
   */
  public  boolean isElementNotDisplayed(final WebElement webElement) {
    try {
      return !webElement.isDisplayed();
    } catch (final NoSuchElementException ele) {
      return true;
    }
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * get element's CSS
   */
  public  String getElementsCSS(final WebDriver driver, final WebElement webElement) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      if (driverWait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed()) {
        highlightElement(webElement);
        return webElement.getAttribute("class");
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return null;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * get web element from string path
   */
  public  WebElement getWebElementFromStringPath(final WebDriver driver, final String xpath) {
    return driver.findElement(By.xpath(xpath));
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * Xpath eists
   */
  public  boolean xPathExists(final WebDriver driver, final String xpath) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
      return true;
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
      return false;
    }
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * get value by element no log
   */
  public  String getValueByElementNoLog(final WebDriver driver, final WebElement webElement, final String elementName) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      if (driverWait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed()) {
        highlightElement(webElement);
        return webElement.getText().trim();
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return null;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * check visibility by element
   */
  public  Boolean checkVisibilityByElement(final WebDriver driver, final WebElement webElement) {
    Boolean isVisible = false;
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      if (driverWait.until(ExpectedConditions.visibilityOf(webElement)) != null) {
        highlightElement(webElement);
        isVisible = true;
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return isVisible;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * is element enabled
   */
  public  Boolean isElementEnabled(final WebDriver driver, final WebElement webElement, final String elementName) {
    Boolean isEnabled = false;
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      if (driverWait.until(ExpectedConditions.visibilityOf(webElement)) != null) {
        highlightElement(webElement);
        isEnabled = webElement.isEnabled();
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return isEnabled;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * is element not present
   */
  public  Boolean isElementNotPresent(final WebDriver driver, final String xPath) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      return driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPath)));
    } catch (final Exception ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
      return true;
    }
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * is element not present with wait
   */
  public  Boolean isElementNotPresentWithWait(final WebDriver driver, final String xPath, final int waitTimeinSec) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeinSec));
    try {
      return driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xPath)));
    } catch (final Exception ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
      return true;
    }
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * is button enabled
   */
  public  Boolean isButtonEnabled(final WebDriver driver, final WebElement webElement) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      if (driverWait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed()) {
        highlightElement(webElement);
        return webElement.isEnabled();
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return null;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * get value by xpath
   */
  public  String getValueByXpath(final WebDriver driver, final String xPath) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      if (driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath))).isDisplayed()) {
        final WebElement webElement = driver.findElement(By.xpath(xPath));
        highlightElement(webElement);
        return webElement.getText().trim();
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return null;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * is form control input field filled and valid
   */
  public  boolean isFormControlInputFieldFilledAndValid(final WebDriver driver, final WebElement webElement) {
    final String fieldCSS = getElementsCSS(driver, webElement);
    return fieldCSS.contains("ui-state-filled") && !fieldCSS.contains("ng-invalid");
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * This method checks the value in list and return boolean reusult.
   */
  public  boolean checkValueInList(final WebDriver driver, final List<WebElement> webElement, final String expectedValueInList,
      final String elementName) {
    final Boolean isValueExist = false;
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      if (driverWait.until(ExpectedConditions.visibilityOfAllElements(webElement)) != null) {
        final List<WebElement> elements = webElement;
        for (int i = 0; i < elements.size(); i++) {
          final String listValue = elements.get(i).getText().trim();
          if (listValue.contains(expectedValueInList)) {
            return true;
          }
        }
        return isValueExist;
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return isValueExist;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * get URL of new tab
   */
  public  String getUrlOfNewTab(final WebDriver driver, final int driverWaitTimeInSecs) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(driverWaitTimeInSecs));
    // get window handlers as list
    final List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
    // Ship to new tab and check if correct page opened
    String shipmentInstructionsTitle = null;
    driver.switchTo().window(browserTabs.get(2));
    if (driverWait.until(ExpectedConditions.titleContains("Main Page"))) {
      shipmentInstructionsTitle = driver.getTitle();
    }
    driver.switchTo().window(browserTabs.get(0));
    return shipmentInstructionsTitle;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * is text there
   */
  public  String isTextThere(final WebDriver driver, final WebElement element, final Boolean testIsEnabled, final String elementName,
      final String attributeName) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      driverWait.until(ExpectedConditions.visibilityOf(element));
      driverWait.until(ExpectedConditions.elementToBeClickable(element));
      Thread.sleep(3000);
      return driverWait.until(drivers -> {
        if (element.getAttribute(attributeName).length() > 0) {
          element.click();
          return element.getAttribute(attributeName);
        }
        return null;
      });
    } catch (org.openqa.selenium.TimeoutException | InterruptedException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return null;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * get element value by ID
   */
  public  String getElementValueByIdByJS(final WebDriver driver, final String elementId, final String elementName) {
    try {
      return (String) jsExecutor.executeScript("return document.getElementById(" + "'" + elementId + "'" + ").value");
    } catch (final org.openqa.selenium.TimeoutException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return null;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * select value in PrimeNgDropdown
   */
  public void selectValueInPrimeNgDropDown(final WebDriver driver, final WebElement dropDownElementOrParent, final String value) {
    final WebElement dropDownElement = findLabelPrimeNgDropdown(driver, dropDownElementOrParent);
    final WebElement element = dropDownElement.findElement(By.xpath(String.format(DROPDOWN_ITEM_SELECTOR_IN_OVERLAY, value)));
    clickElementByWebElement(driver, element);
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * select partial match value in PrimeNgDropdown
   */
  public void selectPartialMatchValueInPrimeNgDropDown(final WebDriver driver, final WebElement dropDownElementOrParent, final String value) {
    final WebElement dropDownElement = findLabelPrimeNgDropdown(driver, dropDownElementOrParent);
    final WebElement element = dropDownElement.findElement(By.xpath(String.format(DROPDOWN_PARTIAL_MATCH_ITEM_SELECTOR_IN_OVERLAY, value)));
    clickElementByWebElement(driver, element);
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * find Label PrimeNgDropdown
   */
  private WebElement findLabelPrimeNgDropdown(final WebDriver driver, final WebElement dropDownElementOrParent) {
    WebElement dropDownElement = null;
    if (dropDownElementOrParent != null && "p-dropdown".equalsIgnoreCase(dropDownElementOrParent.getTagName())) {
      dropDownElement = dropDownElementOrParent;
    } else if (dropDownElementOrParent != null) {
      dropDownElement = dropDownElementOrParent.findElement(By.tagName("p-dropdown"));
    } else {
      dropDownElement = driver.findElement(By.tagName("p-dropdown"));
    }
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    driverWait.until(ExpectedConditions.visibilityOf(dropDownElement));
    driverWait.until(ExpectedConditions.elementToBeClickable(dropDownElement.findElement(By.xpath(DROPDOWN_CLICKABLE_LABEL))));
    clickElementByWebElement(driver, dropDownElement.findElement(By.xpath(DROPDOWN_CLICKABLE_LABEL)));
    try {
      Thread.sleep(300);
    } catch (final InterruptedException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    driverWait.until(ExpectedConditions.visibilityOf(dropDownElement.findElement(By.xpath(DROPDOWN_OVERLAY))));
    return dropDownElement;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * get PrimeNgDropdown value
   */
  public String getPrimeNgDropDownValue(final WebDriver driver, final int driverWaitTimeInSecs, final WebElement dropDownElementOrParent,
      final String elementName, final String dropDownLabel) {
    WebElement dropDownElement = null;
    if (dropDownElementOrParent != null && "p-dropdown".equalsIgnoreCase(dropDownElementOrParent.getTagName())) {
      dropDownElement = dropDownElementOrParent;
    } else if (dropDownElementOrParent != null) {
      dropDownElement = dropDownElementOrParent.findElement(By.tagName("p-dropdown"));
    } else {
      dropDownElement = driver.findElement(By.tagName("p-dropdown"));
    }
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(driverWaitTimeInSecs));
    driverWait.until(ExpectedConditions.visibilityOf(dropDownElement));
    return getValueByElement(driver, dropDownElement.findElement(By.xpath(dropDownLabel)));
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * is PrimeNg check box checked
   */
  public  boolean isPrimeNgCheckboxChecked(final WebDriver driver, final WebElement parentElement) {
    WebElement checkBox = null;
    if (parentElement != null) {
      checkBox = parentElement.findElement(By.tagName("p-checkbox"));
    } else {
      checkBox = driver.findElement(By.tagName("p-checkbox"));
    }
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    driverWait.until(ExpectedConditions.visibilityOf(checkBox));
    return checkBox.findElement(By.xpath("div/div[2]")).getAttribute("class").contains("ui-state-active");
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * high lighter method
   */
  public  void highLighterMethod(final WebDriver driver, final WebElement element) {
    jsExecutor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * Remove high lighter
   */
  public  void removeHighLighter(final WebDriver driver, final WebElement element) {
    jsExecutor.executeScript("arguments[0].setAttribute('style', 'background: white;');", element);
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * get place holder text
   */
  public  String getPlaceHolderText(final WebElement element) {
    return element.getAttribute("placeholder");
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * get current screen URL
   */
  public  String getCurrentScreenUrl(final WebDriver driver) {
    final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[contains(text(),' Equipment ')]")));
    return driver.getCurrentUrl();

  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * focus out of text area
   */
  public  void focusOutOfTextArea(final WebElement webElement) {
    final WebElement destWebElement = webElement;
    destWebElement.sendKeys(Keys.TAB);
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * is spinner loaded
   */
  public  Boolean isSpinnerLoaded(final WebDriver driver, final WebElement webElement, final String elementName) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      if (driverWait.until(ExpectedConditions.invisibilityOf(webElement)) != null) {
        final String webElementCSS = getElementsCSS(driver, webElement);
        if (webElementCSS != null && !webElementCSS.isEmpty()) {
          return webElementCSS.contains("loadingSpinner");
        } else {
          return false;
        }
      }
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return false;
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * is angular spinner loaded
   */
  public  void waitForApiCallInAngular(final WebDriver driver) {
    final Wait<WebDriver> wait = new FluentWait<>(driver).pollingEvery(Duration.ofMillis(200))
        .withTimeout(Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS)).ignoring(NoSuchElementException.class);
    // making sure that spinner is present
    wait.until(d -> ExpectedConditions.visibilityOf(d.findElement(By.xpath(SPINNER_XPATH))));

    // we have to wait until spinner goes away
    final Wait<WebDriver> wait1 = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    wait1.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(SPINNER_XPATH))));
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * get hidden element value by xpath
   */
  public  String getHiddenElementValueByXPath(final WebDriver driver, final String xPath, final String elementName) {
    try {
      final JavascriptExecutor js = (JavascriptExecutor) driver;
      final WebElement hiddenDiv = driver.findElement(By.xpath(xPath));
      String value = hiddenDiv.getText();
      final String script = "return arguments[0].innerHTML";
      return value = (String) ((JavascriptExecutor) driver).executeScript(script, hiddenDiv);
    } catch (final org.openqa.selenium.TimeoutException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
    return null;
  }

  public  void sxoLogon(final WebDriver driver, final String url, final String racfId, final String pwd) {
    navigateToURL(driver, url);
    LOGGER.info("Launched sxo url: " + url);
    driver.findElement(By.id(LOGINPAGE_USERNAME_TEXTBOX_OBJECT_ID)).sendKeys(racfId);
    driver.findElement(By.id(LOGINPAGE_PASSWORD_TEXTBOX_OBJECT_ID)).sendKeys(pwd);
    LOGGER.info(USERNAME + racfId + AND_PASSWORD + pwd + IS_ENTERED);
    driver.findElement(By.id(LOGINPAGE_LOGIN_BUTTON_OBJECT_ID)).click();
    LOGGER.info("Login button is clicked");
    try {
      Thread.sleep(5000);
    } catch (final InterruptedException e) {
      LOGGER.info("Logging Failed");
    }
  }

  /**
   * -------------------------------------------------------------------------------------------------------
   * SXO LOGON Method with Default Staging URL and Credentials
   */
  public  void sxoLogon(final WebDriver driver) {
    navigateToURL(driver, DEFAULT_SXLOGON_STAGING_URL);
    LOGGER.info("Launched sxo url: " + DEFAULT_SXLOGON_STAGING_URL);
    driver.findElement(By.id(LOGINPAGE_USERNAME_TEXTBOX_OBJECT_ID)).sendKeys(DEFAULT_RACFID);
    driver.findElement(By.id(LOGINPAGE_PASSWORD_TEXTBOX_OBJECT_ID)).sendKeys(DEFAULT_RACFID_PASSWORD);
    LOGGER.info(USERNAME + DEFAULT_RACFID + AND_PASSWORD + DEFAULT_RACFID_PASSWORD + IS_ENTERED);
    driver.findElement(By.id(LOGINPAGE_LOGIN_BUTTON_OBJECT_ID)).click();
    LOGGER.info("Login button is clicked");
    try {
      Thread.sleep(5000);
    } catch (final InterruptedException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
  }

  /**
   * ------------------------------------------------------------------------------------------------------
   *
   * @param driver
   * @param url SXO LOGON Method with URL Passing. This method used default Racf ID and Pwd.
   */
  public  void sxoLogon(final WebDriver driver, final String url) {
    navigateToURL(driver, url);
    LOGGER.info("Launched sxo url:{} ", url);
    // SeleniumUtil.setValueToElement(driver, LOGINPAGE_USERNAME_TEXTBOX_OBJECT_ID, DEFAULT_RACFID);
    driver.findElement(By.id(LOGINPAGE_USERNAME_TEXTBOX_OBJECT_ID)).sendKeys(DEFAULT_RACFID);
    driver.findElement(By.id(LOGINPAGE_PASSWORD_TEXTBOX_OBJECT_ID)).sendKeys(DEFAULT_RACFID_PASSWORD);
    LOGGER.info(USERNAME + DEFAULT_RACFID + AND_PASSWORD + DEFAULT_RACFID_PASSWORD + IS_ENTERED);
    driver.findElement(By.id(LOGINPAGE_LOGIN_BUTTON_OBJECT_ID)).click();
    LOGGER.info("Login button is clicked");
    try {
      Thread.sleep(5000);
    } catch (final InterruptedException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
  }

  /**
   * ----This Method is for Sorting of Ascending&Descending Order---------------
   */

  public  void verifyAscendingAndDescending(final WebDriver driver, final String XPATH, final String elementName) {
    try {
      final List<WebElement> AllAscendingAndDescending = driver.findElements(By.xpath(XPATH));
      for (final WebElement AscAndDsc : AllAscendingAndDescending) {
        final String value = AscAndDsc.getText();
        final String script = "return arguments[0].innerHTML";
        LOGGER.info(elementName + ":" + value);
      }
    } catch (final org.openqa.selenium.TimeoutException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
  }

  public  void clearTextField(final WebDriver driver, final WebElement webElement, final String inputValue) {
    final WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      driverWait.until(ExpectedConditions.visibilityOf(webElement));
      highlightElement(webElement);
      webElement.sendKeys(Keys.CONTROL, Keys.chord("a"));
      webElement.sendKeys(Keys.BACK_SPACE);
    } catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
  }

  /**
   * -----JS Method for scroll to element and enter the value ---------
   */

  public void inputbyJsExecutor(WebDriver driver, WebElement element, Object value) {
    final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    wait.until(ExpectedConditions.visibilityOf(element));
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    highlightElement(element);
    waitByTime(thread_sleep);
    if (wait.until(ExpectedConditions.elementToBeClickable(element)) != null) {
      jsExecutor.executeScript("arguments[0].value='" + value + "';", element);
    }
    waitByTime(thread_sleep);
  }

  /**
   * -----double click operation ---------
   */
  public void doubleclick_element(WebDriver driver, String xpath, String colName) throws InterruptedException {
    List<WebElement> findElements = driver.findElements(By.xpath(xpath));
    for (WebElement webElement : findElements) {
      if (webElement.getText().equals(colName)) {
        doubleClick(webElement);
        waitByTime(thread_sleep);
      }
    }

  }

  /**
   * -----JS Method for scroll to element and click based  Webelement ---------
   */

  public void JsClickByElement(final WebElement webelement) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try{
    wait.until(ExpectedConditions.visibilityOf(webelement));
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", webelement);
    waitByTime(thread_sleep);
    highlightElement(webelement);
    if (wait.until(ExpectedConditions.elementToBeClickable(webelement)) != null) {
      jsExecutor.executeScript("arguments[0].click();", webelement);
    }
    }
    catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
  }

  /**
   * -----JS Method for scroll to element and click based on xpath ---------
   */

  public void JsClickByXpath(final String xpath) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DRIVER_WAIT_TIME_IN_SECS));
    try {
      wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
      jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(xpath)));
      waitByTime(thread_sleep);
      highlightElement(xpath);
      if (wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath)))) != null) {
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpath)));
      }
    }
    catch (org.openqa.selenium.TimeoutException | NoSuchElementException ele) {
      LOGGER.error(ERROR_MSG, ele);
      fail();
    }
  }

  /**
   * -----Take the screenshot anf returns path as a astring ---------
   */

 public String takeScreenshot(String screenshotName) {
    String destination = null;
    String imgPath = null;
    while (driver instanceof TakesScreenshot) {
      String dateName = new SimpleDateFormat(CommonConstants.YYYY_MM_DD_HH_MM_SS).format(new Date());
      File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      try {
        imgPath = "\\TestsScreenshots\\" + screenshotName + dateName + ".png";
        destination = System.getProperty("user.dir") + imgPath;
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        LOGGER.info("Screenshot destination : " + destination);
        return imgPath;
      } catch (IOException e) {
        LOGGER.error("takeScreenshot Exception : " + e.getMessage());
      }
    }
    return destination;
  }

  /**
   * -----Comapre the images  ---------
   */

  public  boolean compareImages(WebDriver driver, String savedImage, WebElement locator) {
    try {
      // get the saved image, the expected one
      BufferedImage expectedSatellite = ImageIO.read(new File(System.getProperty("user.dir") + "/images/" + savedImage + ".png"));
      // take the new screenshot
      Screenshot screenshot = new AShot().takeScreenshot(driver, locator);
      BufferedImage actualSatellite = screenshot.getImage();
      // compare both images
      ImageDiffer imgDiff = new ImageDiffer();
      ImageDiff diff = imgDiff.makeDiff(expectedSatellite, actualSatellite);
      return diff.hasDiff();
    } catch (IOException e) {
      System.out.println("OPTA: Not able to compare images - " + e.getMessage());
      e.printStackTrace();
      return true;
    }
  }

  /**
   * -----return the WebDriver Fluent Wait  ---------
   */

  private FluentWait webDriverFluentWait() {
    return new FluentWait(driver).withTimeout(Duration.ofSeconds(defaultMaxTime)).pollingEvery(Duration.ofSeconds(3))
        .ignoring(NoSuchElementException.class, NoSuchFrameException.class);
  }

  /**
   * -----return the WebDriver Fluent Wait  ---------
   */
  private FluentWait webDriverFluentWait(int time) {
    return new FluentWait(driver).withTimeout(Duration.ofSeconds(time)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class,
        NoSuchFrameException.class);
  }

  /**
   * -----Wait for the element for a given amount of time  ---------
   */

  public void waitForElement(final WebElement element, int time) {
    try {
      LOGGER.info("BeforeWaitForElement::" + element);
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
      wait.until(ExpectedConditions.visibilityOf(element));
    } catch (WebDriverException e) {
      LOGGER.error("Exception in waitForElement::" + element + " " + e);
      throw new WebDriverException(e);
    }
  }

  /**
   * -----Wait for the List of elements for a given amount of time  ---------
   */

  private List<WebElement> waitForElements(By element, int timeout) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
    return driver.findElements(element);
  }

  /**
   * -----Wait for the elment for a given amount of time  ---------
   */

  public void waitForElementDisplayed(WebElement element, int time) {
    try {
      if (!driver.getWindowHandles().isEmpty()) {
        waitForElementLoading(element, time);
      }
    } catch (Exception e) {
      LOGGER.error("Locator not found and the reason for failure is " + e);
    }
  }

  /**
   * -----Wait for the elment for a given amount of time with fluent wait  ---------
   */
  private void waitForElementLoading(final WebElement element, int time) {
    try {
      Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(time)).pollingEvery(Duration.ofMillis(5))
          .ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class).ignoring(TimeoutException.class);
      wait.until(ExpectedConditions.elementToBeClickable(element));
      if (!element.isDisplayed()) {
        LOGGER.info("Element " + element + " is not displayed");
      }
    } catch (Exception e) {
      LOGGER.error("Failed to find the element and the reason is " + e);
    }
  }

  /**
   * -----check whether alert present or not  ---------
   */

  public boolean isAlertPresent(int time) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
      wait.until(ExpectedConditions.alertIsPresent());
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      throw new NoAlertPresentException();
    }
  }

  /**
   * -----wait untill DOM load  ---------
   */
  public void waitUntilDomLoad() {
    LOGGER.info("Inside waitForElementLoading");
    FluentWait fluentWait = readyStateWait(driver);
    if (driver.getTitle().contains("/maintenix/")) {
      ExpectedCondition<Boolean> jQueryLoad;
      try {
        jQueryLoad = webDriver -> ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
      } catch (Exception e) {
        jQueryLoad = webDriver -> (true);
        LOGGER.error("Failed to waitForElementLoading: " + e);
      }
      fluentWait.until(jQueryLoad);
    }
    try {
      ExpectedCondition<Boolean> docLoad =
          webDriver -> ((Boolean) ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));
      fluentWait.until(docLoad);
    } catch (Exception e) {
      LOGGER.error("Failed to waitForElementLoading " + e);
    }
    LOGGER.info("Dom load completed");
  }

  private FluentWait readyStateWait(WebDriver driver) {
    return new FluentWait(driver).withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofSeconds(1)).ignoring(WebDriverException.class);
  }

    /**
     * -----check for element present for a given amount of time  ---------
     */

  public boolean isElementPresent(WebElement element, int time) {
    try {
      LOGGER.info("Before isElementPresent::" + element);
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
      wait.until(ExpectedConditions.visibilityOf(element));
      return true;
    } catch (Exception e) {
      LOGGER.info("Exception isElementPresent::" + element + " " + e);
      return false;
    }
  }
    /**
     * -----check for element visibility  ---------
     */

  public boolean isElementVisible(WebElement element) {
    try {
      webDriverFluentWait().until(ExpectedConditions.visibilityOf(element));
      return true;
    } catch (Exception e) {
      LOGGER.info("Exception isElementVisible::" + element + " " + e);
      return false;
    }
  }
    /**
     * -----check for element visibility for given amount of time  ---------
     */

  public boolean isElementVisible(WebElement element, int time) {
    try {
      webDriverFluentWait(time).until(ExpectedConditions.visibilityOf(element));
      return true;
    } catch (Exception e) {
      LOGGER.info("Exception isElementVisible::" + element + " " + e);
      return false;
    }
  }

    /**
     * -----hold the execution for given amount of time  ---------
     */

  public void waitByTime(int time) {
    try {
      Thread.sleep(time);
    } catch (Exception e) {
      LOGGER.info("Fail in wait due to : " + e);
      Thread.currentThread().interrupt();
    }
  }

    /**
     * -----Clear the text and enters the required value in the inut box  ---------
     */

  public void clearAndEnterText(WebElement element, String text) {
    LOGGER.info("Before clearAndEnterText::" + element + ", with text: " + text);
    waitForElement(element, defaultMaxTime);
    if (!isElementVisible(element)) {
      scrollToElement(element);
    }
    highlightElement(element);
    element.click();
    element.clear();
    String value = element.getAttribute("value");
    if (!value.isEmpty()) {
      element.sendKeys(Keys.CONTROL + "a");
      element.sendKeys(Keys.DELETE);
    }
    Actions action = new Actions(driver);
    action.sendKeys(element, text).build().perform();
  }

    /**
     * -----Single CLick using Actions class ---------
     */

  public void singleClick(WebElement element) {
    Actions actions = new Actions(driver);
    waitForElement(element, defaultMaxTime);
    waitByTime(1000);
    if (!isElementVisible(element)) {
      scrollToElement(element);
    }
    waitForElement(element, defaultMaxTime);
    if (isElementVisible(element)) {
      actions.moveToElement(element).build();
      highlightElement(element);
      actions.click(element).build().perform();
    } else {
      waitForElement(element, defaultMaxTime);
      highlightElement(element);
      actions.moveToElement(element).build();
      actions.click(element).build().perform();
    }
  }
    /**
     * -----Double click using Actions class  ---------
     */
  public void doubleClick(WebElement element) {
    Actions actions = new Actions(driver);
    waitForElement(element, defaultMaxTime);
    waitByTime(1000);
    if (!isElementVisible(element)) {
      scrollToElement(element);
    }
    waitForElement(element, defaultMaxTime);
    if (isElementVisible(element)) {
      actions.moveToElement(element).build();
      highlightElement(element);
      actions.doubleClick(element).build().perform();
    } else {
      waitForElement(element, defaultMaxTime);
      highlightElement(element);
      actions.moveToElement(element).build();
      actions.doubleClick(element).build().perform();
    }
  }

  public void changefocus() {
    Actions actions = new Actions(driver);
    actions.moveByOffset(20, 8).click().build().perform();
  }

  public void waiTillLoading (WebElement element){
    waitByTime(500);
    try{
    if(element.isDisplayed())
    webDriverFluentWait(defaultMaxTime).until(ExpectedConditions.invisibilityOf(element));
    } catch(Exception e)
    {
      LOGGER.info("Failed in Waiting for loader");
    }
  }

  public boolean waitUntilTextPresentInElement(WebElement element, String elementText, int seconds) {
    WebDriverWait expWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    return expWait.until(ExpectedConditions.textToBePresentInElement(element, elementText));
  }

  public void scrollToElement(WebElement webelement) {
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("arguments[0].scrollIntoView(true);", webelement);
    jse.executeScript("window.scrollBy(0,100)", "");
    LOGGER.info("ScrollToElement::" + webelement + "Done");
  }

  public void highlightElement(WebElement webElement) {
    try {
      if (driver instanceof JavascriptExecutor) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='4px solid orange'", webElement);
      }
    } catch (Exception e) {
      LOGGER.error("Fail in highlightElement " + e);
    }
  }

  public void highlightElement(By locator) {
    highlightElement(driver.findElement(locator));
  }

  public void highlightElement(String xpath) {
    highlightElement(driver.findElement(By.xpath(xpath)));
  }

  public void setMaxSyncTime(int newSyncTime) {
    maxSyncTime = newSyncTime;
  }

  public void waitTillLoadingCompletes(WebElement element) {
    try {
      long start = System.currentTimeMillis();
      int elapsedTime = 0;
      waitByTime(500);
      while (element.isDisplayed()) {
        LOGGER.info("Waiting for Loading Screens to go away");
        if (elapsedTime > maxSyncTime) {
          break;
        }
        waitByTime(500);
        elapsedTime = Math.round((System.currentTimeMillis() - start) / 1000F);
      }
    } catch (Exception e) {
      if ((e.toString().contains("NoSuchElementException") || e.getMessage().contains("NoStale"))) {
        LOGGER.info("Loading screenshot is not present now");
      } else {
        LOGGER.error(Level.FINEST + "Fail in waitTillLoadingCompletes " + e);
      }
    }
  }
 }
