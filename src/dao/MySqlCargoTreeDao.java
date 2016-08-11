package dao;

import interfaces.dao.ICargoTreeDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.CargoTree;
import datasource.QueryTool;

public class MySqlCargoTreeDao implements ICargoTreeDao {
	private static final Logger log = LogManager
			.getLogger(MySqlCargoTreeDao.class.getName());
	private static final String INSERT = "INSERT INTO CARGO_TREE VALUES(0, ";
	private static final String UPDATE = "UPDATE CARGO_TREE SET ";
	private static final String DELETE = "DELETE FROM CARGO_TREE ";
	private static final String SELECTALL = "SELECT * FROM CARGO_TREE ";

	// @formatter:off
	@Override
	public long create(final CargoTree cgTree) {
		final String query = INSERT +
				cgTree.getCargId() + "," +
				cgTree.getCgtrF0() + "," +
				cgTree.getCgtrF1() + "," +
				cgTree.getCgtrF2() + ")";
		return QueryTool.simpleQueryInsert(query);
	}
	// @formatter:on

	@Override
	public CargoTree read(final long id) {
		final List<CargoTree> list = QueryTool.getSelect(this, SELECTALL
				+ "WHERE cgtrId = " + id, null);
		return list != null ? list.get(0) : null;
	}

	// @formatter:off
	@Override
	public boolean update(final CargoTree cgTree) {
		final String query = UPDATE +
				"cargId = '" + cgTree.getCargId() + "',"
				+ "cgtrF0 = '" + cgTree.getCgtrF0() + "',"
				+ "cgtrF1 = '" + cgTree.getCgtrF1() + "',"
				+ "cgtrF2 = '" + cgTree.getCgtrF2() + "'"
				+ " \n WHERE cgtrId =" + cgTree.getCgtrId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}
	// @formatter:on
	@Override
	public boolean delete(final CargoTree cgTree) {
		final String query = DELETE + "WHERE cgtrId = " + cgTree.getCgtrId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}

	@Override
	public List<CargoTree> findAll() {
		return QueryTool.getSelect(this, SELECTALL, null);
	}

	@Override
	public List<CargoTree> findByCargoId(Long cargId) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE cargId = "
				+ cargId, null);
	}

	// 	@formatter:off
	public static List<CargoTree> fillEntityFields(ResultSet rs) throws SQLException {
		List<CargoTree> list = new ArrayList<>();
		do {
			list.add(new CargoTree(rs.getLong(1),
					rs.getLong(2),
					rs.getLong(3),
					rs.getLong(4),
					rs.getLong(5)));
		} while (rs.next());
		return list;
	}

	// @formatter:on

}
