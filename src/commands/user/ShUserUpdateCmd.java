package commands.user;

import interfaces.dao.IUserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.CommTool;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class ShUserUpdateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShUserUpdateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IUserDao userdao = MySqlDaoFactory.getUserDao();
		Long userId = CommTool.getAndPutParamLong(req, "userId");

		CommTool.setSessionAttr(req, "mode", Config.UPDATEMODE);
		req.setAttribute("user", userdao.read(userId));
		return Config.getInstance().getProperty(Config.USEREDIT);
	}
}
