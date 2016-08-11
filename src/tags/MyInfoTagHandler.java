package tags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyInfoTagHandler extends TagSupport {
	private static final Logger log = LogManager
			.getLogger(MyInfoTagHandler.class.getName());

	private String label;
	SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().write(
					label + format1.format(Calendar.getInstance().getTime()));
		} catch (IOException e) {
			log.error(e);
		}
		return SKIP_BODY;
	}
}
