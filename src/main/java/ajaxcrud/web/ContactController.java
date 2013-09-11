package ajaxcrud.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ajaxcrud.common.AjaxResponseEntity;
import ajaxcrud.common.AjaxResponseStatus;
import ajaxcrud.common.ErrorMessage;
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

	@RequestMapping(value = "/contact/add", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
		model.addAttribute("contact", new Contact());
		return "edit";
	}

	@RequestMapping(value = "/contact/add", method = RequestMethod.POST)
	public @ResponseBody
	AjaxResponseEntity processCreationForm(Contact contact, BindingResult bindingResult) {
		// 验证
		if ("".equals(contact.getName().trim())) {
			List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
			errorMessages.add(new ErrorMessage("name", "姓名必填！"));
			return new AjaxResponseEntity<List<ErrorMessage>>(AjaxResponseStatus.VALIDATION_FAIL, errorMessages);
		}
		try {
			contactService.insertContact(contact);
			return new AjaxResponseEntity(AjaxResponseStatus.SUCCESS);
		} catch (Exception e) {
			return new AjaxResponseEntity<String>(AjaxResponseStatus.EXCEPTION, e.getMessage());
		}
	}

	@RequestMapping(value = "/contact/edit", method = RequestMethod.GET)
	public String initUpdateForm(Integer id, Model model) {
		Contact contact = contactService.getContactById(id);
		model.addAttribute("contact", contact);
		return "edit";
	}

	@RequestMapping(value = "/contact/edit", method = RequestMethod.POST)
	public @ResponseBody
	AjaxResponseEntity processUpdateForm(Contact contact, BindingResult bindingResult) {
		try {
			contactService.updateContact(contact);
			return new AjaxResponseEntity(AjaxResponseStatus.SUCCESS);
		} catch (Exception e) {
			return new AjaxResponseEntity<String>(AjaxResponseStatus.EXCEPTION, e.getMessage());
		}
	}

	@RequestMapping(value = "/contact/delete", method = RequestMethod.POST)
	public @ResponseBody
	AjaxResponseEntity delete(Integer id) {
		try {
			contactService.deleteContact(id);
			return new AjaxResponseEntity(AjaxResponseStatus.SUCCESS);
		} catch (Exception e) {
			return new AjaxResponseEntity<String>(AjaxResponseStatus.EXCEPTION, e.getMessage());
		}
	}
}
