package interfaces.dao;

import java.util.List;

import bean.UserType;

public interface IUserTypeDao extends IDao {
	long create(final UserType uType);

	UserType read(final long id);

	boolean update(final UserType uType);

	boolean delete(final UserType uType);

	List<UserType> findAll();

	List<UserType> findByName(final String cntrName);

	List<UserType> findByNamePattern(final String pattern);

	List<UserType> findByCode(final int uTypeCode);
}
