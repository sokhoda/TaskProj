package commands.document;

import interfaces.service.IDocumentService;
import interfaces.service.IJournalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;
import bean.Document;
import bean.Notification;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

public class DocumentCreateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(DocumentCreateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IDocumentService docService = ServiceFactory.getDocumentService();
		IJournalService jService = ServiceFactory.getJournalService();
		Notification ntf = null;
		Long docId = null;
		String numroute = CommTool.getSessionAttrString(req, "numroute");

		Document document = CommTool.getDocument(req, res);
		CommTool.setSessionAttr(req, "document", document);

		ntf = docService.validate(document);
		if (ntf == null) {
			docId = docService.create(document);
			if (docId > -1) {
				ntf = new Notification(jService.saveMisc(numroute, docId),
						EnumOperations.CREATE);
			} else {
				ntf = new Notification(docId, EnumOperations.CREATE);
			}
		}
		req.setAttribute("message", ntf);
		return Config.getInstance().getProperty(Config.DOCUMENTCREATE);
	}
}
