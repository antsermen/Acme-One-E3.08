package acme.features.administrator.dashboard;

import java.util.Collection;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(i) from Item i where i.itemType = acme.entities.ItemType.COMPONENT")
	Integer totalNumberOfComponents();
	
	@Query("select i.technology, avg(i.retailPrice.amount) from Item i where i.retailPrice.currency = :currency and i.itemType = acme.entities.ItemType.COMPONENT group by i.technology")
	Collection<Tuple> averageRetailPriceOfComponents(String currency);
	
	@Query("select i.technology, stddev(i.retailPrice.amount) from Item i where i.retailPrice.currency = :currency and i.itemType = acme.entities.ItemType.COMPONENT group by i.technology")
	Collection<Tuple> deviationRetailPriceOfComponents(String currency);
	
	@Query("select i.technology, min(i.retailPrice.amount) from Item i where i.retailPrice.currency = :currency and i.itemType = acme.entities.ItemType.COMPONENT group by i.technology")
	Collection<Tuple> minimumRetailPriceOfComponents(String currency);
	
	@Query("select i.technology, max(i.retailPrice.amount) from Item i where i.retailPrice.currency = :currency and i.itemType = acme.entities.ItemType.COMPONENT group by i.technology")
	Collection<Tuple> maximumRetailPriceOfComponents(String currency);
	
	
	
	@Query("select count(i) from Item i where i.itemType = acme.entities.ItemType.TOOL")
	Integer totalNumberOfTools();
	
	@Query("select avg(i.retailPrice.amount) from Item i where i.retailPrice.currency = :currency and i.itemType = acme.entities.ItemType.TOOL")
	Double averageRetailPriceOfTools(String currency);
	
	@Query("select stddev(i.retailPrice.amount) from Item i where i.retailPrice.currency = :currency and i.itemType = acme.entities.ItemType.TOOL")
	Double deviationRetailPriceOfTools(String currency);
	
	@Query("select min(i.retailPrice.amount) from Item i where i.retailPrice.currency = :currency and i.itemType = acme.entities.ItemType.TOOL")
	Double minimumRetailPriceOfTools(String currency);
	
	@Query("select max(i.retailPrice.amount) from Item i where i.retailPrice.currency = :currency and i.itemType = acme.entities.ItemType.TOOL")
	Double maximumRetailPriceOfTools(String currency);
	
	
	
	@Query("select count(p) from Patronage p where p.status = :status")
	Integer totalNumberOfPatronagesByStatus(Status status);
	
	@Query("select avg(p.budget.amount) from Patronage p where p.status = :status")
	Double averageBudgetOfPatronagesByStatus(Status status);
	
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = :status")
	Double deviationBudgetOfPatronagesByStatus(Status status);
	
	@Query("select min(p.budget.amount) from Patronage p where p.status = :status")
	Double minimumBudgetOfPatronagesByStatus(Status status);
	
	@Query("select max(p.budget.amount) from Patronage p where p.status = :status")
	Double maximumBudgetOfPatronagesByStatus(Status status);

}
