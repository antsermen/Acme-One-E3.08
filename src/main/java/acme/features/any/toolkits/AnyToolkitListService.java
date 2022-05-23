package acme.features.any.toolkits;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyToolkitListService implements AbstractListService<Any, Toolkit>{
	
	@Autowired
	protected AnyToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;

		Collection<Toolkit> result;

		result = this.repository.findAllToolkit(true);

		return result;
	}
	
	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"title", "code", "description", "notes","link");
		
		final Collection<Item> items= this.repository.findItemsFromToolkitId(entity.getId());
		final StringBuilder payload= new StringBuilder();
		int counter=0;
		for(final Item item: items) {
			if(counter==items.size()-1) {
				payload.append(item.getCode()+"; "+item.getName());
			}else {
				payload.append(item.getCode()+"; "+item.getName()+";");
			}
			counter ++;

		
		}
	
		model.setAttribute("payload", payload);	
		
	}

}
