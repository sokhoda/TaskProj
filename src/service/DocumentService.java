package service;

import interfaces.dao.IDocumentDao;
import interfaces.service.IDocumentService;

import java.util.List;

import manager.Message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Document;
import bean.Notification;
import dao.factory.MySqlDaoFactory;

public class DocumentService implements IDocumentService {
	private static final Logger log = LogManager
			.getLogger(DocumentService.class.getName());
	private IDocumentDao dao = MySqlDaoFactory.getDocumentDao();

	public DocumentService() {
	}

	@Override
	public Long create(Document document) {
		IDocumentDao dao = MySqlDaoFactory.getDocumentDao();
		String docNo = document.getDocNo();
		Long res = -1L;

		if (docNo.length() != 0) {
			if (dao.findByDocNo(docNo) == null) {
				res = dao.create(document);
			} else {
				log.error(docNo
						+ ':'
						+ Message.getInstance()
						.getProperty(Message.NAME_EXISTS));
			}
		} else {
			log.error(Message.getInstance().getProperty(
					Message.NAME_ZERO_LENGTH));
		}
		return res;
	}

	@Override
	public Notification validate(Document doc) {
		StringBuilder sb = new StringBuilder();
		Notification ntf = null;
		if (doc.getMgrId() == null) {
			sb.append(Message.MANAGER_MISSING);
		}
		if (doc.getDocDate() == null) {
			sb.append(Message.DOCDATE_MISSING);
		}
		if (doc.getDocStatus() == null) {
			sb.append(Message.STATUS_MISSING);
		}
		if (doc.getDocNo().length() == 0) {
			sb.append(Message.DOCNO_MISSING);
		}

		if (sb.length() > 0) {
			ntf = new Notification(sb.toString(), Message.DANGER);
		}
		return ntf;
	}

	@Override
	public List<Document> findByDocStatAndDocNoPattAndCargoPatt(
			String documentfilter, String docnopattern, String cargopattern) {
		return dao.findByDocStatAndDocNoPattAndCargoPatt(documentfilter,
				docnopattern, cargopattern);
	}

	@Override
	public String getAutoDocNo() {
		Long maxDocNo = dao.findLastDocNo();
		return maxDocNo != null ? String.valueOf(++maxDocNo) : "";
	}
}
