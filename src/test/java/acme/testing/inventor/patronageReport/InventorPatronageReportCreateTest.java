package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportCreateTest extends TestHarness {
	
	// Para asegurar el funcionamiento de este test, realice un populate-sample antes de ejecutar el test
	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/patronage-report.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String memorandum, final String link, final String patronageCode, final String confirm) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "My Patronages List");
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
		super.clickOnButton("Create patronage report");

		super.checkFormExists();
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirm", confirm);
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor", "My Patronage Reports");
		super.checkListingExists();
		super.sortListing(0, "desc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("patronageCode", patronageCode);
		
		super.signOut();
		
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/patronage-report-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String memorandum, final String link, final String confirm) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "My Patronages List");
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);
		super.clickOnButton("Create patronage report");

		super.checkFormExists();
		super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("confirm", confirm);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		
		super.signOut();
	}

	@Test
	@Order(30)
	public void hackingTest() {
		
		super.signIn("administrator", "administrator");
		super.navigate("/inventor/patronage-report/create");
		super.checkPanicExists();
		super.signOut();
		super.signIn("administrator", "administrator");
		super.navigate("/inventor/patronage-report/create");
		super.checkPanicExists();
		super.signOut();
		super.signIn("administrator", "administrator");
		super.navigate("/inventor/patronage-report/create");
		super.checkPanicExists();
		super.signOut();
		
	}

}
