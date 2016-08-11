package commands.agtype;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.CommTool;
import commands.ICommand;

public class ShAgTypeCreateCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShAgTypeCreateCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		CommTool.setSessionAttr(req, "mode", Config.CREATEMODE);

		return Config.getInstance().getProperty(Config.AGTYPEEDIT);
	}

}
