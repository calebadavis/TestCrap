package vegas.caleb.echo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START=
			"<!DOCTYPE html>" + 
			"<html lang=\"en\">" + 
			"  <head>" + 
			"    <meta charset=\"UTF-8\">" + 
			"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" + 
			"    <title>SpinalTech Smart Brace Administration (Demo)</title>\r\n" + 
			"  </head>" + 
			"  <body>" +
			"    <h1>SpinalTech Smart Brace Administration (Demo)</h1>";
	public static final String HTML_END="</body></html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.append(HTML_START);
		out.append("<form action=\"/Echo/AdminServlet\" method=\"post\">\n" + 
				"  <label for=\"hours\">Hours (between 1 and 23):</label>\n" + 
				"  <input type=\"number\" id=\"hours\" name=\"hours\" min=\"1\" max=\"23\">\n" +
				"  <br/>" +
				"  <label for=\"pressure\">Pressure (between 10 and 300mmHg):</label>\n" + 
				"  <input type=\"number\" id=\"pressure\" name=\"pressure\" min=\"10\" max=\"300\">\n" + 
				"  <br/>" +
				"  <label for=\"startDelay\">Start delay (minutes between 0 and 60):</label>\n" + 
				"  <input type=\"number\" id=\"startDelay\" name=\"startDelay\" min=\"0\" max=\"60\">\n" + 
				"  <br/>" +
				"  <label for=\"minutesOn\">Pressure on (minutes between 1 and 60):</label>\n" + 
				"  <input type=\"number\" id=\"minutesOn\" name=\"minutesOn\" min=\"1\" max=\"60\">\n" + 
				"  <br/>" +
				"  <label for=\"minutesOff\">Pressure off (minutes between 1 and 60):</label>\n" + 
				"  <input type=\"number\" id=\"minutesOff\" name=\"minutesOff\" min=\"1\" max=\"60\">\n" + 
				"  <input type=\"submit\">\n" + 
				"</form>\n");
		out.append(HTML_END);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("Got POST request...");
		Map<String, String[]> params = request.getParameterMap();
		int paramCount = params.size();
		for (Entry<String, String[]> param : request.getParameterMap().entrySet()) {
			System.out.println(param.getKey() + ": " + param.getValue());
		}
		String
			sPressure = request.getParameter("pressure"),
			sHours = request.getParameter("hours"),
			sMinutesOn = request.getParameter("minutesOn"),
			sMinutesOff = request.getParameter("minutesOff"),
			sStartDelay = request.getParameter("startDelay");
		ServletContext appContext = getServletContext();
		appContext.setAttribute("pressure", Integer.valueOf(sPressure));
		appContext.setAttribute("hours", Integer.valueOf(sHours));
		appContext.setAttribute("minutesOn", Integer.valueOf(sMinutesOn));
		appContext.setAttribute("minutesOff", Integer.valueOf(sMinutesOff));
		appContext.setAttribute("startDelay", Integer.valueOf(sStartDelay));			
	}

}
