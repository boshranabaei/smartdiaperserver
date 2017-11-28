package Server;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SensorServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Baby[] babies= MySQLBridge.msql.getBabies();
		int[] babies_status = new int[5];
		PrintWriter out = response.getWriter();

		for (int i = 0; i < 5; i++) {
			babies_status[i] = Integer.parseInt(request.getParameter("BABY" + (i + 1)));
			
			if(babies[i].status==1)
				babies_status[i]=1;
			
			out.println("Baby " + i + " : " + babies_status[i]);
		}

		if (MySQLBridge.msql.changeBabyStatus(babies_status)) {
			out.println("---The databse is updated sucessfulluy---");
		} else {
			out.println("---ERR in updating the database---");
		}
		out.close();
	}

}
