package manager;

import java.util.ResourceBundle;

public class Message {
	private static Message instance = null;
	private ResourceBundle rb;
	public static final String BUNDLENAME = "i18n.Message";
	public static final String SERVLET_EXECPTION = "SERVLET_EXCEPTION";
	public static final String IO_EXCEPTION = "IO_EXCEPTION";
	public static final String LOGIN_ERROR = "LOGIN_ERROR";
	public static final String CREATION_SUCCESS = "CREATION_SUCCESS";
	public static final String CREATION_FAIL = "CREATION_FAIL";
	public static final String UPDATE_SUCCESS = "UPDATE_SUCCESS";
	public static final String UPDATE_FAIL = "UPDATE_FAIL";
	public static final String DELETE_SUCCESS = "DELETE_SUCCESS";
	public static final String DELETE_FAIL = "DELETE_FAIL";
	public static final String LOGIN_PASS_EXIST = "LOGIN_PASS_EXIST";
	public static final String NAME_EXISTS = "NAME_EXISTS";
	public static final String NAME_ZERO_LENGTH = "NAME_ZERO_LENGTH";
	public static final String PASS_ZERO_LENGTH = "PASS_ZERO_LENGTH";
	public static final String LOGIN_ZERO_LENGTH = "LOGIN_ZERO_LENGTH";
	public static final String SUCCESS = "success";
	public static final String WARNING = "warning";
	public static final String DANGER = "danger";
	public static final String MANAGER_MISSING = "MANAGER_MISSING";
	public static final String DOCDATE_MISSING = "DOCDATE_MISSING";
	public static final String STATUS_MISSING = "STATUS_MISSING";
	public static final String DOCNO_MISSING = "DOCNO_MISSING";
	public static final String UNKNOWN_AGENT_SELECTOR = "UNKNOWN_AGENT_SELECTOR";
	public static final String FAILED_2UPDATE_JOURNAL = "FAILED_2UPDATE_JOURNAL";
	public static final String FAILED_2DELETE_JOURNAL = "FAILED_2DELETE_JOURNAL";
	public static final String DOCNO_AUTONUM_GETLAST_FAIL = "DOCNO_AUTONUM_GETLAST_FAIL";

	private void Message() {
	}

	public static Message getInstance() {
		if (instance == null) {
			instance = new Message();
			instance.rb = ResourceBundle.getBundle(BUNDLENAME);
		}
		return instance;
	}

	public String getProperty(String key) {
		return (String) rb.getObject(key);
	}
}
