package interfaces.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import bean.Country;
import bean.Misc;
import bean.Region;

public interface IMiscService extends IService {
	/* list of regions as param. */
	List<Misc> findRegionAndNamePattern(String miscfilter, String namepattern);

	Region findRegionByMscId(Long mscId);

	Region findRegionByMisc(Misc misc);

	List<Region> findRegionsByCntrId(Long cntrId);

	List<Region> findRegionsByMultipleCntrId(String listOfCntrId);

	Country findCountryByRegion(Region region);

	List<Country> findTopCountries(int count, String order);

	Long getCurrentCntrId(HttpServletRequest req);

	List<Region> getCurrentRegions(Long cntrId);

	Long create(Misc misc);

	String getAlpharoute(String mscIds, String delimiter);

}
