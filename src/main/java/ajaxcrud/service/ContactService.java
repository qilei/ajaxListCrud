package ajaxcrud.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajaxcrud.domain.Contact;
import ajaxcrud.persistence.ContactMapper;

@Service
public class ContactService {
	@Autowired
	private ContactMapper contactMapper;

	public List<Contact> getContacts(int startIndex, int recordCount) {
		Map map = new HashMap<String, Object>();
		map.put("startIndex", startIndex);
		map.put("recordCount", recordCount);
		return contactMapper.getContacts(map);
	}

	public Integer getContactsCount() {
		return contactMapper.getContactsCount();
	}

}
