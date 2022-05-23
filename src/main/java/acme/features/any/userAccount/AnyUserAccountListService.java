package acme.features.any.userAccount;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;
import acme.framework.roles.UserRole;
import acme.framework.services.AbstractListService;


@Service
public class AnyUserAccountListService implements AbstractListService<Any, UserAccount>{
	
	//Internal state----------------------------------------------------------------------
	
	@Autowired 
	protected AnyUserAccountRepository anyUserAccountRepository;
	
	// AbstractListService<Any, UserAccount> interface --------------
	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<UserAccount> findMany(final Request<UserAccount> request) {
		assert request != null;
		final Collection<UserAccount> result = new ArrayList<>();
		final List<UserAccount> aux = new ArrayList<>(this.anyUserAccountRepository.findAllUserEnableds());
		for(final UserAccount ua : aux) {
			boolean isListable = true;
			for(final UserRole ur : ua.getRoles()) {
				if(ur.getAuthorityName().equals("Administrator") || ua.isAnonymous()) {
					isListable = false;
				}
			}
			if(isListable) {
				result.add(ua);
			}
		}
		return result;
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		model.setAttribute("roles", entity.getAuthorityString());
		request.unbind(entity, model, "username");
	}
}
