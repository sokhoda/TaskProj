package commands.document;

import interfaces.dao.IAgentDao;
import interfaces.dao.IDocumentDao;
import interfaces.service.IAgentService;
import interfaces.service.IJournalService;
import interfaces.service.IMiscService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;
import bean.Document;

import commands.CommTool;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class ShDocumentUpdateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShDocumentUpdateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IDocumentDao docDao = MySqlDaoFactory.getDocumentDao();
		IAgentDao agDao = MySqlDaoFactory.getAgentDao();
		IAgentService agService = ServiceFactory.getAgentService();
		IMiscService miscService = ServiceFactory.getMiscService();
		IJournalService jService = ServiceFactory.getJournalService();

		Long docId = CommTool.getParamLong(req, "docId");
		String mscIds = jService.getMiscIdByDocId(docId);
		CommTool.setSessionAttrString(req, "numroute", mscIds);

		CommTool.setSessionAttrString(req, "alpharoute",
				miscService.getAlpharoute(mscIds, ","));
		Document document = docDao.read(docId);

		CommTool.setSessionAttr(req, "mgragent",
				agDao.read(document.getMgrId()));
		agService.setAgentList(req, document.getMcId(), "mcagents");
		agService.setAgentList(req, document.getMgrId(), "mgragents");
		agService.setAgentList(req, document.getCustId(), "custagents");

		CommTool.setSessionAttr(req, "document", document);
		CommTool.setSessionAttrString(req, "srcpage", Config.getInstance()
				.getProperty(Config.DOCUMENTUPDATE));

		return Config.getInstance().getProperty(Config.DOCUMENTUPDATE);
	}
}
