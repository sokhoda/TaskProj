package commands.agtype;

import interfaces.dao.IAgTypeDao;
import interfaces.service.IAgTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.factory.ServiceFactory;
import bean.AgType;
import bean.Notification;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class AgTypeCreateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(AgTypeCreateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IAgTypeService agtService = ServiceFactory.getAgTypeService();
		IAgTypeDao agtdao = MySqlDaoFactory.getAgTypeDao();

		AgType agType = CommTool.getAgType(req, res);
		req.setAttribute("message", new Notification(agtService.create(agType),
				EnumOperations.CREATE));
		req.setAttribute("agtype", agType);

		CommTool.setSessionAttr(req, "agtypes", agtdao.findAll());
		return new ShAgTypeCreateCmd().execute(req, res);
	}

}
