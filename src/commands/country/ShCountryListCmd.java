package commands.country;

import interfaces.dao.ICountryDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.CommTool;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class ShCountryListCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShCountryListCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		ICountryDao dao = MySqlDaoFactory.getCountryDao();

		String countrynamepattern = CommTool.getParamPut2SessionString(req,
				"countrynamepattern");
		req.setAttribute("countrylist",
				dao.findByNamePattern("%" + countrynamepattern + "%"));
		return Config.getInstance().getProperty(Config.COUNTRYLIST);
	}

}
