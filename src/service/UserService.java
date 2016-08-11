package service;

import interfaces.dao.IUserDao;
import interfaces.service.IUserService;

import java.util.List;

import manager.Message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.User;
import dao.factory.MySqlDaoFactory;

public class UserService implements IUserService {
	private static final Logger log = LogManager.getLogger(UserService.class
			.getName());
	private IUserDao dao = MySqlDaoFactory.getUserDao();

	public UserService() {
	}

	@Override
	public List<User> findByUTypeAndNamePattern(String utypeid, String pattern) {
		return dao.findByUTypeAndNamePattern(utypeid, pattern);
	}

	@Override
	public Long create(User user) {
		IUserDao dao = MySqlDaoFactory.getUserDao();
		String name = user.getUserLogin();
		Long res = -1L;
		String pass = user.getUserPass();

		if (pass.length() != 0) {
			if (name.length() != 0) {
				if (dao.findByLogin(name) == null
						&& dao.findByPassword(pass) == null) {
					res = dao.create(user);
				} else {
					log.error(name
							+ ":"
							+ pass
							+ ":"
							+ Message.getInstance().getProperty(
									Message.LOGIN_PASS_EXIST));
				}
			} else {
				log.error(Message.getInstance().getProperty(
						Message.LOGIN_ZERO_LENGTH));
			}
		} else {
			log.error(Message.getInstance().getProperty(
					Message.PASS_ZERO_LENGTH));
		}
		return res;
	}
}