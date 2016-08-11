package commands.user;

import interfaces.dao.IUserDao;
import interfaces.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;
import bean.Notification;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class UserDeleteCmd implements ICommand {
	private static final Logger log = LogManager.getLogger(UserDeleteCmd.class
			.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IUserDao userDao = MySqlDaoFactory.getUserDao();
		IUserService userService = ServiceFactory.getUserService();

		Long userId = CommTool.getAndPutParamLong(req, "userId");

		req.setAttribute("message", new Notification(
				userDao.deleteById(userId) ? 1L : 0L, EnumOperations.DELETE));

		String userutypefilter = CommTool.getSessionAttrString(req,
				"userutypefilter"); // utypeids
		String userloginpattern = CommTool.getSessionAttrString(req,
				"userloginpattern");
		req.setAttribute(
				"userlist",
				userService.findByUTypeAndNamePattern(userutypefilter, "%"
						+ userloginpattern + "%"));
		return Config.getInstance().getProperty(Config.USERLIST);

	}
}
