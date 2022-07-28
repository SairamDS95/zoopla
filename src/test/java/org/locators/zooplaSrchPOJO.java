package org.locators;

import java.util.List;

import org.helper.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class zooplaSrchPOJO extends BaseClass {

	public zooplaSrchPOJO() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@data-testid='listing-price']//p[contains(@class, 'css-1w7anck')]")
	private List<WebElement> prices;

	public List<WebElement> getPrices() {
		return prices;
	}

}
