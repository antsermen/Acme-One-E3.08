package acme.features.inventor.item;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.ItemType;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorItemRepository extends AbstractRepository {
	
	@Query("SELECT i FROM Item i WHERE i.itemType = :type AND i.inventor.id = :id AND i.published = True")
	List<Item> findItemsByTypeAndInventor(ItemType type, Integer id);

	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findItemById(Integer id);
	
	

}
