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
	
	Integer totalNumberOfProposedPatronages;
	Map<Status,Double> averageRetailPriceOfProposedPatronages;
	Map<Status,Double> deviationRetailPriceOfProposedPatronages;
	Map<Status,Double> minimumRetailPriceOfProposedPatronages;
	Map<Status,Double> maximumRetailPriceOfProposedPatronages;
	
	Integer totalNumberOfAcceptedPatronages;
	Map<Status,Double> averageRetailPriceOfAcceptedPatronages;
	Map<Status,Double> deviationRetailPriceOfAcceptedPatronages;
	Map<Status,Double> minimumRetailPriceOfAcceptedPatronages;
	Map<Status,Double> maximumRetailPriceOfAcceptedPatronages;
	
	Integer totalNumberOfDeniedPatronages;
	Map<Status,Double> averageRetailPriceOfDeniedPatronages;
	Map<Status,Double> deviationRetailPriceOfDeniedPatronages;
	Map<Status,Double> minimumRetailPriceOfDeniedPatronages;
	Map<Status,Double> maximumRetailPriceOfDeniedPatronages;
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
}
