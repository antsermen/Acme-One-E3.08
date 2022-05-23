package acme.testing.inventor.toolkits;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class InventorToolkitDeleteTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkits/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveItem(final int recordIndex, final String code,final String title, final String description,
		final String notes,final String link) {
		
		
		super.signIn("inventor1", "inventor1");
		//list
		super.clickOnMenu("Inventor", "My Toolkits List");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, link);
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("notes", notes);
		super.checkInputBoxHasValue("link", link);
		super.clickOnSubmit("Delete");
		super.clickOnMenu("Inventor", "My Toolkits List");
		super.checkNotErrorsExist();

		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkits/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String code,final String title, final String description,
		final String notes, final String link) {
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "My Toolkits List");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("notes", notes);
		super.checkInputBoxHasValue("link", link);
		super.checkNotSubmitExists("Delete");
		
		super.signOut();
		
	}
	
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		super.signIn("administrator", "administrator");
		super.navigate("/inventor/toolkit/create");
		super.checkPanicExists();
		super.signOut();
		
		
	}

}
