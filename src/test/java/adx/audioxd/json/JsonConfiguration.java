package adx.audioxd.json;


import org.apache.commons.lang.Validate;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonConfiguration extends FileConfiguration {
// Global fields
	private static final JSONParser PARSER = new JSONParser();
// End of Global Fields

	private static boolean parse(String json) {
		JSONObject jsonObject;
		try {
			Object obj = PARSER.parse(json);
			if(obj == null) return false;
			if(obj instanceof JSONObject) { jsonObject = (JSONObject) obj; } else { return false; }
			if(jsonObject == null) return false;
		} catch(Exception e) { return false; }
		return true;
	}

	private static JSONObject getJSONObject(String json) {
		try {
			return (JSONObject) PARSER.parse(json);
		} catch(ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String saveToString() {
		String json = "{}";
		return null;
	}

	@Override
	public Object get(String path) {
		return super.get(path);
	}

	@Override
	public void loadFromString(String contents) throws InvalidConfigurationException {
		Validate.notNull(contents, "Contents cannot be null");
		if(!parse(contents)) throw new InvalidConfigurationException("Invalid json file/source");
		JSONObject jO = getJSONObject(contents);

		for(Object keyObject : jO.keySet()) {
			if(!(keyObject instanceof String))
				throw new InvalidConfigurationException("Key must be an instance of a String.");
			String key = (String) keyObject;
			Object value = jO.get(keyObject);
			loadFromObject(key, value);

		}
	}
	private void loadFromObject(String key, Object value) throws InvalidConfigurationException {
		if(value instanceof JSONObject) {
			JSONObject jObj = (JSONObject) value;

			for(Object keyObject : jObj.keySet()) {
				if(!(keyObject instanceof String))
					throw new InvalidConfigurationException("Key must be an instance of a String.");
				String foundKey = (String) keyObject;
				Object foundValue = jObj.get(keyObject);

				loadFromObject(key + "." + foundKey, foundValue);
			}
		} else {
			this.set(key, value);
		}
	}

	@Override
	protected String buildHeader() {
		return null;
	}
}
