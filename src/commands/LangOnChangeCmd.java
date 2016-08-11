package commands;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LangOnChangeCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(LangOnChangeCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String page = null;
		Locale locale = new Locale(CommTool.getParamString(req, "language"));

		CommTool.setSessionAttr(req,
				"javax.servlet.jsp.jstl.fmt.locale.session", locale);
		CommTool.setBundles(req, locale);

		if (CommTool.getSessionAttr(req, "userlogin") != null) {
			page = Config.MAINMENU;
		} else {
			page = Config.LOGIN;
		}
		return Config.getInstance().getProperty(page);
	}
}
