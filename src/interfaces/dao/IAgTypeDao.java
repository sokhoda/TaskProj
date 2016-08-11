package interfaces.dao;

import java.util.List;

import bean.AgType;

public interface IAgTypeDao extends IDao {
	long create(final AgType agtype);

	AgType read(final long id);

	boolean update(final AgType agtype);

	boolean delete(final AgType agtype);

	List<AgType> findAll();

	List<AgType> findByName(final String agtName);

	List<AgType> findByNamePattern(final String pattern);

}
