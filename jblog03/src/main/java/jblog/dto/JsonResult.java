package jblog.dto;

public class JsonResult {
	private String result;
	private Object data;
	private String message;
	
	public static JsonResult success(Object data) {
		return new JsonResult(data);
	}
	
	public static JsonResult fail(String message) {
		return new JsonResult(message);
	}
	
	private JsonResult(Object data) {
		result = "success";
		this.data = data;
		message = null;
	}
	
	private JsonResult(String message) {
		result = "fail";
		data = null;
		this.message = message;
	}
	
	
	
	public String getResult() {
		return result;
	}
	
	public Object getData() {
		return data;
	}
	
	public String getMessage() {
		return message;
	}
	
	
	
}
