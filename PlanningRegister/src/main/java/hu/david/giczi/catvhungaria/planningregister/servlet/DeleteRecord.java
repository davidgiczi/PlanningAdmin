package hu.david.giczi.catvhungaria.planningregister.servlet;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.catvhungaria.planningregister.model.PlanMetaData;



@WebServlet("/DeleteRecord")
public class DeleteRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
       public DeleteRecord() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String delID=request.getParameter("id");
		String fromAll=request.getParameter("fromAll");
		
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
		
		EntityManager em = emf.createEntityManager();
		
	try	{	
			
				 PlanMetaData delRecord = em.find(PlanMetaData.class, Long.parseLong(delID));

				  em.getTransaction().begin();
				  em.remove(delRecord);
				  em.getTransaction().commit();
				
				  if("true".equals(fromAll)) {
					  
					  request.getRequestDispatcher("GetAllRegistrations").forward(request, response);
				  }
				  else if("false".equals(fromAll)) {
					  
					  request.getRequestDispatcher("GetPlanRegistrations").forward(request, response);
				  }
		
							
	}
						
		finally {
			
			 if(em.getTransaction().isActive())
		            em.getTransaction().rollback();
		        em.close();
			
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
