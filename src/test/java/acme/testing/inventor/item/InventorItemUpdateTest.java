package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemUpdateTest extends TestHarness{
	
	// Para asegurar el funcionamiento de este test, realice un populate-sample antes de ejecutar el test
	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String type, final String code, final String description, final String link, final String name, final String retailPrice,	 final String technology) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "My Tools List");
		super.checkListingExists();
		
		super.sortListing(3, "asc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");
		
		super.clickOnMenu("Inventor", "My Tools List");
		super.checkListingExists();
		super.sortListing(3, "asc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("itemType", "Tool");
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("link", link);
		
		super.clickOnSubmit("Delete");
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String type, final String code, final String description, final String link, final String name, final String retailPrice,	 final String technology) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "My Tools List");
		super.checkListingExists();
		super.sortListing(3, "asc");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
	}

	
}
