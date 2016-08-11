package commands.usertype;

import interfaces.dao.IUserTypeDao;

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

public class UserTypeDeleteCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(UserTypeDeleteCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IUserTypeDao dao = MySqlDaoFactory.getUserTypeDao();
		Long uTypeId = CommTool.getParamLong(req, "utypeId");

		req.setAttribute("message", new Notification(
				dao.deleteById(uTypeId) ? 1L : 0L, EnumOperations.DELETE));
		CommTool.setSessionAttr(req, "utypes", dao.findAll());

		String utypenamepattern = CommTool.getSessionAttrString(req,
				"utypenamepattern");

		req.setAttribute("utypelist",
				dao.findByNamePattern("%" + utypenamepattern + "%"));
		return Config.getInstance().getProperty(Config.USERTYPELIST);

	}
}
