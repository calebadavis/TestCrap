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

import org.json.JSONException;
import org.json.JSONObject;

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
		if (lastPost != null) out.append("<br>Most recent JSON POST:<br>" + lastPost);
		out.append(HTML_END);
		//Date date = new Date();
		//out.println(HTML_START + "<h2>Hi There!</h2><br/><h3>Date="+date +"</h3>"+HTML_END);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Got POST request...");
		Map<String, String[]> params = request.getParameterMap();
		int paramCount = params.size();
		for (Entry<String, String[]> param : request.getParameterMap().entrySet()) {
			System.out.println(param.getKey() + ": " + param.getValue());
		}
		String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		try {
			lastPost = new JSONObject(body);
		} catch (JSONException jse) {
			System.out.println("Incoming POST could not be converted to JSON: " + body);
		}
		System.out.println("Body: " + body);
		JSONObject responseJSON = new JSONObject();
		JSONObject routineConfig = new JSONObject();
		try {
			routineConfig.put("hours", 10);
			routineConfig.put("minutesOn", 25);
			routineConfig.put("minutesOff", 5);
			routineConfig.put("pressure", 30);
			responseJSON.put("success", true);
			responseJSON.put("routineConfig", routineConfig);
		} catch (JSONException je) {
			System.out.println("Error building JSON response: " + je.getMessage());
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(responseJSON);
		
		
		//doGet(request, response);
	}
	
	private JSONObject lastPost = null;

}
