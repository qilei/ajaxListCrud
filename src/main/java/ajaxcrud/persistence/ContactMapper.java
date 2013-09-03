package ajaxcrud.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.RowBounds;

import ajaxcrud.domain.Contact;

public interface ContactMapper {
	@Select("select * from contact")
	@Options(resultSetType = ResultSetType.SCROLL_INSENSITIVE)
	public List<Contact> getAll(RowBounds rowBound);
	
	// public List<Contact> selectList(String statement, Object parameter,
	// RowBounds rowBounds);

}
