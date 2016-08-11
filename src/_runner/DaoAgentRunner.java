package _runner;

import interfaces.dao.IAgentDao;

import java.util.Calendar;

import bean.Agent;
import dao.factory.MySqlDaoFactory;

public class DaoAgentRunner {
	public static void main(String[] args) {

		Calendar cal = Calendar.getInstance();
		cal.set(2011, 11, 2);
		Agent agent = new Agent(13L, 2L, "Петроіі", "my address",
				"091 112 45 77", "custom@gmail.com", "XX-112-554",
				"agent.com.ua", "Lithuania", cal.getTime(), "myTag");
		// BaseDaoFactory baseFactory =
		// DaoFactory.getFactory(DriverTypes.MYSQL);
		// IAgentDao agentDao = baseFactory.getAgentDao();
		IAgentDao atDao = MySqlDaoFactory.getAgentDao();

		// System.out.println(atDao.create(agent));

		System.out.println(atDao.read(10L));
		// atDao.update(agent);
		atDao.delete(agent);
		System.out.println(atDao.findAll());
		System.out.println(atDao.findByNamePattern("%pet%"));
		System.out.println(atDao.findByName("myko"));
		System.out.println(atDao.findByType(2L));

	}
}
