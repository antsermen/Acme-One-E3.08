package acme.features.authenticated.inventor;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Announcement;
import acme.entities.Toolkit;
import acme.features.authenticated.announcement.AuthenticatedAnnouncementRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.roles.Inventor;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedInventorListToolkitsService implements AbstractListService<Authenticated, Inventor> {
	
		// Internal state ========================================================
	
		@Autowired
		protected AuthenticatedInventorRepository repository;
		
		// AbstractListService<Authenticated, Inventor> interface ============
		
		@Override
		public boolean authorise(final Request<Inventor> request) {
			assert request != null;
			
			return true;
		}
		
		@Override
		public Collection<Toolkit> findMany(final Request<Inventor> request) {
			assert request != null;
			
			Collection<Toolkit> result;
			int id;
			
			id = request.getModel().getInteger("id");
			result = this.repository.findToolkitsFromInventorId(id);
			
			return result;
		}
		
		@Override
		public void unbind(final Request<Inventor> request, final Toolkit entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			request.unbind(entity, model, "code", "title", "description", "notes", "link", "price");
		}
	}

}
