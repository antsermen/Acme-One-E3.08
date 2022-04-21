package acme.features.any.toolkits;

import org.springframework.stereotype.Service;

@Service
public class AnyToolkitShowService /*implements AbstractShowService<Any,Toolkit>*/{
	
	/*@Autowired
	protected AnyToolkitRepository repository;

	// AbstractShowService<Anonymous, Job> interface --------------------------

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		Integer id;

		id = request.getModel().getInteger("id");
		result = this.repository.findToolkit(id);

		return result;
	}
	
	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		int id;
		Collection<Item> items;
		
		id= request.getModel().getInteger("id");
		items=this.repository.findItemsByToolkit(id);
		
		model.setAttribute("items", items);
		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice","link");
		
	}
*/
}
