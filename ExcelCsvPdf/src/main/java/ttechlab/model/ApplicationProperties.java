package ttechlab.model;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ApplicationProperties {

	private static final String BUNDLE_NAME = "application";
	private static final ResourceBundle RESOURCE_BUNDLE=ResourceBundle.getBundle(BUNDLE_NAME);
	
	public static String getValue(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key).trim();
		}catch (MissingResourceException e) {
			e.printStackTrace();
			return '@'+key+'@';
		}
	}
}
