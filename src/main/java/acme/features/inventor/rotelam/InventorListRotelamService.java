package acme.features.inventor.rotelam;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Item;
import acme.entities.Rotelam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

public class InventorListRotelamService implements AbstractListService<Inventor,Rotelam>{
	
	//Internal state----------------------------------------------------------
	
		@Autowired
		protected InventorRotelamRepository repository;
		
		//AbstractListService------------------------------------------------------

		@Override
		public boolean authorise(final Request<Rotelam> request) {
			assert request != null;
			
			final boolean result;
			int id;
			Item item;
			
			id = request.getModel().getInteger("masterId");
			item= this.repository.findItemById(id);
			result=(item != null && request.isPrincipal(item.getInventor()));
			
			return result;

		}

		@Override
		public Collection<Rotelam> findMany(final Request<Rotelam> request) {
			assert request != null;
			int id;
			id = request.getModel().getInteger("masterId");
			return this.repository.findManyRotelamById(id);
		}

		@Override
		public void unbind(final Request<Rotelam> request, final Rotelam entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			request.unbind(entity, model, "code", "creationMoment", "subject", "explanation", "period", "expenditure", "moreInfo");
		}

}
