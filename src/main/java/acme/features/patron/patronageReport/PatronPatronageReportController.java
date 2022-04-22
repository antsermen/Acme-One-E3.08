package acme.features.patron.patronageReport;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.PatronageReport;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
@RequestMapping("/patron/patronage-report/")

public class PatronPatronageReportController extends AbstractController<Patron, PatronageReport>{

	// Internal state -------------------------------------------------------------------

	@Autowired
	protected PatronPatronageReportListService listService;

	@Autowired
	protected PatronPatronageReportShowService showService;

	// Constructors ---------------------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}
}