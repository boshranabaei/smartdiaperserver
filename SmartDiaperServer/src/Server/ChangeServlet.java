package Server;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class ChangeServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		int babyId = Integer.parseInt(request.getParameter("BABY"));
		
		babyId++; 

		if (MySQLBridge.msql.changeBabyDiaper(babyId)) {
			out.println("---Babay "+ babyId+" diaper got changed sucessfulluy---");
		} else {
			out.println("---ERR in updating the database---");
		}
		out.close();
	}

}
