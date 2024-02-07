package Ollion.SeleniumAssignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Ollion.SeleniumAssignment.PageFactory.StackOverflowPage;

public class TestExecution {
	WebDriver driver;

	@BeforeClass
	public void setup() {
		// Setting up WebDriver
		driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://stackoverflow.com/");
		Reporter.log("Browser Launched");
	}

	@AfterClass
	public void tearDown() {
		// Closing the browser
		if (driver != null) {
			driver.quit();
			Reporter.log("Browser closed");
		}
	}

	@Test
	public void testNavigation() {

		// Initialize StackOverflowPage
		StackOverflowPage stackOverflowPage = new StackOverflowPage(driver);
		// Accept cookies if banner is displayed
		stackOverflowPage.manageCookies();
		// Navigate to questions
		stackOverflowPage.navigateToQuestions();
		// Navigate to users
		stackOverflowPage.navigateToUsers();
		// Navigate to editors
		stackOverflowPage.navigateToEditors();
		// Go to page 2
		stackOverflowPage.goToPage2();
		
		// Get the edit counts
		List<WebElement> editCountList = stackOverflowPage.getEditCountList();
		List<Integer> editCounts = new ArrayList<>();
		// Iterate through the elements to extract and parse the integer values
		for (WebElement element : editCountList) {
			String text = element.getText();
			int editCount = parseEditCount(text);
			editCounts.add(editCount);
		}

		// Find the maximum edit count
		int maxEditCount = Collections.max(editCounts);
		//System.out.println("Maximum edit count: " + maxEditCount);
		Reporter.log("Maximum edit count: " + maxEditCount);

		
		// Find the WebElement corresponding to the maximum edit count
		WebElement maxEditCountElement = null;
		for (WebElement element : editCountList) {
			String text = element.getText();
			int editCount = parseEditCount(text);
			if (editCount == maxEditCount) {
				maxEditCountElement = element;
				break;
			}
		}

		// Get the username corresponding to the max edit count
		String maxEditCountUserName = stackOverflowPage.getUserName(maxEditCountElement);
		//System.out.println("Username corresponding to the max edit count: " + maxEditCountUserName);
		Reporter.log("Username corresponding to the max edit count: " + maxEditCountUserName);

		// Get the user location corresponding to the max edit count
		String maxEditCountUserLocation = stackOverflowPage.getUserLocation(maxEditCountElement);
		//System.out.println("User location corresponding to the max edit count: " + maxEditCountUserLocation);
		Reporter.log("User location corresponding to the max edit count: " + maxEditCountUserLocation);
	}

	// Method to parse the integer value from the string
	private static int parseEditCount(String text) {
		// Split the text to extract the integer value
		String[] parts = text.split("\\s+");
		return Integer.parseInt(parts[0]);
	}

}
