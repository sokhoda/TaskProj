package commands;

import interfaces.dao.IUserDao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;
import manager.Message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Notification;
import bean.User;
import dao.factory.MySqlDaoFactory;

public class LoginCmd implements ICommand {
	private static final Logger log = LogManager.getLogger(LoginCmd.class
			.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IUserDao userDao = MySqlDaoFactory.getUserDao();
		String page = Config.getInstance().getProperty(Config.LOGIN);

		String userPassword = CommTool.getParamString(req, "userpassword");
		String userLogin = CommTool.getParamString(req, "userlogin");
		List<User> list = userDao.findByLoginAndPassword(userLogin,
				userPassword);
		if (list != null) {
			CommTool.setSessionAttr(req, "userlogin", userLogin);
			page = Config.getInstance().getProperty(Config.MAINMENU);
		} else {
			req.setAttribute("message", new Notification(Message.LOGIN_ERROR,
					Message.DANGER));
		}
		return page;
	}
}
