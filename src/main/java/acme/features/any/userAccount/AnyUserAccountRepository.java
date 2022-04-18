package acme.features.any.userAccount;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

public interface AnyUserAccountRepository extends AbstractRepository{
	
	@Query("SELECT ua FROM UserAccount ua WHERE ua.enabled = true")
	Collection<UserAccount> findAllUserEnableds();

	@Query("SELECT ua FROM UserAccount ua WHERE ua.is = :id")
	UserAccount findOneById(Integer id);

}
