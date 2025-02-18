package project;

import internal.GlobalVariable;
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint;
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase;
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData;
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject;
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject;

import com.kms.katalon.core.annotation.Keyword;
import com.kms.katalon.core.checkpoint.Checkpoint;
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords;
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords;
import com.kms.katalon.core.model.FailureHandling;
import com.kms.katalon.core.testcase.TestCase;
import com.kms.katalon.core.testdata.TestData;
import com.kms.katalon.core.testobject.TestObject;
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords;
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords;
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Project {

	@Given("user opens browser and navigates to logon page")
	public void user_opens_browser_and_navigates() {
		WebUiBuiltInKeywords.openBrowser("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@Given("user enters username and password")
	public void user_enters_username_and_password() {
		WebUiBuiltInKeywords.setText(findTestObject("Object Repository/username21"), "admin");
		WebUiBuiltInKeywords.setText(findTestObject("Object Repository/password"), "admin123");
	}

	@Then("user clicks search")
	public void user_clicks_search() {
		try {
		WebUiBuiltInKeywords.click(findTestObject("Object Repository/search"));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

	}

}