package dao;

import interfaces.dao.ICargoDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Cargo;
import datasource.QueryTool;

public class MySqlCargoDao implements ICargoDao {
	private static final Logger log = LogManager.getLogger(MySqlCargoDao.class
			.getName());
	private static final String INSERT = "INSERT INTO CARGOS VALUES(0, ";
	private static final String UPDATE = "UPDATE CARGOS SET ";
	private static final String DELETE = "DELETE FROM CARGOS ";
	private static final String SELECTALL = "SELECT * FROM CARGOS ";

	// @formatter:off
	@Override
	public long create(final Cargo cargo) {
		final String query = INSERT +
				cargo.getCargType() + "," +
				"'" + cargo.getCargName() +"'," +
				"'" + cargo.getCargTag() + "')";
		return QueryTool.simpleQueryInsert(query);
	}
	// @formatter:on

	@Override
	public Cargo read(final long id) {
		final List<Cargo> list = QueryTool.getSelect(this, SELECTALL
				+ "WHERE cargId = " + id, null);
		return list != null ? list.get(0) : null;
	}

	// @formatter:off
	@Override
	public boolean update(final Cargo cargo) {
		final String query = UPDATE +
				"cargType = '" + cargo.getCargType() + "',"
				+ "cargName = '" + cargo.getCargName() + "',"
				+ "cargTag = '" + cargo.getCargTag() + "' \n WHERE cargId =" + cargo.getCargId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}
	// @formatter:on
	@Override
	public boolean delete(final Cargo cargo) {
		final String query = DELETE + "WHERE cargId = " + cargo.getCargId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}

	@Override
	public List<Cargo> findAll() {
		return QueryTool.getSelect(this, SELECTALL, null);
	}

	@Override
	public List<Cargo> findByName(final String cargName) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE cargName = '"
				+ cargName + "'", null);
	}

	@Override
	public List<Cargo> findByNameAndType(String cargName, final int cargType) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE cargName = '"
				+ cargName + "' AND cargType = " + cargType, null);
	}

	@Override
	public List<Cargo> findByNamePattern(final String pattern) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE cargName LIKE '"
				+ pattern + "'", null);
	}

	@Override
	public List<Cargo> findByNamePatternAndType(String pattern, int cargType) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE cargName LIKE '"
				+ pattern + "' AND cargType = " + cargType, null);
	}

	@Override
	public List<Cargo> findByType(int cargType) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE cargType = "
				+ cargType, null);
	}

	@Override
	public List<Cargo> findByTag(String cargTag) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE cargTag = '"
				+ cargTag + "'", null);
	}

	// 	@formatter:off
	public static List<Cargo> fillEntityFields(ResultSet rs) throws SQLException {
		List<Cargo> list = new ArrayList<>();
		do {
			list.add(new Cargo(rs.getLong(1),
					rs.getInt(2),
					rs.getString(3),
					rs.getString(4)));
		} while (rs.next());
		return list;
	}
	// @formatter:on

}
