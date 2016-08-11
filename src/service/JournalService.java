package service;

import interfaces.dao.IJournalDao;
import interfaces.service.IJournalService;

import java.util.List;
import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Journal;
import dao.factory.MySqlDaoFactory;

public class JournalService implements IJournalService {
	private static final Logger log = LogManager.getLogger(MiscService.class
			.getName());
	private IJournalDao jDao = MySqlDaoFactory.getJournalDao();

	public JournalService() {
	}

	@Override
	public String getMiscIdByDocId(Long docId) {
		StringBuilder sb = new StringBuilder();
		List<Journal> jlist = jDao.findByDocId(docId);
		if (jlist != null) {
			for (int i = 0; i < jlist.size(); i++) {
				sb.append(jlist.get(i).getMscId()
						+ (i < jlist.size() - 1 ? "," : ""));
			}
		}
		return sb.toString();
	}

	@Override
	public Long saveMisc(String numroute, Long docId) {
		Long mscId = null;
		StringTokenizer tk = null;
		Long res = -1L;
		numroute = numroute == null ? "" : numroute;
		if (docId != null) {
			tk = new StringTokenizer(numroute, ",");
			try {
				while (tk.hasMoreTokens()) {
					mscId = Long.parseLong(tk.nextToken());
					jDao.create(new Journal(0L, docId, mscId));
				}
				res = 1L;
			} catch (NumberFormatException e) {
				log.error(e);
			}
		}
		return res;
	}

}
