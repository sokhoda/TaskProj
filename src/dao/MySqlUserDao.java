package dao;

import interfaces.dao.IUserDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.User;
import datasource.QueryTool;

public class MySqlUserDao implements IUserDao {
	private static final Logger log = LogManager.getLogger(MySqlUserDao.class
			.getName());
	private static final String INSERT = "INSERT INTO USERS VALUES(0, ";
	private static final String UPDATE = "UPDATE USERS SET ";
	private static final String DELETE = "DELETE FROM USERS ";
	private static final String SELECTALL = "SELECT * FROM USERS ";

	// @formatter:off
	@Override
	public long create(final User user) {
		final String query = INSERT +
				user.getuTypeId() + "," +
				"'" + user.getUserLogin() + "',"+
				"'" + user.getUserPass() + "')";
		return QueryTool.simpleQueryInsert(query);
	}
	// @formatter:on

	@Override
	public User read(final long id) {
		final List<User> list = QueryTool.getSelect(this, SELECTALL
				+ "WHERE userId = " + id, null);
		return list != null ? list.get(0) : null;
	}

	// @formatter:off
	@Override
	public boolean update(final User user) {
		final String query = UPDATE +
				"uTypeId = '" + user.getuTypeId() + "',"
				+ "userLogin = '" + user.getUserLogin() + "',"
				+ "userPass = '" + user.getUserPass() + "'"
				+ " \n WHERE userId = " + user.getUserId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}
	// @formatter:on
	@Override
	public boolean delete(final User user) {
		final String query = DELETE + "WHERE userId = " + user.getUserId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}

	@Override
	public List<User> findAll() {
		return QueryTool.getSelect(this, SELECTALL, null);
	}

	@Override
	public List<User> findByUTypeId(Long uTypeId) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE uTypeId = "
				+ uTypeId, null);
	}

	@Override
	public List<User> findByUTypeName(String uTypeName) {
		return QueryTool
				.getSelect(
						this,
						SELECTALL
						+ " WHERE uTypeId IN (Select uTypeId FROM USER_TYPES WHERE uTypeName = '"
						+ uTypeName + "')", null);
	}

	@Override
	public List<User> findByUTypeAndNamePattern(String utypeid, String pattern) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE uTypeId IN ("
				+ utypeid + ") AND userLogin LIKE '" + pattern + "'", null);
	}

	@Override
	public List<User> findByLogin(String userLogin) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE userLogin = '"
				+ userLogin + "'", null);
	}

	@Override
	public List<User> findByLoginPattern(String pattern) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE userLogin LIKE '"
				+ pattern + "'", null);
	}

	@Override
	public List<User> findByPassword(String userPass) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE userPass = '"
				+ userPass + "'", null);
	}

	@Override
	public List<User> findByPasswordPattern(String pattern) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE userPass LIKE '"
				+ pattern + "'", null);
	}

	@Override
	public boolean deleteById(Long userId) {
		final String query = DELETE + "WHERE userId = " + userId;
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}

	@Override
	public List<User> findByLoginAndPassword(String userLogin,
			String userPassword) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE userLogin = '"
				+ userLogin + "' AND userPass = '" + userPassword + "'", null);
	}

	// 	@formatter:off
	public static List<User> fillEntityFields(ResultSet rs) throws SQLException {
		List<User> list = new ArrayList<>();
		do {
			list.add(new User(rs.getLong(1),
					rs.getLong(2),
					rs.getString(3),
					rs.getString(4)));
		} while (rs.next());
		return list;
	}
	// @formatter:on

}
