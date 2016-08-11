package _runner;

import interfaces.dao.IDocumentDao;

import java.util.Calendar;
import java.util.Random;

import bean.Document;
import dao.factory.MySqlDaoFactory;

public class DaoDocumentRunner {
	private static Random rand = new Random();

	public static Document getRandDoc() {
		Document doc = new Document();
		doc.setMgrId((long) rand.nextInt(3) + 1);
		long id = rand.nextInt(5) + 1;
		while (doc.getMgrId() == id) {
			id = rand.nextInt(5) + 1;
		}
		doc.setMcId(id);
		while (doc.getMgrId() == id || doc.getMcId() == id) {
			id = rand.nextInt(5) + 1;
		}
		doc.setCustId(id);
		doc.setCargo((long) rand.nextInt(3) + 1);
		doc.setDocNo((long) rand.nextInt(30) + 1 + "-"
				+ (char) (rand.nextInt(20) + 65));
		Calendar cal = Calendar.getInstance();
		cal.set(2000 + rand.nextInt(17), rand.nextInt(12), rand.nextInt(29) + 1);
		doc.setDocDate(cal.getTime());

		return doc;
	}

	public static void main(String[] args) {

		Calendar cal = Calendar.getInstance();
		cal.set(2011, 2, 15);

		Document docu = new Document(1L, 55L, 49L, 48L, null, "18-b",
				cal.getTime(), "Акт виконаних робіт", 105.87, 1, "myTag", 100.,
				100., null, null, "addr1", "addr2", "095", "nameee", "routeX");

		// Document docu = new Document();
		// docu.setDocId(0L);
		// docu.setMgrId(7L);
		docu.setDocDate(cal.getTime());
		// BaseDaoFactory baseFactory =
		// DaoFactory.getFactory(DriverTypes.MYSQL);
		// IDocumentDao agentDao = baseFactory.getDocumentDao();
		IDocumentDao docDao = MySqlDaoFactory.getDocumentDao();

		System.out.println(docDao.create(docu));

		System.out.println(docDao.read(9));
		// docDao.update(docu);
		// docDao.delete(docu);
		System.out.println(docDao.findAll());
		// System.out.println(docDao.findByNamePattern("%кт%"));
		// System.out.println(docDao.findByNamePattern("%договір%"));
		cal.set(2011, 0, 2);
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2011, 2, 15);
		System.out.println(docDao.findByDate(cal.getTime()));
		System.out.println(docDao.findByDateInPeriod(cal.getTime(),
				cal2.getTime()));

		System.out.println(docDao.findByMc(6L));

	}
}
