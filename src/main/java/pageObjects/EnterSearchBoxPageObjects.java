package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.TestBase;

public class EnterSearchBoxPageObjects extends TestBase{

	@FindBy(xpath="//input[@placeholder='Search any wine']")
	public WebElement searchBox;
	
	@FindBy(xpath="//a[contains(@data-cartitemsource,'text-search')]//child::span")
	public  List<WebElement> searchResults;
	
	@FindBy(xpath="//a[contains(@href,'regions')]")
	public List<WebElement> capitalResults;
	
	@FindBy(xpath="//a[contains(@data-item-type,'country')]")
	public List<WebElement> countryResults;
	
	@FindBy(xpath="//div[contains(@class,'tablet-column-8 desktop-column-6')]//a[@class='winery']")
	public WebElement winetitle1;
	
	@FindBy(xpath="//div[contains(@class,'tablet-column-8 desktop-column-6')]//span[@class='vintage']")
	public WebElement winetitle2;
	
	@FindBy(xpath="//a[@class='anchor__anchor--3DOSm breadCrumbs__link--1TY6b']")
	public List<WebElement> countryDetails;
		
	
	public EnterSearchBoxPageObjects() {
		PageFactory.initElements(driver, this);
	}
	
	//Method to click on the search bar
	public void clickOnSearchBar() {
		searchBox.click();
	}
	
	//Method to enter text in the search bar
	public void enterKeywordInSearchBar(String SearchValue) {
		searchBox.sendKeys(SearchValue);
		searchBox.sendKeys(Keys.ENTER);
	}
	
	//Method to store the attributes in the list- title, state and country
	public List<String> getSearchResults() {		
		List<String> list=new ArrayList<String>();
		for(int i=0;i<searchResults.size();i++) {
			list.add(searchResults.get(i).getText());
			list.add(capitalResults.get(i).getText());
			list.add(countryResults.get(i).getText());
		}
		
		return list;		
	}
	
	//Method to click on the first result of the search results
	public void clickSearchResult() {
			searchResults.get(0).click();
		
	}
	//Method to verify the attributes of the first result
	public List<String> verifySelectionResult() {
		List<String> list=new ArrayList<String>();
		for(int i=0;i<countryDetails.size();i++) {
			list.add(countryDetails.get(i).getText());
		}
		return list;
	}
	
}
