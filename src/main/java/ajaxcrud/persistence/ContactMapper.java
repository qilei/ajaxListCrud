package ajaxcrud.persistence;

import java.util.List;
import java.util.Map;

import ajaxcrud.domain.Contact;

public interface ContactMapper {
	// @Select("select * from contact")
	// @Options(resultSetType = ResultSetType.SCROLL_INSENSITIVE)
	// public List<Contact> getAll(RowBounds rowBound);
	
	// public List<Contact> selectList(String statement, Object parameter,
	// RowBounds rowBounds);

	public List<Contact> getContacts(Map map);

	public Integer getContactsCount();

}
