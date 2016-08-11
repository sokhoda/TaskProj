package commands.country;

import interfaces.dao.ICountryDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Country;
import bean.Notification;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class CountryUpdateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(CountryUpdateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		ICountryDao dao = MySqlDaoFactory.getCountryDao();

		Country country = CommTool.getCountry(req, res);
		req.setAttribute("message", new Notification(dao.update(country) ? 1L
				: 0L, EnumOperations.UPDATE));
		req.setAttribute("country", country);

		CommTool.setSessionAttr(req, "countries", dao.findAll());
		return Config.getInstance().getProperty(Config.COUNTRYEDIT);
	}
}
