package commands.misc;

import interfaces.service.IMiscService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;
import bean.Misc;
import bean.Notification;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

public class MiscCreateCmd implements ICommand {
	private static final Logger log = LogManager.getLogger(MiscCreateCmd.class
			.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IMiscService miscService = ServiceFactory.getMiscService();

		Misc misc = CommTool.getMisc(req, res);
		CommTool.getAndPutParamLong(req, "cntrId");
		CommTool.getAndPutParamLong(req, "regId");

		req.setAttribute("message", new Notification(miscService.create(misc),
				EnumOperations.CREATE));
		req.setAttribute("misc", misc);
		return new ShMiscCreateCmd().execute(req, res);
	}
}
