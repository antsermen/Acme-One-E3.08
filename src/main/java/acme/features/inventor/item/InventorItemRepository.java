package acme.features.inventor.item;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.ItemType;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorItemRepository extends AbstractRepository {
	
	@Query("SELECT i From Item i WHERE i.itemType = :type AND i.inventor.id = :id")
	List<Item> findItemsByTypeAndInventor(ItemType type, Integer id);

	
	

}
