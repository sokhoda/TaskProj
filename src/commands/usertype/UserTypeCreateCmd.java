package commands.usertype;

import interfaces.dao.IUserTypeDao;
import interfaces.service.IUserTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;
import bean.Notification;
import bean.UserType;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class UserTypeCreateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(UserTypeCreateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IUserTypeService utypeService = ServiceFactory.getUserTypeService();
		IUserTypeDao dao = MySqlDaoFactory.getUserTypeDao();

		UserType uType = CommTool.getUserType(req, res);
		req.setAttribute("message", new Notification(
				utypeService.create(uType), EnumOperations.CREATE));
		req.setAttribute("utype", uType);
		CommTool.setSessionAttr(req, "utypes", dao.findAll());
		return new ShUserTypeCreateCmd().execute(req, res);
	}

}
