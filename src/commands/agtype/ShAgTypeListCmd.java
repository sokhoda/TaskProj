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

import commands.CommTool;
import commands.ICommand;

import dao.factory.MySqlDaoFactory;

public class ShAgTypeListCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShAgTypeListCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		IAgTypeDao agtDao = MySqlDaoFactory.getAgTypeDao();

		String agtypenamepattern = CommTool.getParamPut2SessionString(req,
				"agtypenamepattern");
		List<AgType> agtypelist = agtDao.findByNamePattern("%"
				+ agtypenamepattern + "%");
		req.setAttribute("agtypelist", agtypelist);

		req.setAttribute("agtypelistbean", new AgTypeList(agtypelist));
		return Config.getInstance().getProperty(Config.AGTYPELIST);
	}
}
