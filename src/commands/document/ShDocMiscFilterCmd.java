package commands.document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.CommTool;
import commands.ICommand;

public class ShDocMiscFilterCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(ShDocMiscFilterCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		CommTool.setSessionAttr(req, "document", CommTool.getDocument(req, res));

		// select cities
		CommTool.setSessionAttr(req, "mode", Config.SELECTMODE);

		return Config.getInstance().getProperty(Config.MISCFILTER);
	}
}
