package commands.usertype;

import interfaces.dao.IUserTypeDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Notification;
import bean.UserType;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class UserTypeUpdateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(UserTypeUpdateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IUserTypeDao dao = MySqlDaoFactory.getUserTypeDao();
		UserType uType = CommTool.getUserType(req, res);

		req.setAttribute("message", new Notification(dao.update(uType) ? 1L
				: 0L, EnumOperations.UPDATE));
		req.setAttribute("utype", uType);
		CommTool.setSessionAttr(req, "utypes", dao.findAll());
		return Config.getInstance().getProperty(Config.USERTYPEEDIT);
	}
}
