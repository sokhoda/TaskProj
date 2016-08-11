package commands.document;

import interfaces.dao.IDocumentDao;
import interfaces.dao.IJournalDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Notification;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class DocumentDeleteCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(DocumentDeleteCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IDocumentDao docDao = MySqlDaoFactory.getDocumentDao();
		IJournalDao jDao = MySqlDaoFactory.getJournalDao();
		Long docId = CommTool.getParamLong(req, "docId");
		Notification ntf = null;

		jDao.deleteByDocId(docId);
		ntf = new Notification(docDao.deleteById(docId) ? 1L : 0L,
				EnumOperations.DELETE);

		req.setAttribute("message", ntf);
		return new ShDocumentListCmd().execute(req, res);
	}
}
