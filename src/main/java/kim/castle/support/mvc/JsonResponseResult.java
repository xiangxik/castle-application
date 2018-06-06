package kim.castle.support.mvc;

public class JsonResponseResult {

	public static JsonResponseResult success(Object data) {
		return new JsonResponseResult(200, data);
	}

	public JsonResponseResult(Integer code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}

	public JsonResponseResult(Integer code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	private Integer code;
	private String message;
	private Object data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
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
}
