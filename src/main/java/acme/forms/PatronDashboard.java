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

	Map<Status, Integer> totalNumberOfPatronages;
	Map<Pair<String,Status>,Double> averageBudgetOfPatronages;
	Map<Pair<String,Status>,Double> deviationBudgetOfPatronages;
	Map<Pair<String,Status>,Double> minimumBudgetOfPatronages;
	Map<Pair<String,Status>,Double> maximumBudgetOfPatronages;
	// Derived attributes -----------------------------------------------------

	
	// Relationships ----------------------------------------------------------

	
}
