package dao;

import interfaces.dao.IDocumentDao;
import interfaces.service.IJournalService;
import interfaces.service.IMiscService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import manager.Message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.JournalService;
import service.MiscService;
import bean.Document;
import datasource.QueryTool;

public class MySqlDocumentDao implements IDocumentDao {
	private static final Logger log = LogManager
			.getLogger(MySqlDocumentDao.class.getName());
	private static final String INSERT = "INSERT INTO DOCUMENTS VALUES(0, ";
	private static final String UPDATE = "UPDATE DOCUMENTS SET ";
	private static final String DELETE = "DELETE FROM DOCUMENTS ";
	private static final String SELECTALL = "SELECT * FROM DOCUMENTS ";
	private static final String FROMDOCUMENTS = " FROM DOCUMENTS ";

	// @formatter:off
	@Override
	public long create(final Document document) {
		String query = INSERT +
				document.getMcId() +"," +
				document.getMgrId() + "," +
				document.getCustId() + "," +
				document.getCargoId() + "," +
				"'" + document.getDocNo() + "'," +
				QueryTool.getSQLDate(document.getDocDate()) + "," +
				"'" + document.getDocName() + "'," +
				document.getDocSum() + "," +
				document.getDocStatus() + "," +
				"'" + document.getDocTag() + "'," +
				document.getDocCargWeight() + "," +
				document.getDocCargVolume() + "," +
				QueryTool.getSQLDate(document.getDocLoadDate()) + "," +
				QueryTool.getSQLDate(document.getDocUnloadDate()) + "," +
				"'" + document.getDocLoadAddress() + "'," +
				"'" + document.getDocUnloadAddress() + "'," +
				"'" + document.getDocCustContactPersPhone() + "'," +
				"'" + document.getDocCustContactPersName() + "')";
		return QueryTool.simpleQueryInsert(query);
	}
	// @formatter:on

	@Override
	public Document read(final long id) {
		List<Document> list = QueryTool.getSelect(this, SELECTALL
				+ "WHERE docId = " + id, null);
		return list != null ? list.get(0) : null;
	}

	// @formatter:off
	@Override
	public boolean update(final Document document) {
		String query = UPDATE +
				"mcId = '" + document.getMcId() + "',"
				+ "mgrId = '" + document.getMgrId() + "',"
				+ "custId = '" + document.getCustId() + "',"
				+ "docNo = '" + document.getDocNo() + "',"
				+ "docDate = "  + QueryTool.getSQLDate(document.getDocDate()) + ","
				+ "docName = '" + document.getDocName() + "',"
				+ "docSum = '" + document.getDocSum() + "',"
				+ "docStatus = '" + document.getDocStatus() + "',"
				+ "docTag = '" + document.getDocTag() + "',"
				+ "docCargWeight = '" + document.getDocCargWeight() + "',"
				+ "docCargVolume = '" + document.getDocCargVolume() + "',"
				+ "docLoadDate = "   + QueryTool.getSQLDate(document.getDocLoadDate()) + ","
				+ "docUnloadDate = "  + QueryTool.getSQLDate(document.getDocUnloadDate()) + ","
				+ "docLoadAddress = '" + document.getDocLoadAddress() + "',"
				+ "docUnloadAddress = '" + document.getDocUnloadAddress() + "',"
				+ "docCustContactPersPhone = '" + document.getDocCustContactPersPhone() + "',"
				+ "docCustContactPersName = '" + document.getDocCustContactPersName() + "'"
				+ " \n WHERE docId =" + document.getDocId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}
	// @formatter:on

	@Override
	public boolean delete(final Document document) {
		String query = DELETE + "WHERE docId = " + document.getDocId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}

	@Override
	public List<Document> findAll() {
		return QueryTool.getSelect(this, SELECTALL, null);
	}

	@Override
	public List<Document> findByName(final String docName) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE docName = '"
				+ docName + "'", null);
	}

	@Override
	public List<Document> findByNamePattern(final String pattern) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE docName LIKE '"
				+ pattern + "'", null);
	}

	@Override
	public List<Document> findByDocStatus(final int docStatus) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE docStatus = "
				+ docStatus, null);
	}

	@Override
	public List<Document> findByDocSumInRange(final int sum1, final int sum2) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE dosSum BETWEEN "
				+ sum1 + " AND " + sum2, null);
	}

	@Override
	public List<Document> findByDocNo(final String docNo) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE docNo = '" + docNo
				+ "'", null);
	}

	@Override
	public List<Document> findByDocNoPattern(final String pattern) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE docNo LIKE '"
				+ pattern + "'", null);
	}

	@Override
	public List<Document> findByDate(final Date docDate) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE docDate = '"
				+ new java.sql.Date(docDate.getTime()) + "'", null);
	}

	@Override
	public List<Document> findByDateInPeriod(final Date docDate1,
			final Date docDate2) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE docDate BETWEEN '"
				+ new java.sql.Date(docDate1.getTime()) + "' AND '"
				+ new java.sql.Date(docDate2.getTime()) + "'", null);
	}

	@Override
	public List<Document> findByManager(final Long mgrId) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE mgrId = " + mgrId,
				null);
	}

	@Override
	public List<Document> findByMc(final Long mcId) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE mcId = " + mcId,
				null);
	}

	@Override
	public List<Document> findByCust(final Long custId) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE custId = "
				+ custId, null);
	}

	@Override
	public boolean deleteById(Long docId) {
		String query = DELETE + "WHERE docId = " + docId;
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}

	@Override
	public List<Document> findByDocStatAndDocNoPattAndCargoPatt(
			String documentfilter, String docnopattern, String cargopattern) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE docStatus IN ("
				+ documentfilter + ") AND docNo LIKE '" + docnopattern
				+ "' AND docTag LIKE '" + cargopattern + "'", null);
	}

	@Override
	public Long findLastDocNo() {
		Long res = null;
		String maxDocNo = QueryTool.getSelectSingleVal(
				"SELECT max(CAST(docNo AS DECIMAL(10,0))) " + FROMDOCUMENTS,
				null);
		try {
			res = Long.parseLong(maxDocNo);
		} catch (NumberFormatException e) {
			log.info(Message.getInstance().getProperty(
					Message.DOCNO_AUTONUM_GETLAST_FAIL));
		}
		return res;
	}

	// 	@formatter:off
	public static List<Document> fillEntityFields(ResultSet rs) throws SQLException {
		IMiscService miscService = new MiscService();
		IJournalService jService = new JournalService();
		String mscIds = null;
		Long docId = null;
		List<Document> list = new ArrayList<>();

		do {
			docId = rs.getLong(1);
			mscIds = jService.getMiscIdByDocId(docId);

			list.add( new Document(docId,
					rs.getLong(2),
					rs.getLong(3),
					rs.getLong(4),
					rs.getLong(5),
					rs.getString(6),
					rs.getDate(7),
					rs.getString(8),
					rs.getDouble(9),
					rs.getInt(10),
					rs.getString(11),
					rs.getDouble(12),
					rs.getDouble(13),
					rs.getDate(14),
					rs.getDate(15),
					rs.getString(16),
					rs.getString(17),
					rs.getString(18),
					rs.getString(19),
					miscService.getAlpharoute(mscIds, " - ")
					));
		} while (rs.next());
		return list;
	}

	// @formatter:on

}
