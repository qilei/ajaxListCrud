/**
 * 
 */
package ajaxcrud.common;


/**
 * Description: ajax请求响应实体 All Rights Reserved.
 * 
 * @version 1.0 2013年8月1日 下午5:15:56 by 齐磊（qilei@zuche.com）创建
 */
public class AjaxResponseEntity<T> {

	private AjaxResponseStatus status;

	private T entity;

	public AjaxResponseEntity(AjaxResponseStatus status) {
		super();
		this.status = status;
	}

	public AjaxResponseEntity(AjaxResponseStatus status, T entity) {
		super();
		this.status = status;
		this.entity = entity;
	}

	public AjaxResponseStatus getStatus() {
		return status;
	}

	public void setStatus(AjaxResponseStatus status) {
		this.status = status;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}


}
