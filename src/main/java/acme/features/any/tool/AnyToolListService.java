package acme.features.any.tool;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Tool;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyToolListService implements AbstractListService<Any, Tool>{
	
	@Autowired
	protected AnyToolRepository repository;
	
	@Override
	public boolean authorise(final Request<Tool> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public Collection<Tool> findMany(final Request<Tool> request) {
		assert request != null;

		Collection<Tool> result;

		result = this.repository.findAllTool();

		return result;
	}
	
	@Override
	public void unbind(final Request<Tool> request, final Tool entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"name", "code", "technology", "description", "retailPrice","link");
		
	}

}
