package acme.features.any.userAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyUserAccountShowService implements AbstractShowService<Any, UserAccount>{

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
	public UserAccount findOne(final Request<UserAccount> request) {
		assert request != null;
		return this.anyUserAccountRepository.findOneById(request.getModel().getInteger("id"));
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("name", entity.getIdentity().getName());
		model.setAttribute("surname", entity.getIdentity().getSurname());
		model.setAttribute("email", entity.getIdentity().getEmail());
		request.unbind(entity, model, "username");
	}
	
	

}
