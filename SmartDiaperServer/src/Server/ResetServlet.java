package Server;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ResetServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		if (MySQLBridge.msql.resetDiapers()){
			out.println("--- Number of total diapers => 5 ---");
		} else {
			out.println("---ERR in changing the number of diapers---");
		}
		out.close();
	}

}
