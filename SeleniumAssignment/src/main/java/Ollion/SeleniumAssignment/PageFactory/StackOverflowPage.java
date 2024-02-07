package Ollion.SeleniumAssignment.PageFactory;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class StackOverflowPage {
	private WebDriver driver;

	@FindBy(id = "onetrust-banner-sdk")
	private WebElement manageCookiesBanner;

	@FindBy(className = "s-topbar--menu-btn")
	private WebElement menuButton;

	@FindBy(id = "nav-questions")
	private WebElement questionsLink;

	@FindBy(id = "nav-users")
	private WebElement usersLink;

	@FindBy(xpath = "//a[@data-value='editors']")
	private WebElement editorsLink;

	@FindBy(xpath = "//a[@title='Go to page 2']")
	private WebElement page2Link;

	@FindBy(className = "user-tags")
	private List<WebElement> editCountList;

	public StackOverflowPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void manageCookies() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement manageCookiesBanner = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-banner-sdk")));

		// Check if the banner is displayed
		if (manageCookiesBanner.isDisplayed()) {
			// Close the banner by clicking the "Accept" button
			WebElement acceptButton = driver.findElement(By.id("onetrust-reject-all-handler"));
			acceptButton.click();
			Reporter.log("Accepted cookies");
		}
		else {
            Reporter.log("No cookies banner displayed");
        }
	}

	public void navigateToQuestions() {
		menuButton.click();
		questionsLink.click();
		Reporter.log("Navigated to Questions page");
	}

	public void navigateToUsers() {
		usersLink.click();
		Reporter.log("Navigated to Users page");
	}

	public void navigateToEditors() {
		editorsLink.click();
		Reporter.log("Navigated to Editors page");
	}

	public void goToPage2() {
		page2Link.click();
		Reporter.log("Navigated to Page 2");
	}

	public List<WebElement> getEditCountList() {
		return editCountList;
	}

	public String getUserName(WebElement element) {
		return element.findElement(By.xpath("./preceding-sibling::div[@class='user-details']/a")).getText();
	}

	public String getUserLocation(WebElement element) {
		return element
				.findElement(By.xpath("./preceding-sibling::div[@class='user-details']/span[@class='user-location']"))
				.getText();
	}
}
