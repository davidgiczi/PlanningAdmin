package hu.david.giczi.catvhungaria.planningregister.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hu.david.giczi.catvhungaria.planningregister.model.PlanMetaData;
import hu.david.giczi.catvhungaria.planningregister.model.TimeStamp;



@WebServlet("/GetPlanRegistrations")
public class GetPlanRegistrations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	private List<PlanMetaData> planRegistrations;
	private String storedPlanNumber;
	private String storedYear;
	
    public GetPlanRegistrations() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter pw=response.getWriter();
		
		String planNumber = request.getParameter("plan");
		String year=request.getParameter("year");
		String modifyRecord=request.getParameter("modify");
		String deleteRecord=request.getParameter("del");
		String close=request.getParameter("close");
		
		
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
		
		EntityManager em = emf.createEntityManager();
		
	
		if(planNumber!=null) {
		
			
		storedPlanNumber=planNumber;
		storedYear=year;
			
		showPlanByPlanNumber(storedPlanNumber, storedYear, emf, em, pw, request, response);	
			
		
		}
		
		
		
		else if(modifyRecord!=null) {
			
			
		modifyRecordByRecordNumber(modifyRecord, emf, em, pw, request, response);
			
		
		}
		else if(deleteRecord!=null) {
			
			
		deleteRecord(deleteRecord, emf, em, pw, request, response);
			
		}
		
		else if("closing".equals(close)) {
			
			
		closeThePlan(storedPlanNumber, storedYear, emf, em, request, response);
			
		}
		
		else {
			
			showPlanByPlanNumber(storedPlanNumber, storedYear, emf, em, pw, request, response);	
			
		}
		
		
		
	
			
		}
		

	
	private void showPlanByPlanNumber(String storedPlanNumber, String storedYear,
			EntityManagerFactory emf, EntityManager em, PrintWriter pw, 
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	try {

		
		if(Integer.parseInt(storedPlanNumber)>0) {
			
			String number="CATV-"+storedPlanNumber+"/"+storedYear+"%";
			
			TypedQuery<PlanMetaData> query =em.createQuery("SELECT p FROM PlanMetaData p WHERE p.planNumber LIKE \'"+number+"\'", PlanMetaData.class);
			
			planRegistrations=query.getResultList();
			
			Collections.sort(planRegistrations);
			
			if(!planRegistrations.isEmpty() || planRegistrations.size()>Integer.parseInt(storedPlanNumber)-1) {
				
				
				setSession(planRegistrations, request);
				
				request.setAttribute("regs", planRegistrations);
				
				request.setAttribute("fromAll", false);
				
				request.getRequestDispatcher("regs.jsp").forward(request, response);
				
			}
			else {
				
				request.setAttribute("years", TimeStamp.getYears(5));
				request.setAttribute("msg", -1);
				RequestDispatcher req=request.getRequestDispatcher("start.jsp");
	    		pw.println("<h4>Plan with number \'"+number+"\' value not found!</h4>");
	    		req.include(request, response);
				
			}
			
			
		}
		
		else {
			
			request.setAttribute("years", TimeStamp.getYears(5));
			request.setAttribute("msg", -1);
			RequestDispatcher req=request.getRequestDispatcher("start.jsp");
    		pw.println("<h4>Invalid input value, please, add it again!</h4>");
    		req.include(request, response);
				
		}
		
		

		}
		catch(NumberFormatException e) {
			
			request.setAttribute("years", TimeStamp.getYears(5));
			request.setAttribute("msg", -1);
			RequestDispatcher req=request.getRequestDispatcher("start.jsp");
    		pw.println("<h4>Invalid input value, please, add it again!</h4>");
    		req.include(request, response);
			
			
		}
	
	finally {
    	
        if(em.getTransaction().isActive())
            em.getTransaction().rollback();
        em.close();
    }
		
		
	}
	
	
	private void modifyRecordByRecordNumber(String modifyRecord,
			EntityManagerFactory emf, EntityManager em, PrintWriter pw, 
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			
			if(Integer.parseInt(modifyRecord)>0 && planRegistrations.size()>Integer.parseInt(modifyRecord)-1) {
				
					
					PlanMetaData data=planRegistrations.get(Integer.parseInt(modifyRecord)-1);
					
					request.setAttribute("record", data);
					
					request.setAttribute("fromAll", false);
					
					if(data.getIsOK()) {
						
						request.setAttribute("ready", true);
					}
					else {
						
						request.setAttribute("ready", false);
						
					}
					
					request.getRequestDispatcher("modify.jsp").forward(request, response);
					
				}
			
				else {
					
					request.setAttribute("regs", planRegistrations);
					request.setAttribute("fromAll", false);
										
					RequestDispatcher req=request.getRequestDispatcher("regs.jsp");
		    		pw.println("<h4>Record with number \'" +modifyRecord+"\' value not found!</h4>");
		    		req.include(request, response);
					
					
				}
					
			
		}
		catch (NumberFormatException e) {
			
			request.setAttribute("regs", planRegistrations);
			request.setAttribute("fromAll", false);
			
			RequestDispatcher req=request.getRequestDispatcher("regs.jsp");
    		pw.println("<h4>Invalid input value, please, add it again!</h4>");
    		req.include(request, response);
			
			
		}
		
	}
	
	
	private void closeThePlan(String storedPlanNumber, String storedYear,
			EntityManagerFactory emf, EntityManager em, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	try	{
		
		String number="CATV-"+storedPlanNumber+"/"+storedYear+"%";
		
		TypedQuery<PlanMetaData> query =em.createQuery("SELECT p FROM PlanMetaData p WHERE p.planNumber LIKE \'"+number+"\'", PlanMetaData.class);
		
		planRegistrations=query.getResultList();
		
		
		for (PlanMetaData planMetaData : planRegistrations) {
			
			if(!planMetaData.getIsOK()) {
			
			PlanMetaData plan=em.find(PlanMetaData.class, planMetaData.getId());
			
			em.getTransaction().begin();
			plan.setIsOK(true);
			em.getTransaction().commit();
			
	}	
				
				
		}
		
		query =em.createQuery("SELECT p FROM PlanMetaData p WHERE p.planNumber LIKE \'"+number+"\'", PlanMetaData.class);
		
		planRegistrations=query.getResultList();
		
		Collections.sort(planRegistrations);
		
		setSession(planRegistrations, request);
		
		request.setAttribute("regs", planRegistrations);
			
		request.setAttribute("fromAll", false);
		
		request.getRequestDispatcher("regs.jsp").forward(request, response);
		
	}
		finally {
	    	
	        if(em.getTransaction().isActive())
	            em.getTransaction().rollback();
	        em.close();
	    }
		
	}

	private void deleteRecord(String deleteRecord, EntityManagerFactory emf, EntityManager em,  PrintWriter pw,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
try {
		
			
			if(Integer.parseInt(deleteRecord)>0 && Integer.parseInt(deleteRecord)-1<planRegistrations.size()) {
				
			PlanMetaData data=planRegistrations.get(Integer.parseInt(deleteRecord)-1);
			
			planRegistrations.remove(Integer.parseInt(deleteRecord)-1);
			
			request.setAttribute("record", deleteRecord);
			request.setAttribute("reg", data);
			request.setAttribute("fromAll", false);
			request.getRequestDispatcher("del.jsp").forward(request, response);
			
			
			}
			else {
				
				request.setAttribute("regs", planRegistrations);
				request.setAttribute("fromAll", false);
				
				RequestDispatcher req=request.getRequestDispatcher("regs.jsp");
	    		pw.println("<h4>Record with number \'"+deleteRecord+"\' not found!</h4>");
	    		req.include(request, response);
				
			}
				
			
		} catch (NumberFormatException e) {
			
			
			request.setAttribute("regs", planRegistrations);
			request.setAttribute("fromAll", false);
			
			RequestDispatcher req=request.getRequestDispatcher("regs.jsp");
			pw.println("<h4>Invalid input value, please, add it again!</h4>");
			req.include(request, response);
			
		}
		
	}
	
	private void setSession(List<PlanMetaData> store,  HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("store", store);
		session.setAttribute("fromAll", false);
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
