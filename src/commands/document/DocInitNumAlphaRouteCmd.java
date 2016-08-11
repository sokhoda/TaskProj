package commands.document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commands.CommTool;
import commands.ICommand;

public class DocInitNumAlphaRouteCmd implements ICommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {

		CommTool.setRouteToSession(req);

		return CommTool.getSessionAttrString(req, "srcpage");
	}
}
