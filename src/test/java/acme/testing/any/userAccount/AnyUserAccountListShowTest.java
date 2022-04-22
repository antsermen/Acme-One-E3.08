package acme.testing.any.userAccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyUserAccountListShowTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/user-account/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String username, final String roles, final String name, final String surname, final String email) {
		
		//List--------------------------------------------
		super.clickOnMenu("Anonymous", "User Accounts List");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, username);
		super.checkColumnHasValue(recordIndex, 1, roles);
		
		//Show-----------------------------------------------------------
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("username", username);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("surname", surname);
		super.checkInputBoxHasValue("email", email);
		
	}

}
