package _runner;

import bean.UserType;
import interfaces.dao.IUserTypeDao;
import dao.factory.MySqlDaoFactory;

public class DaoUserTypeRunner {
	public static void main(String[] args) {
		int mscNo = 1;
		UserType uType = new UserType(8L, 1, "noviceAdmin2");
		// BaseDaoFactory baseFactory =
		// DaoFactory.getFactory(DriverTypes.MYSQL);
		// IAgentDao agentDao = baseFactory.getAgentDao();
		IUserTypeDao uTypeDao = MySqlDaoFactory.getUserTypeDao();

		// System.out.println(uTypeDao.create(uType));

		System.out.println(uTypeDao.read(11));
		// uTypeDao.update(uType);
		// uTypeDao.delete(uType);
		System.out.println(uTypeDao.findAll());
		System.out.println("-----------------findByNamePattern--------------"
				+ uTypeDao.findByNamePattern("%min%"));
		System.out
		.println("------------------------findByName---------------------"
				+ uTypeDao.findByName("AdMin"));
		System.out.println(uTypeDao.findByCode(1));
	}
}
