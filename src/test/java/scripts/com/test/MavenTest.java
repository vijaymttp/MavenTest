

package scripts.com.test;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.scotts.base.ArsinSeleniumAPI;
import com.scotts.base.GenerateFinalValidations;


public class MavenTest {

	GenerateFinalValidations oGenerateFinalValidations;
	ArsinSeleniumAPI oASelFW = null;

	Map<String, String> inputData = null;
	List<String> availableSizes = new ArrayList<String>();
	List<String> notAvailableSizes = new ArrayList<String>();
	//PostData testRailData=new PostData();
	int buildNum=0;
	@Parameters({ "prjName", "testEnvironment", "instanceName", "sauceUser", "moduleName", "testSetName" })
	@BeforeTest
	public void oneTimeSetUp(String prjName, String testEnvironment, String instanceName, String sauceUser,
			String moduleName, String testSetName) {
		String[] environment=new ArsinSeleniumAPI().getEnvironment(testEnvironment,this.getClass().getName());
		String os=environment[0];String browser=environment[1];String testCasename=this.getClass().getSimpleName();
		try {
			oASelFW = new ArsinSeleniumAPI(prjName, testCasename, browser, os, instanceName, sauceUser, moduleName,
					testSetName);
			oASelFW.startSelenium("");
			oGenerateFinalValidations = new GenerateFinalValidations(oASelFW);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}		
	}

	@Test
	public void scottsMyAccount() throws UnknownHostException {
		try {
			WebDriverWait wait = new WebDriverWait(oASelFW.driver, 100);

			System.out.println("Sample Maven Project");
			oASelFW.effecta("sendReport", "Test ", "TestMaven","Pass");
			// Final Validation Message
			oGenerateFinalValidations = new GenerateFinalValidations(oASelFW);
			oGenerateFinalValidations.setFinalValidationMsg("");

			if (oASelFW.sResultFlag.equalsIgnoreCase("Fail")) {
				oASelFW.testNgFail();
			}
		

		} catch (Exception e) {

			e.printStackTrace();
			oGenerateFinalValidations.setFinalValidationMsg(oASelFW.failMessageDesc);

			oASelFW.reportStepDtlsWithScreenshot(e.getMessage(), e.getMessage(), "Fail");
		}
	}

	@AfterClass
	public void oneTearDown() throws IOException {
		oGenerateFinalValidations.generateFinalValidationsFile();
		oASelFW.stopSelenium();
		// oASelFW.driver.quit();
	}

}
