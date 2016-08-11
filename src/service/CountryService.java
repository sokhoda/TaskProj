package service;

import interfaces.dao.ICountryDao;
import interfaces.service.ICountryService;
import manager.Message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Country;
import dao.factory.MySqlDaoFactory;

public class CountryService implements ICountryService {
	private static final Logger log = LogManager.getLogger(CountryService.class
			.getName());
	private ICountryDao cntrDao = MySqlDaoFactory.getCountryDao();

	public CountryService() {
	}

	@Override
	public Long create(Country country) {
		ICountryDao dao = MySqlDaoFactory.getCountryDao();
		String name = country.getCntrName();
		Long res = -1L;

		if (name.length() != 0) {
			if (dao.findByName(name) == null) {
				res = dao.create(country);
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
