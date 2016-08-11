package commands.agent;

import interfaces.service.IAgentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;

import commands.CommTool;
import commands.ICommand;

public class ShAgentListCmd implements ICommand {
	private static final Logger log = LogManager.getLogger(ShAgentListCmd.class
			.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IAgentService agService = ServiceFactory.getAgentService();

		String agentagtypefilter = CommTool.getParamPut2SessionString(req,
				"agentagtypefilter");
		String agentnamepattern = CommTool.getParamPut2SessionString(req,
				"agentnamepattern");
		// log.info("agentagtypefilter = " + agentagtypefilter
		// + "\n agentnamepattern=" + agentnamepattern);
		req.setAttribute(
				"aglist",
				agService.findByAgTypeAndNamePattern(agentagtypefilter, "%"
						+ agentnamepattern + "%"));
		return Config.getInstance().getProperty(Config.AGENTLIST);
	}
}
