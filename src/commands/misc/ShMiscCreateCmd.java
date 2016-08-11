package commands.misc;

import interfaces.service.IMiscService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;
import bean.Region;

import commands.CommTool;
import commands.ICommand;

public class ShMiscCreateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShMiscCreateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IMiscService miscService = ServiceFactory.getMiscService();
		Long cntrId = miscService.getCurrentCntrId(req);
		List<Region> regions = miscService.getCurrentRegions(cntrId);

		req.setAttribute("regions", regions);
		req.setAttribute("cntrId", cntrId);

		CommTool.setRouteToSession(req);

		CommTool.setSessionAttr(req, "mode", Config.CREATEMODE);
		return Config.getInstance().getProperty(Config.MISCEDIT);
	}
}
