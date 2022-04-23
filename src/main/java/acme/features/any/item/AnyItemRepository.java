package acme.features.any.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.Item;
import acme.entities.ItemType;
import acme.framework.repositories.AbstractRepository;

public interface AnyItemRepository extends AbstractRepository {
	
	@Query("select i from Item i where i.itemType=:type and i.published=:True")
	Collection<Item> findAllItemType(ItemType type,boolean True);
	
	@Query("select i from Item i where i.id=:id")
	Item findItemById(Integer id);

}
