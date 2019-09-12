package hu.david.giczi.catvhungaria.planningregister.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.catvhungaria.planningregister.model.Names;
import hu.david.giczi.catvhungaria.planningregister.model.PlanMetaData;
import hu.david.giczi.catvhungaria.planningregister.model.TimeStamp;


@WebServlet("/InputData")
public class InputData extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
	
    public InputData() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		PrintWriter pw=response.getWriter();
		
		
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
	
		EntityManager em = emf.createEntityManager();
		
		
        try {
           
        	String number=request.getParameter("number");
  
        	
        	if ("".equals(number) || Integer.parseInt(number)<=0) {
        		
        		new Names();
    			
    			request.setAttribute("catvnames", Names.getCatvNames());
    			request.setAttribute("upcnames", Names.getUpcNames());
        		
        		RequestDispatcher req=request.getRequestDispatcher("input.jsp");
        		pw.println("<h4>Invalid input value, please, add it again!</h4>");
        		req.include(request, response);
        		
        	}
        	
        	else {
        		
        	
        		        
            String name = request.getParameter("name");
            String catv=request.getParameter("catv");
            String upc=request.getParameter("upc");
            String comment=request.getParameter("comment");
            String isOk=request.getParameter("ready");
            String kozmu=request.getParameter("kozmu");
            String road=request.getParameter("road");
            String owner=request.getParameter("owner");
            
            Boolean ready=false;
           
             
            if("true".equals(isOk)) {
            	
            	ready=true;
            }
            
            if("true".equals(kozmu)) {
            	
            kozmu=TimeStamp.timeStamp();
            }
            	
            if("true".equals(road)) {
            	
            	road=TimeStamp.timeStamp();
            }
			
            if("true".equals(owner)) {
	
            	owner=TimeStamp.timeStamp();
            }
            
            if(name.length()>255) {
            	name=name.substring(0,255);
            }
            
            if(comment.length()>255) {
            	comment=comment.substring(0,255);
            }
            
            
            
                em.getTransaction().begin();
                em.persist(new PlanMetaData(number, name, catv, TimeStamp.timeStamp(), upc,
                				TimeStamp.timeStamp(), kozmu, road, owner,  comment, ready));
                
                em.getTransaction().commit();
                
                new Names();
    			
    			request.setAttribute("catvnames", Names.getCatvNames());
    			request.setAttribute("upcnames", Names.getUpcNames());
             
                RequestDispatcher req=request.getRequestDispatcher("input.jsp");
                pw.println("<h4>You have added metadata of the the plan successfully!</h4>");
                req.include(request, response);
     
        	}
        }
      
        catch (NumberFormatException e) {
			
        	new Names();
			
			request.setAttribute("catvnames", Names.getCatvNames());
			request.setAttribute("upcnames", Names.getUpcNames());
        	
        	RequestDispatcher req=request.getRequestDispatcher("input.jsp");
    		pw.println("<h4>Invalid input value, please, add it again!</h4>");
    		req.include(request, response);
        	
		}
        
        finally {
        	
        	
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            em.close();
        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
