package commands.usertype;

import interfaces.dao.IUserTypeDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.CommTool;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class ShUserTypeUpdateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShUserTypeUpdateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IUserTypeDao dao = MySqlDaoFactory.getUserTypeDao();
		Long uTypeId = CommTool.getParamLong(req, "utypeId");

		log.info("utypeId=" + uTypeId);
		CommTool.setSessionAttr(req, "mode", Config.UPDATEMODE);
		req.setAttribute("utype", dao.read(uTypeId));
		return Config.getInstance().getProperty(Config.USERTYPEEDIT);
	}
}
