package _runner;

import bean.Journal;
import interfaces.dao.IJournalDao;
import dao.factory.MySqlDaoFactory;

;

public class DaoJournalRunner {
	public static void main(String[] args) {
		int mscNo = 1;
		Journal journal = new Journal(82L, 30L, 371L);
		// BaseDaoFactory baseFactory =
		// DaoFactory.getFactory(DriverTypes.MYSQL);
		// IAgentDao agentDao = baseFactory.getAgentDao();
		IJournalDao journalDao = MySqlDaoFactory.getJournalDao();

		// System.out.println(journalDao.create(journal));

		System.out.println(journalDao.read(80));
		// journalDao.update(journal);
		journalDao.delete(journal);
		System.out.println(journalDao.findAll());
		System.out.println("-----------------findByDocId--------------"
				+ journalDao.findByDocId(29L));
		System.out
				.println("------------------------findByMscId---------------------"
						+ journalDao.findByMscId(367L));
	}
}
