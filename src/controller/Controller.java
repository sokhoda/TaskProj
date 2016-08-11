package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commands.ICommand;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet(name = "Controller", urlPatterns = { "/Controller" })
public class Controller extends HttpServlet {
	private static final Logger log = LogManager
			.getLogger(Controller.class.getName());
	private static final long serialVersionUID = 1L;
	private static final ControllerHelper cHelper = ControllerHelper
			.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {

	}

	public void performTask(HttpServletRequest req, HttpServletResponse res) {
		ICommand command = cHelper.getCommand(req);
		try {
			String page = command.execute(req, res);
			if (page != null) {
				RequestDispatcher dispatcher = req.getServletContext()
						.getRequestDispatcher(page);
				dispatcher.forward(req, res);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void getResult(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Integer x = Integer.parseInt(request.getParameter("x"));
		Integer y = Integer.parseInt(request.getParameter("y"));
		Integer sum = x + y;
		response.getWriter().print("x+y=" + sum);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().print("doGet::");
		performTask(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().print("doPost::");
		performTask(request, response);

	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

}
