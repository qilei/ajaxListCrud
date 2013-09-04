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

	// public List<Contact> getAll(int page) {
	// int offset = (page - 1) * 10;
	// int limit = 10;
	// RowBounds rowBounds = new RowBounds(offset, limit);
	// return contactMapper.getAll(rowBounds);
	// }
	//
	// public List<Contact> selectList() {
	// // int offset = 100;
	// // int limit = 25;
	// // RowBounds rowBounds = new RowBounds(offset, limit);
	// // return contactMapper.selectList(statement, parameter, rowBounds)
	// return null;
	// }
	//
	// public DataSet<Contact>
	// findContactWithDatatablesCriterias(DatatablesCriterias criterias) {
	// int offset = criterias.getDisplayStart();
	// int limit = criterias.getDisplaySize();
	// RowBounds rowBounds = new RowBounds(offset, limit);
	// List<Contact> contacts = contactMapper.getAll(rowBounds);
	// Long count = 12L;
	// Integer countFiltered = (Integer) (contacts.size());
	// return new DataSet<Contact>(contacts, count,
	// Long.parseLong(countFiltered.toString()));
	// }

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
