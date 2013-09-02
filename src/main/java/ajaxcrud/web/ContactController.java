package ajaxcrud.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ajaxcrud.domain.Contact;
import ajaxcrud.service.ContactService;

@Controller
@RequestMapping("contact")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		List<Contact> contacts = contactService.getAll();
		model.addAttribute("contacts", contacts);
		return "list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Contact> findAll(Model model) {
		List<Contact> contacts = contactService.getAll();
		return contacts;
	}
}
