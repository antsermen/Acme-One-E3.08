package acme.features.inventor.rotelam;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.ItemType;
import acme.entities.Rotelam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorRotelamRepository extends AbstractRepository{
	
	@Query("SELECT r FROM Rotelam r WHERE r.id = :id")
	Rotelam findRotelamById(Integer id);
	
	@Query("SELECT i FROM Item i WHERE i.itemType = :type AND i.inventor.id = :id")
	List<Item> findItemsByTypeAndInventor(ItemType type, Integer id);
	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findItemById(Integer id);
	
	@Query("select r from Rotelam r where r.inventor.id=:id")
	Collection<Rotelam> findManyRotelamById(Integer id);

}
