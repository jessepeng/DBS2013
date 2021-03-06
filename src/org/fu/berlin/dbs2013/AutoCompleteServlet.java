package org.fu.berlin.dbs2013;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AutoCompleteServlet
 */
@WebServlet("/AutoCompleteServlet")
public class AutoCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoCompleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintStream responseStream = new PrintStream(response.getOutputStream());
		Database db = Database.getInstance();
		
		String suggest = request.getParameter("suggest");
		
		if (suggest != null) {
			
			List<String> suggestions = db.getAutoCompleteSuggestions(suggest);
			
			StringBuilder responseString = new StringBuilder("[");
			
			for (String suggestion : suggestions) {
				responseString.append("\"" + suggestion + "\"").append(",");
			}
			
			responseString.deleteCharAt(responseString.length() - 1).append("]");
			
			response.setContentType("application/json");
			response.setContentLength(responseString.length());
			response.setCharacterEncoding("ISO-8859-1");
			responseStream.println(responseString);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
