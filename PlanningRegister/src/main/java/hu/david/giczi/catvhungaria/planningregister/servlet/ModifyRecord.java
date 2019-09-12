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

@WebServlet("/ModifyRecord")
public class ModifyRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ModifyRecord() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
		
		EntityManager em = emf.createEntityManager();
		
		
		
		try {
		
		
		String id=request.getParameter("id");
		String number=request.getParameter("number");
		String name=request.getParameter("name");
		String catv=request.getParameter("catv");
		String catvdate=request.getParameter("catvdate");
		String upc=request.getParameter("upc");
		String upcdate=request.getParameter("upcdate");
		String comment=request.getParameter("comment");
		String kozmu=request.getParameter("kozmu");
		String road=request.getParameter("road");
		String owner=request.getParameter("owner");
		String fromAll=request.getParameter("all");
		String isReady=request.getParameter("ready");
		
		  if(name.length()>255) {
          	name=name.substring(0,255);
          }
          
          if(comment.length()>255) {
          	comment=comment.substring(0,255);
          }
          
          Boolean ready=false;
          
          if("true".equals(isReady)) {
        	  
        	ready=true;
          }
          
          
		PlanMetaData plan=em.find(PlanMetaData.class, Long.parseLong(id));
		
		em.getTransaction().begin();
		
		plan.setPlanNumber(number);
		plan.setPlanName(name);
		plan.setNameOfCATVControlPerson(catv);
		plan.setDateOfCATVControl(catvdate);
		plan.setNameOfUPCControlPerson(upc);
		plan.setDateOfUPCControl(upcdate);
		plan.setComment(comment);
		plan.setEkozmu(kozmu);
		plan.setRoadStatement(road);
		plan.setOwnerStatement(owner);
		plan.setIsOK(ready);
		em.getTransaction().commit();
		
		request.setAttribute("ready", ready);
		
		if("true".equals(fromAll)) {
			
			request.getRequestDispatcher("GetAllRegistrations").forward(request, response);
		}
		else if("false".equals(fromAll)) {
			

			request.getRequestDispatcher("GetPlanRegistrations").forward(request, response);
			
		}
		
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
