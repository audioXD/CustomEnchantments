package adx.audioxd.json;


import org.bukkit.configuration.InvalidConfigurationException;
import org.junit.Test;

public class Main {

	@Test
	public void test() {
		JsonConfiguration jsonConfiguration = new JsonConfiguration();
		try {
			jsonConfiguration.loadFromString(FileUtils.readFile(FileUtils.getFileInJar("test.json")));
		} catch(InvalidConfigurationException e) {
			e.printStackTrace();
			return;
		}

		for(String key : jsonConfiguration.getKeys(false)) {
			if(jsonConfiguration.isConfigurationSection(key)) {
				System.out.println(key + ":");
				for(String key1 : jsonConfiguration.getConfigurationSection(key).getKeys(false)) {
					System.out.println("  " + key1 + ": " + jsonConfiguration.getConfigurationSection(key).getString(key1));
				}
			} else {
				System.out.println(key + ": " + jsonConfiguration.getString(key));
			}
		}
	}
}
