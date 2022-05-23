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

package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code, final String legalStuff,
		final String budget, final String creationDate, final String startDate, final String deadline, final String info, 
		final String inventor, final String patron) {
		super.signIn("patron2", "patron2");
		super.clickOnMenu("Patron", "My Patronages List");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, budget);
		super.checkColumnHasValue(recordIndex, 3, creationDate);
		super.clickOnListingRecord(recordIndex);
		
        super.checkFormExists();
        super.checkInputBoxHasValue("status", status);
        super.checkInputBoxHasValue("code", code);
        super.checkInputBoxHasValue("legalStuff", legalStuff);
        super.checkInputBoxHasValue("budget", budget);
        super.checkInputBoxHasValue("creationDate", creationDate);
        super.checkInputBoxHasValue("startDate", startDate);
        super.checkInputBoxHasValue("deadline", deadline);
        super.checkInputBoxHasValue("info", info);
        super.checkInputBoxHasValue("inventor", inventor);
        super.checkInputBoxHasValue("patron", patron);
		
        super.signOut();

	}


}