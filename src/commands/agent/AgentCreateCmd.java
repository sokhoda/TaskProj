package commands.agent;

import interfaces.service.IAgentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;
import bean.Agent;
import bean.Notification;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

public class AgentCreateCmd implements ICommand {
	private static final Logger log = LogManager.getLogger(AgentCreateCmd.class
			.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IAgentService agService = ServiceFactory.getAgentService();

		Agent agent = CommTool.getAgent(req, res);
		req.setAttribute("message", new Notification(agService.create(agent),
				EnumOperations.CREATE));
		req.setAttribute("agent", agent);
		return new ShAgentCreateCmd().execute(req, res);
	}
}
