package commands.agent;

import interfaces.dao.IAgentDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.CommTool;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class ShAgentUpdateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShAgentUpdateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IAgentDao agdao = MySqlDaoFactory.getAgentDao();

		Long agId = CommTool.getParamLong(req, "agId");

		CommTool.setSessionAttr(req, "mode", Config.UPDATEMODE);
		req.setAttribute("agent", agdao.read(agId));
		return Config.getInstance().getProperty(Config.AGENTEDIT);
	}
}
