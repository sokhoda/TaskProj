package _runner;

import bean.Misc;
import interfaces.dao.IMiscDao;
import dao.factory.MySqlDaoFactory;

public class DaoMiscRunner {
	public static void main(String[] args) {
		int mscNo = 1;
		Misc misc = new Misc(527L, 174L, mscNo, "Теребовля1", "тег1");
		// BaseDaoFactory baseFactory =
		// DaoFactory.getFactory(DriverTypes.MYSQL);
		// IAgentDao agentDao = baseFactory.getAgentDao();
		IMiscDao mscDao = MySqlDaoFactory.getMiscDao();

		// System.out.println(mscDao.create(misc));

		System.out.println(mscDao.read(520));
		// mscDao.update(misc);
		// mscDao.delete(misc);
		// System.out.println(mscDao.findAll());
		System.out.println("-----------------findByNamePattern--------------"
				+ mscDao.findByNamePattern("%Тернопіль%"));
		System.out
				.println("------------------------findByName---------------------"
						+ mscDao.findByName("Тернопіль"));
		System.out.println(mscDao.findByCountryId(74L));

		System.out.println(mscDao.findByRegionId(132L));
	}
}
