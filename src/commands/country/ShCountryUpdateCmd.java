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

public class ShCountryUpdateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShCountryUpdateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		ICountryDao dao = MySqlDaoFactory.getCountryDao();
		Long cntrId = CommTool.getParamLong(req, "cntrId");

		CommTool.setSessionAttr(req, "mode", Config.UPDATEMODE);
		req.setAttribute("country", dao.read(cntrId));
		return Config.getInstance().getProperty(Config.COUNTRYEDIT);
	}
}
