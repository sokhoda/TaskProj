package commands.document;

import interfaces.dao.IAgentDao;
import interfaces.service.IAgentService;

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

import dao.factory.MySqlDaoFactory;

public class DocSetAgentOnChangeCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(DocSetAgentOnChangeCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IAgentDao agDao = MySqlDaoFactory.getAgentDao();
		IAgentService agService = ServiceFactory.getAgentService();
		Agent agent = null;

		Document document = CommTool.getDocument(req, res);
		String listType = CommTool.getParamString(req, "docselector");
		if (listType.equals("mcagents")) {
			document.setMcId(CommTool.getParamLong(req, "mc"));
		} else if (listType.equals("mgragents")) {
			agent = agDao.read(CommTool.getParamLong(req, "mgr"));
			CommTool.setSessionAttr(req, "mgragent", agent);
			document.setMgrId(agent.getAgId());
		} else if (listType.equals("custagents")) {
			document.setCustId(CommTool.getParamLong(req, "cust"));
		} else {
			log.error(Message.UNKNOWN_AGENT_SELECTOR);
		}

		CommTool.setSessionAttr(req, "document", document);

		return CommTool.getSessionAttrString(req, "srcpage");
	}
}
