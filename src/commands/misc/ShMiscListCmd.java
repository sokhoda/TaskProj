package commands.misc;

import interfaces.dao.IRegionDao;
import interfaces.service.IMiscService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;

import commands.CommTool;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class ShMiscListCmd implements ICommand {
	private static final Logger log = LogManager.getLogger(ShMiscListCmd.class
			.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IRegionDao regDao = MySqlDaoFactory.getRegionDao();
		IMiscService miscService = ServiceFactory.getMiscService();

		CommTool.getParamPut2SessionString(req, "misccountryfilter");
		String miscregionfilter = CommTool.getParamPut2SessionString(req,
				"miscregionfilter");
		String miscnamepattern = CommTool.getParamPut2SessionString(req,
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
