package kim.castle.support.data;

import java.util.HashMap;

public class DataWrapper extends HashMap<String, Object> {

	private static final long serialVersionUID = -1374730336518083602L;

	public static DataWrapper instance() {
		return new DataWrapper();
	}

	public DataWrapper v(String key, Object value) {
		put(key, value);
		return this;
	}
}
