package acme.forms;

import java.io.Serializable;
import java.util.Map;

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
	
	Map<String, Double> averageBudgetOfProposedPatronages;
	Map<String, Double> averageBudgetOfAcceptedPatronages;
	Map<String, Double> averageBudgetOfDeniedPatronages;
	
	Map<String, Double> deviationBudgetOfProposedPatronages;
	Map<String, Double> deviationBudgetOfAcceptedPatronages;
	Map<String, Double> deviationBudgetOfDeniedPatronages;
	
	Map<String, Double> minimumBudgetOfProposedPatronages;
	Map<String, Double> minimumBudgetOfAcceptedPatronages;
	Map<String, Double> minimumBudgetOfDeniedPatronages;
	
	Map<String, Double> maximumBudgetOfProposedPatronages;
	Map<String, Double> maximumBudgetOfAcceptedPatronages;
	Map<String, Double> maximumBudgetOfDeniedPatronages;
	
	// Derived attributes -----------------------------------------------------

	
	// Relationships ----------------------------------------------------------

	
}
