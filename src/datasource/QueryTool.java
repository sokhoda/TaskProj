package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.MySqlAgTypeDao;
import dao.MySqlAgentDao;
import dao.MySqlCargoDao;
import dao.MySqlCargoTreeDao;
import dao.MySqlCountryDao;
import dao.MySqlDocumentDao;
import dao.MySqlJournalDao;
import dao.MySqlMiscDao;
import dao.MySqlRegionDao;
import dao.MySqlUserDao;
import dao.MySqlUserTypeDao;

public class QueryTool {
	private static final Logger log = LogManager.getLogger(QueryTool.class
			.getName());
	private static ConnPool connPool = ConnPool
			.getInstance(Config.CONNECTION_POOL_SIZE);

	// @formatter:off
	public static String getSelectSingleVal(final String query, final List params) {
		Connection conn = connPool.getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = null;
		if (conn != null) {
			try {
				ps = QueryTool.getPrepStatement(conn, query, params);
				rs = ps.executeQuery();
				if (rs.next()) {
					result = rs.getString(1);
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (ps != null) {
						ps.close();
					}
					connPool.putConnection(conn);
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
			}
		} else {
			log.error(Config.CONNECTION_UNAVAILABLE);
		}
		return result;
	}
	// @formatter:on

	// @formatter:off
	public static <T> List<T> getSelect(Object dao, final String query, final List params) {
		//		log.info(query);
		Connection conn = connPool.getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<T> list = null;

		if (conn != null) {
			try {
				//	conn = ConnectionFactory.getInstance().getConnection();
				ps = QueryTool.getPrepStatement(conn, query, params);
				rs = ps.executeQuery();
				if (rs.next()) {
					if (dao instanceof MySqlDocumentDao) {
						list = (List<T>) MySqlDocumentDao.fillEntityFields(rs);
					}else if(dao instanceof MySqlJournalDao) {
						list = (List<T>) MySqlJournalDao.fillEntityFields(rs);
					}else if(dao instanceof MySqlCargoDao) {
						list = (List<T>) MySqlCargoDao.fillEntityFields(rs);
					}else if(dao instanceof MySqlCargoTreeDao) {
						list = (List<T>) MySqlCargoTreeDao.fillEntityFields(rs);
					}else if(dao instanceof MySqlAgentDao) {
						list = (List<T>) MySqlAgentDao.fillEntityFields(rs);
					}else if(dao instanceof MySqlAgTypeDao) {
						list = (List<T>) MySqlAgTypeDao.fillEntityFields(rs);
					}else if(dao instanceof MySqlMiscDao) {
						list = (List<T>) MySqlMiscDao.fillEntityFields(rs);
					}else if(dao instanceof MySqlRegionDao) {
						list = (List<T>) MySqlRegionDao.fillEntityFields(rs);
					}else if(dao instanceof MySqlCountryDao) {
						list = (List<T>) MySqlCountryDao.fillEntityFields(rs);
					}else if(dao instanceof MySqlUserDao) {
						list = (List<T>) MySqlUserDao.fillEntityFields(rs);
					}else if(dao instanceof MySqlUserTypeDao) {
						list = (List<T>) MySqlUserTypeDao.fillEntityFields(rs);
					}else {
						log.warn("getSelect::DaoType not defined!");
					}

				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (ps != null) {
						ps.close();
					}
					connPool.putConnection(conn);
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
			}
		} else {
			log.error(Config.CONNECTION_UNAVAILABLE);
		}
		return list;
	}
	// @formatter:on

	public static long simpleQueryInsert(String query) {
		Connection conn = connPool.getConnection();
		Statement stm = null;
		long id = -1;
		if (conn != null) {
			try {
				stm = conn.createStatement();

				stm.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
				ResultSet rs = stm.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getLong(1);
				}
			} catch (SQLException e) {
				log.error(e.getMessage() + "\n" + query);
			} finally {
				try {
					if (stm != null) {
						stm.close();
					}
					connPool.putConnection(conn);
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
			}
		} else {
			log.error(Config.CONNECTION_UNAVAILABLE);
		}
		return id;
	}

	public static int getUpdateDelete(String query, List params) {
		// log.info(query);
		int res = 0;
		Connection conn = connPool.getConnection();
		PreparedStatement ps = null;

		if (conn != null) {
			try {
				ps = QueryTool.getPrepStatement(conn, query, params);
				res = ps.executeUpdate();
			} catch (SQLException e) {
				log.error(e.getMessage());
			} finally {
				try {
					if (ps != null) {
						ps.close();
					}
					connPool.putConnection(conn);
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
			}
		} else {
			log.error(Config.CONNECTION_UNAVAILABLE);
		}
		return res;
	}

	public static PreparedStatement getPrepStatement(Connection conn,
			String sql, List params) {
		PreparedStatement ps = null;

		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);
				if (params != null) {
					int i = 1;
					for (Object obj : params) {
						if (obj instanceof Integer) {
							ps.setInt(i++, (Integer) obj);
						} else if (obj instanceof Long) {
							ps.setLong(i++, (Long) obj);
						} else if (obj instanceof String) {
							ps.setString(i++, (String) obj);
						} else if (obj instanceof Double) {
							ps.setDouble(i++, (Double) obj);
						} else if (obj instanceof Float) {
							ps.setFloat(i++, (Float) obj);
						} else if (obj instanceof Boolean) {
							ps.setBoolean(i++, (Boolean) obj);
						} else if (obj instanceof Date) {
							ps.setDate(i++,
									new java.sql.Date(((Date) obj).getTime()));
						}
					}
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return ps;
	}

	public static String getSQLDate(Date date) {
		String res = null;
		if (date != null) {
			res = "'" + new java.sql.Date(date.getTime()) + "'";
		}
		return res;
	}
}
