package ajaxcrud.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ajaxcrud.domain.Contact;
import ajaxcrud.service.ContactService;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;

@Controller
@RequestMapping("contact")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		// DatatablesCriterias criterias =
		// DatatablesCriterias.getFromRequest(request);
		// DataSet<Contact> contacts =
		// contactService.findContactWithDatatablesCriterias(criterias);
		// model.addAttribute("contacts", contacts.getRows());
		return "list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public DatatablesResponse<Contact> findAll(HttpServletRequest request, Model model) {
		DatatablesCriterias criterias = DatatablesCriterias.getFromRequest(request);
		DataSet<Contact> contacts = contactService.findContactWithDatatablesCriterias(criterias);
		return DatatablesResponse.build(contacts, criterias);
	}
}
