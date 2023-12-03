package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

	private static ConfigManager manager;
	private static final Properties prop = new Properties();
	
	public ConfigManager() throws IOException {
		
		// Another way
		//ConfigManager.class.getResourceAsStream("filepath");
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\resources\\config.properties");
		prop.load(fis);
	}
	
	public static ConfigManager getInstance() {
		
		// Implementing singleton in a synchronized manner
		if(manager == null) {
			synchronized (ConfigManager.class) {
				try {
					manager = new ConfigManager();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return manager;
	}
	
	public String getString(String key) {
		return System.getProperty(key,prop.getProperty(key));
	}
}
