package interfaces.dao;

import java.util.List;

import bean.Region;

public interface IRegionDao extends IDao {
	long create(final Region region);

	Region read(final long id);

	boolean update(final Region region);

	boolean delete(final Region region);

	List<Region> findAll();

	List<Region> findByCountryId(final Long cntrId);

	List<Region> findByMultipleCntrId(String listOfCntrId);

	List<Region> findByName(final String cntrName);

	List<Region> findByNamePattern(final String pattern);

	List<Region> findByTag(final String cntrTag);

	List<Region> findByAgTypeAndNamePattern(String cntrId, final String pattern);
}
