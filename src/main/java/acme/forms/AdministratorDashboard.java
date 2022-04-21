package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.Status;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AdministratorDashboard implements Serializable {
	
	// Serialisation identifier -----------------------------------------------
	
	protected static final long	serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------------------	
	
	Integer totalNumberOfComponents;
	Map<Pair<String,String>,Double> averageRetailPriceOfComponents;
	Map<Pair<String,String>,Double> deviationRetailPriceOfComponents;
	Map<Pair<String,String>,Double> minimumRetailPriceOfComponents;
	Map<Pair<String,String>,Double> maximumRetailPriceOfComponents;
	
	Integer totalNumberOfTools;
	Map<String,Double> averageRetailPriceOfTools;
	Map<String,Double> deviationRetailPriceOfTools;
	Map<String,Double> minimumRetailPriceOfTools;
	Map<String,Double> maximumRetailPriceOfTools;
	
	Map<Status, Integer> totalNumberOfPatronagesByStatus;
	Map<Status,Double> averageBudgetOfPatronagesByStatus;
	Map<Status,Double> deviationBudgetOfPatronagesByStatus;
	Map<Status,Double> minimumBudgetOfPatronagesByStatus;
	Map<Status,Double> maximumBudgetOfPatronagesByStatus;
	
	

	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
}
