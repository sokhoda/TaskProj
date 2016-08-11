package commands.document;

import interfaces.service.IAgentService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;
import bean.Agent;
import bean.Document;

import commands.CommTool;
import commands.ICommand;

public class DocInitDocSelectorCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(DocInitDocSelectorCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IAgentService agService = ServiceFactory.getAgentService();

		String listType = CommTool.getSessionAttrString(req, "docselector");

		String agentagtypefilter = CommTool.getParamString(req,
				"agentagtypefilter");
		String agentnamepattern = CommTool.getParamString(req,
				"agentnamepattern");

		List<Agent> list = agService.findByAgTypeAndNamePattern(
				agentagtypefilter, "%" + agentnamepattern + "%");
		CommTool.setSessionAttr(req, listType, list);
		Document document = (Document) CommTool.getSessionAttr(req, "document");
		Agent agent = (list != null ? list.get(0) : null);
		Long id = (agent != null ? agent.getAgId() : null);

		if (listType.equals("mcagents")) {
			document.setMcId(id);
		} else if (listType.equals("mgragents")) {
			CommTool.setSessionAttr(req, "mgragent", agent);
			document.setMgrId(id);
		} else if (listType.equals("custagents")) {
			document.setCustId(id);
		} else {
			log.error(Message.UNKNOWN_AGENT_SELECTOR);
		}
		CommTool.setSessionAttr(req, "document", document);

		return CommTool.getSessionAttrString(req, "srcpage");
	}
}
