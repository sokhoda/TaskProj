package commands.region;

import interfaces.dao.IRegionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.CommTool;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class ShRegionUpdateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShRegionUpdateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IRegionDao dao = MySqlDaoFactory.getRegionDao();
		Long regId = CommTool.getParamLong(req, "regId");

		CommTool.setSessionAttr(req, "mode", Config.UPDATEMODE);
		req.setAttribute("region", dao.read(regId));
		return Config.getInstance().getProperty(Config.REGIONEDIT);
	}
}
