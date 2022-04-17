package com.assignment.dto;

/**
 * @author Shruti.Bodhe
 *
 */

public class ResponseDto {

	private Boolean status;

	private Integer statusCode;

	private String message;

	private Object data;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseDto [status=" + status + ", statusCode=" + statusCode + ", message=" + message + ", data="
				+ data + "]";
	}

}
