package com.scotts.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.thoughtworks.selenium.SeleneseTestBase;

public class ArsinFrameWork extends SeleneseTestBase {
	public final String FAIL = "Fail";
	public final String PASS = "Pass";

	// Reporting
	protected ArrayList<String> dateTime = new ArrayList<String>();
	protected ArrayList<String> description = new ArrayList<String>();
	protected ArrayList<String> status = new ArrayList<String>();
	protected ArrayList<String> verification = new ArrayList<String>();
	protected ArrayList<String> commandName = new ArrayList<String>();
	public int iStepCount = 1;
	public String sResultFlag = PASS;
	public boolean sTeslaServiceFlag;
	public static String Execution_Environment = "";
	// Command line arguments
	public String sAutomationPath = "";
	public String sBrowser = "";
	public String sECName = "";
	public String sModuleName = "";
	private String modulePath = "";
	public String sURL = "";
	public String sProjectName = "";
	public String os = "";

	// Effecta configurations
	public String sDataSetNo = "";
	public String sExecuteID = "";
	public String sExecutionMode = "";
	public String sTestCaseID = "";
	public String sTestCaseName = "";
	public String sXMLFileName = "";
	public String sXmlFilePath = "";
	public String[] sauce_Values;
	public String timeDiff;
	public String duration;
	// TestData
	private HashMap<String, String> testData;
	public String sResultsFolderPath = "";
	public String sResultFilePath = "";
	public String sResultExcelFilePath = "";
	public String sValidationResultFilePath = "";

	public String sImagePath = "";
	public String sImageExt = "";

	public Date startTime;
	public Date endTime;
	public String testngResultStatus;
	public Date serviceStartTime;
	public Date serviceEndTime;
	public String serviceStatus = "Pass";
	public String serviceTestCaseName;

	public ArsinFrameWork() {

	}

	public static void main(String[] a) {
		System.out.println("kajadskdd");
	}

	public ArsinFrameWork(String sProjectName, String sECName, String sBrowser, String os, String moduleName) {
		System.out.println("test");
		this.sAutomationPath = System.getenv("Automation_Path");
		this.sECName = sECName;
		this.sModuleName = moduleName;
		this.sProjectName = sProjectName;
		this.sBrowser = sBrowser;
		this.os = os;

		String sInputDataFilePath;

		if (this.sModuleName.contains(".")) {
			String packages[] = this.sModuleName.split("/.");
			for (String pack : packages) {
				this.modulePath += pack + File.separator;
			}
		} else {
			this.modulePath = this.sModuleName;
		}
		sInputDataFilePath = this.sAutomationPath + "Data" + File.separator + this.sProjectName + File.separator
				+ "scripts" + File.separator + this.modulePath;
		String fileXlsx = sInputDataFilePath + File.separator + this.sECName + ".xlsx";
		String fileXls = sInputDataFilePath + File.separator + this.sECName + ".xls";

		System.out.println("------>>>   " + fileXls);
		if (new File(fileXls).exists() || new File(fileXlsx).exists()) {
			testData = readExcelDataSheet();
		} else {
			testData = null;
			System.err.println("No Input Data File Is Available");
		}
	}

	/* This method is used to retrive the Temp folder path */
	public String getTempPath() {
		try {
			String cananicalpath = File.createTempFile("temp_file", "tmp").getCanonicalPath();
			int s = cananicalpath.lastIndexOf("/");
			return cananicalpath.substring(0, s + 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void initializeProperties() {

		String sExecuteID = this.sauce_Values[8];
		String sInstance = this.sauce_Values[2];
		String sUser = this.sauce_Values[1];
		// String sExecutionTime = this.sauce_Values[0];
		String sModuleName = this.sauce_Values[3];
		String sTestSetName = this.sauce_Values[4];

		this.sImagePath = (this.sAutomationPath + "MetaDataSources/" + this.sProjectName + "/" + this.sECName
				+ "/Images/");
		this.sImageExt = ".PNG";
		this.sResultsFolderPath = (this.sAutomationPath + "Results/" + this.sProjectName + "/" + sInstance + "/" + sUser
				+ "/" + getDate1() + "/" + sModuleName + "/" + sTestSetName + "/");
		this.sResultFilePath = (this.sResultsFolderPath + this.sECName + "_" + sExecuteID + "_" + sResultFlag
				+ ".html");
		this.sValidationResultFilePath = (this.sResultsFolderPath + this.sECName + "_Test" + "_" + sExecuteID + "_"
				+ sResultFlag + ".html");
		this.sResultExcelFilePath = (this.sResultsFolderPath + this.sECName + "_" + sExecuteID + "_" + sResultFlag
				+ ".xls");
	}

	public String getDate1() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}

	public void setSauceLabValues(String[] sauceValues) {

		this.sauce_Values = sauceValues;
		this.endTime = new Date();
		resultXMLFileCreation();
	}

	/**
	 * This method returns given number of spaces as a string
	 * 
	 * @param noofSpaces
	 *            number of spaces required
	 * @return String string of spaces
	 */
	public String addspace(int noofSpaces) {
		String space = "";
		for (int i = 0; i < noofSpaces; i++) {
			space = space + (char) 32;
		}
		return space;
	}

	/**
	 * This method appends Current Date and time to value retrieved from excel
	 * sheet.
	 * 
	 * @param excelInputColName
	 *            Column name specified in excel sheet
	 * @return String value updated with current data and time
	 */
	public String appendCurrentDateNTime(String excelInputColName) {
		String value = getExcelData(excelInputColName);
		if (!value.toLowerCase().startsWith("r_"))
			return value;
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
		String dateNow = formatter.format(currentDate.getTime());
		value = value.substring(value.indexOf("_") + 1);
		value = value + dateNow;
		return value;
	}

	/**
	 * This method returns Current Date and time in String format
	 * 
	 * @return String value current data and time
	 */
	public String getCurrentDateNTime() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
		// DateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
		String dateNow = formatter.format(currentDate.getTime());
		return dateNow;
	}

	/**
	 * This method first checks whether email ID is valid or not by calling
	 * isEmailIDValid() method and then appends the current date and time.
	 * 
	 * @param excelInputColName
	 *            Column name specified in excel sheet
	 * @return String EmailID updated with current data and time
	 */
	public String appendCurrentDateNTimeToEmail(String excelInputColName) {
		String value = "";
		String email = getExcelData(excelInputColName);
		if (!email.toLowerCase().startsWith("r_"))
			return email;
		if (!isEmailIDValid(email)) {
			commandName.add("EmailValidation");
			reportStepDetails("Validating Email ID", "Invalid Email ID.Please specify valid Email ID", FAIL);
			// stopSelenium();
		}
		email = email.substring(email.indexOf("_") + 1);
		value = email.substring(0, (email.indexOf("@")));
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyHHmmss");
		String dateNow = formatter.format(currentDate.getTime());
		value = value + dateNow + email.substring(email.indexOf("@"));
		return value;
	}

	/**
	 * This method sorts and returns an array of float values.
	 * 
	 * @param arr
	 *            Array to sort
	 * @return Sorted array of floats
	 */
	public float[] bubbleSort(float arr[]) {
		int i, j, arrLength;
		float temp;
		arrLength = arr.length;
		for (i = 0; i < arrLength; i++) {
			for (j = 1; j < (arrLength - i); j++) {
				if (arr[j - 1] > arr[j]) {
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	}

	/**
	 * This method gets array of strings and converts them into float array and
	 * returns the array of float values.
	 * 
	 * @param sarray
	 *            String array to be converted to float
	 * @return array of floats
	 */
	public float[] convertStringArraytoFloatArray(String[] sArray) throws Exception {
		if (sArray != null) {
			float fArray[] = new float[sArray.length];
			for (int i = 0; i < sArray.length; i++) {
				fArray[i] = Float.parseFloat(sArray[i]);
			}
			return fArray;
		}
		return null;
	}

	/**
	 * This method takes String and converts into required date format
	 * 
	 * @param strDate
	 *            Input string
	 * @param reqDtFormat
	 *            Date format
	 * @return String Returns string converted to specified date format
	 */
	public String convertStringToRequiredDateFormat(String strDate, String reqDtFormat) {
		String datestring2;
		try {
			Date date1 = new SimpleDateFormat(reqDtFormat).parse(strDate);
			datestring2 = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(date1);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return datestring2;
	}

	/**
	 * This method returns required currency format in the form of String.
	 * 
	 * @param price
	 *            Input price to format
	 * @return String updated price
	 */
	public String Currencyformat(String price) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return nf.format(Integer.parseInt(price));
	}

	/**
	 * This method encodes special characters in given string
	 * 
	 * @param inpParameter
	 * @return String encoded string
	 */
	public String encodeSpecialCharacters(String inpParameter) {
		inpParameter = inpParameter.replace("&", "&amp;");// &amp;
		inpParameter = inpParameter.replace("<", "&lt;");
		inpParameter = inpParameter.replace(">", "&gt;");
		inpParameter = inpParameter.replace("%3C", "&lt;");
		inpParameter = inpParameter.replace("%3E", "&gt;");
		return inpParameter;
	}

	/**
	 * This method returns the price with US format.
	 * 
	 * @param price
	 * @return String formatted price
	 */
	public String formatPrice(String price) {
		if (price.startsWith("$"))
			price = price.substring(1);

		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
		formatter.setMaximumFractionDigits(2);
		return formatter.format(Double.parseDouble(price));
	}

	/**
	 * This method returns the price with UK format.
	 * 
	 * @param price
	 * @return String formatted price
	 */
	public String formatUKPrice(String price) {
		if (price.startsWith("�"))
			price = price.substring(1);

		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.UK);
		formatter.setMaximumFractionDigits(2);
		return formatter.format(Double.parseDouble(price));
	}

	/**
	 * This method returns value corresponding to given excel column(key) from
	 * Hashmap containing TestData
	 * 
	 * @param columnName
	 *            excel column heading, we call key
	 * @return String containing value of a given key
	 */
	public String getExcelData(String columnName) {
		return readDataFromExcel(columnName);
	}

	/**
	 * This method returns current date and time as String according to given
	 * date format.
	 * 
	 * @return String Date
	 */
	public String getExecutionTime() {
		return now("yyyy.MM.dd  'at' hh:mm:ss ");
	}

	/**
	 * This method converts String into float.
	 * 
	 * @param str
	 *            String containing both characters and digits
	 * @return float value
	 */
	public float getfloat(String str) {
		if (str == null) {
			return 0;
		}
		StringBuffer strBuff = new StringBuffer();
		char c;

		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);

			if (Character.isDigit(c) || c == 46) {
				strBuff.append(c);
			}
		}
		return Float.valueOf(strBuff.toString().trim()).floatValue();
	}

	/**
	 * This method is to check given email is valid or not.
	 * 
	 * @param email
	 * @return true if email id is valid, otherwise false
	 */
	public boolean isEmailIDValid(String email) {
		// Set the email pattern string
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		// Match the given string with the pattern
		Matcher m = p.matcher(email);
		// check whether match is found
		boolean matchFound = m.matches();
		return matchFound;
	}

	/**
	 * This method takes date format and returns current date and time as String
	 * according to given date format.
	 * 
	 * @param dateFormat
	 * @return String Date parsed in given date format
	 */
	public String now(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}

	public HashMap<String, String> readDataFromExcel() {
		return testData;
	}

	/**
	 * This method reads data from TestData excel sheet and stores in testdata
	 * Hashmap
	 */
	/**
	 * Description: Reads all data from current test script related data sheet
	 * (If Exist) and returns a map (all headings as keys & data as values of
	 * the respected key) Deals with both HSSFWorkbook & XSSFWorkbook
	 * 
	 * @return Map<String, String>
	 */
	private HashMap<String, String> readExcelDataSheet() {
		String sInputDataFilePath = this.sAutomationPath + "Data" + File.separator + this.sProjectName + File.separator
				+ "scripts" + File.separator + this.modulePath;

		HashMap<String, String> namelist = new HashMap<String, String>();
		FileInputStream inputStream = null;
		try {
			File dir = new File(sInputDataFilePath);
			String dataFilePath = null;
			if (dir.exists() && dir.isDirectory()) {
				boolean flag = false;
				File[] allFiles = dir.listFiles();

				if (allFiles.length > 0) {
					for (int i = 0; i < allFiles.length; i++) {
						String fileName = allFiles[i].getAbsolutePath();

						if (FilenameUtils.getBaseName(fileName).equals(sECName)) {
							dataFilePath = fileName;
							System.out.println("Input Data File Path: " + dataFilePath);
							flag = true;
							break;
						}
					}
				} else {
					System.err.println("No Data Files Are Available In The Path: " + sInputDataFilePath);
				}

				if (!flag) {
					System.err.println("Data File Is Not Available In \"" + sInputDataFilePath + "\" For The Class: \""
							+ sECName + "\"");
				}
			} else {
				System.err
						.println("Please Verify The Specified Class:" + sECName + " Packages : " + sInputDataFilePath);
			}

			if (new File(dataFilePath).exists()) {

				Workbook workbook = null;
				Sheet worksheet = null;

				inputStream = new FileInputStream(dataFilePath);

				if (FilenameUtils.getExtension(dataFilePath).equalsIgnoreCase("xls")) {
					workbook = new HSSFWorkbook(inputStream);
				} else if (FilenameUtils.getExtension(dataFilePath).equalsIgnoreCase("xlsx")) {
					workbook = new XSSFWorkbook(inputStream);
				} else {
					System.err.println("\n\nPlease Provide Either \".xls\" or \".xlsx\" Excel File\n\n");
				}

				worksheet = workbook.getSheetAt(0);

				if (worksheet.getLastRowNum() >= 1) {

					Row keysRow = worksheet.getRow(0);
					Row valuesRow = worksheet.getRow(1);

					int cellCount = keysRow.getLastCellNum();

					for (int i = 0; i < cellCount; i++) {

						Cell cell = keysRow.getCell(i);
						if (cell == null) {
							continue;
						}

						String key = cell.getStringCellValue();
						String value = "";

						Cell valCell = valuesRow.getCell(i);
						if (valCell == null) {
							value = "";
						} else {
							switch (valCell.getCellType()) {
							case 0:
								value = "" + Double.valueOf(valCell.getNumericCellValue()).longValue();
								break;
							case 1:
								value = "" + valCell.getStringCellValue();
								break;
							case 2:
								break;
							}
						}
						namelist.put(key, value);
					}

				} else {
					System.err.println("Please Provide Data For The Data File: " + dataFilePath);
				}
			} else {
				commandName.add("readDataFromExcel");
				reportStepDetails("Retrieving TestData",
						"TestData sheet doesn't exists under path \"" + sInputDataFilePath + "\"", FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				System.out.println("" + e);
			}
		}
		return namelist;
	}

	/**
	 * Description: Useful to get value/data of the provided key of Currently
	 * executing Test Script
	 * 
	 * @param columnName
	 *            (Name of the cell/Column Heading in data sheet)
	 * @return value of the provided key
	 */
	public String readDataFromExcel(String columnName) {

		if (testData != null) {
			String value = testData.get(columnName);
			if (value != null) {
				return value;
			} else {
				System.err.println("\nNo Input Data Is Available For Column: " + columnName);
				// System.exit(0);
			}
		} else {
			System.err.println("\nNo Input Data File Is Available");
			// System.exit(0);
		}
		return null;
	}

	/**
	 * This method adds step details to array lists used for reporting
	 * 
	 * @param ver
	 *            Verification
	 * @param desc
	 *            Description
	 * @param status
	 *            Pass (or) Fail status
	 */
	public void reportStepDetails(String ver, String des, String stepStatus) {
		verification.add(ver);
		description.add(des);
		status.add(stepStatus);
		dateTime.add(getExecutionTime());
		if (stepStatus.equalsIgnoreCase(FAIL))
			sResultFlag = stepStatus;
		iStepCount += 1;
	}

	public void resultXMLFileCreation() {
		initializeProperties();
		// BufferedReader reader = null;
		// Writer output = null;
		Writer output1 = null;
		try {
			// String name, xmlstring, sProjectName, sEnvironment, sRunId,

			// sBuild, sdate;
			String xmlstring;
			String username;
			String sExecuteID = sauce_Values[8];
			String sInstanceName = sauce_Values[2];
			// String sTestCaseName=this.sECName;
			timeDiff = getDateTimeDifference(this.startTime, this.endTime);
			duration = getTime(this.startTime, this.endTime);
			SimpleDateFormat sdf;
			String module = sModuleName;
			if (module.toLowerCase().contains("smoke")) {
				module = "Smoke Test";
			} else {
				module = "Regression Test";
			}
			sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss z");
			String TCStartTime = sdf.format(this.startTime);
			String TCEndTime = sdf.format(this.endTime);
			//// System.outprintln("Executed ID");
			username = System.getProperty("user.name");
			// + " encoding=" + (char) 34 + "ISO-8859-1" + (char) 34
			// + (char) 34 + " href=" + (char) 34 +
			// sAutomationPath+"Results"+File.separator+"Results.xsl"
			xmlstring = "<?xml version=" + (char) 34 + "1.0" + (char) 34 + " encoding=" + (char) 34 + "UTF-8"
					+ (char) 34 + " standalone=" + (char) 34 + "no" + (char) 34 + "?><?xml-stylesheet type=" + (char) 34
					+ "text/xsl" + (char) 34 + " href=" + (char) 34 + sAutomationPath + "Results" + File.separator
					+ "Results.xsl" + (char) 34 + "?>";
			String newline = System.getProperty("line.separator");
			// reader = new BufferedReader(new InputStreamReader(System.in));
			xmlstring = xmlstring + newline + addspace(2) + "<TestCase>" + newline + addspace(4) + "<Details>"
					+ newline;
			xmlstring = xmlstring + addspace(4) + "<ProjectName>" + module + "</ProjectName>" + newline;
			xmlstring = xmlstring + addspace(4) + "<Environment>" + sInstanceName + "</Environment>" + newline;
			xmlstring = xmlstring + addspace(4) + "<OSBrowser>" + os + "-" + sBrowser + "</OSBrowser>" + newline;
			xmlstring = xmlstring + addspace(4) + "<ModuleName>" + sModuleName + "</ModuleName>" + newline;
			xmlstring = xmlstring + addspace(4) + "<TestCaseName>" + this.sECName + "</TestCaseName>" + newline;
			xmlstring = xmlstring + addspace(4) + "<User>" + username + "</User>" + newline;
			xmlstring = xmlstring + addspace(4) + "<Date>" + now("yyyy.MM.dd  'at' hh:mm:ss ") + "</Date>" + newline;
			xmlstring = xmlstring + addspace(4) + "<StartTime>" + TCStartTime + "</StartTime>" + newline;
			xmlstring = xmlstring + addspace(4) + "<EndTime>" + TCEndTime + "</EndTime>" + newline;
			xmlstring = xmlstring + addspace(4) + "<Duration>" + timeDiff + "</Duration>" + newline;
			xmlstring = xmlstring + addspace(4) + "<RunId>" + sExecuteID

			+ "</RunId>" + newline;
			xmlstring = xmlstring + addspace(4) + "<Result>" + sResultFlag + "</Result>" + newline;
			xmlstring = xmlstring + addspace(4) + "</Details>" + newline + newline;
			xmlstring = xmlstring + addspace(4) + "<Steps>" + newline;
			int vSize = verification.size();

			if (vSize < 0) {
				commandName.add("Exception");
				reportStepDetails("No Selenium code " + "\"", "Please Contact Functional/Automation team :  ", FAIL);
			}
			for (int i1 = 0; i1 < verification.size(); i1++) {

				xmlstring = xmlstring + addspace(6) + "<Step>" + newline;
				xmlstring = xmlstring + addspace(8) + "<StepNumber>" + (i1 + 1) + "</StepNumber>" + newline;

				xmlstring = xmlstring + addspace(8) + "<Expected>" + verification.get(i1) + "</Expected>" + newline;
				String sDescriptioncontent = description.get(i1);
				if (sDescriptioncontent.contains("Screenshot:")) {
					xmlstring = xmlstring + addspace(8) + "<Actual>"
							+ sDescriptioncontent.substring(0, sDescriptioncontent.indexOf("Screenshot:")) + "</Actual>"
							+ newline;
					xmlstring = xmlstring + addspace(8) + "<Screenshot>"
							+ sDescriptioncontent.substring(sDescriptioncontent.indexOf("Screenshot:") + 11)
							+ "</Screenshot>" + newline;
				} else
					xmlstring = xmlstring + addspace(8) + "<Actual>" + description.get(i1) + "</Actual>" + newline;
				xmlstring = xmlstring + addspace(8) + "<Status>" + status.get(i1) + "</Status>" + newline;
				xmlstring = xmlstring + addspace(8) + "<DateTime>" + dateTime.get(i1) + "</DateTime>" + newline;
				xmlstring = xmlstring + addspace(6) + "</Step>" + newline;
			}

			xmlstring = xmlstring + addspace(4) + "</Steps>" + newline;
			xmlstring = xmlstring + addspace(2) + "</TestCase>" + newline;

			// create folder structure
			File dir = new File(this.sResultsFolderPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			// create xml in the temp folder for testng highlevel reporting
			String resultHtml = this.sResultFilePath;// html report path For
														// local repository
														// reporting
			String tempXml = getTempPath() + this.sECName + ".xml";// temp xml
																	// path
			System.out.println("**Temp Xml**" + tempXml);

			// write result xml to temp folder in the tempXmlPath
			File xmlfile = new File(tempXml);
			output1 = new BufferedWriter(new FileWriter(xmlfile));
			output1.write(xmlstring);
			output1.close();
			generateHtml(tempXml, resultHtml);
			File reportFile = new File(getTempPath() + "CurrentRunReports");
			if (!reportFile.exists()) {
				reportFile.mkdir();
			}

			generateHtml(tempXml, getTempPath() + "CurrentRunReports/" + this.sECName + ".html");// html
																									// report
																									// path,
																									// generating
																									// html
																									// report
																									// for
																									// testng
																									// high
																									// level
																									// reporting
			System.out.println("Result HTML File: " + getTempPath() + "CurrentRunReports/" + this.sECName + ".html");
			// xmlfile.delete();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getDataForMultipleServicePropFile(String key, String value) {
		try {

			File file = new File(getTempPath() + "CurrentRunReports/multipleservices.properties");
			Properties prop = new Properties();
			prop.put(key, value);
			prop.store(new FileOutputStream(file, true), "Multiservice");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateHtml(String xmlFile, String htmlFilePath) {
		String xslFile = sAutomationPath + "Results/Results.xsl";
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource(xslFile));
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.transform(new javax.xml.transform.stream.StreamSource(xmlFile),
					new javax.xml.transform.stream.StreamResult(new FileOutputStream(htmlFilePath)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateHtmlForSubService(String xmlFile, String htmlFilePath) {
		String xslFile = sAutomationPath + "Results/Results1.xsl";

		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource(xslFile));
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.transform(new javax.xml.transform.stream.StreamSource(xmlFile),
					new javax.xml.transform.stream.StreamResult(new FileOutputStream(htmlFilePath)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method rounds a value to the specified number of decimal places.
	 * 
	 * @param Rval
	 *            a floating-point value to be rounded.
	 * @param Rp1
	 *            the number of decimal places in the return value.
	 * @return float Rounded float value
	 */
	public float round(float Rval, int Rpl) {
		float p = (float) Math.pow(10, Rpl);
		Rval = Rval * p;
		float tmp = Math.round(Rval);
		return tmp / p;
	}

	/**
	 * 
	 * @param constantName
	 * @return
	 */
	public String getConstantValue(String constantName) {
		String url = "";
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(sAutomationPath + sProjectName + "/Constants/constant.properties"));
			// prop.loadFromXML(new FileInputStream(sAutomationPath +
			// sProjectName + "\\Constants\\constant.xml"));
			url = prop.getProperty(constantName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return url;
	}

	public String getURL(String constantName, String instanceName) {
		String url = "";
		try {

			Properties prop = new Properties();

			prop.load(new FileInputStream(sAutomationPath + sProjectName + "/Constants/constant.properties"));
			url = prop.getProperty(constantName);
			if (url == null) {
				System.err.println("\nURL NOT FOUND, PLEASE CHECK ONCE AGAIN");
			}

			if (instanceName.toLowerCase().equals("prod")) {
				if (url.contains("instanceName")) {
					url = url.replace("-instanceName", "");
					if (url.contains("comm")) {
						url = url.replace("comm", "communities");
					}
				} else {
					if (url.contains("comm")) {
						url = url.replace("comm", "communities");
					}
				}

			} else {
				if (url.contains("instanceName"))
					url = url.replace("instanceName", instanceName);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Contants Properties File: " + sAutomationPath + sProjectName
					+ "/Constants/constant.properties" + "Not Found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * 
	 * @param constantName
	 * @return
	 */
	public String getConstValFrmPropertyFile(String constantName) {
		String value = "";
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream(this.sAutomationPath + this.sProjectName + "/Constants/constant.properties"));
			value = prop.getProperty(constantName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}

	/**
	 * 
	 * @param dbPath
	 * @return
	 */
	public Connection getConnectionObj(String dbPath) {
		Connection conn = null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String dbName;
			if (dbPath.contains("/")) {
				dbName = dbPath + ".accdb";
			} else {
				dbName = sAutomationPath + "Data" + File.separator + sProjectName + File.separator + dbPath + ".accdb";
			}
			System.out.println("Database Name: " + dbName);
			String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};Dbq=" + dbName;
			conn = DriverManager.getConnection(database);
			if (conn != null) {
				System.out.println("connection established");
			} else {
				System.err.println("connection not established");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 
	 * @param dbName
	 * @param sSQL
	 * @return
	 */
	public HashMap<String, String> readDataFromAccessDB(String dbPath, String sSQL) {
		Connection conn = getConnectionObj(dbPath);
		HashMap<String, String> namelist = new HashMap<String, String>();
		Statement smt = null;
		try {
			smt = conn.createStatement();
			smt.execute(sSQL);
			ResultSet rs = smt.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();
			rs.next();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				String sColName = rsmd.getColumnName(i);
				String sColVal = rs.getString(i);
				namelist.put(sColName, sColVal);
			}
			smt.close();
			conn.close();
		} catch (SQLException e) {
			commandName.add("Exception");
			reportStepDetails("Data not available",
					"Please check the availability of test data in the DB:" + e.toString(), "Fail");
			e.printStackTrace();
		} catch (Exception e) {
			commandName.add("Exception");
			reportStepDetails(e.toString(), e.toString(), "Fail");
			e.printStackTrace();
		}

		return namelist;
	}

	/**
	 * 
	 * @param sSQL
	 * @return
	 */
	public HashMap<String, String> readDataFromAccessDB(String sSQL) {
		String dbPath = "MYVMWare";
		Connection conn = getConnectionObj(dbPath);
		HashMap<String, String> namelist = new HashMap<String, String>();
		Statement smt = null;
		try {
			smt = conn.createStatement();
			smt.execute(sSQL);
			ResultSet rs = smt.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();
			rs.next();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				String sColName = rsmd.getColumnName(i);
				String sColVal = rs.getString(i);
				namelist.put(sColName, sColVal);
			}
			smt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return namelist;
	}

	public int readRowCountFromAccessDB(String sSQL) {
		String dbPath = "MYVMWare";
		Connection conn = getConnectionObj(dbPath);
		Statement smt = null;
		int rowCount = 0;
		try {
			smt = conn.createStatement();
			smt.execute(sSQL);
			ResultSet rs = smt.getResultSet();

			while (rs.next()) {
				rowCount = rs.getInt(1);
			}
			smt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rowCount;
	}

	public int readRowCountFromAccessDB(String dbPath, String sSQL) {
		Connection conn = getConnectionObj(dbPath);
		Statement smt = null;
		int rowCount = 0;
		try {
			smt = conn.createStatement();
			smt.execute(sSQL);
			ResultSet rs = smt.getResultSet();

			while (rs.next()) {
				rowCount = rs.getInt(1);
			}
			smt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rowCount;
	}

	/**
	 * 
	 * @param dbName
	 * @param sSQL
	 * @return
	 */
	public boolean insertDataIntoAccessDB(String dbName, String sSQL) {
		Connection conn = getConnectionObj(dbName);
		Statement smt = null;
		boolean rs = false;
		try {
			smt = conn.createStatement();
			rs = smt.execute(sSQL);
			smt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 
	 * @param sSQL
	 * @return
	 */
	public boolean insertDataIntoAccessDB(String sSQL) {
		String dbPath = "MYVMWare";
		Connection conn = getConnectionObj(dbPath);
		Statement smt = null;
		boolean rs = false;
		try {
			smt = conn.createStatement();
			rs = smt.execute(sSQL);
			smt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static String getDateTimeDifference(Date startDate, Date endDate) {
		String time = "";
		try {
			long diff = endDate.getTime() - startDate.getTime();
			long diffSeconds = diff / 1000L % 60L;
			long diffMinutes = diff / 60000L % 60L;
			long diffHours = diff / 3600000L % 24L;

			if (diffHours < 10L)
				time = time + "0" + diffHours + "h:";
			else {
				time = time + diffHours + "h:";
			}
			if (diffMinutes < 10L)
				time = time + "0" + diffMinutes + "m:";
			else {
				time = time + diffMinutes + "m:";
			}
			if (diffSeconds < 10L)
				time = time + "0" + diffSeconds + "s";
			else
				time = time + diffSeconds + "s";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	public static String getTime(Date startDate, Date endDate) {
		String time = "";
		try {
			long diff = endDate.getTime() - startDate.getTime();
			long diffSeconds = diff / 1000L % 60L;
			long diffMinutes = diff / 60000L % 60L;
			long diffHours = diff / 3600000L % 24L;
			if (diffHours > 0L)
				time = time + diffHours + "h ";
			if (diffMinutes > 0L)
				time = time + diffMinutes + "m ";
			if (diffSeconds > 0L)
				time = time + diffSeconds + "s";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 
	 * @param sbrowser
	 * @return
	 */
	public static String[] splitString(String sbrowser) {
		String regex = " ";
		String[] p = sbrowser.split(regex);
		return p;
	}

	/**
	 * 
	 * @param hashMapItems
	 * @param currentScript
	 * @return
	 */
	public String[] getEnvironment(String hashMapItems, String currentScript) {
		try {

			if (hashMapItems.contains("{") || hashMapItems.contains("}")) {

				hashMapItems = hashMapItems.replace("{", "").replace("}", "");
			}
			String[] moduleName = new String[5];
			if (currentScript.contains(".")) {
				moduleName = currentScript.split("/.");
			}
			String requiredtcAndOsAndBrowser = "";
			if (hashMapItems.contains(",")) {
				String[] tcAndOsAndBrowser = hashMapItems.split(",");
				for (int i = 0; i < tcAndOsAndBrowser.length; i++) {
					if ((tcAndOsAndBrowser[i].contains(currentScript))
							|| (tcAndOsAndBrowser[i].contains(moduleName[(moduleName.length - 1)]))) {
						requiredtcAndOsAndBrowser = tcAndOsAndBrowser[i];
					}
				}
			} else {
				requiredtcAndOsAndBrowser = hashMapItems;
			}

			String osAndBrowser = requiredtcAndOsAndBrowser.split("=")[1];
			
			String[] envrnment = new String[2];
			String os = osAndBrowser.split("::")[0];
			String browser = osAndBrowser.split("::")[1];
			// String browser = osAndBrowser.split("\\|\\|")[1];

			envrnment[0] = os;
			envrnment[1] = browser;
			
			return envrnment;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getSauceuserAccessKey(String sauceuser) {
		try {
			String sauceUserAccessKey = "";
			Connection conn = getConnectionObj("TestSets");
			Statement smt = conn.createStatement();
			String sSQL = "select SauceUserAccessKey from InterfaceData where SauceUser='" + sauceuser + "'";
			smt.execute(sSQL);
			ResultSet rs = smt.getResultSet();
			while (rs.next()) {
				sauceUserAccessKey = rs.getString(1);
			}
			return sauceUserAccessKey;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void FileCopy(String sourceFilePath, String desFilePath) {
		try {
			File sourceFile = new File(sourceFilePath);

			File targetFile = new File(desFilePath);

			// copy file from one location to other
			FileUtils.copyFile(sourceFile, targetFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void resultsSubservicesReport(HashMap<Integer, String> report) {

		Writer output1 = null;
		try {
			String xmlstring;
			String username;
			String sExecuteID = "ServiceReport";
			String sInstanceName = sauce_Values[2];
			serviceEndTime = new Date();
			String servicetimeDiff = getDateTimeDifference(this.serviceStartTime, this.serviceEndTime);
			;

			username = System.getProperty("user.name");
			xmlstring = "<?xml version=" + (char) 34 + "1.0" + (char) 34 + " encoding=" + (char) 34 + "UTF-8"
					+ (char) 34 + " standalone=" + (char) 34 + "no" + (char) 34 + "?><?xml-stylesheet type=" + (char) 34
					+ "text/xsl" + (char) 34 + " href=" + (char) 34 + sAutomationPath + "Results" + File.separator
					+ "Results1.xsl" + (char) 34 + "?>";
			String newline = System.getProperty("line.separator");
			xmlstring = xmlstring + newline + addspace(2) + "<TestCase>" + newline + addspace(4) + "<Details>"
					+ newline;
			xmlstring = xmlstring + addspace(4) + "<ProjectName>" + sProjectName + "</ProjectName>" + newline;
			xmlstring = xmlstring + addspace(4) + "<Environment>" + sInstanceName + "</Environment>" + newline;
			xmlstring = xmlstring + addspace(4) + "<OSBrowser>" + os + "-" + sBrowser + "</OSBrowser>" + newline;
			xmlstring = xmlstring + addspace(4) + "<ModuleName>" + sModuleName + "</ModuleName>" + newline;
			xmlstring = xmlstring + addspace(4) + "<TestCaseName>" + "ServiceReport" + "</TestCaseName>" + newline;
			xmlstring = xmlstring + addspace(4) + "<User>" + username + "</User>" + newline;
			xmlstring = xmlstring + addspace(4) + "<Date>" + now("yyyy.MM.dd  'at' hh:mm:ss ") + "</Date>" + newline;
			xmlstring = xmlstring + addspace(4) + "<StartTime>" + serviceStartTime + "</StartTime>" + newline;
			xmlstring = xmlstring + addspace(4) + "<EndTime>" + serviceEndTime + "</EndTime>" + newline;
			xmlstring = xmlstring + addspace(4) + "<Duration>" + servicetimeDiff + "</Duration>" + newline;
			xmlstring = xmlstring + addspace(4) + "<RunId>" + sExecuteID + "</RunId>" + newline;
			xmlstring = xmlstring + addspace(4) + "<Result>" + serviceStatus + "</Result>" + newline;
			xmlstring = xmlstring + addspace(4) + "</Details>" + newline + newline;
			xmlstring = xmlstring + addspace(4) + "<Steps>" + newline;
			for (int keys : report.keySet()) {
				String value = report.get(keys);
				String[] dat = value.split("&&");
				xmlstring = xmlstring + addspace(6) + "<Step>" + newline;
				xmlstring = xmlstring + addspace(8) + "<StepNumber>" + keys + "</StepNumber>" + newline;
				String sDescriptioncontent = "Screenshot:" + dat[1];
				if (sDescriptioncontent.contains("Screenshot:")) {
					xmlstring = xmlstring + addspace(8) + "<Actual>" + dat[0] + "</Actual>" + newline;
					xmlstring = xmlstring + addspace(8) + "<Screenshot>" + dat[1] + "</Screenshot>" + newline;
				} else
					xmlstring = xmlstring + addspace(8) + "<Actual>" + dat[1] + "</Actual>" + newline;
				xmlstring = xmlstring + addspace(8) + "<Expected>" + dat[0] + "</Expected>" + newline;
				xmlstring = xmlstring + addspace(8) + "<Status>" + dat[2] + "</Status>" + newline;
				xmlstring = xmlstring + addspace(6) + "</Step>" + newline;
			}

			xmlstring = xmlstring + addspace(4) + "</Steps>" + newline;
			xmlstring = xmlstring + addspace(2) + "</TestCase>" + newline;

			// create folder structure
			File dir = new File(this.sResultsFolderPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			// create xml in the temp folder for testng highlevel reporting
			String resultHtml = this.sResultsFolderPath + this.serviceTestCaseName + ".html";// html
																								// report
																								// path
																								// For
																								// local
																								// repository
																								// reporting
			String tempXml = getTempPath() + this.serviceTestCaseName + ".xml";// temp
																				// xml
																				// path
			System.out.println("**tempXml**" + tempXml);
			File xmlfile = new File(tempXml);
			output1 = new BufferedWriter(new FileWriter(xmlfile));
			output1.write(xmlstring);
			output1.close();
			generateHtmlForSubService(tempXml, resultHtml);
			File reportFile = new File(getTempPath() + "CurrentRunReports");
			if (!reportFile.exists()) {
				reportFile.mkdir();
			}
			generateHtmlForSubService(tempXml,
					getTempPath() + "CurrentRunReports/" + this.serviceTestCaseName + ".html");// html
																								// report
																								// path,
																								// generating
																								// html
																								// report
																								// for
																								// testng
																								// high
																								// level
																								// reporting
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}