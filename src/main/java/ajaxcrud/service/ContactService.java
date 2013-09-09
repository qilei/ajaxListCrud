package ajaxcrud.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajaxcrud.domain.Contact;
import ajaxcrud.domain.Sequence;
import ajaxcrud.persistence.ContactMapper;
import ajaxcrud.persistence.SequenceMapper;

@Service
public class ContactService {
	@Autowired
	private ContactMapper contactMapper;
	@Autowired
	private SequenceMapper sequenceMapper;

	public List<Contact> getContacts(int startIndex, int recordCount) {
		Map map = new HashMap<String, Object>();
		map.put("startIndex", startIndex);
		map.put("recordCount", recordCount);
		return contactMapper.getContacts(map);
	}

	public Integer getContactsCount() {
		return contactMapper.getContactsCount();
	}

	public void insertContact(Contact contact) {
		contact.setId(this.getNextId("contactid"));
		contactMapper.insertContact(contact);
	}

	public int getNextId(String name) {
		Sequence sequence = new Sequence(name, -1);
		sequence = (Sequence) sequenceMapper.getSequence(sequence);
		if (sequence == null) {
			throw new RuntimeException("Error: A null sequence was returned from the database (could not get next "
					+ name + " sequence).");
		}
		Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
		sequenceMapper.updateSequence(parameterObject);
		return sequence.getNextId();
	}

}
