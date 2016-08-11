package commands.region;

import interfaces.service.IRegionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;

import commands.CommTool;
import commands.ICommand;

public class ShRegionListCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShRegionListCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IRegionService regService = ServiceFactory.getRegionService();

		String regioncountryfilter = CommTool.getParamPut2SessionString(req,
				"regioncountryfilter");
		String regionnamepattern = CommTool.getParamPut2SessionString(req,
				"regionnamepattern");
		req.setAttribute(
				"regionlist",
				regService.findByAgTypeAndNamePattern(regioncountryfilter, "%"
						+ regionnamepattern + "%"));

		return Config.getInstance().getProperty(Config.REGIONLIST);
	}
}
