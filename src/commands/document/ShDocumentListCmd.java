package commands.document;

import interfaces.dao.IAgentDao;
import interfaces.service.IDocumentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;

import commands.CommTool;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class ShDocumentListCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShDocumentListCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IDocumentService docService = ServiceFactory.getDocumentService();
		IAgentDao agDao = MySqlDaoFactory.getAgentDao();

		String documentfilter = CommTool.getSessionAttrString(req,
				"documentfilter");
		String docnopattern = CommTool
				.getSessionAttrString(req, "docnopattern");
		String cargopattern = CommTool
				.getSessionAttrString(req, "cargopattern");

		CommTool.setSessionAttr(req, "documentlist", docService
				.findByDocStatAndDocNoPattAndCargoPatt(documentfilter, "%"
						+ docnopattern + "%", "%" + cargopattern + "%"));

		CommTool.setSessionAttr(req, "docagentlist", agDao.findAll());

		return Config.getInstance().getProperty(Config.DOCUMENTLIST);
	}
}
