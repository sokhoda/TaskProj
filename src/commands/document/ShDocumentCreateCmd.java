package commands.document;

import interfaces.dao.IAgentDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import commands.CommTool;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class ShDocumentCreateCmd implements ICommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IAgentDao agDao = MySqlDaoFactory.getAgentDao();
		CommTool.removeSessionAttr(req, "numroute");
		CommTool.removeSessionAttr(req, "alpharoute");
		CommTool.setDefaultDocumentToSession(req);
		CommTool.setSessionAttrString(req, "srcpage", Config.getInstance()
				.getProperty(Config.DOCUMENTCREATE));

		CommTool.getAndPutParamString(req, "documentfilter"); // docStatus
		CommTool.getAndPutParamString(req, "docnopattern"); // docNo
		CommTool.getAndPutParamString(req, "cargopattern");

		return Config.getInstance().getProperty(Config.DOCUMENTCREATE);
	}
}
