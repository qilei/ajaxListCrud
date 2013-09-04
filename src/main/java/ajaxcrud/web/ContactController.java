package ajaxcrud.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ajaxcrud.common.JtableJsonResponse;
import ajaxcrud.domain.Contact;
import ajaxcrud.service.ContactService;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		return "list";
	}

	@RequestMapping(value = "/contact/list")
	@ResponseBody
	public JtableJsonResponse<Object> findAll(HttpServletRequest request, Model model, Integer jtStartIndex,
			Integer jtPageSize) {
		List<Contact> contacts = contactService.getContacts(jtStartIndex, jtPageSize);
		int count = contactService.getContactsCount();
		JtableJsonResponse<Object> responseEntity = new JtableJsonResponse<Object>();
		responseEntity.setResult("OK");
		responseEntity.setRecords(contacts);
		responseEntity.setTotalRecordCount(count);
		return responseEntity;
	}
}
