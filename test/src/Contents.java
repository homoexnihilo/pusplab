

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Survey.
 */
@WebServlet("/Contents")
public class Contents extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Define states 
	private static final int NEED_NAME = 0;
	private static final int NEED_PROJECT_TYPE = 1;
	private static final int NEED_PROJECT_DATA = 2;
	
	private FormGenerator formGenerator = new FormGenerator(); 
	
	static private Database db = new Database();
	
		
    /**
     * Default constructor. 
     */
    public Contents() {
    }
    
        
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get the session
		HttpSession session = request.getSession(true);
		
		
		
		boolean sessionShouldBeEnded = false;
		
		
		// Get a writer, which will be used to write the next page for the user 
		PrintWriter out = response.getWriter();
		
		// Start the page, print the HTML header and start the body part of the page
		out.println("<html>");
        out.println("<head><title> FANTASTIC WEB APPLICATION </title></head>");
        out.println("<body>");
        out.println("<h1>Contents:</h1>");
        
        try {
        	for(String line : db.getDatabase()) {
        		out.println(line + "<br>");
        	}
        } catch(SQLException e) {
        	e.printStackTrace();
        }

        // Print the end of the HTML-page
        out.println("</body></html>");
        
        if (sessionShouldBeEnded)
        	session.invalidate();        		    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
