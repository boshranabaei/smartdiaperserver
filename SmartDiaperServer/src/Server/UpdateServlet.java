package Server;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SuppressWarnings("serial")
public class UpdateServlet extends HttpServlet {

	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Gson gson = new GsonBuilder().create();
		PrintWriter out = response.getWriter();
		
		Baby[] babies= MySQLBridge.msql.getBabies();
		for(int i=0;i<babies.length;i++)
			babies[i].image=null;
		out.println( gson.toJson(babies));
		out.close();
		
	}
}
