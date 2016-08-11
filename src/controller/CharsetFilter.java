package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharsetFilter implements Filter {
	private String encoding;

	@Override
	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("requestEncoding");

		encoding = "utf8";
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain next) throws IOException, ServletException {
		req.setCharacterEncoding(encoding);
		res.setCharacterEncoding(encoding);

		next.doFilter(req, res);
	}

	@Override
	public void destroy() {
	}
}
