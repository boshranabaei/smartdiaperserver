package Server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.resource.Resource;

public class ServerMain {

	public static void main(String[] args) throws Exception {

		// Increase the input size
		System.setProperty("org.eclipse.jetty.server.Request.maxFormContentSize", "-1");
		System.setProperty("org.eclipse.jetty.servlet.MaxAge", "1");

		// Create a basic Jetty server object
		Server server = new Server(8080);

		// Adding context handler for Servlets
		ServletContextHandler ServHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

		// ...Baby Data Servlet
		ServHandler.addServlet(BabyDataServlet.class, "/getbabydata");
		ServHandler.setBaseResource(Resource.newResource("."));

		// ...Sensor Servlet
		ServHandler.addServlet(SensorServlet.class, "/diaperstatus");
		ServHandler.setBaseResource(Resource.newResource("."));

		// ...Sensor Servlet
		ServHandler.addServlet(ResetServlet.class, "/resetdiapers");
		ServHandler.setBaseResource(Resource.newResource("."));

		// Adding handlers to the server
		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { ServHandler });
		server.setHandler(handlers);

		// Start things up! By using the server.join() the server thread will
		// join with the current thread
		server.start();
		server.dump();
		server.join();

	}
}
