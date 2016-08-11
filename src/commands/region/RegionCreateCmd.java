package commands.region;

import interfaces.service.IRegionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;
import bean.Notification;
import bean.Region;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

public class RegionCreateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(RegionCreateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IRegionService service = ServiceFactory.getRegionService();
		Region region = CommTool.getRegion(req, res);

		req.setAttribute("message", new Notification(service.create(region),
				EnumOperations.CREATE));
		req.setAttribute("region", region);
		return new ShRegionCreateCmd().execute(req, res);
	}
}
