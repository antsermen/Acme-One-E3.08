package acme.features.any.tool;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Tool;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolRepository extends AbstractRepository{
	
	@Query("select a from Tool a")
	Collection<Tool> findAllTool();
	
	@Query("select a from Tool a where a.id= :id")
	Tool findToolById(int id);
	
	

}
