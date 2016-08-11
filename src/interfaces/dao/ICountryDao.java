package interfaces.dao;

import java.util.List;

import bean.Country;

public interface ICountryDao extends IDao {
	long create(final Country agent);

	Country read(final long id);

	boolean update(final Country agent);

	boolean delete(final Country agent);

	List<Country> findAll();

	List<Country> findByName(final String cntrName);

	List<Country> findByNamePattern(final String pattern);

	List<Country> findByTag(final String cntrTag);

	List<Country> findTop(int count, String order);

}
