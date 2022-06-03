package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Chimpum;
import acme.entities.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorChimpumRepository extends AbstractRepository {
	
	@Query("SELECT i FROM Item i WHERE i.id = :id")
	Item findItemById(int id);
	
	@Query("SELECT i FROM Item i WHERE i.code = :code")
	Item findItemByCode(String code);
	
	@Query("SELECT c FROM Chimpum c WHERE c.id = :id")
	Chimpum findChimpumById(int id);
	
	@Query("SELECT c FROM Chimpum c WHERE c.item.id = :id")
	Collection<Chimpum> findChimpumByItemId(int id);

}
