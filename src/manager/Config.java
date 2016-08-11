package manager;

import java.io.File;
import java.util.ResourceBundle;

public class Config {
	private static Config instance = null;
	private ResourceBundle rb;

	public static final String BUNDLENAME = "i18n.Config";
	public static final String MAINMENUBUNDLENAME = "i18n.MainMenu";

	public static final String i18N = "i18n";
	public static final String MAINMENUBUNDLE = "MainMenu";
	public static final String MESSAGEBUNDLE = Message.class.getSimpleName();
	public static final String i18RESOURCEPATH = "resources" + File.separator
			+ "i18n";
	public static final String PROPERTIES = "properties";
	public static final String DRIVER = "DRIVER";
	public static final String URL = "URL";
	public static final int CONNECTION_POOL_SIZE = 10;

	public static final String CONNECTION_UNAVAILABLE = "CONNECTION_UNAVAILABLE";
	public static final String ERROR = "ERROR";
	public static final String LOGIN = "LOGIN";
	public static final String INDEX = "INDEX";
	public static final String MAINMENU = "MAINMENU";
	public static final String ASC = "ASC";
	public static final int DEFAULTMODE = 10;
	public static final int CREATEMODE = 0;
	public static final int UPDATEMODE = 1;
	public static final int SELECTMODE = 2;

	// agent
	public static final String AGENTLIST = "AGENTLIST";
	public static final String AGENTEDIT = "AGENTEDIT";
	public static final String AGENTFILTER = "AGENTFILTER";
	// agtype
	public static final String AGTYPELIST = "AGTYPELIST";
	public static final String AGTYPEEDIT = "AGTYPEEDIT";
	public static final String AGTYPEFILTER = "AGTYPEFILTER";
	// country
	public static final String COUNTRYLIST = "COUNTRYLIST";
	public static final String COUNTRYEDIT = "COUNTRYEDIT";
	public static final String COUNTRYFILTER = "COUNTRYFILTER";
	// region
	public static final String REGIONLIST = "REGIONLIST";
	public static final String REGIONEDIT = "REGIONEDIT";
	public static final String REGIONFILTER = "REGIONFILTER";
	// misc
	public static final int MISCCITYNO = 1;
	public static final String MISCLIST = "MISCLIST";
	public static final String MISCEDIT = "MISCEDIT";
	public static final String MISCFILTER = "MISCFILTER";
	// user
	public static final String USERLIST = "USERLIST";
	public static final String USEREDIT = "USEREDIT";
	public static final String USERFILTER = "USERFILTER";
	// usertype
	public static final String USERTYPELIST = "USERTYPELIST";
	public static final String USERTYPEEDIT = "USERTYPEEDIT";
	public static final String USERTYPEFILTER = "USERTYPEFILTER";
	// cargo
	public static final String CARGOLIST = "CARGOLIST";
	public static final String CARGOEDIT = "CARGOEDIT";
	public static final String CARGOFILTER = "CARGOFILTER";
	// document
	public static final String DOCUMENTLIST = "DOCUMENTLIST";
	public static final String DOCUMENTCREATE = "DOCUMENTCREATE";
	public static final String DOCUMENTUPDATE = "DOCUMENTUPDATE";
	public static final String DOCUMENTFILTER = "DOCUMENTFILTER";

	private void Config() {
	}

	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
			instance.rb = ResourceBundle.getBundle(BUNDLENAME);
		}
		return instance;
	}

	public String getProperty(String key) {
		return (String) rb.getObject(key);
	}
}
