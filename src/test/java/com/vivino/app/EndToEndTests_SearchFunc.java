package com.vivino.app;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import reusableComponents.PropertiesOperations;
import testBase.TestBase;

public class EndToEndTests_SearchFunc extends TestBase {

	/*In this Test method, validating the keyword matches with the search results. 
	if true then display the match else show the attributes which are not matching*/
	@Test(enabled = true)
	public void SearchCriteria_Vivino() throws Exception {
		enterSearch.clickOnSearchBar();
		String keyword = PropertiesOperations.getPropertyValueByKey("input");
		enterSearch.enterKeywordInSearchBar(keyword);
		List<String> KeywordNotFound = new ArrayList<String>();
		List<String> names = enterSearch.getSearchResults();
		int count=0;
		for (String search:names) {
			if (search.contains(keyword)) {
				System.out.println(search);
				count++;
			}
			else {
				KeywordNotFound.add(search);
			}

		}
		if(count==0) {
			Assert.assertFalse(false, "No results matching with the keyword");
		}
		
		for(String keywordNotFound :KeywordNotFound) {
			System.out.println("These are the attributes for which keyword is not found"+keywordNotFound);
		}
		
		
		
		
	}
	/*In this Test method, validating the click on the first search result and verify the match
	with the attributes stored in the list. If true, then log which attribute matches*/
	@Test(enabled = true)
	public void validateClickOnOneSelection() throws Exception {
		
		enterSearch.clickOnSearchBar();
		String keyword = PropertiesOperations.getPropertyValueByKey("input");
		enterSearch.enterKeywordInSearchBar(keyword);
		List<String> names = enterSearch.getSearchResults();	
		enterSearch.clickSearchResult();
		List<String> countryInformation=enterSearch.verifySelectionResult();
		int counter=0,newCount=0,count=0;
		for(String searchValue:names) {
			counter++;
			if(enterSearch.winetitle1.getText().concat(" ").concat(enterSearch.winetitle2.getText()).equalsIgnoreCase(searchValue)) {
				test.log(Status.PASS, "The title of the selected wine matches the stored list");
				
				for(String countryInfo:countryInformation) {
					if(countryInfo.equalsIgnoreCase(names.get(counter+1))) {
						newCount++;
						test.log(Status.PASS,"The countries are matching");
					}
					if(countryInfo.equalsIgnoreCase(names.get(counter))) {
						count++;
						test.log(Status.PASS,"The state information is matching");
					}
				}
				
			}
		}
		if(counter==names.size()-1) {
			test.log(Status.PASS, "The attribute stored in the list does not match with the fetched attribute of the selected wine");
		}
		if(newCount==0) {
			test.log(Status.PASS, "The country attribute is not matching");
		}
		if(count==0) {
			test.log(Status.PASS,"The state attribute is not matching");
		}
		int countValue=0;
		if(enterSearch.winetitle1.getText().concat(" ").concat(enterSearch.winetitle2.getText()).contains(keyword)) {
			System.out.println("Attribute title contains the keyword " +  enterSearch.winetitle1.getText().concat(" ").concat(enterSearch.winetitle2.getText()));
			countValue=1;
		}
		else {
			
				if(countryInformation.get(0).contains(keyword)) {
					countValue=1;
					test.log(Status.PASS,"Attribute country contains the keyword "+ countryInformation.get(0));
				}
				if(countryInformation.get(1).contains(keyword)) {
					countValue=1;
					test.log(Status.PASS,"Attribute state contains the keyword "+ countryInformation.get(1));
				}

		}
		if(countValue==0) {
			test.log(Status.PASS,"The attributes are not matching");
		}
	}
	
}
