package _runner;

import bean.CargoTree;
import interfaces.dao.ICargoTreeDao;
import dao.factory.MySqlDaoFactory;

public class DaoCargoTreeRunner {
	public static void main(String[] args) {
		int mscNo = 1;
		CargoTree cargoTree = new CargoTree(798L, 769L, 1L, 1L, 1L);
		// BaseDaoFactory baseFactory =
		// DaoFactory.getFactory(DriverTypes.MYSQL);
		// IAgentDao agentDao = baseFactory.getAgentDao();
		ICargoTreeDao ctDao = MySqlDaoFactory.getCargoTreeDao();

		// System.out.println(ctDao.create(cargoTree));

		System.out.println(ctDao.read(769));
		ctDao.update(cargoTree);
		ctDao.delete(cargoTree);
		System.out.println(ctDao.findAll());
		System.out
				.println("------------------------findByCargoId---------------------"
						+ ctDao.findByCargoId(799L));
	}
}
