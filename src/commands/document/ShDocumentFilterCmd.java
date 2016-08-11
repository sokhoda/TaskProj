package commands.document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import commands.ICommand;

public class ShDocumentFilterCmd implements ICommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {

		return Config.getInstance().getProperty(Config.DOCUMENTFILTER);
	}

}
