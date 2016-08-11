package dao;

import interfaces.dao.IJournalDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Journal;
import datasource.QueryTool;

public class MySqlJournalDao implements IJournalDao {
	private static final Logger log = LogManager
			.getLogger(MySqlJournalDao.class.getName());
	private static final String INSERT = "INSERT INTO JOURNAL VALUES(0, ";
	private static final String UPDATE = "UPDATE JOURNAL SET ";
	private static final String DELETE = "DELETE FROM JOURNAL ";
	private static final String SELECTALL = "SELECT * FROM JOURNAL ";

	// @formatter:off
	@Override
	public long create(final Journal journal) {
		final String query = INSERT +
				journal.getDocId() + "," +
				journal.getMscId() + ")";
		return QueryTool.simpleQueryInsert(query);
	}
	// @formatter:on

	@Override
	public Journal read(final long id) {
		final List<Journal> list = QueryTool.getSelect(this, SELECTALL
				+ "WHERE jId = " + id, null);
		return list != null ? list.get(0) : null;
	}

	// @formatter:off
	@Override
	public boolean update(final Journal journal) {
		final String query = UPDATE +
				"docId = '" + journal.getDocId() + "',"
				+ "mscId = '" + journal.getMscId() + "'"
				+ " \n WHERE jId = " + journal.getJId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}
	// @formatter:on

	@Override
	public boolean delete(final Journal journal) {
		final String query = DELETE + "WHERE jId = " + journal.getJId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}

	@Override
	public boolean deleteByDocId(Long docId) {
		final String query = DELETE + "WHERE docId = " + docId;
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}

	@Override
	public List<Journal> findAll() {
		return QueryTool.getSelect(this, SELECTALL, null);
	}

	@Override
	public List<Journal> findByDocId(Long docId) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE docId = " + docId,
				null);
	}

	@Override
	public List<Journal> findByMscId(Long mscId) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE mscId = " + mscId,
				null);
	}

	// @formatter:off
	public static List<Journal> fillEntityFields(ResultSet rs) throws SQLException {
		List<Journal> list = new ArrayList<>();
		do {
			list.add(new Journal(rs.getLong(1),
					rs.getLong(2),
					rs.getLong(3)));
		} while (rs.next());
		return list;
	}
	// @formatter:on

}
