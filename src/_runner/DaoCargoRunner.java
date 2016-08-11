package _runner;

import bean.Cargo;
import interfaces.dao.ICargoDao;
import dao.factory.MySqlDaoFactory;

public class DaoCargoRunner {
	public static void main(String[] args) {
		int mscNo = 1;
		Cargo cargo = new Cargo(400L, 1, "�����", "���1");
		// BaseDaoFactory baseFactory =
		// DaoFactory.getFactory(DriverTypes.MYSQL);
		// IAgentDao agentDao = baseFactory.getAgentDao();
		ICargoDao cargoDao = MySqlDaoFactory.getCargoDao();

		System.out.println(cargoDao.create(cargo));

		System.out.println(cargoDao.read(769));
		// cargoDao.update(cargo);
		// cargoDao.delete(cargo);
		System.out.println(cargoDao.findAll());
		System.out.println("-----------------findByNamePattern--------------"
				+ cargoDao.findByNamePattern("%�%"));
		System.out
				.println("-----------------findByNamePattern-ANd Type-------------"
						+ cargoDao.findByNamePatternAndType("%�%", 1));
		System.out
		.println("------------------------findByName---------------------"
				+ cargoDao.findByName("��������"));
		System.out
				.println("------------------------findByNameAnd Type---------------------"
						+ cargoDao.findByNameAndType("��������", 1));
		System.out.println(cargoDao.findByType(1));
	}
}
