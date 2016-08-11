package _runner;

import interfaces.dao.IRegionDao;
import bean.Region;
import dao.factory.MySqlDaoFactory;

public class DaoRegionRunner {
	public static void main(String[] args) {

		Region reg = new Region(65L, 36L, "Волинська", "ferf");
		// BaseDaoFactory baseFactory =
		// DaoFactory.getFactory(DriverTypes.MYSQL);
		// IAgentDao agentDao = baseFactory.getAgentDao();
		IRegionDao rgDao = MySqlDaoFactory.getRegionDao();

		// System.out.println(rgDao.create(reg));

		System.out.println(rgDao.read(190));
		// rgDao.update(reg);
		// rgDao.delete(reg);
		System.out.println(rgDao.findAll());
		System.out.println(rgDao.findByNamePattern("%ськ%"));
		System.out.println(rgDao.findByName("Полтавська"));
		System.out.println(rgDao.findByCountryId(37L));
	}
}
