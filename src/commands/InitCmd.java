package commands;

import interfaces.dao.IAgTypeDao;
import interfaces.dao.ICountryDao;
import interfaces.dao.IUserTypeDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.DocStatus;
import controller.InternLoadPropertFiles;
import dao.factory.MySqlDaoFactory;

public class InitCmd implements ICommand {
	private static final Logger log = LogManager.getLogger(InitCmd.class
			.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IAgTypeDao agtdao = MySqlDaoFactory.getAgTypeDao();
		IUserTypeDao utypeDao = MySqlDaoFactory.getUserTypeDao();
		ICountryDao cntrDao = MySqlDaoFactory.getCountryDao();

		CommTool.setSessionAttr(req, "agtypes", agtdao.findAll());
		CommTool.setSessionAttr(req, "countries", cntrDao.findAll());
		CommTool.setSessionAttr(req, "utypes", utypeDao.findAll());
		CommTool.setSessionAttr(req, "createmode", Config.CREATEMODE);
		CommTool.setSessionAttr(req, "updatemode", Config.UPDATEMODE);
		CommTool.setSessionAttr(req, "selectmode", Config.SELECTMODE);
		CommTool.setSessionAttr(req, "defaultmode", Config.DEFAULTMODE);
		CommTool.setSessionAttr(req, "docstatuses", DocStatus.getList());
		// set possible languages to session
		String langstring = "EN";
		String str = InternLoadPropertFiles.getLangAbbrev(Config.i18N,
				Config.MAINMENUBUNDLE);
		if (str.length() > 0) {
			langstring = str;
		}
		CommTool.setSessionAttr(req, "langstring", langstring);
		CommTool.setBundles(req, req.getLocale());
		return Config.getInstance().getProperty(Config.LOGIN);
	}
}
