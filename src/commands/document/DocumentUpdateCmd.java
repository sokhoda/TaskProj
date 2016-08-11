package commands.document;

import interfaces.dao.IDocumentDao;
import interfaces.dao.IJournalDao;
import interfaces.service.IDocumentService;
import interfaces.service.IJournalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.DocumentService;
import service.JournalService;
import service.factory.ServiceFactory;
import bean.Document;
import bean.Notification;
import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;
import dao.factory.MySqlDaoFactory;

public class DocumentUpdateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(DocumentUpdateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IDocumentDao docDao = MySqlDaoFactory.getDocumentDao();
		IJournalDao jDao = MySqlDaoFactory.getJournalDao();
		IDocumentService docService = ServiceFactory.getDocumentService();
		IJournalService jService = ServiceFactory.getJournalService();
		Notification ntf = null;

		Document document = CommTool.getDocument(req, res);
		Long docId = document.getDocId();

		String numroute = CommTool.getSessionAttrString(req, "numroute");

		ntf = docService.validate(document);
		if (ntf == null) {
			if (docDao.update(document)) {
				jDao.deleteByDocId(docId);
				ntf = new Notification(jService.saveMisc(numroute, docId),
						EnumOperations.UPDATE);
			} else {
				ntf = new Notification(docId, EnumOperations.UPDATE);
			}
		}
		req.setAttribute("message", ntf);

		CommTool.setSessionAttr(req, "document", document);
		return Config.getInstance().getProperty(Config.DOCUMENTUPDATE);
	}
}
