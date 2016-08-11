package tags;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyBodyTagHandler extends BodyTagSupport {
	private static final Logger log = LogManager
			.getLogger(MyBodyTagHandler.class.getName());
	private int sizze;
	SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

	public void setSizze(int sizze) {
		this.sizze = sizze;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext
					.getOut()
					.write("<table class=\"table table-striped table-hover table-condensed \"  border=\"2\" style=\"width:95%;\"");
			pageContext.getOut().write("<tr><td>");
		} catch (IOException e) {
			log.error(e);
		}
		return EVAL_BODY_INCLUDE;
	}

	// @Override
	// public void doInitBody() throws JspException {
	// // TODO Auto-generated method stub
	// super.doInitBody();
	// }

	@Override
	public int doAfterBody() throws JspException {
		if (sizze-- > 1) {
			try {
				pageContext.getOut().write("</td></tr><tr><td>");
			} catch (IOException e) {
				log.error(e);
			}
			return EVAL_BODY_AGAIN;
		} else {
			return SKIP_BODY;
		}
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().write("</td></tr>");
			pageContext.getOut().write("</table>");
		} catch (IOException e) {
			log.error(e);
		}
		return SKIP_BODY;
	}

}
