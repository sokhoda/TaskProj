package dao.factory;

import interfaces.dao.IAgTypeDao;
import interfaces.dao.IAgentDao;
import interfaces.dao.ICargoDao;
import interfaces.dao.ICargoTreeDao;
import interfaces.dao.ICountryDao;
import interfaces.dao.IDocumentDao;
import interfaces.dao.IJournalDao;
import interfaces.dao.IMiscDao;
import interfaces.dao.IRegionDao;
import interfaces.dao.IUserDao;
import interfaces.dao.IUserTypeDao;
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

public class MySqlDaoFactory {

	public static IAgentDao getAgentDao() {
		return new MySqlAgentDao();
	}

	public static IAgTypeDao getAgTypeDao() {
		return new MySqlAgTypeDao();
	}

	public static ICountryDao getCountryDao() {
		return new MySqlCountryDao();
	}

	public static IDocumentDao getDocumentDao() {
		return new MySqlDocumentDao();
	}

	public static IJournalDao getJournalDao() {
		return new MySqlJournalDao();
	}

	public static IMiscDao getMiscDao() {
		return new MySqlMiscDao();
	}

	public static IRegionDao getRegionDao() {
		return new MySqlRegionDao();
	}

	public static IUserDao getUserDao() {
		return new MySqlUserDao();
	}

	public static IUserTypeDao getUserTypeDao() {
		return new MySqlUserTypeDao();
	}

	public static ICargoDao getCargoDao() {
		return new MySqlCargoDao();
	}

	public static ICargoTreeDao getCargoTreeDao() {
		return new MySqlCargoTreeDao();
	}
}
