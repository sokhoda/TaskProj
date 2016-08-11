package commands.country;

import interfaces.dao.ICountryDao;
import interfaces.service.ICountryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;
import bean.Country;
import bean.Notification;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class CountryCreateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(CountryCreateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		ICountryService cntrService = ServiceFactory.getCountryService();
		ICountryDao dao = MySqlDaoFactory.getCountryDao();

		Country country = CommTool.getCountry(req, res);
		req.setAttribute("message",
				new Notification(cntrService.create(country),
						EnumOperations.CREATE));
		req.setAttribute("country", country);
		CommTool.setSessionAttr(req, "countries", dao.findAll());
		return new ShCountryCreateCmd().execute(req, res);
	}

}
