/*
 * EmployerJobListMineTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.patron.patronage.patronageReport;

import java.util.Date;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageReportListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage-report/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String memorandum, final String link, final Date creationMoment, final String name) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Patron", "Patronage Report List");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, memorandum);
		super.checkColumnHasValue(recordIndex, 1, link);
		super.checkColumnHasValue(recordIndex, 2, creationMoment.toString());
		super.checkColumnHasValue(recordIndex, 3, name);

		super.clickOnListingRecord(recordIndex);
		
        super.checkFormExists();
        super.checkInputBoxHasValue("memorandum", memorandum);
        super.checkInputBoxHasValue("link", link);
        super.checkInputBoxHasValue("creationMoment", creationMoment.toString());
       
		
        super.signOut();

	}


}