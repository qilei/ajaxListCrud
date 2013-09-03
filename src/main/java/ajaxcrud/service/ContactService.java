package ajaxcrud.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ajaxcrud.domain.Contact;
import ajaxcrud.persistence.ContactMapper;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;

@Service
public class ContactService {
	@Autowired
	private ContactMapper contactMapper;

	public List<Contact> getAll(int page) {
		int offset = (page - 1) * 10;
		int limit = 10;
		RowBounds rowBounds = new RowBounds(offset, limit);
		return contactMapper.getAll(rowBounds);
	}

	public List<Contact> selectList() {
		// int offset = 100;
		// int limit = 25;
		// RowBounds rowBounds = new RowBounds(offset, limit);
		// return contactMapper.selectList(statement, parameter, rowBounds)
		return null;
	}

	public DataSet<Contact> findContactWithDatatablesCriterias(DatatablesCriterias criterias) {
		int offset = criterias.getDisplayStart();
		int limit = criterias.getDisplaySize();
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Contact> contacts = contactMapper.getAll(rowBounds);
		Long count = 12L;
		Integer countFiltered = (Integer) (contacts.size());
		return new DataSet<Contact>(contacts, count, Long.parseLong(countFiltered.toString()));
	}

}
