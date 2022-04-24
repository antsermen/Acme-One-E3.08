package acme.features.patron.dashboard;

import java.util.Collection;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository {

	
	
	
	
	
	@Query("select count(p) from Patronage p where p.status = acme.entities.Status.ACCEPTED group by p.status")
	Integer totalNumberOfAcceptedPatronages();
	
	@Query("select count(p) from Patronage p where p.status = acme.entities.Status.PROPOSED group by p.status")
	Integer totalNumberOfProposedPatronages();
	
	@Query("select count(p) from Patronage p where p.status = acme.entities.Status.DENIED group by p.status")
	Integer totalNumberOfDeniedPatronages();

	
	/////////////////////////////////////////////////////////////
	
	
	@Query("select p.budget.currency, avg(p.budget.amount), stddev(p.budget.amount), min(p.budget.amount), max(p.budget.amount) from Patronage p where p.status = acme.entities.Status.ACCEPTED group by p.budget.currency")
	Collection<Tuple> indicatorsBudgetOfAcceptedPatronages();
	
	@Query("select p.budget.currency, avg(p.budget.amount), stddev(p.budget.amount), min(p.budget.amount), max(p.budget.amount) from Patronage p where p.status = acme.entities.Status.PROPOSED group by p.budget.currency")
	Collection<Tuple> indicatorsBudgetOfProposedPatronages();
	
	@Query("select p.budget.currency, avg(p.budget.amount), stddev(p.budget.amount), min(p.budget.amount), max(p.budget.amount) from Patronage p where p.status = acme.entities.Status.DENIED group by p.budget.currency")
	Collection<Tuple> indicatorsBudgetOfDeniedPatronages();

}
