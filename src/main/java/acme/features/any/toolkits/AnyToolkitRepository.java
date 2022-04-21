package acme.features.any.toolkits;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository{
	
	@Query("select t from Toolkit t")
	Collection<Toolkit> findAllToolkit();
	
	@Query("select t from Toolkit t where t.id=:id")
	Toolkit findToolkit(int id);
	
	//@Query("select q.item.id from Quantity q where q.toolkit.id:=id")
	//Collection<Item> findItemsByToolkit(int id);
	
	

}
