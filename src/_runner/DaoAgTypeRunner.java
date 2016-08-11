package _runner;

import interfaces.dao.IAgTypeDao;

import java.util.Calendar;

import bean.AgType;
import dao.factory.MySqlDaoFactory;

public class DaoAgTypeRunner {
	public static void main(String[] args) {

		Calendar cal = Calendar.getInstance();
		cal.set(2011, 11, 2);
		AgType agType = new AgType(5L, "Компания", "myTag");
		// BaseDaoFactory baseFactory =
		// DaoFactory.getFactory(DriverTypes.MYSQL);
		// IAgentDao agentDao = baseFactory.getAgentDao();
		IAgTypeDao atDao = MySqlDaoFactory.getAgTypeDao();

		// System.out.println(atDao.create(agType));
		System.out.println(atDao.read(3));
		// atDao.update(agType);
		// atDao.delete(agType);
		System.out.println(atDao.findAll());
		System.out.println(atDao.findByNamePattern("%an%"));
		System.out.println(atDao.findByName("ff"));

	}
}
