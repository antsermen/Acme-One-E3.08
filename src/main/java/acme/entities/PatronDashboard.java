package acme.entities;

import javax.persistence.Entity;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PatronDashboard extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------------------

	Integer	totalNumberOfProposedPatronages;
	Integer totalNumberOfAcceptedPatronages;
	Integer totalNumberOfDeniedPatronages;
	
	Money averageBudgetOfProposedPatronages;
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
