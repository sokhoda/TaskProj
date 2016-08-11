package service;

import interfaces.dao.ICargoDao;
import interfaces.service.ICargoService;
import manager.Message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Cargo;
import dao.factory.MySqlDaoFactory;

public class CargoService implements ICargoService {
	private static final Logger log = LogManager.getLogger(CargoService.class
			.getName());
	private ICargoDao dao = MySqlDaoFactory.getCargoDao();

	public CargoService() {
	}

	@Override
	public Long create(Cargo cargo) {
		ICargoDao dao = MySqlDaoFactory.getCargoDao();
		String name = cargo.getCargName();
		Long res = -1L;

		if (name.length() != 0) {
			if (dao.findByName(name) == null) {
				res = dao.create(cargo);
			} else {
				log.error(name
						+ ':'
						+ Message.getInstance()
						.getProperty(Message.NAME_EXISTS));
			}
		} else {
			log.error(Message.getInstance().getProperty(
					Message.NAME_ZERO_LENGTH));
		}
		return res;
	}

}
