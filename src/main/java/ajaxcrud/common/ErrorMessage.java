/**
 * Description: ErrorMessage.java
 * All Rights Reserved.
 * @version 1.0  2013-4-10 上午10:33:06  by 齐磊（qilei@zuche.com）创建
 */
package ajaxcrud.common;

/**
 * Description: 
 * All Rights Reserved.
 * @version 1.0  2013-4-10 上午10:33:06  by 齐磊（qilei@zuche.com）创建
 */
public class ErrorMessage {

	private String fieldName;
	private String message;

	public ErrorMessage(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getMessage() {
		return message;
	}

}
