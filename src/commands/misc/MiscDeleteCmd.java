package commands.misc;

import interfaces.dao.IMiscDao;
import interfaces.dao.IRegionDao;
import interfaces.service.IMiscService;

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

public class MiscDeleteCmd implements ICommand {
	private static final Logger log = LogManager.getLogger(MiscDeleteCmd.class
			.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IMiscService miscService = ServiceFactory.getMiscService();
		IMiscDao dao = MySqlDaoFactory.getMiscDao();
		IRegionDao regDao = MySqlDaoFactory.getRegionDao();

		Long mscId = CommTool.getParamLong(req, "mscId");
		req.setAttribute("message", new Notification(dao.deleteById(mscId) ? 1L
				: 0L, EnumOperations.DELETE));

		String miscregionfilter = CommTool.getSessionAttrString(req,
				"miscregionfilter");
		String miscnamepattern = CommTool.getSessionAttrString(req,
				"miscnamepattern");
		req.setAttribute(
				"misclist",
				miscService.findRegionAndNamePattern(miscregionfilter, "%"
						+ miscnamepattern + "%"));

		req.setAttribute("regions", regDao.findAll());

		CommTool.removeSessionAttr(req, "misc");
		return Config.getInstance().getProperty(Config.MISCLIST);
	}
}
