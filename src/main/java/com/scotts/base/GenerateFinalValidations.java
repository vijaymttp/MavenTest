package com.scotts.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class GenerateFinalValidations {

	private Properties properties = new Properties();
	private File file = null;
	private OutputStream out = null;

	private ArsinSeleniumAPI oASelFW = null;

	public GenerateFinalValidations(ArsinSeleniumAPI oASelFW) {

		this.oASelFW = oASelFW;
		File currentRunDir = new File(System.getProperty("java.io.tmpdir") + "CurrentRunReports");
		if (!currentRunDir.exists()) {
			currentRunDir.mkdir();
		}
		file = new File(currentRunDir.getAbsolutePath() + File.separator + "finalValidations.properties");
		try {
			out = new FileOutputStream(file, true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method will add the class/testcase name as key to the properties
	 * file along with validation message as value
	 * 
	 * @param className
	 *            (Name of the class/testcase)
	 * @param validationMsg
	 *            (Final validation of the testcase)
	 * 
	 */
	public void setFinalValidationMsg(String className, String validationMsg) {

		if (oASelFW.failMessageDesc.isEmpty()) {
			properties.put(className, validationMsg);
		} else {
			properties.put(className, oASelFW.failMessageDesc);
		}
	}

	public void setFinalValidationMsg(String validationMsg) {

		String className = oASelFW.sECName;

		if (oASelFW.failMessageDesc.isEmpty()) {
			properties.put(className, validationMsg);
		} else {
			properties.put(className, oASelFW.failMessageDesc);
		}
	}

	/**
	 * This method will generates an XML file which contains all the class names
	 * along with their validation messages FileName is
	 * "%temp%/CurrentRunReports/finalValidations.xml"
	 * 
	 * @throws IOException
	 */
	public void generateFinalValidationsFile() throws IOException {
		properties.store(out, "Final Validations Of Each Class");
		out.close();
		System.out.println("\nFinal Validations File Generated");
	}
}
