package org.helper;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public static Actions a;
	public static Alert al;
	public static TakesScreenshot ts;
	public static JavascriptExecutor js;
	public static Robot r;
	public static Select s;

	public static void chromeBrowser() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	public static void firefoxBrowser() {

		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}

	public static void maximise() {
		driver.manage().window().maximize();
	}

	public static void openUrl(String url) {
		driver.get(url);
	}

	public static void fetchTitle() {
		driver.getTitle();
	}

	public static void fetchCurrentUrl() {
		driver.getCurrentUrl();
	}

	public static void closetab() {
		driver.close();
	}

	public static void fillText(WebElement ele, String text) {
		ele.sendKeys(text);
	}

	public static void click(WebElement ele) {
		ele.click();
	}

	public static void closeBrowser() {
		driver.quit();
	}

	public static void hold(int ms) throws InterruptedException {
		Thread.sleep(ms);
	}

	public static void gotToElement(WebElement ele) {
		a = new Actions(driver);
		a.moveToElement(ele).perform();
		;
	}

	public static void dragdrop(WebElement src, WebElement dest) {
		a = new Actions(driver);
		a.dragAndDrop(src, dest).perform();
		;
	}

	public static void rightClick(WebElement ele) {
		a = new Actions(driver);
		a.contextClick(ele).perform();
		;
	}

	public static void leftclick(WebElement ele) {
		a = new Actions(driver);
		a.doubleClick().perform();
		;
	}

	public static void acceptAlert() {
		al = driver.switchTo().alert();
		al.accept();
	}

	public static void declineAlert() {
		al = driver.switchTo().alert();
		al.dismiss();
	}

	public static void getSnap(String fileName) throws IOException {
		ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File perm = new File("C:\\Users\\HP\\eclipse-workspace\\HarveyNorman\\Screenshots\\" + fileName + ".png");
		FileUtils.copyFile(temp, perm);
	}

	public static void jsSendKeys(WebElement ele, String text) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setattribute('value', '" + text + "')", ele);
	}

	public static void jsClick(WebElement ele) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click", ele);
	}

	public static void jsScrollDown(WebElement ele) {
		js = (JavascriptExecutor) driver;
		js.executeScript("argument[0].scrollIntoView(true)", ele);
	}

	public static void jsScrollUp(WebElement ele) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false)", ele);
	}

	public static void switchToFrame(String name) {
		driver.switchTo().frame(name);
	}

	public static void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}

	public static void navigateTo(String URL) {
		driver.navigate().to(URL);
	}

	public static void navigateForward() {
		driver.navigate().forward();
	}

	public static void navigateBack() {
		driver.navigate().back();
	}

	public static void refreshPage() {
		driver.navigate().refresh();
	}

	public static void cutText() throws AWTException {
		r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_X);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_X);
	}

	public static void copyText() throws AWTException {
		r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_C);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_C);
	}

	public static void pasteText() throws AWTException {
		r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
	}

	public static void dropDownIndex(WebElement ele, int index) {
		s = new Select(ele);
		s.selectByIndex(index);
	}

	public static void dropDownName(WebElement ele, String name) {
		s = new Select(ele);
		s.selectByValue(name);
	}

	public static String readFromExcel(String fileName, String shName, int rowNo, int cellNo) throws IOException {
		File f = new File("C:\\Users\\HP\\eclipse-workspace\\HarveyNorman\\External Files\\" + fileName + ".xlsx");
		FileInputStream fin = new FileInputStream(f);
		Workbook book = new XSSFWorkbook(fin);
		Sheet sh = book.getSheet(shName);
		Row r = sh.getRow(rowNo);
		Cell c = r.getCell(cellNo);

		int type = c.getCellType();

		String input;

		if (type == 1) {
			input = c.getStringCellValue();
		} else if (DateUtil.isCellDateFormatted(c)) {
			Date date = c.getDateCellValue();

			SimpleDateFormat sim = new SimpleDateFormat("MM dd, yyyy");
			input = sim.format(date);
		} else {

			double d = c.getNumericCellValue();
			long l = (long) d;

			input = String.valueOf(l);
		}

		return input;

	}

}