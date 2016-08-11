package commands.usertype;

import interfaces.dao.IUserTypeDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.CommTool;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class ShUserTypeListCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShUserTypeListCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IUserTypeDao dao = MySqlDaoFactory.getUserTypeDao();

		String utypenamepattern = CommTool.getParamPut2SessionString(req,
				"utypenamepattern");

		req.setAttribute("utypelist",
				dao.findByNamePattern("%" + utypenamepattern + "%"));
		return Config.getInstance().getProperty(Config.USERTYPELIST);
	}
}
