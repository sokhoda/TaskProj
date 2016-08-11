package dao;

import interfaces.dao.IAgTypeDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.AgType;
import datasource.QueryTool;

public class MySqlAgTypeDao implements IAgTypeDao {
	private static final Logger log = LogManager.getLogger(MySqlAgTypeDao.class
			.getName());
	private static final String INSERT = "INSERT INTO AG_TYPES VALUES(0, ";
	private static final String UPDATE = "UPDATE AG_TYPES SET ";
	private static final String DELETE = "DELETE FROM AG_TYPES ";
	private static final String SELECTALL = "SELECT * FROM AG_TYPES ";

	// @formatter:off
	@Override
	public long create(final AgType agtype) {
		final String query = INSERT +
				"'" + agtype.getAgtName() +"'," +
				"'" + agtype.getAgtTag() + "');";
		return QueryTool.simpleQueryInsert(query);
	}
	// @formatter:on

	@Override
	public AgType read(final long id) {
		final List<AgType> list = QueryTool.getSelect(this, SELECTALL
				+ "WHERE agtId = " + id, null);
		return list != null ? list.get(0) : null;
	}

	// @formatter:off
	@Override
	public boolean update(final AgType agtype) {
		final String query = UPDATE +
				"agtName = '" + agtype.getAgtName() + "',"
				+ "agtTag = '" + agtype.getAgtTag() + "' \n WHERE agtId =" + agtype.getAgtId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}
	// @formatter:on
	@Override
	public boolean delete(final AgType agtype) {
		final String query = DELETE + "WHERE agtId = " + agtype.getAgtId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}

	@Override
	public List<AgType> findAll() {
		return QueryTool.getSelect(this, SELECTALL, null);
	}

	@Override
	public List<AgType> findByName(final String agtName) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE agtName = '"
				+ agtName + "'", null);
	}

	@Override
	public List<AgType> findByNamePattern(final String pattern) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE agtName LIKE '"
				+ pattern + "'", null);
	}

	@Override
	public boolean deleteById(Long agtId) {
		final String query = DELETE + "WHERE agtId = " + agtId;
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}

	// 	@formatter:off
	public static List<AgType> fillEntityFields(ResultSet rs) throws SQLException {
		List<AgType> list = new ArrayList<>();
		do {
			list.add( new AgType(rs.getLong(1),
					rs.getString(2),
					rs.getString(3)));
		} while (rs.next());
		return list;
	}
	// @formatter:on

}
