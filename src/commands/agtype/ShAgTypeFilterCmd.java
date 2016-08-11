package commands.agtype;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

import commands.ICommand;

public class ShAgTypeFilterCmd implements ICommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		return Config.getInstance().getProperty(Config.AGTYPEFILTER);
	}

}
