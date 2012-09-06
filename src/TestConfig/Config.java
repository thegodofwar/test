package TestConfig;

import java.io.InputStream;
import java.util.Properties;

public class Config {
   
	public static void main(String args[]) {
		System.out.println(getValueByKey("kanzww"));
	}
	
	public static String getValueByKey(String key) {
		InputStream in = null;
		in = Config.class.getClassLoader()
				.getResourceAsStream(
						"siteInfoConfig/siteInfo." + key
								+ ".properties");
		Properties siteConfigProperties = new Properties();
		try {
			siteConfigProperties.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siteConfigProperties.getProperty("class.htmlParser");
	}
	
}
