package ajaxcrud.common;

import org.codehaus.jackson.annotate.JsonProperty;

public class JtableJsonResponse<T> {
	@JsonProperty("Result")
	private String result;

	@JsonProperty("Records")
	private T records;

	@JsonProperty("TotalRecordCount")
	private Integer totalRecordCount;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public T getRecords() {
		return records;
	}

	public void setRecords(T records) {
		this.records = records;
	}

	public Integer getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(Integer totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

}
