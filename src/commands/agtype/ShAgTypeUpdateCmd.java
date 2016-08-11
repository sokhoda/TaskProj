package commands.agtype;

import interfaces.dao.IAgTypeDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.CommTool;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class ShAgTypeUpdateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShAgTypeUpdateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IAgTypeDao agtdao = MySqlDaoFactory.getAgTypeDao();

		Long agtId = CommTool.getParamLong(req, "agtId");

		CommTool.setSessionAttr(req, "mode", Config.UPDATEMODE);
		req.setAttribute("agtype", agtdao.read(agtId));
		return Config.getInstance().getProperty(Config.AGTYPEEDIT);
	}
}
