package acme.testing.administrator.systemconfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationUpdateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/system-configuration/system-configuration.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String systemCurrency, final String acceptedCurrencies, final String strongSpamTerms,
		final String strongSpamThreshold, final String weakSpamTerms,final String weakSpamThreshold) {


		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "System Configuration");

		super.fillInputBoxIn("systemCurrency", systemCurrency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("strongSpamTerms", strongSpamTerms);
		super.fillInputBoxIn("strongSpamThreshold", strongSpamThreshold);
		super.fillInputBoxIn("weakSpamTerms", weakSpamTerms);
		super.fillInputBoxIn("weakSpamThreshold", weakSpamThreshold);

		super.clickOnSubmit("Update");

		super.clickOnMenu("Administrator", "System Configuration");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.checkColumnHasValue(recordIndex, 1, systemCurrency);
		super.clickOnListingRecord(recordIndex);

		super.checkFormExists();
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("strongSpamTerms", strongSpamTerms);
		super.checkInputBoxHasValue("strongSpamThreshold", strongSpamThreshold);
		super.checkInputBoxHasValue("weakSpamTerms", weakSpamTerms);
		super.checkInputBoxHasValue("weakSpamThreshold", weakSpamThreshold);



		super.signOut();
	}

}
