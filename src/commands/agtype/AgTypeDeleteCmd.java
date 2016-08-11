package commands.agtype;

import interfaces.dao.IAgTypeDao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.AgType;
import bean.AgTypeList;
import bean.Notification;

import commands.CommTool;
import commands.EnumOperations;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class AgTypeDeleteCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(AgTypeDeleteCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IAgTypeDao dao = MySqlDaoFactory.getAgTypeDao();

		Long agtId = CommTool.getParamLong(req, "agtId");
		req.setAttribute("message", new Notification(dao.deleteById(agtId) ? 1L
				: 0L, EnumOperations.DELETE));
		CommTool.setSessionAttr(req, "agtypes", dao.findAll());

		String agtypenamepattern = CommTool.getSessionAttrString(req,
				"agtypenamepattern");
		List<AgType> agtypelist = dao.findByNamePattern("%" + agtypenamepattern
				+ "%");
		req.setAttribute("agtypelist", agtypelist);
		req.setAttribute("agtypelistbean", new AgTypeList(agtypelist));
		return Config.getInstance().getProperty(Config.AGTYPELIST);
	}
}
