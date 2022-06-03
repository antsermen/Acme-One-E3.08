package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumListShowTest extends TestHarness {

	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/positive-list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String itemCode, final String pattern, final String creationMoment, final String title, final String description, final String initialPeriod, final String finalPeriod, final String budget, final String link) {
		super.signIn("inventor1", "inventor1");
		//List----------------------------------------------
		super.clickOnMenu("Inventor", "My Tools List");
		super.checkListingExists();
		super.sortListing(2, "asc");
		super.clickOnListingRecord(0);
		super.clickOnButton("Chimpum List");
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, creationMoment);
		super.checkColumnHasValue(recordIndex, 1, title);
		
		//Show--------------------------------------------------------------
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("itemCode", itemCode);
		super.checkInputBoxHasValue("pattern", pattern);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("initialPeriod", initialPeriod);
		super.checkInputBoxHasValue("finalPeriod", finalPeriod);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("link", link);
		super.signOut();
		
		
	}
	
	
}
