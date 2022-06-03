package acme.testing.inventor.chimpum;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorChimpumUpdateTest extends TestHarness {
	
	// Para asegurar el funcionamiento de este test, realice un populate-sample antes de ejecutar el test
	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/positive-update.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String itemCode, final String pattern, final String creationMoment, final String title, final String description, final String initialPeriod, final String finalPeriod, final String budget, final String link) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "My Tools List");
		super.checkListingExists();
		super.sortListing(2, "asc");
		super.clickOnListingRecord(0);
		super.clickOnButton("Chimpum List");
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);

		super.checkFormExists();
		super.fillInputBoxIn("pattern", pattern);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("initialPeriod", initialPeriod);
		super.fillInputBoxIn("finalPeriod", finalPeriod);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmit("Update");
		
		super.clickOnMenu("Inventor", "My Tools List");
		super.checkListingExists();
		super.sortListing(2, "asc");
		super.clickOnListingRecord(0);
		super.clickOnButton("Chimpum List");
		super.sortListing(0, "desc");
		super.checkColumnHasValue(recordIndex, 1, title);
		
		//Show--------------------------------------------------------------
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("itemCode", itemCode);
		super.checkInputBoxHasValue("pattern", pattern);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("initialPeriod", initialPeriod);
		super.checkInputBoxHasValue("finalPeriod", finalPeriod);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("link", link);
		super.signOut();
		
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/chimpum/negative-update.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String itemCode, final String pattern, final String creationMoment, final String title, final String description, final String initialPeriod, final String finalPeriod, final String budget, final String link) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "My Tools List");
		super.checkListingExists();
		super.sortListing(2, "asc");
		super.clickOnListingRecord(0);
		super.clickOnButton("Chimpum List");
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);

		super.checkFormExists();
		super.fillInputBoxIn("pattern", pattern);
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("initialPeriod", initialPeriod);
		super.fillInputBoxIn("finalPeriod", finalPeriod);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();
		super.signOut();
	}

	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/chimpum/update");
		super.checkPanicExists();
	}

}
