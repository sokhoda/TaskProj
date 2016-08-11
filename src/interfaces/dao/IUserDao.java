package interfaces.dao;

import java.util.List;

import bean.User;

public interface IUserDao extends IDao {
	long create(final User user);

	User read(final long id);

	boolean update(final User user);

	boolean delete(final User user);

	List<User> findAll();

	List<User> findByUTypeId(final Long uTypeId);

	List<User> findByUTypeName(final String uTypeName);

	List<User> findByUTypeAndNamePattern(String utypeid, final String pattern);

	List<User> findByLogin(final String userLogin);

	List<User> findByLoginPattern(final String pattern);

	List<User> findByPassword(final String userPass);

	List<User> findByPasswordPattern(final String pattern);

	List<User> findByLoginAndPassword(final String userLogin,
			final String userPassword);

}
