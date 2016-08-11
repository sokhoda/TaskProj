package commands.misc;

import interfaces.dao.IMiscDao;
import interfaces.service.IMiscService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;
import bean.Misc;
import bean.Notification;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class MiscUpdateCmd implements ICommand {
	private static final Logger log = LogManager.getLogger(MiscUpdateCmd.class
			.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IMiscDao dao = MySqlDaoFactory.getMiscDao();
		IMiscService miscService = ServiceFactory.getMiscService();
		Misc misc = CommTool.getMisc(req, res);

		Long cntrId = CommTool.getAndPutParamLong(req, "cntrId");
		CommTool.getAndPutParamLong(req, "regId");

		req.setAttribute("regions", miscService.getCurrentRegions(cntrId));

		req.setAttribute("message", new Notification(
				dao.update(misc) ? 1L : 0L, EnumOperations.UPDATE));
		CommTool.setSessionAttr(req, "misc", misc);
		return Config.getInstance().getProperty(Config.MISCEDIT);
	}
}
