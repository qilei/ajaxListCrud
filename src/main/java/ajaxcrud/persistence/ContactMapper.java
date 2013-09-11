package ajaxcrud.persistence;

import java.util.List;
import java.util.Map;

import ajaxcrud.domain.Contact;

public interface ContactMapper {

	public List<Contact> getContacts(Map map);

	public Integer getContactsCount();

	public Contact getContactById(Integer id);

	public void insertContact(Contact contact);

	public void updateContact(Contact contact);

	public void deleteContact(Integer id);

}
