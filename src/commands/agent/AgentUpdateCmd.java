package commands.agent;

import interfaces.dao.IAgentDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Agent;
import bean.Notification;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class AgentUpdateCmd implements ICommand {
	private static final Logger log = LogManager.getLogger(AgentUpdateCmd.class
			.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IAgentDao dao = MySqlDaoFactory.getAgentDao();
		Agent agent = CommTool.getAgent(req, res);

		req.setAttribute("message", new Notification(dao.update(agent) ? 1L
				: 0L, EnumOperations.UPDATE));
		req.setAttribute("agent", agent);
		return Config.getInstance().getProperty(Config.AGENTEDIT);
	}
}
