package commands.misc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import commands.CommTool;
import commands.ICommand;

public class ShMiscFilterCmd implements ICommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		CommTool.removeSessionAttr(req, "misc");
		CommTool.setRouteToSession(req);
		return Config.getInstance().getProperty(Config.MISCFILTER);
	}

}
