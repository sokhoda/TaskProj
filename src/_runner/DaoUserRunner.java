package _runner;

import bean.User;
import interfaces.dao.IUserDao;
import dao.factory.MySqlDaoFactory;

public class DaoUserRunner {
	public static void main(String[] args) {
		int mscNo = 1;
		User uType = new User(14L, 9L, "Petro", "111");
		// BaseDaoFactory baseFactory =
		// DaoFactory.getFactory(DriverTypes.MYSQL);
		// IAgentDao agentDao = baseFactory.getAgentDao();
		IUserDao user = MySqlDaoFactory.getUserDao();

		// System.out.println(user.create(uType));

		System.out.println(user.read(15));
		// user.update(uType);
		// user.delete(uType);
		System.out.println(user.findAll());
		System.out.println("-----------------findByLoginPattern--------------"
				+ user.findByLoginPattern("%a%"));

		System.out
		.println("------------------------findByLogin---------------------"
				+ user.findByLogin("iryna"));
		System.out
				.println("-----------------findByPasswordPattern--------------"
						+ user.findByPasswordPattern("%2%"));
		System.out
		.println("------------------------findByPass---------------------"
				+ user.findByPassword("qwerty"));
	}
}
