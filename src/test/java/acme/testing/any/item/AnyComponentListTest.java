package acme.testing.any.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyComponentListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/componentList.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String itemType, final String code, final String description, final String link, 
		final String name, final String retailPrice, final String systemRetailPrice, final String technology, final String published, final String inventor) {
		

		super.clickOnMenu("Anonymous", "Component List");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, technology);
		super.checkColumnHasValue(recordIndex, 2, retailPrice);

	}

}
