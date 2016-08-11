package commands.agent;

import interfaces.dao.IAgentDao;
import interfaces.service.IAgentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;
import bean.Notification;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class AgentDeleteCmd implements ICommand {
	private static final Logger log = LogManager.getLogger(AgentDeleteCmd.class
			.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IAgentDao agdao = MySqlDaoFactory.getAgentDao();
		IAgentService agService = ServiceFactory.getAgentService();

		Long agId = CommTool.getParamLong(req, "agId");

		req.setAttribute("message", new Notification(
				agdao.deleteById(agId) ? 1L : 0L, EnumOperations.DELETE));

		String agentagtypefilter = CommTool.getSessionAttrString(req,
				"agentagtypefilter");
		String agentnamepattern = CommTool.getSessionAttrString(req,
				"agentnamepattern");
		req.setAttribute(
				"aglist",
				agService.findByAgTypeAndNamePattern(agentagtypefilter, "%"
						+ agentnamepattern + "%"));
		return Config.getInstance().getProperty(Config.AGENTLIST);
	}
}
