package com.scotts.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import sun.misc.BASE64Decoder;

import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;

public class ArsinSeleniumAPI extends ArsinFrameWork {

	/**
	 * This enum contains all required Selenese constants.
	 */

	public String failMessageDesc = "";

	public String instanceName;
	private String sauceUser;
	private String currentUser;
	private String executionTime;
	private String moduleName;
	private String testSetName;
	private String testCaseName;
	ByteArrayOutputStream baos;

	PrintStream ps;
	PrintStream old;
	DesiredCapabilities capabilities = null;

	private enum enumSeleniumSelenses {
		isElementInVisible, assertValue, assertSubText, assertText, assertTextPresent, assertTitle, assertConfirmation, assertAlert, clickAndWait, clickSpecifiedLink, compareURLs, compare, captureEntirePageScreenshot, click, closeBrowser, deleteAllVisibleCookies, eOpenWindow, eClickAndWait, getXpathCount, getSelectedLabel, getCookieByName, getText, getAttribute, getURL, getTitle, goBack, getSelectOptions, getAllWindowNames, getValue, isSpecifiedOptionChecked, isChecked, isElementPresent, openURL, openWindow, sendReport, select, sendReportWithOutExit, selectWindow, waitForFrameAndSelect, typeSpecifiedText, type, typeEmpty, verifyElementPresent, verifyValue, verifySubText, verifyTextPresent, verifyText, verifyElement, waitForElementPresent, waitForTextPresent, waitForPopUp, windowMaximize, waitUntilElementVisible, waitForAlertPresent, waitForValue, waitForPageToLoad, eOpen, verifyTitle, verifyConfirmation, verifyAlert, typePassword, selectSpecifiedOption, selectFrame, clickSpecifiedLinkNWait, clickAndWaitForElementPresent
	}

	public ArsinSeleniumAPI() {

	}

	private void printEq(int count) {
		for (int i = 0; i < count + 5; i++)
			System.out.print("=");
		System.out.println();
	}

	private void printExecutionDetails() {
		String projectDetails = "Automation Path: " + sAutomationPath + " | Browser: " + sBrowser
				+ " | Operating System: " + os;
		int pcount = projectDetails.length();
		String name = "Test Case/Script Name: " + sECName;
		int ncount = name.length();

		int count = 0;
		if (pcount >= ncount) {
			count = pcount;
		} else {
			count = ncount;
		}
		printEq(count);
		System.out.println("Project Details\n");
		System.out.println(projectDetails + "\nProject Name: " + sProjectName + "\n" + name);
		printEq(count);
	}

	public static void main(String[] a) {
		System.out.println("samplet test");
	}

	public ArsinSeleniumAPI(String sProjectName, String sECName, String sBrowser, String os, String instanceName,
			String sauceUser, String moduleName, String testSetName) {
		super(sProjectName, sECName, sBrowser, os, moduleName);
		this.instanceName = instanceName;
		this.sauceUser = sauceUser;
		this.currentUser = System.getProperty("user.name");
		this.executionTime = this.getCurrentDateNTime();
		this.moduleName = moduleName;
		this.testSetName = testSetName;
		this.testCaseName = sECName;
		serviceTestCaseName = sECName;

		// print execution details
		printExecutionDetails();

	}

	String jobID = "";
	private enumSeleniumSelenses enumCommand;
	public WebDriver driver;
	private String accessKey = "";

	/**
	 * This method checks whether given string is enum or not if the given
	 * string is enum then this method returns enum type of that string.
	 * 
	 * @param sCommand
	 *            Command specified in Test Script
	 * @return enumSeleniumSelenses returns enum constant
	 */
	private enumSeleniumSelenses checkEnumConstant(String sCommand) {
		try {
			enumCommand = enumSeleniumSelenses.valueOf(sCommand);
			return enumCommand;
		} catch (Exception e) {
			e.printStackTrace();
			reportUnknowSeleniumCmdErr(sCommand);
		}
		return null;
	}

	/**
	 * This method captures the screenshot.
	 */
	public String captureScreenShot() {
		String screenShotFilePath = null;
		try {
			File scrFile = null;

			if (this.sauceUser.equalsIgnoreCase("Local")) {
				scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			} else {
				scrFile = ((TakesScreenshot) new Augmenter().augment(driver)).getScreenshotAs(OutputType.FILE);
			}

			String screenShotPah = this.sAutomationPath + "Results/" + this.sProjectName + "/" + this.instanceName + "/"
					+ this.currentUser + "/" + getDate1() + "/" + sModuleName + "/" + this.testSetName + "/";

			File fi = new File(screenShotPah);
			if (!fi.exists()) {
				fi.mkdirs();
			}
			System.out.println("Screenshot Path: " + screenShotPah + sECName + "_" + iStepCount + ".png");
			FileUtils.copyFile(scrFile, new File(screenShotPah + sECName + "_" + iStepCount + ".png"));
			FileUtils.copyFileToDirectory(new File(screenShotPah + sECName + "_" + iStepCount + ".png"),
					new File(getTempPath() + "CurrentRunReports/"));
			screenShotFilePath = "Screenshot:" + sECName + "_" + iStepCount + ".png";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return screenShotFilePath;
	}

	// captureScreenShot
	public String captureBase64ScreenShot() {
		String screenShotFilePath = null;
		try {

			String imgData;

			if (this.sauceUser.equalsIgnoreCase("Local")) {
				// scrFile = ((TakesScreenshot)
				// driver).getScreenshotAs(OutputType.FILE);
				imgData = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			} else {
				// scrFile=((TakesScreenshot)new
				// Augmenter().augment(driver)).getScreenshotAs(OutputType.FILE);

				imgData = ((TakesScreenshot) new Augmenter().augment(driver)).getScreenshotAs(OutputType.BASE64);
			}
			System.out.println("Image data: " + imgData);
			String screenShotPah = this.sAutomationPath + "Results/" + this.sProjectName + "/" + this.instanceName + "/"
					+ this.currentUser + "/" + getDate1() + "/" + sModuleName + "/" + this.testSetName + "/";

			File fi = new File(screenShotPah);
			if (!fi.exists()) {
				fi.mkdirs();
			}
			String screenshotName = sECName + "_" + iStepCount + ".png";
			File scrFile = new File(screenShotPah + screenshotName);
			System.out.println("Screenshot Path: " + scrFile.getAbsolutePath());

			ImageIO.write(decodeToImage(imgData), "png", scrFile);
			screenShotFilePath = "Screenshot:" + scrFile.getName();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return screenShotFilePath;
	}

	/**
	 * Description: Method to convert Image String to Buffered Image
	 * 
	 * @param imageString
	 * @return Buffered Image Object
	 */
	private BufferedImage decodeToImage(String imageString) {

		BufferedImage image = null;
		byte[] imageByte;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			imageByte = decoder.decodeBuffer(imageString);
			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bis);
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

	/**
	 * This method takes selenese command in the form of string and checks that
	 * string is available in the enum list or not. If available, then
	 * corresponding case will be executed and will return value of type String.
	 * 
	 * @param sCommand
	 *            Selenese Command
	 * @return String either Pass (or) Fail (or) value returned by selenese
	 *         command
	 */
	public String effecta(String sCommand) {
		String sText = "";
		try {
			commandName.add(sCommand);
			enumCommand = checkEnumConstant(sCommand);
			switch (enumCommand) {
			case deleteAllVisibleCookies:
				driver.manage().deleteAllCookies();
				reportStepDetails("The Command \"deleteAllVisibleCookies\"",
						"Successfully deleted all cookies visible to the current page", PASS);
				return PASS;
			case waitForPageToLoad:
				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
				return PASS;
			case getURL:
				sText = driver.getCurrentUrl();
				reportStepDetails("Retrieving the absolute URL of current page",
						"URL of current page:" + encodeSpecialCharacters(sText), PASS);
				return (sText);
			case getTitle:
				sText = driver.getTitle();
				reportStepDetails("Retrieving page title", "Title of current page:" + encodeSpecialCharacters(sText),
						PASS);
				return (sText);
			case closeBrowser:
				driver.close();
				reportStepDetails("Closing window", "Successfully closed window", PASS);
				return PASS;
			case goBack:
				driver.navigate().back();
				reportStepDetails("Going back to the previous page", "Successfully navigated to previous page", PASS);
				return PASS;
			default:
				reportUnknowSeleniumCmdErr(sCommand);
			}

		} catch (Exception e) {
			e.printStackTrace();
			reportException(sCommand, e.toString());
		}
		return "";
	}

	/**
	 * This method takes selenese command and its target in the form of string
	 * and checks that string is available in the enum or not. If string is
	 * available in the enum list then corresponding case will be executed and
	 * will return value of type String.
	 * 
	 * @param sCommand
	 *            Selenese command
	 * @param sTarget
	 *            an element locator (or) timeout (or) expected value
	 * @return String either Pass (or) Fail (or) Value returned by selenese
	 *         command
	 */
	public String effecta(String sCommand, String sTarget) {
		int sTargetFlag = 0;
		String sText = "";
		String sValue = "";
		boolean bFlag = false;
		commandName.add(sCommand);
		try {
			if (sCommand.equals("waitForPageToLoad") || sCommand.equals("windowMaximize")
					|| sCommand.equals("assertAlert") || sCommand.equals("verifyAlert")
					|| sCommand.equals("isElementPresent") || sCommand.equals("getAttribute")
					|| sCommand.equals("selectWindow") || sCommand.equals("selectFrame")
					|| sCommand.equals("getCookieByName") || sCommand.equals("openURL") || sCommand.equals("eOpen")
					|| sCommand.equals("assertTitle") || sCommand.equals("verifyTitle")
					|| sCommand.equals("assertConfirmation") || sCommand.equals("verifyConfirmation")
					|| sCommand.equals("waitForAlertPresent") || sCommand.equals("eClickAndWait")
					|| sCommand.equals("waitForElementPresent")) {
				sTargetFlag = 1;

			} else if (sTarget.contains("&&")) {
				String[] sTargetArray = sTarget.split("&&");
				for (int i = 0; i < sTargetArray.length; i++) {
					if (isElementPresent(sTargetArray[i])) {
						sTarget = sTargetArray[i];
						sTargetFlag = 1;
						break;
					}
				}
			} else if (isElementPresent(sTarget)) {
				sTargetFlag = 1;
			}

			if (sCommand.equals("verifyElementPresent") && sTargetFlag == 0) {
				return (FAIL);
			}

			if (sTargetFlag == 1) {

				enumCommand = checkEnumConstant(sCommand);
				switch (enumCommand) {

				case waitForAlertPresent:
					boolean flag = false;
					int secsToWait = Integer.parseInt(sTarget);

					for (int second = 0;; second++) {
						if (second >= secsToWait) {
							flag = false;
							break;
						}
						if (isAlertPresent()) {
							flag = true;
							break;
						}
						Thread.sleep(1000);
					}

					return Boolean.toString(flag);

				case waitForElementPresent:
					flag = false;
					secsToWait = 60;

					for (int second = 0;; second++) {
						if (second >= secsToWait) {
							flag = false;
							break;
						}
						if (isElementPresent(sTarget)) {
							flag = true;
							break;
						}
						Thread.sleep(1000);
					}
					if (!flag) {
						reportStepDtlsWithScreenshot("Checking the existence of element in :" + sCommand + " command:",
								"Element verification \"" + sTarget + "\" failed", FAIL);
					}

					return Boolean.toString(flag);

				case waitForValue:
					flag = false;
					for (int second = 0;; second++) {
						if (second >= 10) {
							flag = false;
							break;
						}
						sText = webElement(sTarget).getAttribute("value");
						if (sText.length() > 0) {
							flag = true;
							break;
						}
						Thread.sleep(1000);
					}

					return sText;

				case getValue:
					sText = webElement(sTarget).getAttribute("value");
					return sText;

				case click:
					webElement(sTarget).click();
					// reportStepDetails("Clicking button/link/image",
					// "Successfully clicked the button/link/image", PASS);
					return PASS;
				case isChecked:
					bFlag = webElement(sTarget).isSelected();
					if (bFlag)
						reportStepDetails("Verifying whether checkbox/radio button is checked or not",
								"Checkbox/radio button is checked", PASS);
					else
						reportStepDetails("Verifying whether checkbox/radio button is checked or not",
								"Checkbox/radio button is not checked", PASS);
					return (Boolean.toString(bFlag));
				case waitForPageToLoad:

					driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

					reportStepDtlsWithScreenshot("Waiting for a new page to load",
							"Execution waited for new page to get loaded", PASS);
					return PASS;
				case openURL:
					driver.get(sTarget);
					reportStepDetails("Opening URL", "Successfully loaded URL:" + encodeSpecialCharacters(sTarget),
							PASS);
					return PASS;
				case eOpen:
					sValue = getExcelData(sTarget);
					driver.get(sValue);
					reportStepDetails("Opening URL", "Successfully loaded URL:" + encodeSpecialCharacters(sValue),
							PASS);
					return sValue;
				case assertTitle:
					sText = driver.getTitle().trim();
					if (sText.equals(sTarget)) {
						reportStepDetails("Expected page title : " + encodeSpecialCharacters(sTarget),
								"Successfully validated page title:  " + encodeSpecialCharacters(sText), PASS);
					} else {
						reportStepDtlsWithScreenshot("Expected page title : " + encodeSpecialCharacters(sTarget),
								"Displayed page title :  " + encodeSpecialCharacters(sText), FAIL);

					}
					return sText;
				case verifyTitle:
					sValue = getExcelData(sTarget);
					sText = driver.getTitle().trim();
					if (sText.equals(sValue)) {
						reportStepDetails(sTarget + " Expected page title : " + encodeSpecialCharacters(sValue),
								"Successfully validated page title:  " + encodeSpecialCharacters(sText), PASS);
					} else {
						reportStepDtlsWithScreenshotWithoutExit(
								sTarget + " Expected page title : " + encodeSpecialCharacters(sValue),
								"Displayed page title :  " + encodeSpecialCharacters(sText), FAIL);

					}
					return sText;

				case assertConfirmation:
					if (isAlertPresent()) {
						sText = driver.switchTo().alert().getText();
						driver.switchTo().alert().accept();
						if (sText.equals(sTarget)) {
							reportStepDetails("Expected confirmation message: " + encodeSpecialCharacters(sTarget),
									"Successfully validated confirmation message:  " + encodeSpecialCharacters(sText),
									PASS);
						} else {
							reportStepDtlsWithScreenshot(
									"Expected confirmation message: " + encodeSpecialCharacters(sTarget),
									"Displayed confirmation message:  " + encodeSpecialCharacters(sText), FAIL);
						}
					} else {
						reportStepDetails("Verifying confirmation message", "There is no confirmation message", FAIL);
					}
					return sText;
				case verifyConfirmation:
					if (isAlertPresent()) {
						sText = driver.switchTo().alert().getText();
						driver.switchTo().alert().accept();
						sValue = getExcelData(sTarget);
						if (sText.equals(sValue)) {
							reportStepDetails(
									sTarget + " Expected confirmation message: " + encodeSpecialCharacters(sValue),
									"Successfully validated confirmation message:  " + encodeSpecialCharacters(sText),
									PASS);
						} else {
							reportStepDtlsWithScreenshotWithoutExit(
									sTarget + " Expected confirmation message: " + encodeSpecialCharacters(sValue),
									"Displayed confirmation message:  " + encodeSpecialCharacters(sText), FAIL);
						}
					} else {
						reportStepDetails(sTarget + " Verifying confirmation message",
								"There is no confirmation message", FAIL);
					}
					return sText;
				case windowMaximize:
					driver.manage().window().maximize();
					reportStepDetails("Maximizing window", "Successfully maximized window", PASS);
					return PASS;
				case typeEmpty:
					webElement(sTarget).clear();
					reportStepDetails("Clearing an input text field", "Successfully cleared text in input field", PASS);
					return PASS;
				case eClickAndWait:
					sTarget = getExcelData(sTarget).trim();
					webElement(sTarget).click();
					sText = encodeSpecialCharacters(webElement(sTarget).getText());
					// waitForPageToLoad().setTimeToWait(30000);
					driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
					reportStepDetails("Clicking button/link/image:" + sText,
							"Successfully clicked the button/link/image:" + sText, PASS);
					return PASS;
				case verifyElementPresent:
					// sText = webElement(sTarget).getText();
					reportStepDetails("Verifying element existence", "Element exists.", PASS);
					return PASS;
				case isElementPresent:
					sText = Boolean.toString(isElementPresent(sTarget));
					reportStepDetails("Checking the existence of element:", "Element exists", PASS);

					return sText;
				case assertAlert:
					sText = driver.switchTo().alert().getText();
					if (sText.equals(sTarget)) {
						reportStepDetails("Expected alert message: " + encodeSpecialCharacters(sTarget),
								"Successfully validated alert message:  " + encodeSpecialCharacters(sText), PASS);
					} else {
						reportStepDtlsWithScreenshot("Expected alert message: " + encodeSpecialCharacters(sTarget),
								"Displayed alert message: " + encodeSpecialCharacters(sText), FAIL);
					}
					return sText;
				case verifyAlert:
					sValue = getExcelData(sTarget).trim();
					sText = driver.switchTo().alert().getText();
					if (sText.equals(sValue)) {
						reportStepDetails(sTarget + " Expected alert message : " + encodeSpecialCharacters(sValue),
								"Successfully validated alert message:  " + encodeSpecialCharacters(sText), PASS);
					} else {
						reportStepDtlsWithScreenshotWithoutExit(
								sTarget + " Expected alert message : " + encodeSpecialCharacters(sValue),
								"Displayed  alert message:  " + encodeSpecialCharacters(sText), FAIL);

					}
					return sText;
				case getText:
					sText = webElement(sTarget).getText();
					return sText;
				case selectWindow:
					driver.switchTo().window(sTarget);

					reportStepDetails("Selecting the window " + encodeSpecialCharacters(sTarget),
							"Successfully selected window with ID " + encodeSpecialCharacters(sTarget), PASS);
					return PASS;
				case selectFrame:

					if (sTarget.matches("[0-9]+")) {
						int index = Integer.parseInt(sTarget);
						driver.switchTo().frame(index);
					} else {
						driver.switchTo().frame(sTarget);
					}

					reportStepDetails("Selecting the window " + encodeSpecialCharacters(sTarget),
							"Successfully selected window with ID " + encodeSpecialCharacters(sTarget), PASS);
					return PASS;

				case waitForFrameAndSelect:

					WebDriverWait wait = new WebDriverWait(driver, 100);
					if (sTarget.matches("[0-9]+")) {
						int index = Integer.parseInt(sTarget);
						driver.switchTo().frame(index);
					} else {
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(sTarget));
						driver.switchTo().frame(sTarget);
					}

					reportStepDetails("Selecting the window " + encodeSpecialCharacters(sTarget),
							"Successfully selected window with ID " + encodeSpecialCharacters(sTarget), PASS);
					return PASS;

				case getAttribute:
					sTarget = sTarget.substring(0, sTarget.lastIndexOf("@"));
					String atribute_name = sTarget.substring(sTarget.lastIndexOf("@") + 1);
					sText = webElement(sTarget).getAttribute(atribute_name);
					return sText;
				case getXpathCount:
					sText = Integer.toString(driver.findElements(By.xpath(sTarget)).size());
					return sText;
				case getSelectedLabel:
					Select foo = new Select(webElement(sTarget));
					sText = foo.getFirstSelectedOption().getText();
					reportStepDetails("Retrieving selected option label",
							"Drop-down contains label:" + encodeSpecialCharacters(sText), PASS);
					return sText;
				case getCookieByName:
					sText = driver.manage().getCookieNamed(sTarget).toString();
					reportStepDetails("Retrieving the value of cookie \"" + encodeSpecialCharacters(sTarget) + "\"",
							"Value of cookie \"" + encodeSpecialCharacters(sTarget) + "\":"
									+ encodeSpecialCharacters(sText),
							PASS);
					return sText;
				case clickAndWait:

					WebDriverWait wait1 = new WebDriverWait(driver, 250);
					WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(by(sTarget)));
					element.click();

					if (sBrowser.contains("Safari")) {
						Thread.sleep(30000);
					} else {
						driver.manage().timeouts().pageLoadTimeout(250, TimeUnit.SECONDS);
					}

					reportStepDetails("Clicking link/image/button", "Successfully clicked link/image/button", PASS);
					return PASS;
				default:
					reportUnknowSeleniumCmdErr(sCommand);
				}
			} else {
				captureScreenShot();
				reportElementNotFound(sCommand);
			}
		} catch (Exception e) {
			e.printStackTrace();

			reportException(sCommand, e.toString());
		}
		return "";
	}

	/**
	 * This method takes selenese command and its target in the form of string
	 * and checks that string is available in the enum or not. If string is
	 * available in the enum list then corresponding case will be executed and
	 * will return value of type String.
	 * 
	 * @param sCommand
	 *            Selenese Command
	 * @param sTarget
	 *            an element locator
	 * @param sTitle
	 *            label name or expected value or excel column name or timeout
	 * @return String either Pass (or) Fail (or) value returned by selenese
	 *         command
	 */
	public String effecta(String sCommand, String sTarget, String sTitle) {
		String sText, encodedTitle, sValue = "";
		int sTargetFlag = 0;
		boolean checked, flag = false;
		commandName.add(sCommand);
		try {
			encodedTitle = encodeSpecialCharacters(sTitle);
			if (sCommand.equals("sendReport") || sCommand.equals("compare") || sCommand.equals("openWindow")
					|| sCommand.equals("waitForPopUp") || sCommand.equals("compareURLs")
					|| sCommand.equals("captureEntirePageScreenshot") || sCommand.equals("waitForElementPresent")
					|| sCommand.equals("waitForTextPresent") || sCommand.equals("eOpenWindow")
					|| sCommand.equals("isElementInVisible") || sCommand.equals("waitUntilElementVisible")
					|| sCommand.equals("verifyElementPresent") || sCommand.equals("getAttribute")) {
				sTargetFlag = 1;
			} else if (sTarget.contains("&&")) {
				String[] sTargetArray = sTarget.split("&&");
				for (int i = 0; i < sTargetArray.length; i++) {
					if (isElementPresent(sTargetArray[i])) {
						sTarget = sTargetArray[i];
						sTargetFlag = 1;
						break;
					}
				}
			} else if (isElementPresent(sTarget)) {
				sTargetFlag = 1;
			}

			if (sTargetFlag == 1) {

				enumCommand = checkEnumConstant(sCommand);
				switch (enumCommand) {
				case verifyElementPresent:
					reportStepDetails("Validated: " + encodedTitle + " Element is available ",
							"Successfully validated element:  " + encodeSpecialCharacters(sTitle), PASS);

					return sTitle;

				case assertValue:
					sText = webElement(sTarget).getAttribute("value").trim();
					sTitle = sTitle.trim();
					if (sText.equals(sTitle)) {
						reportStepDetails("Expected value: " + encodedTitle,
								"Successfully validated value: " + encodeSpecialCharacters(sText), PASS);
					} else {
						reportStepDtlsWithScreenshot("Expected value: " + encodedTitle,
								"Actual value: " + encodeSpecialCharacters(sText), FAIL);
					}
					return sText;
				case isElementInVisible:
					sText = "Element is Present";
					Thread.sleep(3000);
					try {
						if (webElement(sTarget).isDisplayed()) {
							sText = webElement(sTarget).getText();
							reportStepDtlsWithScreenshotWithoutExit("Validated Element :" + encodedTitle, sText, FAIL);
						} else {
							sText = "Element is Not Present";
							reportStepDetails("Validated Element: " + encodedTitle, sText, PASS);
						}
					} catch (Exception e) {
						sText = "Element is Not Present";
						reportStepDetails("Validated Element: " + encodedTitle, sText, PASS);
						e.printStackTrace();
					}
					return sText;
				case eOpenWindow:
					sValue = getExcelData(sTarget);
					driver.navigate().to(sValue);
					reportStepDetails("Opening popup window " + encodedTitle,
							"Successfully opened " + encodedTitle + " popup window", PASS);
					return sValue;
				case waitUntilElementVisible:
					flag = false;
					int secsToWait = Integer.parseInt(sTitle);

					for (int second = 0;; second++) {
						if (second >= secsToWait) {
							flag = false;
							break;
						}
						if (webElement(sTarget).isDisplayed()) {
							flag = true;
							break;
						}
						Thread.sleep(1000);
					}
					return Boolean.toString(flag);
				case isSpecifiedOptionChecked:
					checked = webElement(sTarget).isSelected();
					if (checked)
						reportStepDetails("Verifying whether \"" + encodedTitle + "\" is selected or not",
								"\"" + encodedTitle + "\" is selected", PASS);
					else
						reportStepDetails("Verifying whether \"" + encodedTitle + "\" is selected or not",
								"\"" + encodedTitle + "\" is not selected", PASS);
					return (Boolean.toString(checked));
				case typeEmpty:
					webElement(sTarget).clear();
					reportStepDetails("Setting value of " + encodedTitle, encodedTitle + " is set to : \"\"", PASS);
					return PASS;
				case type:
					sValue = readDataFromExcel(sTitle);
					System.out.println("Input Value of \"" + sTitle + "\": " + sValue);
					WebElement elmnt = webElement(sTarget);
					elmnt.clear();
					elmnt.sendKeys(sValue);
					reportStepDetails("Setting value of " + encodedTitle,
							encodedTitle + " is set to value: \"" + encodeSpecialCharacters(sValue) + "\"", PASS);
					return sValue;
				case typePassword:
					sValue = getExcelData(sTitle);
					elmnt = webElement(sTarget);
					elmnt.clear();
					String value = encryptPassword(sValue);
					elmnt.sendKeys(sValue);
					reportStepDetails("Setting value of " + encodedTitle, encodedTitle + " is set to : " + value, PASS);
					return sValue;
				case isChecked:
					checked = false;
					encodedTitle = encodeSpecialCharacters(sTitle);
					checked = webElement(sTarget).isSelected();
					if (checked)
						reportStepDetails("Verifying whether \"" + encodedTitle + "\" is selected or not",
								"\"" + encodedTitle + "\" is selected", PASS);

					else
						reportStepDetails("Verifying whether \"" + encodedTitle + "\" is selected or not",
								"\"" + encodedTitle + "\" is not selected", PASS);
					return Boolean.toString(checked);
				case waitForElementPresent:

					secsToWait = Integer.parseInt(sTitle);
					// System.out.println("enter into
					// driverWaitForElementPresent");
					WebDriverWait wait = new WebDriverWait(driver, secsToWait);
					WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by(sTarget)));
					if (element == null) {
						reportStepDtlsWithScreenshot("Checking the existence of element in :" + sCommand + " command:",
								"Element verification \"" + sTarget + "\" failed", FAIL);
					}
					return element.toString();

				case waitForValue:
					flag = false;
					secsToWait = Integer.parseInt(sTitle);
					sText = "";
					for (int second = 0;; second++) {
						if (second >= secsToWait) {
							flag = false;
							break;
						}
						sText = webElement(sTarget).getAttribute("value").trim();
						if (sText.trim().length() > 0) {
							flag = true;
							break;
						}
						Thread.sleep(1000);
					}
					return sText;

				case getText:
					encodedTitle = encodeSpecialCharacters(sTitle);
					sText = webElement(sTarget).getText();
					reportStepDetails("Retrieving " + encodedTitle,
							"Retrieved " + encodedTitle + ":" + encodeSpecialCharacters(sText), PASS);
					return sText;
				case getAttribute:

					encodedTitle = encodeSpecialCharacters(sTitle);
					sTarget = sTarget.substring(0, sTarget.lastIndexOf("@"));
					String atribute_type = sTarget.substring(sTarget.lastIndexOf("@") + 1);
					sText = webElement(sTarget).getAttribute(atribute_type);
					reportStepDetails("Retrieving " + encodedTitle,
							"Retrieved " + encodedTitle + ":" + encodeSpecialCharacters(sText), PASS);
					return sText;
				case select:
					sValue = getExcelData(sTitle);
					if (sValue.startsWith("label="))
						sValue = sValue.substring(sValue.indexOf("=") + 1);
					WebElement select = webElement(sTarget);
					List<WebElement> options = select.findElements(By.tagName("option"));
					for (WebElement option : options) {
						if (option.getText().equals(sValue)) {
							option.click();
							break;
						}
					}

					reportStepDetails("Selecting an option from drop-down:" + encodedTitle,
							"Successfully selected the list value: " + encodeSpecialCharacters(sValue), PASS);
					return sValue;

				case captureEntirePageScreenshot:
					String screenshotFile = captureScreenShot();
					reportStepDetails(encodeSpecialCharacters(sTarget), screenshotFile, PASS);

					return PASS;

				case getXpathCount:

					encodedTitle = encodeSpecialCharacters(sTitle);
					sText = String.valueOf(driver.findElements(By.xpath(sTarget)).size());
					reportStepDetails("Verifying " + encodedTitle, "Number of " + encodedTitle + ": " + sText, PASS);
					return sText;
				case openWindow:
					driver.navigate().to(sTarget);
					reportStepDetails("Opening popup window " + encodedTitle,
							"Successfully opened " + encodedTitle + " popup window", PASS);
					return PASS;

				case verifyValue:
					sValue = getExcelData(sTitle).trim();
					sText = webElement(sTarget).getAttribute("value").trim();
					if (sText.equals(sValue)) {
						reportStepDetails(encodedTitle + "  Expected value : " + encodeSpecialCharacters(sValue),
								"Successfully validated value:  " + encodeSpecialCharacters(sText), PASS);
						return sText;
					} else {
						reportStepDtlsWithScreenshot(
								encodedTitle + " Expected value : " + encodeSpecialCharacters(sValue),
								"Displayed value :  " + encodeSpecialCharacters(sText), FAIL);
					}
					return sText;
				case getValue:

					encodedTitle = encodeSpecialCharacters(sTitle).trim();
					sText = webElement(sTarget).getAttribute("value").trim();
					reportStepDetails("Retrieving value of input field: " + encodedTitle,
							"Successfully retrieved value: " + encodeSpecialCharacters(sText), PASS);
					return sText;
				case sendReport:
					reportStepDetails(encodeSpecialCharacters(" " + sTarget), encodeSpecialCharacters(sTitle), PASS);
				case verifySubText:
					sValue = getExcelData(sTitle).trim();
					sText = webElement(sTarget).getText().trim();
					if (sText.contains(sValue)) {
						reportStepDetails(" " + "Verifying text " + encodedTitle, "\"" + encodeSpecialCharacters(sValue)
								+ "\" text appears in \"" + encodeSpecialCharacters(sText) + "\"", PASS);
					} else {
						reportStepDtlsWithScreenshot(" " + "Verifying text " + encodedTitle,
								"\"" + encodeSpecialCharacters(sValue) + "\" text doesn't appears \""
										+ encodeSpecialCharacters(sText) + "\"",
								FAIL);
					}
					return sText;
				case assertSubText:
					sText = webElement(sTarget).getText().trim();
					if (sText.contains(sTitle)) {
						reportStepDetails("Verifying text:\"" + encodedTitle + "\"",
								"\"" + encodedTitle + "\" text appears in \"" + encodeSpecialCharacters(sText) + "\"",
								PASS);
					} else {
						reportStepDtlsWithScreenshot(
								"Verifying text:\"" + encodedTitle + "\"", "\"" + encodedTitle
										+ "\" text doesn't appears in \"" + encodeSpecialCharacters(sText) + "\"",
								FAIL);
					}
					return sText;
				case verifyTextPresent:

					sValue = getExcelData(sTitle).trim();
					sText = webElement(sTarget).getText().trim();
					if (sText.equals(sValue)) {
						reportStepDetails(
								" " + "Validated: " + encodedTitle + " Expected text : "
										+ encodeSpecialCharacters(sValue),
								"Displayed  text :  " + encodeSpecialCharacters(sText), PASS);
					} else {
						reportStepDtlsWithScreenshotWithoutExit(
								" " + "Validated: " + encodedTitle + " Expected text : "
										+ encodeSpecialCharacters(sValue),
								"Displayed  text :  " + encodeSpecialCharacters(sText), FAIL);
					}
					return sText;
				case verifyText:

					sText = webElement(sTarget).getText().trim();
					sValue = sTitle.trim();
					if (sText.equals(sValue)) {
						reportStepDetails(
								" " + "Validated: " + encodedTitle + " Expected text : "
										+ encodeSpecialCharacters(sValue),
								"Successfully validated text:  " + encodeSpecialCharacters(sText), PASS);
					} else {
						reportStepDtlsWithScreenshotWithoutExit(
								" " + "Validated: " + encodedTitle + " Expected text : "
										+ encodeSpecialCharacters(sValue),
								"Displayed  text :  " + encodeSpecialCharacters(sText), FAIL);

					}
					return sText;
				case assertText:
					sText = webElement(sTarget).getText().trim();
					sTitle = sTitle.trim();
					if (sText.equals(sTitle)) {
						reportStepDetails(" " + "Verification:Expected text : " + encodedTitle,
								"Successfully validated text:  " + encodeSpecialCharacters(sText), PASS);
					} else {
						reportStepDtlsWithScreenshot(" " + "Expected text : " + encodedTitle,
								"Displayed  text :  " + encodeSpecialCharacters(sText), FAIL);
						// stopSelenium();
					}
					return sText;
				case assertTextPresent:
					sText = webElement(sTarget).getText().trim();
					sTitle = sTitle.trim();
					if (sText.equals(sTitle)) {
						reportStepDetails(" " + "Expected text : " + encodedTitle,
								"Successfully validated text:  " + encodeSpecialCharacters(sText), PASS);
					} else {
						reportStepDtlsWithScreenshotWithoutExit(" " + "Expected text : " + encodedTitle,
								"Displayed  text :  " + encodeSpecialCharacters(sText), FAIL);
					}
					return sText;
				case click:

					if (sTitle.endsWith(".id") || sTitle.endsWith(".name") || sTitle.endsWith(".link"))
						encodedTitle = encodeSpecialCharacters(sTitle.substring(0, sTitle.lastIndexOf(".")));
					else
						encodedTitle = encodeSpecialCharacters(sTitle);
					webElement(sTarget).click();
					reportStepDetails("Clicking button/link/image:" + encodedTitle,
							"Successfully clicked button/link/image:" + encodedTitle, PASS);
					return PASS;
				case clickAndWait:

					WebDriverWait wait1 = new WebDriverWait(driver, 250);
					WebElement element1 = wait1.until(ExpectedConditions.elementToBeClickable(by(sTarget)));

					if (sTitle.endsWith(".id") || sTitle.endsWith(".name") || sTitle.endsWith(".link"))
						encodedTitle = encodeSpecialCharacters(sTitle.substring(0, sTitle.lastIndexOf(".")));
					else
						encodedTitle = encodeSpecialCharacters(sTitle);
					element1.click();

					try {
						driver.manage().timeouts().pageLoadTimeout(250, TimeUnit.SECONDS);
					} catch (TimeoutException te) {
						reportException(sCommand, sTitle);
					} catch (Exception e) {
						reportException(sCommand, sTitle);
					}

					reportStepDetails("Clicking button/link/image:" + encodedTitle,
							"Successfully clicked button/link/image:" + encodedTitle, PASS);
					return PASS;

				case clickSpecifiedLink:
					webElement(sTarget).click();
					reportStepDetails("Clicking button/link/image:" + encodedTitle,
							"Successfully clicked button/link/image:" + encodedTitle, PASS);
					return PASS;
				case clickSpecifiedLinkNWait:
					webElement(sTarget).click();
					// waitForPageToLoad().setTimeToWait(30000);
					driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
					reportStepDtlsWithScreenshot("Clicking button/link/image:" + encodedTitle,
							"Successfully clicked the button/link/image:" + encodedTitle, PASS);
					return PASS;
				case verifyElement:

				case compare:
					String sTitleString = "";
					String sTargetString = "";
					sTarget = sTarget.trim();
					sTitle = sTitle.trim();
					if (sTarget.contains(":")) {
						String[] sTargetVlaue = sTarget.split(":");
						if (sTargetVlaue[1].equals("")) {
							sTarget = sTargetVlaue[0];
							sTargetString = sTarget;
						} else {
							sTarget = sTargetVlaue[1];
							sTargetString = sTargetVlaue[0] + sTarget;
						}
					} else {
						sTargetString = sTarget;
					}
					if (sTitle.contains(":")) {
						String[] sTitleVlaue = sTitle.split(":");
						if (sTitleVlaue[1].equals("")) {
							sTitle = sTitleVlaue[0];
							sTitleString = sTitle;
						} else {
							sTitle = sTitleVlaue[1];
							sTitleString = sTitleVlaue[0] + sTitle;
						}
					} else {
						sTitleString = sTitle;
					}
					sTarget = sTarget.trim();
					sTitle = sTitle.trim();
					if (sTarget.equals(sTitle)) {
						reportStepDetails(encodeSpecialCharacters(sTargetString), encodeSpecialCharacters(sTitleString),
								PASS);
						return PASS;
					} else {

						reportStepDtlsWithScreenshot(encodeSpecialCharacters(sTargetString),
								encodeSpecialCharacters(sTitleString), FAIL);
						return FAIL;

					}
				case compareURLs:
					String sTitleString1 = "";
					String sTargetString1 = "";
					sTarget = sTarget.trim();
					sTitle = sTitle.trim();
					if (sTarget.contains("::")) {
						String[] sTargetVlaue = sTarget.split("::");
						if (sTargetVlaue[1].equals("")) {
							sTarget = sTargetVlaue[0];
							sTargetString1 = sTarget;
						} else {
							sTarget = sTargetVlaue[1];
							sTargetString1 = sTargetVlaue[0] + ": " + sTarget;
						}
					} else {
						sTargetString1 = sTarget;
					}
					if (sTitle.contains("::")) {
						String[] sTitleVlaue = sTitle.split("::");
						if (sTitleVlaue[1].equals("")) {
							sTitle = sTitleVlaue[0];
							sTitleString1 = sTitle;
						} else {
							sTitle = sTitleVlaue[1];
							sTitleString1 = sTitleVlaue[0] + ": " + sTitle;
						}
					} else {
						sTitleString1 = sTitle;
					}
					sTarget = sTarget.trim();
					sTitle = sTitle.trim();
					if (sTarget.equals(sTitle)) {
						reportStepDetails(encodeSpecialCharacters(sTargetString1),
								encodeSpecialCharacters(sTitleString1), PASS);
						return PASS;
					} else {
						reportStepDtlsWithScreenshot(encodeSpecialCharacters(sTargetString1),
								encodeSpecialCharacters(sTitleString1), FAIL);
						return FAIL;
					}

				default:
					reportUnknowSeleniumCmdErr(sCommand);
				}
			} else {
				if (sCommand.equals("verifyElement")) {
					encodedTitle = encodeSpecialCharacters(sTitle);
					reportStepDtlsWithScreenshot("Checking the existence of element:" + encodedTitle,
							"Element verification \"" + encodedTitle + "\" failed", FAIL);
					return Boolean.toString(false);
				} else if (sCommand.equals("verifyElementPresent")) {
					encodedTitle = encodeSpecialCharacters(sTitle);
					reportStepDtlsWithScreenshotWithoutExit("Checking the existence of element:" + encodedTitle,
							"Element verification \"" + encodedTitle + "\" failed", FAIL);
				} else {
					captureScreenShot();
					reportElementNotFound(sCommand, encodedTitle);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (sCommand.contains("waitFor")) {
				reportException(sCommand, "Expected");
			} else {
				reportException(sCommand, sTitle);
			}
			// failMessageDesc += "\"" + sTitle + "\" Locator Not Found";
		}
		return "";
	}

	/**
	 * This method takes selenese command and its target in the form of string
	 * and checks that string is available in the enum or not. If string is
	 * available in the enum list then corresponding case will be executed and
	 * will return value of type String.
	 * 
	 * @param sCommand
	 *            Selenese Command
	 * @param sTarget
	 *            an element locator (or) verification report (or) timeout
	 * @param sTitle
	 *            label name or expected value or excel column name (or)
	 *            Description
	 * @param sResult
	 *            label name or Pass/Fail status or timeout
	 * @return String either Pass (or) Fail (or) value returned by selenese
	 *         command
	 */
	public String effecta(String sCommand, String sTarget, String sTitle, String sResult) {
		String sText = "";
		String encodedTitle = "";
		int sTargetFlag = 0;
		boolean flag = false;
		commandName.add(sCommand);
		try {
			if (sCommand.equals("sendReport") || sCommand.equals("sendReportWithOutExit")
					|| sCommand.equals("captureEntirePageScreenshot") || sCommand.equals("waitForElementPresent")) {
				sTargetFlag = 1;
			} else if (sTarget.contains("&&")) {
				String[] sTargetArray = sTarget.split("&&");
				for (int i = 0; i < sTargetArray.length; i++) {
					if (isElementPresent(sTargetArray[i])) {
						sTarget = sTargetArray[i];
						sTargetFlag = 1;
						break;
					}
				}
			} else if (isElementPresent(sTarget)) {
				sTargetFlag = 1;
			}

			if (sTargetFlag == 1) {

				enumCommand = checkEnumConstant(sCommand);
				switch (enumCommand) {
				case type:
					String sValue = sTitle;
					WebElement elmnt1 = webElement(sTarget);
					elmnt1.clear();
					elmnt1.sendKeys(sValue);
					reportStepDetails("Setting value of " + sResult,
							sResult + " is set to value: \"" + encodeSpecialCharacters(sValue) + "\"", PASS);
					return sValue;
				case typePassword:
					sValue = sTitle;
					WebElement eleObj = webElement(sTarget);
					eleObj.clear();
					eleObj.sendKeys(sValue);

					String value = encryptPassword(sValue);

					reportStepDetails("Setting value of " + sResult, sResult + " is set to : " + value, PASS);
					return sValue;
				/*
				 * case select: sValue = sTitle; if
				 * (sValue.startsWith("label=")) sValue =
				 * sValue.substring(sValue.indexOf("=") + 1); WebElement select
				 * = webElement(sTarget); List<WebElement> options =
				 * select.findElements(By .tagName("option")); for (WebElement
				 * option : options) {
				 * 
				 * if (option.getText().equals(sValue)) { option.click(); break;
				 * } }
				 * 
				 * Select select = new Select(webElement(sTarget));
				 * select.selectByValue(sValue);
				 * 
				 * reportStepDetails("Selecting an option from drop-down:" +
				 * sResult, "Successfully selected the list value : " +
				 * encodeSpecialCharacters(sValue), PASS); return sValue;
				 */

				case select:
					WebElement selectElement = webElement(sTarget);
					sValue = null;
					sResult = encodeSpecialCharacters(sResult);
					if (selectElement == null) {
						reportStepDetails("Verifying \"" + sResult + "\" Select Dropdown Locator",
								"\"" + sResult + "\" Locator Not Valide, Please Check Once Again", FAIL);
					} else {
						Select selectObj = new Select(selectElement);

						if (sTitle.contains("Index=")) {
							sValue = sTitle.substring(sTitle.indexOf("=") + 1);
							selectObj.selectByIndex(Integer.parseInt(sValue));

							reportStepDetails("Selecting an option from drop-down:" + sResult,
									"Successfully selected Index : " + encodeSpecialCharacters(sValue), PASS);

						} else if (sTitle.contains("Value=")) {
							sValue = sTitle.substring(sTitle.indexOf("=") + 1);
							selectObj.selectByValue(sValue);

							reportStepDetails("Selecting an option from drop-down:" + sResult,
									"Successfully Selected Value : " + encodeSpecialCharacters(sValue), PASS);

						} else if (sTitle.contains("Text=")) {
							sValue = sTitle.substring(sTitle.indexOf("=") + 1);
							selectObj.selectByVisibleText(sValue);

							reportStepDetails("Selecting an option from drop-down:" + sResult,
									"Successfully Selected Visible Text : " + encodeSpecialCharacters(sValue), PASS);

						} else {
							reportStepDetails(
									"Verifying Provided Index/Value/Text Of \"" + sResult + "\" Select Dropdown",
									"Index/Value/Text \"" + encodeSpecialCharacters(sTitle)
											+ "\" Is Not Valide, Please Check Once Again",
									FAIL);
						}
					}
					return sValue;

				case sendReport:
					if (sResult.equalsIgnoreCase(FAIL)) {
						reportStepDtlsWithScreenshot(encodeSpecialCharacters(" " + sTarget),
								encodeSpecialCharacters(sTitle), FAIL);
						// stopSelenium();
					} else if (sResult.equalsIgnoreCase(PASS)) {
						reportStepDetails(encodeSpecialCharacters(" " + sTarget), encodeSpecialCharacters(sTitle),
								PASS);
					} else {
						reportUnknowSeleniumCmdErr("sendReport with argument as " + sResult);
					}
					return sResult;
				case captureEntirePageScreenshot:

					reportStepDtlsWithScreenshot(encodeSpecialCharacters(sTarget), encodeSpecialCharacters(sTitle),
							sResult);
					return PASS;
				case clickAndWait:

					WebDriverWait wait1 = new WebDriverWait(driver, 250);
					WebElement element1 = wait1.until(ExpectedConditions.elementToBeClickable(by(sTarget)));

					encodedTitle = encodeSpecialCharacters(sTitle);
					element1.click();
					if (sBrowser.contains("Safari")) {
						Thread.sleep(Integer.parseInt(sResult));
					} else {

						try {
							driver.manage().timeouts().pageLoadTimeout(250, TimeUnit.SECONDS);
						} catch (TimeoutException te) {
							reportException(sCommand, sTitle);
						} catch (Exception e) {
							reportException(sCommand, sTitle);
						}
					}
					reportStepDetails("Clicking button/link/image:" + encodedTitle,
							"Successfully clicked button/link/image:" + encodedTitle, PASS);

					return PASS;
				case clickAndWaitForElementPresent:
					encodedTitle = encodeSpecialCharacters(sTitle);
					webElement(sTarget).click();
					WebDriverWait wait = new WebDriverWait(driver, 200);
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(by(sResult)));
					} catch (TimeoutException te) {
						// reportException(sCommand, te.toString());
						reportException(sCommand, sTitle);
					} catch (Exception e) {
						reportException(sCommand, sTitle);
					}
					reportStepDetails("Clicking button/link/image:" + encodedTitle,
							"Successfully clicked button/link/image:" + encodedTitle, PASS);

					return PASS;

				case sendReportWithOutExit:
					if (sResult.equalsIgnoreCase(FAIL)) {
						reportStepDtlsWithScreenshotWithoutExit(encodeSpecialCharacters(" " + sTarget),
								encodeSpecialCharacters(sTitle), FAIL);
					} else if (sResult.equalsIgnoreCase(PASS)) {
						reportStepDetails(encodeSpecialCharacters(" " + sTarget), encodeSpecialCharacters(sTitle),
								PASS);
					} else {
						reportUnknowSeleniumCmdErr("sendReportWithOutExit with argument as " + sResult);
					}
					return sResult;
				case waitForElementPresent:
					flag = false;
					int secsToWait = Integer.parseInt(sTitle);

					for (int second = 0;; second++) {
						if (second >= secsToWait) {
							flag = false;
							break;
						}
						if (isElementPresent(sTarget)) {
							flag = true;
							break;
						}
						Thread.sleep(1000);
					}
					if (!flag) {
						encodedTitle = encodeSpecialCharacters(sResult);
						reportStepDtlsWithScreenshot("Checking the existence of element:" + encodedTitle,
								"Element verification \"" + encodedTitle + "\" failed", FAIL);
					}
					return Boolean.toString(flag);
				case verifyText:
					/*
					 * sValue = getExcelData(sTitle); sText =
					 * webElement(sTarget).getText().trim();
					 */
					sText = webElement(sTarget).getText().trim();
					sTitle = sTitle.trim();
					encodedTitle = encodeSpecialCharacters(sResult);
					if (sText.equalsIgnoreCase(sTitle)) {
						reportStepDetails(" " + "Expected " + encodedTitle + ": " + encodeSpecialCharacters(sTitle),
								"Successfully validated " + encodedTitle + ":  " + encodeSpecialCharacters(sText),
								PASS);
					} else {
						reportStepDtlsWithScreenshotWithoutExit(
								" " + "Expected " + encodedTitle + ": " + encodeSpecialCharacters(sTitle),
								"Displayed " + encodedTitle + ":  " + encodeSpecialCharacters(sText), FAIL);
					}
					return sText;
				case assertText:

					sText = webElement(sTarget).getText().trim();

					sTitle = sTitle.trim();
					// encodedTitle =
					// encodeSpecialCharacters(getExcelData(sResult));
					encodedTitle = encodeSpecialCharacters(sResult);
					if (sText.equalsIgnoreCase(sTitle)) {
						reportStepDetails(" " + "Expected " + encodedTitle + ": " + encodeSpecialCharacters(sTitle),
								"Successfully validated " + encodedTitle + ":  " + encodeSpecialCharacters(sText),
								PASS);
					} else {
						reportStepDtlsWithScreenshot(
								" " + "Expected " + encodedTitle + ": " + encodeSpecialCharacters(sTitle),
								"Displayed " + encodedTitle + ":  " + encodeSpecialCharacters(sText), FAIL);
						// stopSelenium();
					}

					return sText;

				case assertValue:
					sText = webElement(sTarget).getAttribute("value").trim();
					sTitle = sTitle.trim();
					// encodedTitle =
					// encodeSpecialCharacters(getExcelData(sResult));
					encodedTitle = encodeSpecialCharacters(sResult);
					if (sText.equalsIgnoreCase(sTitle)) {
						reportStepDetails("Expected " + encodedTitle + ": " + encodeSpecialCharacters(sTitle),
								"Successfully validated " + encodedTitle + ":" + encodeSpecialCharacters(sText), PASS);
					} else {
						reportStepDtlsWithScreenshot(
								"Expected " + encodedTitle + ": " + encodeSpecialCharacters(sTitle),
								"Displayed " + encodedTitle + ":  " + encodeSpecialCharacters(sText), FAIL);
					}
					return sText;
				case typeSpecifiedText:
					encodedTitle = encodeSpecialCharacters(sResult);
					WebElement elmnt = webElement(sTarget);
					elmnt.clear();
					// System.out.println("sTitlesTitle"+sTitle);
					elmnt.sendKeys(sTitle);
					// reportStepDetails("Setting value of " + encodedTitle,
					// encodedTitle + " is set to value: \""
					// + encodeSpecialCharacters(sTitle) + "\"",
					// PASS);
					return sTitle;
				case assertTextPresent:
					sText = webElement(sTarget).getText().trim();
					sTitle = sTitle.trim();
					// encodedTitle =
					// encodeSpecialCharacters(getExcelData(sResult));
					encodedTitle = encodeSpecialCharacters(sResult);
					if (sText.equals(sTitle)) {
						reportStepDetails(" " + "Expected " + encodedTitle + ": " + encodeSpecialCharacters(sTitle),
								"Successfully validated " + encodedTitle + ":  " + encodeSpecialCharacters(sText),
								PASS);
					} else {
						reportStepDtlsWithScreenshotWithoutExit(
								" " + "Expected " + encodedTitle + ": " + encodeSpecialCharacters(sTitle),
								"Displayed " + encodedTitle + ":  " + encodeSpecialCharacters(sText), FAIL);
						// stopSelenium();
						// fail(sCommand);
					}
					return sText;
				case assertSubText:
					sText = webElement(sTarget).getText().trim();
					// encodedTitle =
					// encodeSpecialCharacters(getExcelData(sResult));
					encodedTitle = encodeSpecialCharacters(sResult);
					if (sText.contains(sTitle)) {
						reportStepDetails("Verifying " + encodedTitle + ":\"" + encodeSpecialCharacters(sTitle) + "\"",
								"\"" + encodeSpecialCharacters(sTitle) + "\" text appears in \""
										+ encodeSpecialCharacters(sText) + "\"",
								PASS);
					} else {
						reportStepDtlsWithScreenshot(
								"Verifying " + encodedTitle + ":\"" + encodeSpecialCharacters(sTitle) + "\"",
								"\"" + encodeSpecialCharacters(sTitle) + "\" text doesn't appears in \""
										+ encodeSpecialCharacters(sText) + "\"",
								FAIL);
					}
					return sText;
				default:
					reportUnknowSeleniumCmdErr(sCommand);
				}
			} else {
				captureScreenShot();
				reportElementNotFound(sCommand, sResult);
			}

		} catch (Exception e) {

			e.printStackTrace();
			// System.out.println("Enter into exception block");
			captureScreenShot();
			reportException(sCommand, sResult);
		}
		return "";
	}

	/**
	 * This method takes selenese command in the form of string and checks that
	 * string is available in the enum or not. If string is available in the
	 * enum list then corresponding case will be executed and will return array
	 * of type String.
	 * 
	 * @param sCommand
	 *            Selenese Command
	 * @return array of Strings
	 */
	public String[] effectaArray(String sCommand) {
		try {
			enumCommand = checkEnumConstant(sCommand);
			switch (enumCommand) {
			case getAllWindowNames:
				Set<String> availableWindows = driver.getWindowHandles();
				String[] windowHandles = availableWindows.toArray(new String[availableWindows.size()]);
				/*
				 * for (String handle :driver.getWindowHandles()) { String
				 * handles = driver.switchTo().window(handle).getTitle(); }
				 * String options=(String)handles[1];
				 * driver.switchTo().window((String)handles[1]);
				 */
				reportStepDetails("Retrieving all window names",
						"No of windows identified:\"" + windowHandles.length + "\"", PASS);
				return windowHandles;
			default:
				reportUnknowSeleniumCmdErr(sCommand);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportException(sCommand, e.toString());
		}
		return null;
	}

	/**
	 * This method takes selenese command and target in the form of string and
	 * checks that string is available in the enum or not. If string is
	 * available in the enum list then corresponding case will be executed and
	 * will return array of type String.
	 * 
	 * @param sCommand
	 *            Selenese Command
	 * @param sTarget
	 *            an element locator
	 * @return array of Strings
	 */
	public String[] effectaArray(String sCommand, String sTarget) {
		int sTargetFlag = 0;
		try {
			if (sTarget.contains("&&")) {
				String[] sTargetArray = sTarget.split("&&");
				for (int t = 0; t < sTargetArray.length; t++) {
					if (isElementPresent(sTargetArray[t])) {
						sTarget = sTargetArray[t];
						sTargetFlag = 1;
						break;
					}
				}
			} else if (isElementPresent(sTarget)) {
				sTargetFlag = 1;
			}

			if (sTargetFlag == 1) {
				commandName.add(sCommand);
				enumCommand = checkEnumConstant(sCommand);
				switch (enumCommand) {
				case getSelectOptions:
					Select select = new Select(webElement(sTarget));
					List<WebElement> options = select.getOptions();
					String[] optionnames = new String[options.size()];
					for (int i = 0; i < options.size(); i++) {
						WebElement we = options.get(i);
						optionnames[i] = we.getText();
					}
					reportStepDetails("Retrieving all list of options from select drop-down",
							"No of options available in the drop-down \"" + optionnames.length + "\"", PASS);
					return optionnames;
				default:
					reportUnknowSeleniumCmdErr(sCommand);
				}
			} else {
				captureScreenShot();
				reportElementNotFound(sCommand);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportException(sCommand, e.toString());
		}
		return null;
	}

	/**
	 * This method takes selenese command and target in the form of string and
	 * checks that string is available in the enum or not. If string is
	 * available in the enum list then corresponding case will be executed and
	 * will return array of type String.
	 * 
	 * @param sCommand
	 *            Selenese Command
	 * @param sTarget
	 *            an element locator
	 * @param sTitle
	 *            labelname (or) fieldname
	 * @return array of Strings
	 */
	public String[] effectaArray(String sCommand, String sTarget, String sTitle) {
		int sTargetFlag = 0;
		String encodedTitle = "";
		try {
			if (sTarget.contains("&&")) {
				String[] sTargetArray = sTarget.split("&&");
				for (int t = 0; t < sTargetArray.length; t++) {
					if (isElementPresent(sTargetArray[t])) {
						sTarget = sTargetArray[t];
						sTargetFlag = 1;
						break;
					}
				}
			} else if (isElementPresent(sTarget)) {
				sTargetFlag = 1;
			}

			if (sTargetFlag == 1) {
				commandName.add(sCommand);
				enumCommand = checkEnumConstant(sCommand);
				switch (enumCommand) {
				case getSelectOptions:
					encodedTitle = encodeSpecialCharacters(getExcelData(sTitle));
					Select select = new Select(webElement(sTarget));
					List<WebElement> options = select.getOptions();
					String[] optionnames = new String[options.size()];
					for (int i = 0; i < options.size(); i++) {
						WebElement we = options.get(i);
						optionnames[i] = we.getText();
					}
					reportStepDetails("Retrieving all list of options from drop-down \"" + encodedTitle + "\"",
							"No of options available in the drop-down \"" + encodedTitle + "\": " + optionnames.length,
							PASS);
					return optionnames;
				default:
					reportUnknowSeleniumCmdErr(sCommand);
				}
			} else {
				captureScreenShot();
				reportElementNotFound(sCommand, sTitle);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportException(sCommand, e.toString());
		}
		return null;
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			// fail(Ex.toString());
			return false;
		}
	}

	public boolean isElementPresent(String sTarget) {
		try {
			webElement(sTarget);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} catch (StaleElementReferenceException s) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This method reports element not found message and stops execution
	 * 
	 * @param sCommand
	 *            selenese command specified in the script
	 */
	private void reportElementNotFound(String sCommand) {

		reportStepDtlsWithScreenshot("The element of a command " + "\"" + sCommand + "\" not found",
				"The element of a command " + "\"" + sCommand + "\" not found at step : " + iStepCount
						+ " : on the displayed page",
				FAIL);

	}

	private void reportElementNotFound(String sCommand, String sTitle) {

		reportStepDtlsWithScreenshot("\"" + sTitle + "\" element  locator not found",
				"The \"" + sTitle + "\" element  locator not found to " + "\"" + sCommand + "\" at step : " + iStepCount
						+ " : on the displayed page",
				FAIL);

	}
	/**
	 * This method reports exception details and stops execution.
	 * 
	 * @param sCommand
	 *            selenese command specified in the script
	 * @param oExp
	 *            Exception Object
	 */
	/*
	 * private void reportException(String sCommand, Exception oExp) {
	 * commandName.add("Exception"); reportStepDtlsWithScreenshot(sCommand,
	 * "Thrown an error : " + encodeSpecialCharacters(oExp.toString()), FAIL); }
	 */

	/**
	 * This method reports error message corresponding to exception and stops
	 * execution.
	 * 
	 * @param sCommand
	 *            selenese command specified in the script
	 * @param sErrMsg
	 *            error message corresponding to exception
	 */
	private void reportException(String sCommand, String elementName) {

		// System.out.println("entered into report exception method");
		/*
		 * reportStepDtlsWithScreenshot(sCommand, "Thrown an error : " +
		 * encodeSpecialCharacters(sErrMsg), FAIL);
		 */
		commandName.add("Exception");
		reportStepDtlsWithScreenshot(sCommand, elementName + " element is not found", FAIL);
	}

	/**
	 * This method reports unknown selenium command error to end user.
	 * 
	 * @param sCommand
	 *            selenese command specified in the script
	 */
	private void reportUnknowSeleniumCmdErr(String sCommand) {
		commandName.add("UnKnown Command");
		reportStepDtlsWithScreenshot("Unknown Framework Command " + "\"" + sCommand + "\"",
				"Unknown Selenium/effecta Command " + "\"" + sCommand + "\"please Contact Automtaion team", FAIL);
	}

	public void startSelenium() {

		try {
			if (this.sauceUser.equalsIgnoreCase("Local"))
				invokeSeleniumLocally();
			else {
				AccessDBMethods obj = new AccessDBMethods(sProjectName);
				String accessKey = obj.getSauceuserAccessKey(this.sauceUser);
				System.out.println("Sauce Username: " + this.sauceUser + "\n" + "AccessKey: " + accessKey);
				invokeSeleniumInSauceLabs(this.sauceUser, accessKey);
			}
		} catch (Exception e) {

			e.printStackTrace();
			reportStepDtlsWithScreenshot("Launching main page",
					"Thrown an error : " + encodeSpecialCharacters(e.toString()), FAIL);
		}
	}

	public void startSelenium(String url) {
		// System.out.println("******startSelenium(-)**********"+url);
		sURL = url;
		try {
			if (this.sauceUser.equalsIgnoreCase("Local")) {
				invokeSeleniumLocally();
			} else {
				String accessKey = getSauceuserAccessKey(this.sauceUser);
				System.out.println("Sauce Username: " + this.sauceUser + "\n" + "AccessKey: " + accessKey);
				this.accessKey = accessKey;
				invokeSeleniumInSauceLabs(this.sauceUser, accessKey);
			}
		} catch (Exception e) {
			e.printStackTrace();
			reportStepDtlsWithScreenshot("Launching main page",
					"Thrown an error : " + encodeSpecialCharacters(e.toString()), FAIL);
		}
	}

	/**
	 * This method starts selenium server and opens application in the specified
	 * browser locally.
	 */
	private void invokeSeleniumLocally() {
		try {
			System.out.println("Starting Selenium Locally...\n");
			if (sBrowser.equalsIgnoreCase("Firefox")) {
				FirefoxProfile firefoxProfile = new FirefoxProfile();
				System.out.println("about to create firefoxdriver");
				driver = new FirefoxDriver(firefoxProfile);
			} else if (sBrowser.equalsIgnoreCase("Internet Explorer")) {
				// String osVersion=System.getProperty("os.arch");
				File file = null;
				file = new File(sAutomationPath + "Resources" + File.separator + "IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				this.driver = new InternetExplorerDriver();

			} else if (sBrowser.equalsIgnoreCase("Safari")) {
				driver = new SafariDriver();
			} else if (sBrowser.equalsIgnoreCase("Chrome")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("chrome.switches", "--disable-extensions");
				File file = new File(sAutomationPath + "Resources" + File.separator + "chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				driver = new ChromeDriver(options);
			} else if (sBrowser.equalsIgnoreCase("Android")) {
				// capabilities for Appium
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("device", "Android");
				capabilities.setCapability("deviceName", "03157df34367203d"); // shaik
				// capabilities.setCapability("deviceName", "0123456789ABCDEF");
				// //testdevice
				// capabilities.setCapability("deviceName", "018df784b01445a0");
				// //vijay
				capabilities.setCapability("newCommandTimeout", 120);
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("autoLaunch", true);
				capabilities.setCapability("platformVersion", "5.1.1");
				capabilities.setCapability("app-package", "com.scotts.menswear");
				capabilities.setCapability("app-activity", "com.jd.jdsports.MainActivity");

				driver = (AppiumDriver) new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			} else if (sBrowser.equalsIgnoreCase("iOS")) {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("appium-version", "1.0");
				capabilities.setCapability("platformName", "iOS");
				capabilities.setCapability("platformVersion", "8.4");
				capabilities.setCapability("deviceName", "iPhone 6");
				driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			}

			// System.out.println("URL: " + sURL);
			// System.out.println("Project Name:" + sProjectName);
			if (driver == null) {
				System.err.println("Webdriver object is null");
			}
			// driver.manage().deleteAllCookies();
			//System.out.println("cookies deleted");

			if (sURL == "" || sURL == null) {
				
				reportStepDetails(" Launching Application", "Successfully launched Application ", PASS);
				System.out.println("came to if loop end");
			} else {
				driver.get(sURL);
				driver.manage().window().maximize();
				System.out.println("Browser Launched With URL: " + sURL);

				commandName.add("OPen");
				reportStepDetails(" Launching main page", "Successfully launched main page: "
						+ encodeSpecialCharacters(sURL) + " in  " + sBrowser + " browser ", PASS);
			}

			startTime = new Date();
			serviceStartTime = new Date();

		} catch (WebDriverException e) {
			e.printStackTrace();
			reportStepDtlsWithScreenshot("Launching main page",
					"Thrown an error : " + encodeSpecialCharacters(e.toString()), FAIL);
			// stopSelenium();
			//// fail("invokeSeleniumLocally");

		} catch (Exception e) {
			e.printStackTrace();
			reportStepDtlsWithOutScreenshot("Launching main page",
					"Thrown an error : " + encodeSpecialCharacters(e.toString()), FAIL);
			// stopSelenium();
			//// fail("invokeSeleniumLocally");

		}
	}

	/**
	 * This method starts selenium server and opens application in the specified
	 * browser in sauce labs.
	 */
	private void invokeSeleniumInSauceLabs(String username, String accessKey) {

		try {

			capabilities = new DesiredCapabilities();
			capabilities.setCapability("record-video", true);
			capabilities.setCapability("video-upload-on-pass", true);
			capabilities.setCapability("record-screenshots", false);
			capabilities.setCapability("sauce-advisor", false);
			capabilities.setCapability("requireWindowFocus", true);
			// capabilities.setCapability("max-duration", 120);

			if (os.equalsIgnoreCase("Windows XP")) {
				capabilities.setPlatform(Platform.XP);
				invokeSeleniumUtility(capabilities);
			} else if (os.equalsIgnoreCase("windows 7")) {
				capabilities.setCapability("platform", "Windows 7");
				invokeSeleniumUtility(capabilities);
			} else if (os.equalsIgnoreCase("windows 8")) {
				capabilities.setCapability("platform", "Windows 8");
				invokeSeleniumUtility(capabilities);
			} else if (os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
				invokeSeleniumUtility(capabilities);
			} else if (os.equalsIgnoreCase("Windows 8.1")) {
				if (this.sBrowser.toLowerCase().contains("firefox")) {
					capabilities = new SauceEnvironment(capabilities, this.sBrowser).win81Firefox();
				} else if (this.sBrowser.toLowerCase().contains("internet")) {
					capabilities = new SauceEnvironment(capabilities, this.sBrowser).win81IE();
				} else if (this.sBrowser.toLowerCase().contains("chrome")) {
					capabilities = new SauceEnvironment(capabilities, this.sBrowser).win81Chrome();
				}
				capabilities.setCapability("name", this.sECName);
			}

			else if (os.equalsIgnoreCase("Mac-OS X 10.6")) {
				if (this.sBrowser.toLowerCase().contains("firefox")) {
					capabilities = new SauceEnvironment(capabilities, this.sBrowser).macSnowFirefox();
				} else if (this.sBrowser.toLowerCase().contains("safari")) {
					capabilities = new SauceEnvironment(capabilities, this.sBrowser).macSnowSafari();
				} else if (this.sBrowser.toLowerCase().contains("chrome")) {
					capabilities = new SauceEnvironment(capabilities, this.sBrowser).macSnowSnowChrome();
				}
				capabilities.setCapability("name", this.sECName);
			} else if (os.equalsIgnoreCase("Mac-OS X 10.8")) {
				if (this.sBrowser.toLowerCase().contains("safari")) {
					capabilities = new SauceEnvironment(capabilities, this.sBrowser).macMountainSafari();
				} else if (this.sBrowser.toLowerCase().contains("chrome")) {
					capabilities = new SauceEnvironment(capabilities, this.sBrowser).macMountainChrome();
				}
				capabilities.setCapability("name", this.sECName);
			} else if (os.equalsIgnoreCase("Mac-OS X 10.9")) {
				if (this.sBrowser.toLowerCase().contains("firefox")) {
					capabilities = new SauceEnvironment(capabilities, this.sBrowser).macMavericksFirefox();
				} else if (this.sBrowser.toLowerCase().contains("safari")) {
					capabilities = new SauceEnvironment(capabilities, this.sBrowser).macMavericksSafari();
				} else if (this.sBrowser.toLowerCase().contains("chrome")) {
					capabilities = new SauceEnvironment(capabilities, this.sBrowser).macMavericksChrome();
				}
				capabilities.setCapability("name", this.sECName);
			}

			else if (os.equalsIgnoreCase("Android")) {
				capabilities = DesiredCapabilities.android();
				capabilities.setCapability("platform", "Linux");
				invokeSeleniumUtility1(capabilities);
			} else if (os.equalsIgnoreCase("OS X 10.6")) {
				if (splitString(sBrowser)[0].equalsIgnoreCase("Iphone")) {
					capabilities = DesiredCapabilities.iphone();
					capabilities.setCapability("browserName", "iPhone");
					capabilities.setCapability("device-orientation", "portrait");

				} else if (splitString(sBrowser)[0].equalsIgnoreCase("Ipad")) {
					capabilities = DesiredCapabilities.ipad();
				}
				capabilities.setCapability("platform", "OS X 10.6");
				invokeSeleniumUtility1(capabilities);
			} else if (os.equalsIgnoreCase("OS X 10.8")) {
				if (splitString(sBrowser)[0].equalsIgnoreCase("Iphone")) {
					capabilities = DesiredCapabilities.iphone();
					capabilities.setCapability("browserName", "iPhone");
					capabilities.setCapability("device-orientation", "portrait");
				} else if (splitString(sBrowser)[0].equalsIgnoreCase("Ipad")) {
					capabilities = DesiredCapabilities.ipad();
				}
				capabilities.setCapability("platform", "OS X 10.8");
				invokeSeleniumUtility1(capabilities);
			}

			// System.out.println("accessKeyaccessKey"+accessKey);

			driver = new RemoteWebDriver(
					new URL("http://" + username + ":" + accessKey + "@ondemand.saucelabs.com:80/wd/hub"),
					capabilities);
			driver.get(sURL);
			startTime = new Date();
			serviceStartTime = new Date();
			reportStepDetails("Launching main page", "Successfully launched main page: " + encodeSpecialCharacters(sURL)
					+ " in  " + sBrowser + " browser ", PASS);
		} catch (Exception e) {
			e.printStackTrace();
			reportStepDtlsWithScreenshot("Launching main page",
					"Thrown an error : " + encodeSpecialCharacters(e.toString()), FAIL);
		}
	}

	public String getCurrentBrowserVersion() {
		return capabilities.getVersion();
	}

	public void invokeSeleniumUtility(DesiredCapabilities capabilities) {
		if (splitString(this.sBrowser)[0].toLowerCase().contains("internet")) {
			capabilities.setBrowserName("internet explorer");
			capabilities.setVersion(splitString(this.sBrowser)[2]);
		} else {
			capabilities.setBrowserName(splitString(this.sBrowser)[0]);
			capabilities.setVersion(splitString(this.sBrowser)[1]);
		}
		capabilities.setCapability("name", this.sECName);
		// capabilities.setCapability("parent-tunnel", "eash");

	}

	public void invokeSeleniumUtility1(DesiredCapabilities capabilities) {
		capabilities.setVersion(splitString(sBrowser)[1]);
		capabilities.setCapability("name", sECName);
	}

	public static String[] splitString(String sbrowser) {
		// String regex = "(?<=[\\w&&\\D])(?=\\d)";
		String regex = " ";
		String p[] = sbrowser.split(regex);
		return p;
	}

	public void stopSelenium() {
		try {
			System.out.println("Entered into stop selenium" + iStepCount);
			if (this.sauceUser.equalsIgnoreCase("Local"))
				stopSeleniumRunningLocally();
			else
				stopSeleniumRunningInSauceLabs();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void stopSeleniumWithOutExit() {
		try {
			System.out.println("Entered into stop selenium" + iStepCount);
			if (this.sauceUser.equalsIgnoreCase("Local"))
				stopSeleniumRunningLocallyWithOutExit();
			else
				stopSeleniumRunningInSauceLabsWithOutExit();
			startTime = new Date();
			this.failMessageDesc = "";
		} catch (Exception e) {
			// fail("stopSelenium:"+e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * This method will close application, stops webdriver and calls report
	 * generation method.
	 * 
	 * @throws Exception
	 */
	public void stopSeleniumRunningLocally() throws Exception {
		String jobID = null;
		try {
			if (driver != null) {
				jobID = ((RemoteWebDriver) driver).getSessionId().toString();
				System.out.println("jobid=" + jobID);
			} else
				System.out.println(
						"***driver object is null while type casting from webdriver to remotewebdriver for jobid****");
			String[] values_sauce = { this.executionTime, this.currentUser, this.instanceName, this.moduleName,
					this.testSetName, this.sECName, this.sauceUser, this.os, jobID, this.sResultFlag };
			System.out.println("ResultFlag = " + this.sResultFlag);
			setSauceLabValues(values_sauce);
			// String sInsertTestSetResults = "Insert into
			// TestSetResults(ExecutionDate,Username,Execution_Environment,ModuleName,TestSetName,TestCaseName,SauceLabAccountName,OS,BrowserName,SessionID,Status)
			// values ('" + this.executionTime + "','" + this.currentUser +
			// "','" + this.instanceName + "','" + this.moduleName + "','" +
			// this.testSetName + "','" + this.testCaseName + "','" +
			// this.sauceUser + "','" + this.os + "','" + this.sBrowser + "','"
			// + jobID + "','" + this.sResultFlag + "');";
			// insertDataIntoAccessDB("TestSets", sInsertTestSetResults);

			// generateHtml(getTempPath() + this.sECName + ".xml");
			this.driver.quit();
			// killIEAndChromeDrivers();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("entered into finally");
		}
	}

	public void quitBrowser() throws Exception {
		driver.quit();
		killIEAndChromeDrivers();
	}

	/**
	 * This method will close application, stops webdriver and calls report
	 * generation method.
	 * 
	 * @throws Exception
	 */
	public void stopSeleniumRunningLocallyWithOutExit() throws Exception {
		String jobID = null;
		try {
			if (driver != null) {
				jobID = ((RemoteWebDriver) driver).getSessionId().toString();
				System.out.println("Session/Job ID: " + jobID);
			} else
				System.out.println(
						"***driver object is null while type casting from webdrier to remotewebdrier for jobid****");
			String[] values_sauce = { this.executionTime, this.currentUser, this.instanceName, this.moduleName,
					this.testSetName, this.testCaseName, this.sauceUser, this.os, jobID, this.sResultFlag };
			setSauceLabValues(values_sauce);

			dateTime.clear();
			description.clear();
			status.clear();
			verification.clear();
			commandName.clear();
			iStepCount = 1;
			// String sInsertTestSetResults = "Insert into
			// TestSetResults(ExecutionDate,Username,Execution_Environment,ModuleName,TestSetName,TestCaseName,SauceLabAccountName,OS,BrowserName,SessionID,Status)
			// values ('" + this.executionTime + "','" + this.currentUser +
			// "','" + this.instanceName + "','" + this.moduleName + "','" +
			// this.testSetName + "','" + this.testCaseName + "','" +
			// this.sauceUser + "','" + this.os + "','" + this.sBrowser + "','"
			// + jobID + "','" + this.sResultFlag + "');";
			// insertDataIntoAccessDB("TestSets", sInsertTestSetResults);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sResultFlag = PASS;
		}
	}

	/**
	 * This method will kills IE and chrome drivers
	 */
	public void killIEAndChromeDrivers() throws Exception {

		final String KILL = "taskkill /IM ";
		String processName = null;
		if (sBrowser.toLowerCase().contains("internet")) {
			processName = "IEDriverServer.exe"; // IE process
		} else if (sBrowser.toLowerCase().contains("chrome")) {
			processName = "chromeDriverServer.exe"; // Chrome process
		}

		Runtime.getRuntime().exec(KILL + processName);
		Thread.sleep(3000); // Allow OS to kill the process
	}

	/**
	 * This method will close application, stops web driver and calls report
	 * generation method.
	 * 
	 * @throws Exception
	 */
	public void stopSeleniumRunningInSauceLabs() throws Exception {
		String jobID = ((RemoteWebDriver) driver).getSessionId().toString();
		try {
			String[] values_sauce = { this.executionTime, this.currentUser, this.instanceName, this.moduleName,
					this.testSetName, this.testCaseName, this.sauceUser, this.os, jobID, this.sResultFlag };
			setSauceLabValues(values_sauce);
			// String sInsertTestSetResults = "Insert into
			// TestSetResults(ExecutionDate,Username,Execution_Environment,ModuleName,TestSetName,TestCaseName,SauceLabAccountName,BrowserName,SessionID,Status)
			// values ('" + this.executionTime + "','" + this.currentUser +
			// "','" + this.instanceName + "','" + this.moduleName + "','" +
			// this.testSetName + "','" + this.testCaseName + "','" +
			// this.sauceUser + "','" + this.sBrowser + "','" + jobID + "','" +
			// this.sResultFlag + "');";

			// insertDataIntoAccessDB("TestSets", sInsertTestSetResults);

			// generateHtml(getTempPath()+this.sECName+".xml");

		} catch (Exception e) {
			// System.out.println(" in stopSelenium method: Hurrah you did
			// mistake : "+ e);
			// fail("stopSeleniumRunningInSauceLabs:"+e.toString());
			e.printStackTrace();
		} finally {

			updateSauceJobInfo(jobID);
			driver.quit();
			killIEAndChromeDrivers();
		}
	}

	public void testNgFail() {
		fail("Fail");
	}

	/**
	 * This method generates the report without close the browser generation
	 * method.
	 * 
	 * @throws Exception
	 */
	public void stopSeleniumRunningInSauceLabsWithOutExit() throws Exception {
		String jobID = ((RemoteWebDriver) driver).getSessionId().toString();
		try {
			String[] values_sauce = { this.executionTime, this.currentUser, this.instanceName, this.moduleName,
					this.testSetName, this.testCaseName, this.sauceUser, this.os, jobID, this.sResultFlag };
			setSauceLabValues(values_sauce);
			// String sInsertTestSetResults = "Insert into
			// TestSetResults(ExecutionDate,Username,Execution_Environment,ModuleName,TestSetName,TestCaseName,SauceLabAccountName,BrowserName,SessionID,Status)
			// values ('" + this.executionTime + "','" + this.currentUser +
			// "','" + this.instanceName + "','" + this.moduleName + "','" +
			// this.testSetName + "','" + this.testCaseName + "','" +
			// this.sauceUser + "','" + this.sBrowser + "','" + jobID + "','" +
			// this.sResultFlag + "');";
			// insertDataIntoAccessDB("TestSets", sInsertTestSetResults);
			// generateHtml(getTempPath()+this.sECName+".xml");
			dateTime.clear();
			description.clear();
			status.clear();
			verification.clear();
			commandName.clear();

			iStepCount = 1;
		} catch (Exception e) {
			// System.out.println(" in stopSelenium method: Hurrah you did
			// mistake : "+ e);
			// fail("stopSeleniumRunningInSauceLabs:"+e.toString());
			e.printStackTrace();
		} finally {
			updateSauceJobInfo(jobID);
			sResultFlag = PASS;
		}
	}

	public void updateSauceJobInfo(String jobID) {
		// System.out.println("updating sauce job info");
		SauceREST client = new SauceREST(this.sauceUser, this.accessKey);
		System.out.println("ResultFlag: " + sResultFlag);
		if (sResultFlag.equalsIgnoreCase("pass"))
			client.jobPassed(jobID);
		else if (sResultFlag.equalsIgnoreCase("fail"))
			client.jobFailed(jobID);
	}

	public boolean verifyText(String locator, String text) {
		return webElement(locator).getText().contains(text);
	}

	public WaitForPageToLoad waitForPageToLoad() {
		return new WaitForPageToLoad();
	}

	/**
	 * This method captures screenshot and adds step details with screenshot to
	 * array lists used for reporting
	 * 
	 * @param ver
	 *            Verification
	 * @param desc
	 *            Description
	 * @param status
	 *            Pass (or) Fail status
	 */
	public void reportStepDtlsWithScreenshot(String ver, String des, String stepStatus) {
		captureScreenShot();
		reportStepDtlsWithOutScreenshot(ver, des, stepStatus);
	}

	public void reportStepDtlsWithOutScreenshot(String ver, String des, String stepStatus) {

		// captureScreenShot();
		verification.add(ver);
		description.add(des + "Screenshot:" + sECName + "_" + iStepCount + ".png");
		dateTime.add(getExecutionTime());
		status.add(stepStatus);

		if (stepStatus.equalsIgnoreCase(FAIL)) {
			System.out.println("Failed Step Details:" + des);
			try {
				GenerateFinalValidations gen = new GenerateFinalValidations(this);
				gen.setFinalValidationMsg(sECName, des);
				gen.generateFinalValidationsFile();
			} catch (Exception e) {
				e.printStackTrace();
			}

			sResultFlag = stepStatus;
			fail("command:" + ver + "||" + "description:" + des + "||" + stepStatus);
		}
		iStepCount += 1;
	}

	public void reportStepDtlsWithScreenshotWithoutExit(String ver, String des, String stepStatus) {
		/*
		 * try{ GenerateFinalValidations gen=new GenerateFinalValidations();
		 * gen.setFinalValidationMsg(sECName, des);
		 * gen.generateFinalValidationsFile(); } catch(Exception e){
		 * e.printStackTrace(); }
		 */

		failMessageDesc += "\n | " + des;

		captureScreenShot();

		verification.add(ver);
		description.add(des + "Screenshot:" + sECName + "_" + iStepCount + ".png");
		/*
		 * System.out.println("Report step details method path:"+des +
		 * "Screenshot:" + sAutomationPath +"Results" +File.separator+
		 * "Screenshots" + File.separator + sECName + "_" +
		 * getCurrentDateNTime() + "_" + iStepCount + ".png");
		 */
		status.add(stepStatus);
		dateTime.add(getExecutionTime());
		if (stepStatus.equalsIgnoreCase(FAIL))
			sResultFlag = stepStatus;

	}

	public WebElement webElement(String sTarget) {

		if (sTarget.startsWith("css")) {
			sTarget = sTarget.substring(4);
			return driver.findElement(By.cssSelector(sTarget));
		}
		if (sTarget.startsWith("//")) {
			return driver.findElement(By.xpath(sTarget));
		}
		if (sTarget.startsWith("xpath")) {
			sTarget = sTarget.substring(6);
			return driver.findElement(By.xpath(sTarget));
		}
		if (sTarget.startsWith("name")) {
			sTarget = sTarget.substring(5);
			return driver.findElement(By.name(sTarget));
		}
		if (sTarget.startsWith("id")) {
			sTarget = sTarget.substring(3);
			return driver.findElement(By.id(sTarget));
		}
		if (sTarget.startsWith("link")) {
			sTarget = sTarget.substring(5);
			return driver.findElement(By.linkText(sTarget));
		}
		return null;
	}

	public By by(String sTarget) {
		if (sTarget.startsWith("css")) {
			sTarget = sTarget.substring(4);
			return By.cssSelector(sTarget);
		}
		if (sTarget.startsWith("//")) {
			return By.xpath(sTarget);
		}
		if (sTarget.startsWith("xpath")) {
			sTarget = sTarget.substring(6);
			return By.xpath(sTarget);
		}
		if (sTarget.startsWith("name")) {
			sTarget = sTarget.substring(5);
			return By.name(sTarget);
		}
		if (sTarget.startsWith("id")) {
			sTarget = sTarget.substring(3);
			return By.id(sTarget);
		}
		if (sTarget.startsWith("link")) {
			sTarget = sTarget.substring(5);
			return By.linkText(sTarget);
		}
		return null;
	}

	/* This method change the string as ***/
	public String encryptPassword(String pwd) {
		int len = pwd.length();
		String cryPwd = "";
		for (int i = 0; i < len; i++) {
			cryPwd = cryPwd + "*";

		}
		return cryPwd;
	}

}
