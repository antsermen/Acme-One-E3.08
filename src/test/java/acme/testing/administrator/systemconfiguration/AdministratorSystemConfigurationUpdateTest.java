package acme.testing.administrator.systemconfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationUpdateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/system-configuration/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String acceptedCurrencies, final String systemCurrency, final String strongSpamTerms,
		final String strongSpamThreshold, final String weakSpamTerms,final String weakSpamThreshold) {


		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "System Configuration");

		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("systemCurrency", systemCurrency);
		super.fillInputBoxIn("strongSpamTerms", strongSpamTerms);
		super.fillInputBoxIn("strongSpamThreshold", strongSpamThreshold);
		super.fillInputBoxIn("weakSpamTerms", weakSpamTerms);
		super.fillInputBoxIn("weakSpamThreshold", weakSpamThreshold);

		super.clickOnSubmit("Update");

		super.clickOnMenu("Authenticated", "System Configuration");
	
		super.checkFormExists();
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("strongSpamTerms", strongSpamTerms);
		super.checkInputBoxHasValue("strongSpamThreshold", strongSpamThreshold);
		super.checkInputBoxHasValue("weakSpamTerms", weakSpamTerms);
		super.checkInputBoxHasValue("weakSpamThreshold", weakSpamThreshold);



		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/system-configuration/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex,final String systemCurrency, final String acceptedCurrencies, final String strongSpamTerms,
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

		super.checkErrorsExist();

		super.signOut();
	}
	@Test
	@Order(30)
	public void hackingTest() {
		super.navigate("/administrator/system-configuration/show");
		super.checkPanicExists();
		
		super.signIn("patron1", "patron1");
		super.navigate("/administrator/system-configuration/show");
		super.checkPanicExists();
		super.signOut();
	}



}

