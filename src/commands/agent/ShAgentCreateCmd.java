package commands.agent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import commands.CommTool;
import commands.ICommand;

public class ShAgentCreateCmd implements ICommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		CommTool.setSessionAttr(req, "mode", Config.CREATEMODE);
		return Config.getInstance().getProperty(Config.AGENTEDIT);
	}

}
