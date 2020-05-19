package vegas.caleb.echo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EchoServlet
 */
@WebServlet("/EchoServlet")
public class EchoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String HTML_START=
			"<!DOCTYPE html>" + 
			"<html lang=\"en\">" + 
			"  <head>" + 
			"    <meta charset=\"UTF-8\">" + 
			"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" + 
			"    <title>King of the Hamburders</title>\r\n" + 
			"  </head>" + 
			"  <body>";
	public static final String HTML_END="</body></html>";
			
    /**
     * Default constructor. 
     */
    public EchoServlet() {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.append(HTML_START);
		out.append("Served at: ").append(request.getContextPath());
		out.append("<br>Date and Time: " + new Date());
		out.append(HTML_END);
		//Date date = new Date();
		//out.println(HTML_START + "<h2>Hi There!</h2><br/><h3>Date="+date +"</h3>"+HTML_END);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		for (Entry<String, String[]> param : request.getParameterMap().entrySet()) {
			out.println(param.getKey() + ": " + param.getValue());
			String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

			out.println("Body: " + body);
		}
		
		//doGet(request, response);
	}

}