package acme.features.patron.dashboard;

import java.util.Collection;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.roles.Patron;

@Repository
public interface PatronDashboardRepository extends AbstractRepository {

	
	
	@Query("select p from Patron p where p.userAccount.id = :id")
	Patron findOnePatronByUserAccountId(int id);
	
	
	@Query("select count(p) from Patronage p where p.patron.id = id and p.status = acme.entities.Status.ACCEPTED group by p.status ")
	Integer totalNumberOfAcceptedPatronages(int id);
	
	@Query("select count(p) from Patronage p where p.patron.id = id and p.status = acme.entities.Status.PROPOSED group by p.status")
	Integer totalNumberOfProposedPatronages(int id);
	
	@Query("select count(p) from Patronage p where p.patron.id = id and p.status = acme.entities.Status.DENIED group by p.status")
	Integer totalNumberOfDeniedPatronages(int id);

	
	/////////////////////////////////////////////////////////////
	
	
	@Query("select p.budget.currency, avg(p.budget.amount), stddev(p.budget.amount), min(p.budget.amount), max(p.budget.amount) from Patronage p where p.patron.id = id and p.status = acme.entities.Status.ACCEPTED group by p.budget.currency")
	Collection<Tuple> indicatorsBudgetOfAcceptedPatronages(int id);
	
	@Query("select p.budget.currency, avg(p.budget.amount), stddev(p.budget.amount), min(p.budget.amount), max(p.budget.amount) from Patronage p where p.patron.id = id and p.status = acme.entities.Status.PROPOSED group by p.budget.currency")
	Collection<Tuple> indicatorsBudgetOfProposedPatronages(int id);
	
	@Query("select p.budget.currency, avg(p.budget.amount), stddev(p.budget.amount), min(p.budget.amount), max(p.budget.amount) from Patronage p where p.patron.id = id and p.status = acme.entities.Status.DENIED group by p.budget.currency")
	Collection<Tuple> indicatorsBudgetOfDeniedPatronages(int id);

}
