package commands.country;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.ICommand;

public class ShCountryFilterCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShCountryFilterCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		return Config.getInstance().getProperty(Config.COUNTRYFILTER);
	}

}
