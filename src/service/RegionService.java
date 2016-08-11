package service;

import interfaces.dao.IRegionDao;
import interfaces.service.IRegionService;

import java.util.List;

import manager.Message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Region;
import dao.factory.MySqlDaoFactory;

public class RegionService implements IRegionService {
	private static final Logger log = LogManager.getLogger(RegionService.class
			.getName());
	private IRegionDao dao = MySqlDaoFactory.getRegionDao();

	public RegionService() {
	}

	@Override
	public List<Region> findByAgTypeAndNamePattern(String cntrId, String pattern) {
		return dao.findByAgTypeAndNamePattern(cntrId, pattern);
	}

	@Override
	public Long create(Region region) {
		IRegionDao dao = MySqlDaoFactory.getRegionDao();
		String name = region.getRegName();
		Long res = -1L;

		if (name.length() != 0) {
			if (dao.findByName(name) == null) {
				res = dao.create(region);
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
