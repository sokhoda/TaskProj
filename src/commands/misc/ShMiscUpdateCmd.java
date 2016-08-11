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
import bean.Region;

import commands.CommTool;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class ShMiscUpdateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShMiscUpdateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IMiscDao dao = MySqlDaoFactory.getMiscDao();
		IMiscService miscService = ServiceFactory.getMiscService();

		Long mscId = CommTool.getAndPutParamLong(req, "mscId");
		Misc misc = dao.read(mscId);
		Region region = miscService.findRegionByMisc(misc);
		Long cntrId = region.getCntrId();
		req.setAttribute("regId", region.getRegId());
		req.setAttribute("cntrId", cntrId);
		req.setAttribute("regions", miscService.getCurrentRegions(cntrId));

		CommTool.getAndPutParamString(req, "countryfilter");
		CommTool.getAndPutParamString(req, "miscfilter");
		CommTool.getAndPutParamString(req, "namepattern");

		CommTool.setSessionAttr(req, "mode", Config.UPDATEMODE);
		CommTool.setSessionAttr(req, "misc", misc);
		return Config.getInstance().getProperty(Config.MISCEDIT);
	}
}
