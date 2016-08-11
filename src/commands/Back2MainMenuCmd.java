package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

public class Back2MainMenuCmd implements ICommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		CommTool.setSessionAttr(req, "mode", Config.DEFAULTMODE);
		return Config.getInstance().getProperty(Config.MAINMENU);
	}

}
