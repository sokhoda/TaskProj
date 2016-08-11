package dao;

import interfaces.dao.ICountryDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Country;
import datasource.QueryTool;

public class MySqlCountryDao implements ICountryDao {
	private static final Logger log = LogManager
			.getLogger(MySqlCountryDao.class.getName());

	private static final String INSERT = "INSERT INTO COUNTRIES VALUES(0, ";
	private static final String UPDATE = "UPDATE COUNTRIES SET ";
	private static final String DELETE = "DELETE FROM COUNTRIES ";
	private static final String SELECTALL = "SELECT * FROM COUNTRIES ";

	// @formatter:off
	@Override
	public long create(final Country country) {
		final String query = INSERT +
				"'" + country.getCntrName() +"'," +
				"'" + country.getCntrTag() + "')";
		return QueryTool.simpleQueryInsert(query);
	}
	// @formatter:on

	@Override
	public Country read(final long id) {
		final List<Country> list = QueryTool.getSelect(this, SELECTALL
				+ "WHERE cntrId = " + id, null);
		return list != null ? list.get(0) : null;
	}

	// @formatter:off
	@Override
	public boolean update(final Country country) {
		final String query = UPDATE +
				"cntrName = '" + country.getCntrName() + "',"
				+ "cntrTag = '" + country.getCntrTag() + "' \n WHERE cntrId =" + country.getCntrId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}
	// @formatter:on
	@Override
	public boolean delete(final Country country) {
		final String query = DELETE + "WHERE cntrId = " + country.getCntrId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}

	@Override
	public List<Country> findAll() {
		return QueryTool.getSelect(this, SELECTALL, null);
	}

	@Override
	public List<Country> findByName(final String cntrName) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE cntrName = '"
				+ cntrName + "'", null);
	}

	@Override
	public List<Country> findByNamePattern(final String pattern) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE cntrName LIKE '"
				+ pattern + "'", null);
	}

	@Override
	public List<Country> findByTag(String cntrTag) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE cntrTag = '"
				+ cntrTag + "'", null);
	}

	@Override
	public boolean deleteById(Long cntrId) {
		final String query = DELETE + "WHERE cntrId = " + cntrId;
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}

	@Override
	public List<Country> findTop(int count, String order) {
		return QueryTool.getSelect(this, SELECTALL + "ORDER BY cntrId " + order
				+ " LIMIT " + count, null);
	}

	// 	@formatter:off
	public static List<Country> fillEntityFields(ResultSet rs) throws SQLException {
		List<Country> list = new ArrayList<>();
		do {
			list.add(new Country(rs.getLong(1),
					rs.getString(2),
					rs.getString(3)));
		} while (rs.next());
		return list;
	}
	// @formatter:on

}
