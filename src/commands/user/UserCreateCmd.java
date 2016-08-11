package commands.user;

import interfaces.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;
import bean.Notification;
import bean.User;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

public class UserCreateCmd implements ICommand {
	private static final Logger log = LogManager.getLogger(UserCreateCmd.class
			.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IUserService userService = ServiceFactory.getUserService();
		User user = CommTool.getUser(req, res);

		req.setAttribute("message", new Notification(userService.create(user),
				EnumOperations.CREATE));
		req.setAttribute("user", user);
		return new ShUserCreateCmd().execute(req, res);
	}
}
