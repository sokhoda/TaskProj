package service;

import interfaces.dao.ICountryDao;
import interfaces.dao.IMiscDao;
import interfaces.dao.IRegionDao;
import interfaces.service.IMiscService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import manager.Config;
import manager.Message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Country;
import bean.Misc;
import bean.Region;

import commands.CommTool;

import dao.factory.MySqlDaoFactory;

public class MiscService implements IMiscService {
	private static final Logger log = LogManager.getLogger(MiscService.class
			.getName());
	private IMiscDao miscDao = MySqlDaoFactory.getMiscDao();
	private IRegionDao regDao = MySqlDaoFactory.getRegionDao();
	private ICountryDao countryDao = MySqlDaoFactory.getCountryDao();

	public MiscService() {
	}

	@Override
	public List<Misc> findRegionAndNamePattern(String miscfilter,
			String namepattern) {
		return miscDao.findRegionAndNamePattern(miscfilter, namepattern);
	}

	@Override
	public Region findRegionByMisc(Misc misc) {
		Region region = null;
		if (misc != null) {
			region = regDao.read(misc.getRegId());
		}
		return region;
	}

	@Override
	public Region findRegionByMscId(Long mscId) {
		Misc misc = miscDao.read(mscId);
		return findRegionByMisc(misc);
	}

	@Override
	public List<Region> findRegionsByMultipleCntrId(String listOfCntrId) {
		return regDao.findByMultipleCntrId(listOfCntrId);
	}

	@Override
	public Country findCountryByRegion(Region region) {
		return countryDao.read(region.getCntrId());
	}

	@Override
	public List<Region> findRegionsByCntrId(Long cntrId) {
		return regDao.findByCountryId(cntrId);
	}

	@Override
	public List<Country> findTopCountries(int count, String order) {
		return countryDao.findTop(count, order);
	}

	@Override
	public Long getCurrentCntrId(HttpServletRequest req) {
		Long cntrId = CommTool.getParamLong(req, "cntrId");
		if (cntrId == null) {
			List<Country> countries = findTopCountries(1, Config.ASC);
			if (countries != null && countries.size() > 0) {
				cntrId = countries.get(0).getCntrId();
			}
		}
		return cntrId;
	}

	@Override
	public List<Region> getCurrentRegions(Long cntrId) {
		List<Region> regions = null;
		if (cntrId != null) {
			regions = findRegionsByCntrId(cntrId);
		}
		return regions;
	}

	@Override
	public Long create(Misc misc) {
		IMiscDao dao = MySqlDaoFactory.getMiscDao();
		String name = misc.getMscName();
		Long res = -1L;

		if (name.length() != 0) {
			if (dao.findByNameAndRegIdAndTag(name, misc.getRegId(),
					misc.getMscTag()) == null) {
				res = dao.create(misc);
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

	@Override
	public String getAlpharoute(String mscIds, String delimiter) {
		StringBuilder sb = new StringBuilder();
		if (mscIds != null && mscIds.length() > 0) {
			List<Misc> list = miscDao.findByMscId(mscIds);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					sb.append(list.get(i).getMscName()
							+ (i < list.size() - 1 ? delimiter : ""));
				}
			}
		}
		return sb.toString();
	}
}
