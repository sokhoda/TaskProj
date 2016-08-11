package dao;

import interfaces.dao.IAgentDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Agent;
import datasource.QueryTool;

public class MySqlAgentDao implements IAgentDao {
	private static final Logger log = LogManager.getLogger(MySqlAgentDao.class
			.getName());
	private static final String INSERT = "INSERT INTO AGENTS VALUES(0, ";
	private static final String UPDATE = "UPDATE AGENTS SET ";
	private static final String DELETE = "DELETE FROM AGENTS ";
	private static final String SELECTALL = "SELECT * FROM AGENTS ";

	// @formatter:off
	@Override
	public long create(final Agent agent) {
		final String query = INSERT +
				agent.getAgtId() + "," +
				"'" + agent.getAgName() +"'," +
				"'" + agent.getAgAddress() + "'," +
				"'" + agent.getAgPhone() + "'," +
				"'" + agent.getAgEmail() + "'," +
				"'" + agent.getAgPassport() + "'," +
				"'" + agent.getAgWww() + "'," +
				"'" + agent.getAgCountry() + "'," +
				QueryTool.getSQLDate(agent.getAgDate()) + "," +
				"'" + agent.getAgTag() + "');";
		return QueryTool.simpleQueryInsert(query);
	}
	// @formatter:on

	@Override
	public Agent read(final Long id) {
		final List<Agent> list = QueryTool.getSelect(this, SELECTALL
				+ "WHERE agId = " + id, null);
		return list != null ? list.get(0) : null;
	}

	// @formatter:off
	@Override
	public boolean update(final Agent agent) {
		final String query = UPDATE +
				"agtId = '" + agent.getAgtId() + "',"
				+ "agName = '" + agent.getAgName() + "',"
				+ "agAddress = '" + agent.getAgAddress() + "',"
				+ "agPhone = '" + agent.getAgPhone() + "',"
				+ "agEmail = '" + agent.getAgEmail() + "',"
				+ "agPassport = '" + agent.getAgPassport() + "',"
				+ "agWww = '" + agent.getAgWww() + "',"
				+ "agCountry = '" + agent.getAgCountry() + "',"
				+ "agDate = " + QueryTool.getSQLDate(agent.getAgDate()) + ","
				+ "agTag = '" + agent.getAgTag() + "' \n WHERE agId =" + agent.getAgId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}
	// @formatter:on
	@Override
	public boolean delete(final Agent agent) {
		final String query = DELETE + "WHERE agId = " + agent.getAgId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}

	@Override
	public List<Agent> findAll() {
		return QueryTool.getSelect(this, SELECTALL, null);
	}

	@Override
	public List<Agent> findByName(final String agName) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE agName = '"
				+ agName + "'", null);
	}

	@Override
	public List<Agent> findByNamePattern(final String pattern) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE agName LIKE '"
				+ pattern + "'", null);
	}

	@Override
	public List<Agent> findByType(final Long agType) {
		return QueryTool.getSelect(this,
				SELECTALL + " WHERE agtId = " + agType, null);
	}

	@Override
	public List<Agent> findByAgTypeAndNamePattern(String agtypeid,
			String pattern) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE agName LIKE '"
				+ pattern + "' AND agtId IN (" + agtypeid + ")", null);
	}

	@Override
	public boolean deleteById(Long agId) {
		final String query = DELETE + "WHERE agId = " + agId;
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}

	@Override
	public List<Agent> findAllByAgId(Long agId) {
		return QueryTool.getSelect(this, SELECTALL
				+ " WHERE agtId IN (SELECT agtId FROM AGENTS WHERE agId ="
				+ agId + ")", null);
	}

	// 	@formatter:off
	public static List<Agent> fillEntityFields(ResultSet rs) throws SQLException {
		List<Agent> list = new ArrayList<>();
		do {
			list.add( new Agent(rs.getLong(1),
					rs.getLong(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getString(9),
					rs.getDate(10),
					rs.getString(11)));
		} while (rs.next());
		return list;
	}
	// @formatter:on

}
