package _runner;

import bean.Country;
import interfaces.dao.ICountryDao;
import dao.factory.MySqlDaoFactory;

public class DaoCountryRunner {
	public static void main(String[] args) {

		Country country = new Country(42L, "Німеччина", "");
		// BaseDaoFactory baseFactory =
		// DaoFactory.getFactory(DriverTypes.MYSQL);
		// IAgentDao agentDao = baseFactory.getAgentDao();
		ICountryDao ctDao = MySqlDaoFactory.getCountryDao();

		// System.out.println(ctDao.create(country));

		System.out.println(ctDao.read(88));
		// ctDao.update(country);
		// ctDao.delete(country);
		System.out.println(ctDao.findAll());
		System.out.println(ctDao.findByNamePattern("%чч%"));
		System.out.println(ctDao.findByName("Україна"));

	}
}
