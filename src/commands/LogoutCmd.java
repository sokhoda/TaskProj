package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Config;

public class LogoutCmd implements ICommand {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {

		req.getSession().invalidate();
		req.getSession(true);
		return Config.getInstance().getProperty(Config.INDEX);
	}
}
