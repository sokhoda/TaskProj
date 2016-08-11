package service;

import interfaces.dao.IAgTypeDao;
import interfaces.service.IAgTypeService;
import manager.Message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.AgType;
import dao.factory.MySqlDaoFactory;

public class AgTypeService implements IAgTypeService {
	private static final Logger log = LogManager.getLogger(AgTypeService.class
			.getName());
	private IAgTypeDao agtdao = MySqlDaoFactory.getAgTypeDao();

	public AgTypeService() {
	}

	@Override
	public Long create(AgType agType) {
		IAgTypeDao dao = MySqlDaoFactory.getAgTypeDao();
		String name = agType.getAgtName();
		Long res = -1L;

		if (name.length() != 0) {
			if (dao.findByName(name) == null) {
				res = dao.create(agType);
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
