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

public class OnMultipleCountryChangeCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(OnMultipleCountryChangeCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IMiscService miscService = ServiceFactory.getMiscService();

		String misccountryfilter = CommTool.getParamString(req,
				"misccountryfilter");
		List<Region> regions = miscService
				.findRegionsByMultipleCntrId(misccountryfilter);

		// req.setAttribute("regions", regions);
		CommTool.setSessionAttr(req, "regions", regions);
		req.setAttribute("misccountryfilter", misccountryfilter);
		req.setAttribute("miscregionfilter", null);

		CommTool.getParamPut2SessionString(req, "miscnamepattern");

		return Config.getInstance().getProperty(Config.MISCFILTER);
	}
}
