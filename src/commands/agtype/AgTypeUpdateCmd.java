package commands.agtype;

import interfaces.dao.IAgTypeDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.AgType;
import bean.Notification;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class AgTypeUpdateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(AgTypeUpdateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IAgTypeDao dao = MySqlDaoFactory.getAgTypeDao();

		AgType agType = CommTool.getAgType(req, res);

		req.setAttribute("message", new Notification(dao.update(agType) ? 1L
				: 0L, EnumOperations.UPDATE));
		req.setAttribute("agtype", agType);
		CommTool.setSessionAttr(req, "agtypes", dao.findAll());

		return Config.getInstance().getProperty(Config.AGTYPEEDIT);
	}
}
