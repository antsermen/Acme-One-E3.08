package acme.features.any.tool;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.ItemType;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolRepository extends AbstractRepository{
	
	@Query("select i from Item i where i.itemType = :type")
	Collection<Item> findAllItemsFromOneType(ItemType type);
	
	@Query("select i from Item i where i.id= :id")
	Item findToolById(int id);
	
	

}
