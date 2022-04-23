package acme.testing.any.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyToolListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/toolList.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String name, final String code, final String technology, final String description, final String retailPrice, final String link) {
		

		super.clickOnMenu("Anonymous", "Tool List");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, technology);
		super.checkColumnHasValue(recordIndex, 2, retailPrice);

	}

	// Ancillary methods ------------------------------------------------------

}
