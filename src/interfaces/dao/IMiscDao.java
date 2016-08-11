package interfaces.dao;

import java.util.List;

import bean.Misc;

public interface IMiscDao extends IDao {
	long create(final Misc misc);

	Misc read(final long id);

	boolean update(final Misc misc);

	boolean delete(final Misc misc);

	List<Misc> findAll();

	List<Misc> findByCountryId(final Long cntrId);

	List<Misc> findByMultipleRegId(String listOfRegId);

	List<Misc> findByRegionId(final Long regId);

	List<Misc> findByName(final String cntrName);

	List<Misc> findByNameAndRegIdAndTag(final String mscName, Long regId,
			String mscTag);

	List<Misc> findByNamePattern(final String pattern);

	List<Misc> findByTag(final String cntrTag);

	List<Misc> findRegionAndNamePattern(String miscfilter, String namepattern);

	List<Misc> findByMscId(String mscIds);
}
