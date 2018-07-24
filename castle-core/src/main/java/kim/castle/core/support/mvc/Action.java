package kim.castle.core.support.mvc;

public class Action {

	public static final int CODE_SUCCESS = 0;
	public static final int CODE_FAILURE = 1;
	public static final int CODE_VALIDATE_ERROR = 2;
	public static final int CODE_NO_PERMISSION = 3;

	public static final String ALERT_SUCCESS = "操作成功";
	public static final String ALERT_FAILURE = "操作成功";

	private int code = CODE_SUCCESS;
	private Type type = Type.alert;
	private Object data;

	public Action() {

	}

	public static Action create(int code, Type type, Object data) {
		Action action = new Action();
		action.code = code;
		action.type = type;
		action.data = data;
		return action;
	}

	public static Action composite(int code, Action... actions) {
		Action action = new Action();
		action.code = code;
		action.type = Type.composite;
		action.data = actions;
		return action;
	}

	public static Action success() {
		return create(CODE_SUCCESS, Type.none, null);
	}

	public static Action successAlert() {
		return create(CODE_SUCCESS, Type.alert, ALERT_SUCCESS);
	}

	public static Action successAlert(String message) {
		return create(CODE_SUCCESS, Type.alert, message);
	}

	public static Action successRedirect(String url) {
		return create(CODE_SUCCESS, Type.redirect, url);
	}

	public static Action successAlertThenRedirect(String message, String url) {
		return composite(CODE_SUCCESS, successAlert(message), successRedirect(url));
	}

	public static Action successAlertThenRedirect(String url) {
		return successAlertThenRedirect(ALERT_SUCCESS, url);
	}

	public static Action failure() {
		return create(CODE_FAILURE, Type.none, null);
	}

	public static Action failureAlert() {
		return create(CODE_FAILURE, Type.alert, ALERT_FAILURE);
	}

	public static Action failureAlert(String message) {
		return create(CODE_FAILURE, Type.alert, message);
	}

	public static Action validateError(Object data) {
		return create(CODE_VALIDATE_ERROR, Type.none, data);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	private enum Type {
		none, alert, redirect, composite
	}
}
