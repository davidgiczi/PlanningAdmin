package hu.david.giczi.catvhungaria.planningregister.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.catvhungaria.planningregister.model.PlanMetaData;
import hu.david.giczi.catvhungaria.planningregister.model.TimeStamp;


@WebServlet("/ShowContent")
public class ShowContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ShowContent() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String fileURL = request.getParameter("url");
		
		
		if( !"".equals(fileURL) ) {
			
			Runtime run = Runtime.getRuntime();
			
			run.exec("C:\\Program Files (x86)\\Mozilla Thunderbird\\thunderbird " +fileURL);
			
		}
		
		
		Boolean from = (Boolean) request.getSession().getAttribute("fromAll");
		List<PlanMetaData> store = (List<PlanMetaData>) request.getSession().getAttribute("store");
		
		
		request.setAttribute("fromAll", from);
		request.setAttribute("regs", store);
		
		request.getRequestDispatcher("regs.jsp").forward(request, response);
			
	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
