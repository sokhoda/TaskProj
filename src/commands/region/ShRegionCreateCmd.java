package commands.region;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import commands.CommTool;
import commands.ICommand;

public class ShRegionCreateCmd implements ICommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		CommTool.setSessionAttr(req, "mode", Config.CREATEMODE);

		return Config.getInstance().getProperty(Config.REGIONEDIT);
	}
}
