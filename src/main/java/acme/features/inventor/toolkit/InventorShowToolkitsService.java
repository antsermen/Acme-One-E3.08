package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorShowToolkitsService implements AbstractShowService<Inventor, Toolkit>{
	
			// Internal state ======================================================
	
			@Autowired
			protected InventorToolkitRepository repository;
				
			// AbstractShowService<Inventor, Toolkit> interface ==========
				
			@Override
			public boolean authorise(final Request<Toolkit> request) {
				assert request != null;
				return request.getPrincipal().getActiveRoleId() == this.repository.findOneToolkitById(request.getModel().getInteger("id")).getInventor().getId();
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
					
				final int id;
				final Money toolkitPrice = new Money();	
				
				id = request.getModel().getInteger("id");
				/*toolkitPrice.setAmount(this.repository.calculateToolkitPrice(id));*/
				final Double rp= this.repository.calculateToolkitPrice(id);
				if(rp==null) {
					toolkitPrice.setAmount(0.);
				}else {
					toolkitPrice.setAmount(rp);
				}
				toolkitPrice.setCurrency(this.repository.findSystemConfiguration().getSystemCurrency());
			
				final Collection<Item> items = this.repository.findItemsFromToolkitId(id);
				
				model.setAttribute("toolkitPrice", toolkitPrice);
				model.setAttribute("items", items);
				for (final Item i: items) {
					model.setAttribute("itemName", i.getName());
					model.setAttribute("itemType", i.getItemType());
					model.setAttribute("itemDescription", i.getDescription());
					model.setAttribute("itemRetailPrice", i.getRetailPrice());
					model.setAttribute("itemSystemRetailPrice", i.getSystemRetailPrice());
				}
				request.unbind(entity, model,"code","title", "description", "notes", "link","published");
			}
}
