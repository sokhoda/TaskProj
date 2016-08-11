package commands.country;

import interfaces.dao.ICountryDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Notification;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class CountryDeleteCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(CountryDeleteCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		ICountryDao dao = MySqlDaoFactory.getCountryDao();

		Long cntrId = CommTool.getParamLong(req, "cntrId");
		req.setAttribute("message", new Notification(
				dao.deleteById(cntrId) ? 1L : 0L, EnumOperations.DELETE));
		CommTool.setSessionAttr(req, "countries", dao.findAll());

		String countrynamepattern = CommTool.getSessionAttrString(req,
				"countrynamepattern");
		req.setAttribute("countrylist",
				dao.findByNamePattern("%" + countrynamepattern + "%"));
		return Config.getInstance().getProperty(Config.COUNTRYLIST);
	}
}
