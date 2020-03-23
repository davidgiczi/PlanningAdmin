package hu.david.giczi.catvhungaria.planningregister.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.catvhungaria.planningregister.model.Names;
import hu.david.giczi.catvhungaria.planningregister.model.TimeStamp;



@WebServlet("/Init")
public class Init extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Init() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw=response.getWriter();
		
		String username=request.getParameter("user");
		String password=request.getParameter("pass");
		
		
		
		if("david.giczi".equals(username) && "localhero".equals(password)) {
			
			
			request.setAttribute("years", TimeStamp.getYears(5));
			request.setAttribute("msg", -1);
			request.getRequestDispatcher("start.jsp").forward(request, response);
			
			
		}
		else {
			
			RequestDispatcher req=request.getRequestDispatcher("index.jsp");
    		pw.println("<h4>Invalid username or password, please, add it again!</h4>");
    		req.include(request, response);
			
			
		}
		
		if(username==null && password==null) {
			
			
			new Names();
			
			request.setAttribute("catvnames", Names.getCatvNames());
			request.setAttribute("upcnames", Names.getUpcNames());
			
			request.getRequestDispatcher("input.jsp").forward(request, response);
			
			
			
		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
