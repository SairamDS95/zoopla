package org.locators;

import org.helper.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class zooplaListPOJO extends BaseClass {

	public zooplaListPOJO() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//p[contains(@class, 'css-1q5qety')]")
	private WebElement name;

	@FindBy(xpath = "//a[@data-testid='agent-properties-link']")
	private WebElement details;

	@FindBy(xpath = "(//div[@class='clearfix'])[2]//a")
	private WebElement agentName;

	public WebElement getName() {
		return name;
	}

	public WebElement getDetails() {
		return details;
	}

	public WebElement getAgentName() {
		return agentName;
	}

}
