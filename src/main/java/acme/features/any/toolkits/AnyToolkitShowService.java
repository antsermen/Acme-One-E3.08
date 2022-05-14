package acme.features.any.toolkits;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Toolkit;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any,Toolkit>{
	
	@Autowired
	protected AnyToolkitRepository repository;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigurationRepository;

	// AbstractShowService<Anonymous, Job> interface --------------------------

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
			
		final Toolkit result;
		int id;
			
		id = request.getModel().getInteger("id");
		result = this.repository.findOneToolkitById(id);
			
		return result;
	}
		
	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
			
		int id;
		final Money toolkitPrice = new Money();	
		id = request.getModel().getInteger("id");
		toolkitPrice.setAmount(this.repository.calculateToolkitPrice(id));
		toolkitPrice.setCurrency(this.systemConfigurationRepository.findSystemConfiguration().getSystemCurrency());
		final Collection<Item> items = this.repository.findItemsFromToolkitId(id);
		
		model.setAttribute("toolkitPrice", toolkitPrice);
		model.setAttribute("items", items);
		for (final Item i: items) {
			model.setAttribute("itemName", i.getName());
			model.setAttribute("itemType", i.getItemType());
			model.setAttribute("itemDescription", i.getDescription());
			model.setAttribute("itemRetailPrice", i.getRetailPrice());
		}
		request.unbind(entity, model, "title", "description", "notes", "link", "code");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
	}
}
