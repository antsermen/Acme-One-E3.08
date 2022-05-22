package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.Quantity;
import acme.entities.SystemConfiguration;
import acme.entities.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorToolkitRepository extends AbstractRepository{
	
	@Query("select t from Toolkit t where t.inventor.id = :id")
	Collection<Toolkit> findToolkitsFromInventorId(int id);
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findOneToolkitById(int id);
	
	@Query("select sum(q.item.systemRetailPrice.amount * q.itemsNumber) from Quantity q where q.toolkit.id =:id")
	Double calculateToolkitPrice(int id);
	
	@Query("select q.item from Quantity q where q.toolkit.id = :id")
	Collection<Item> findItemsFromToolkitId(int id);
	
	@Query("select t from Toolkit t where t.code = :code")
	Toolkit findOneToolkitByCode(String code);
	
	@Query("select i from Inventor i where i.id = :id")
	Inventor findOneEmployerById(int id);
	
	@Query("select s from SystemConfiguration s")
	SystemConfiguration findSystemConfiguration();
	
	@Query("select q from Quantity q where q.toolkit.id = :id")
	Collection<Quantity> findQuantityByToolkitId(int id);
	
	@Query("select q from Quantity q where q.id = :id")
	Quantity findOneQuantityById(int id);
}
