package acme.features.authenticated.announcement;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.Announcement;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
@RequestMapping("/authenticated/announcement/")
public class AuthenticatedAnnouncementController extends AbstractController<Authenticated, Announcement> {
	
	// Internal state ===========================================================
	
	@Autowired
	protected AuthenticatedAnnouncementListRecentService listRecentService;
	
	@Autowired
	protected AuthenticatedAnnouncementShowService showService;
	
	// Constructors =============================================================
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-recent", "list", this.listRecentService);
	}
	
}
