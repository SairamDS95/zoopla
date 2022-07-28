package org.locators;

import org.helper.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class zooplaPOJO extends BaseClass {

	public zooplaPOJO() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@class = 'c-voGFy']")
	private WebElement srch;

	@FindBy(xpath = "//button[contains(@class, 'c-juxpAL c-juxpAL-fKTdVa')]")
	private WebElement srchBtn;

	public WebElement getSrch() {
		return srch;
	}

	public WebElement getSrchBtn() {
		return srchBtn;
	}

}
