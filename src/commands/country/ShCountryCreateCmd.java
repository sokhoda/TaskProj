package commands.country;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.CommTool;
import commands.ICommand;

public class ShCountryCreateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShCountryCreateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		CommTool.setSessionAttr(req, "mode", Config.CREATEMODE);
		return Config.getInstance().getProperty(Config.COUNTRYEDIT);
	}

}
