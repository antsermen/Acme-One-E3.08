package acme.features.any.toolkits;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitsRepository extends AbstractRepository{
	
	@Query("select t from Toolkit t")
	Collection<Toolkit> findAllToolkit();
	
	//@Query("select t from Tool where t.toolkit.id=:masterID")
	//Collection<Toolkit> findManyToolkitByTool(int masterID);
	
	

}
