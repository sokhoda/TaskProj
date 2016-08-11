package commands.user;

import interfaces.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;

import commands.CommTool;
import commands.ICommand;

public class ShUserListCmd implements ICommand {
	private static final Logger log = LogManager.getLogger(ShUserListCmd.class
			.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IUserService userService = ServiceFactory.getUserService();

		String userutypefilter = CommTool.getParamPut2SessionString(req,
				"userutypefilter"); // utypeids
		String userloginpattern = CommTool.getParamPut2SessionString(req,
				"userloginpattern");
		req.setAttribute(
				"userlist",
				userService.findByUTypeAndNamePattern(userutypefilter, "%"
						+ userloginpattern + "%"));
		return Config.getInstance().getProperty(Config.USERLIST);
	}
}
