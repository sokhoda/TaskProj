package commands.document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.CommTool;
import commands.ICommand;

public class DocSetFilterAttrCmd implements ICommand {
	private static final Logger log = LogManager
			.getLogger(DocSetFilterAttrCmd.class.getName());

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {

		CommTool.setSessionAttr(req, "documentfilter",
				CommTool.getParamString(req, "documentfilter"));
		CommTool.setSessionAttr(req, "docnopattern",
				CommTool.getParamString(req, "docnopattern"));
		CommTool.setSessionAttr(req, "cargopattern",
				CommTool.getParamString(req, "cargopattern"));

		return new ShDocumentListCmd().execute(req, res);
	}
}
