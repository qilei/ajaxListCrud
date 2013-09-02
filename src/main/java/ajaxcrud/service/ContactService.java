package ajaxcrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajaxcrud.domain.Contact;
import ajaxcrud.persistence.ContactMapper;

@Service
public class ContactService {
	@Autowired
	private ContactMapper contactMapper;

	public List<Contact> getAll() {
		return contactMapper.getAll();
	}
}
