package commands.region;

import interfaces.dao.IRegionDao;
import interfaces.service.IRegionService;

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

public class RegionDeleteCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(RegionDeleteCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IRegionService regService = ServiceFactory.getRegionService();
		IRegionDao dao = MySqlDaoFactory.getRegionDao();
		Long regId = CommTool.getParamLong(req, "regId");

		req.setAttribute("message", new Notification(dao.deleteById(regId) ? 1L
				: 0L, EnumOperations.DELETE));

		String regioncountryfilter = CommTool.getSessionAttrString(req,
				"regioncountryfilter");
		String regionnamepattern = CommTool.getSessionAttrString(req,
				"regionnamepattern");
		req.setAttribute(
				"regionlist",
				regService.findByAgTypeAndNamePattern(regioncountryfilter, "%"
						+ regionnamepattern + "%"));

		return Config.getInstance().getProperty(Config.REGIONLIST);
	}
}
