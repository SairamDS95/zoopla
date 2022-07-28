package org.stepdefinition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.helper.BaseClass;
import org.locators.zooplaListPOJO;
import org.locators.zooplaPOJO;
import org.locators.zooplaSrchPOJO;
import org.openqa.selenium.WebElement;

public class StepDef extends BaseClass {

	public static void main(String[] args) throws InterruptedException {

		chromeBrowser();
		openUrl("https://www.zoopla.co.uk/");
		maximise();

		zooplaPOJO zp = new zooplaPOJO();
		zooplaListPOJO zl = new zooplaListPOJO();
		zooplaSrchPOJO zs = new zooplaSrchPOJO();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		zp.getSrch().sendKeys("London");
		zp.getSrchBtn().click();

		List<WebElement> prices = zs.getPrices();

		Map<WebElement, Integer> map = new HashMap<WebElement, Integer>();

		for (int i = 0; i < prices.size(); i++) {
			map.put(prices.get(i), Integer.parseInt(prices.get(i).getText().replaceAll("[£|,]", "")));
		}

		List<Entry<WebElement, Integer>> le = new ArrayList<Map.Entry<WebElement, Integer>>(map.entrySet());
		le.sort(Entry.comparingByValue());

		le.get(le.size() - 6).getKey().click();

		String name = zl.getName().getText();
		zl.getDetails().click();

		String agntName = zl.getAgentName().getText();

		if (name.equalsIgnoreCase(agntName)) {
			System.out.println("The Agent Name is \"" + agntName + "\"");
		}

		closeBrowser();

	}

}
