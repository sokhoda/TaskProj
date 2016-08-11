package service;

import interfaces.dao.IUserTypeDao;
import interfaces.service.IUserTypeService;
import manager.Message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.UserType;
import dao.factory.MySqlDaoFactory;

public class UserTypeService implements IUserTypeService {
	private static final Logger log = LogManager
			.getLogger(UserTypeService.class.getName());
	private IUserTypeDao dao = MySqlDaoFactory.getUserTypeDao();

	public UserTypeService() {
	}

	@Override
	public Long create(UserType userType) {
		IUserTypeDao dao = MySqlDaoFactory.getUserTypeDao();
		String name = userType.getuTypeName();
		Long res = -1L;
		if (name.length() != 0) {
			if (dao.findByName(name) == null) {
				res = dao.create(userType);
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
