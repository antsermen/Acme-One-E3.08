package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolkitRepository extends AbstractRepository{
	
	@Query("select t from Toolkit t where t.inventor.id = :id")
	Collection<Toolkit> findToolkitsFromInventorId(int id);
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findOneToolkitById(int id);
	
	@Query("select sum(q.item.retailPrice.amount * q.itemsNumber) from Quantity q where q.toolkit.id =:id")
	Double calculateToolkitPrice(int id);
	
}
