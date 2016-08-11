package commands.region;

import interfaces.dao.IRegionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Notification;
import bean.Region;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class RegionUpdateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(RegionUpdateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IRegionDao dao = MySqlDaoFactory.getRegionDao();
		Region region = CommTool.getRegion(req, res);

		req.setAttribute("message", new Notification(dao.update(region) ? 1L
				: 0L, EnumOperations.UPDATE));
		req.setAttribute("region", region);
		return Config.getInstance().getProperty(Config.REGIONEDIT);
	}
}
