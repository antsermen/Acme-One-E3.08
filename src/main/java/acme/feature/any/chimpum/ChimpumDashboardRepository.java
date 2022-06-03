package acme.feature.any.chimpum;

import java.util.Collection;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChimpumDashboardRepository extends AbstractRepository {

	@Query("select c.budget.currency, 1.0 * count(c) / (select count(a) from Chimpum a) from Chimpum c where c.item != null group by c.budget.currency")
	Collection<Tuple> ratioBudgetChimpumByCurrency();
	
	@Query("select c.budget.currency, avg(c.budget.amount), stddev(c.budget.amount), min(c.budget.amount), max(c.budget.amount) from Chimpum c group by c.budget.currency")
	Collection<Tuple> indicatorsBudgetChimpumByCurrency();

}
