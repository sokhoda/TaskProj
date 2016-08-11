package commands.user;

import interfaces.dao.IUserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Notification;
import bean.User;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class UserUpdateCmd implements ICommand {
	private static final Logger log = LogManager.getLogger(UserUpdateCmd.class
			.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IUserDao userdao = MySqlDaoFactory.getUserDao();
		User user = CommTool.getUser(req, res);

		req.setAttribute("message", new Notification(userdao.update(user) ? 1L
				: 0L, EnumOperations.UPDATE));
		req.setAttribute("user", user);
		return Config.getInstance().getProperty(Config.USEREDIT);
	}
}
