package acme.forms;

import java.io.Serializable;

import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------------------

	Integer	totalNumberOfProposedPatronages;
	Integer totalNumberOfAcceptedPatronages;
	Integer totalNumberOfDeniedPatronages;
	
	Money averageBudgetOfProposedPatronages; //average: Map<Pair<Currency,Status>,Double> - Currency es un enumerado (y hago lo mismo para min, max y demás)  
	Money averageBudgetOfAcceptedPatronages;
	Money averageBudgetOfDeniedPatronages;
	
	Money deviationBudgetOfProposedPatronages;
	Money deviationBudgetOfAcceptedPatronages;
	Money deviationBudgetOfDeniedPatronages;
	
	Money minimumBudgetOfProposedPatronages;
	Money minimumBudgetOfAcceptedPatronages;
	Money minimumBudgetOfDeniedPatronages;
	
	Money maximumBudgetOfProposedPatronages;
	Money maximumBudgetOfAcceptedPatronages;
	Money maximumBudgetOfDeniedPatronages;
	
	// Derived attributes -----------------------------------------------------

	
	// Relationships ----------------------------------------------------------

	
}
