package hu.david.giczi.catvhungaria.planningregister.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import hu.david.giczi.catvhungaria.planningregister.model.PlanComparator;
import hu.david.giczi.catvhungaria.planningregister.model.PlanMetaData;
import hu.david.giczi.catvhungaria.planningregister.model.TimeStamp;

@WebServlet("/GetAllRegistrations")
public class GetAllRegistrations extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String savedYear;
	private String savedText;
	private List<PlanMetaData> allRegistrations;
	private List<PlanMetaData> yearRegistrations;
	private List<PlanMetaData> searchRegistrations;
	
    public GetAllRegistrations() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw=response.getWriter();
		
		String year=request.getParameter("year");
		String deleteRecord=request.getParameter("del");
		String modifyRecord=request.getParameter("modify");
		String searchText=request.getParameter("search");
		String back=request.getParameter("back");
			
		EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
		
		EntityManager em = emf.createEntityManager();
		
		
		
		if(back!=null) {
			
			savedText=null;
			
			request.setAttribute("years", TimeStamp.getYears(5));
			
			request.getRequestDispatcher("start.jsp").forward(request, response);
			
			return;
		}
		
			
			
				
		if(year!=null) {
			
			savedYear=year;
			
			if("all".equals(savedYear)) {
				
				showAllRegistrations(request, response, emf, em, pw);
				
			}
			else {
				
				showYearRegistrations(savedYear, request, response, emf, em, pw);
			}
			
			
		}
		else if(modifyRecord!=null) {
			
			
			if("all".equals(savedYear) && savedText==null) {
				
				
				modifyFromAllRegistrations(modifyRecord,request, response, emf, em, pw);
				
			}
			if(!"all".equals(savedYear) && savedText==null) {
				
				modifyFromYearRegistrations(modifyRecord, request, response, emf, em, pw);
				
			}
			else if(savedText!=null) {
				
				modifySearchRegistration(modifyRecord, request, response, emf, em, pw);
				
			}
					
			
		}
		else if(deleteRecord!=null){
			
			
			if("all".equals(savedYear) && savedText==null) {
				
				 
				deleteFromAllRegistrations(deleteRecord, request, response, emf, em, pw);
				
			}
			if(!"all".equals(savedYear) && savedText==null) {
				
				
				deleteFromYearRegistrations(deleteRecord, request, response, emf, em, pw);
				
			}
			else if(savedText!=null) {
				
				deleteSearchRegistration(deleteRecord, request, response, emf, em, pw);
				
			}
			
		}
		else if(searchText!=null) {
			
			savedText=searchText;
			
			showSearchRegistrations(savedText, request, response, emf, em, pw);
			
		}
		else {
			
			
			if("all".equals(savedYear) && savedText==null) {
			
				showAllRegistrations(request, response, emf, em, pw);
			}
			else if(!"all".equals(savedYear) && savedText==null){
				
				showYearRegistrations(savedYear, request, response, emf, em, pw);
				
			}
			else if(savedText!=null) {
				
				
				showSearchRegistrations(savedText, request, response, emf, em, pw);
				
				
			}
			
			
		}
		
	}

	
	
	private void showAllRegistrations(HttpServletRequest request, HttpServletResponse response,
			EntityManagerFactory emf, EntityManager em, PrintWriter pw) throws ServletException, IOException {
		
		
		try {	
			
			TypedQuery<PlanMetaData> query =em.createQuery("SELECT p FROM PlanMetaData p", PlanMetaData.class);
			
			allRegistrations=query.getResultList();
			
			Collections.sort(allRegistrations, new PlanComparator());
		
			request.setAttribute("regs", allRegistrations);
			
			request.setAttribute("fromAll", true);
			
			request.getRequestDispatcher("regs.jsp").forward(request, response);
			
			
		}	
			
		finally {
	    	
	        if(em.getTransaction().isActive())
	            em.getTransaction().rollback();
	        em.close();
	    }
		
	}
			
			
			
	
	private void modifyFromAllRegistrations(String modifyRecord, HttpServletRequest request, HttpServletResponse response,
			EntityManagerFactory emf, EntityManager em, PrintWriter pw) throws ServletException, IOException {
				
		
	try {	
		
		if(Integer.parseInt(modifyRecord)>0 && Integer.parseInt(modifyRecord)-1<allRegistrations.size()) {
			
			PlanMetaData data=allRegistrations.get(Integer.parseInt(modifyRecord)-1);
			
			request.setAttribute("record", data);
			request.setAttribute("fromAll", true);
			
			if(data.getIsOK()) {
				
				request.setAttribute("ready", true);
			}
			else {
				
				request.setAttribute("ready", false);
			}
			
			request.getRequestDispatcher("modify.jsp").forward(request, response);
		
		}
		else {
			
			request.setAttribute("regs", allRegistrations);
			request.setAttribute("fromAll", true);

        	RequestDispatcher req=request.getRequestDispatcher("regs.jsp");
    		pw.println("<h4>Record with number \'"+modifyRecord+"\' not found!</h4>");
    		req.include(request, response);
			
		}
		
	}
	catch(NumberFormatException e) {
		
		request.setAttribute("regs", allRegistrations);
		request.setAttribute("fromAll", true);
		
		RequestDispatcher req=request.getRequestDispatcher("regs.jsp");
		pw.println("<h4>Invalid input value, please, add it again!</h4>");
		req.include(request, response);
		
		
	}
			
	}
	
	
	private void showYearRegistrations(String savedYear, HttpServletRequest request, HttpServletResponse response,
			EntityManagerFactory emf, EntityManager em, PrintWriter pw) throws ServletException, IOException {
	
		
		try {
		
		String number="CATV-%/"+savedYear+"%";
		
		TypedQuery<PlanMetaData> query =em.createQuery("SELECT p FROM PlanMetaData p WHERE p.planNumber LIKE \'"+number+"\'", PlanMetaData.class);
		
		yearRegistrations=query.getResultList();
		
		
		if(yearRegistrations.isEmpty()) {
			
			request.setAttribute("years", TimeStamp.getYears(5));
			
			RequestDispatcher req=request.getRequestDispatcher("start.jsp");
    		pw.println("<h4>There aren't any registrations in "+ savedYear+".</h4>");
    		req.include(request, response);
			
			return;
		}
		
		
		Collections.sort(yearRegistrations, new PlanComparator());
	
		request.setAttribute("regs", yearRegistrations);
		
		request.setAttribute("fromAll", true);
		
		request.getRequestDispatcher("regs.jsp").forward(request, response);
		
		
		}
		finally {
	    	
	        if(em.getTransaction().isActive())
	            em.getTransaction().rollback();
	        em.close();
	    }
	}	
		
	
	
	private void modifyFromYearRegistrations(String modifyRecord, HttpServletRequest request, HttpServletResponse response,
			EntityManagerFactory emf, EntityManager em, PrintWriter pw) throws ServletException, IOException {
		
		
	try {	
		
		if(Integer.parseInt(modifyRecord)>0 && Integer.parseInt(modifyRecord)-1<yearRegistrations.size()) {
			
			PlanMetaData data=yearRegistrations.get(Integer.parseInt(modifyRecord)-1);
			
			request.setAttribute("record", data);
			request.setAttribute("fromAll", true);
			
			if(data.getIsOK()) {
				
				request.setAttribute("ready", true);
			}
			else {
				
				request.setAttribute("ready", false);
			}
			
			request.getRequestDispatcher("modify.jsp").forward(request, response);
		
		}
		else {
			
			request.setAttribute("regs", yearRegistrations);
			request.setAttribute("fromAll", true);

        	RequestDispatcher req=request.getRequestDispatcher("regs.jsp");
    		pw.println("<h4>Record with number \'"+modifyRecord+"\' not found!</h4>");
    		req.include(request, response);
			
		}
		
	}
	catch(NumberFormatException e) {
		
		request.setAttribute("regs", yearRegistrations);
		request.setAttribute("fromAll", true);
		
		RequestDispatcher req=request.getRequestDispatcher("regs.jsp");
		pw.println("<h4>Invalid input value, please, add it again!</h4>");
		req.include(request, response);
		
		
	}
}
	
		
	private void deleteFromAllRegistrations(String deleteRecord, HttpServletRequest request, HttpServletResponse response,
			EntityManagerFactory emf, EntityManager em, PrintWriter pw) throws ServletException, IOException{
		
try {
			
			
				if(Integer.parseInt(deleteRecord)>0 && Integer.parseInt(deleteRecord)-1<allRegistrations.size()) {
					
				PlanMetaData data=allRegistrations.get(Integer.parseInt(deleteRecord)-1);
				
				request.setAttribute("record", deleteRecord);
				request.setAttribute("reg", data);
				request.setAttribute("fromAll", true);
				request.getRequestDispatcher("del.jsp").forward(request, response);
				
				
				}
				else {
					
					request.setAttribute("regs", allRegistrations);
					request.setAttribute("fromAll", true);
					
					RequestDispatcher req=request.getRequestDispatcher("regs.jsp");
		    		pw.println("<h4>Record with number \'"+deleteRecord+"\' not found!</h4>");
		    		req.include(request, response);
					
				}
					
				
			} catch (NumberFormatException e) {
				
				
				request.setAttribute("regs", allRegistrations);
				request.setAttribute("fromAll", true);
				
				RequestDispatcher req=request.getRequestDispatcher("regs.jsp");
				pw.println("<h4>Invalid input value, please, add it again!</h4>");
				req.include(request, response);
				
			}
						
		
		
	}
	
	private void deleteFromYearRegistrations(String recordNumber, HttpServletRequest request, HttpServletResponse response,
	EntityManagerFactory emf, EntityManager em, PrintWriter pw) throws ServletException, IOException{
		
		
		try {
			
			
			if(Integer.parseInt(recordNumber)>0 && Integer.parseInt(recordNumber)-1<yearRegistrations.size()) {
				
			PlanMetaData data=yearRegistrations.get(Integer.parseInt(recordNumber)-1);
			
			request.setAttribute("record", recordNumber);
			request.setAttribute("reg", data);
			request.setAttribute("fromAll", true);
			request.getRequestDispatcher("del.jsp").forward(request, response);
			
			
			}
			else {
				
				request.setAttribute("regs", yearRegistrations);
				request.setAttribute("fromAll", true);
				
				RequestDispatcher req=request.getRequestDispatcher("regs.jsp");
	    		pw.println("<h4>Record with number \'"+recordNumber+"\' not found!</h4>");
	    		req.include(request, response);
				
			}
				
			
		} catch (NumberFormatException e) {
			
			
			request.setAttribute("regs", yearRegistrations);
			request.setAttribute("fromAll", true);
			
			RequestDispatcher req=request.getRequestDispatcher("regs.jsp");
			pw.println("<h4>Invalid input value, please, add it again!</h4>");
			req.include(request, response);
			
		}
		
		
		
	}
	
	

	private void showSearchRegistrations(String searchText, HttpServletRequest request, HttpServletResponse response,
	EntityManagerFactory emf, EntityManager em, PrintWriter pw) throws ServletException, IOException{
		
				
		if("".equals(searchText)) {
			
			request.setAttribute("years", TimeStamp.getYears(5));
			
			RequestDispatcher req=request.getRequestDispatcher("start.jsp");
    		pw.println("<h4>Invalid input value, please, add it again!</h4>");
    		req.include(request, response);
			
			return;
				
		}
		
		
		try {
			
			TypedQuery<PlanMetaData> query =em.createQuery("SELECT p FROM PlanMetaData p", PlanMetaData.class);
			
			List<PlanMetaData> store=query.getResultList();
			
			Collections.sort(store, new PlanComparator());
			
			searchRegistrations=new ArrayList<>();
			
			for (PlanMetaData planMetaData : store) {
				
				
				
				if(planMetaData.getPlanName().contains(searchText) || planMetaData.getComment().contains(searchText)) {
					
					searchRegistrations.add(planMetaData);
					
				}	
			}
			
			
			if(searchRegistrations.isEmpty()) {
				
				request.setAttribute("years", TimeStamp.getYears(5));
				
				RequestDispatcher req=request.getRequestDispatcher("start.jsp");
	    		pw.println("<h4>Record by text \'"+searchText+ "\' not found!</h4>");
	    		req.include(request, response);
				
	    		savedText=null;
	    		
				return;
				
			}
			
			request.setAttribute("regs", searchRegistrations);
			
			request.setAttribute("fromAll", true);
			
			request.getRequestDispatcher("regs.jsp").forward(request, response);
			
			
			
			
		}
		finally {
	    	
	        if(em.getTransaction().isActive())
	            em.getTransaction().rollback();
	        em.close();
	    }
		
		
		
	}
	
	private void deleteSearchRegistration(String deleteRecord, HttpServletRequest request, HttpServletResponse response,
			EntityManagerFactory emf, EntityManager em, PrintWriter pw) throws ServletException, IOException{
		
		
try {
			
			if(Integer.parseInt(deleteRecord)>0 && Integer.parseInt(deleteRecord)-1<searchRegistrations.size()) {
				
			PlanMetaData data=searchRegistrations.get(Integer.parseInt(deleteRecord)-1);
			
			request.setAttribute("record", deleteRecord);
			request.setAttribute("reg", data);
			request.setAttribute("fromAll", true);
			request.getRequestDispatcher("del.jsp").forward(request, response);
			
			
			}
			else {
				
				request.setAttribute("regs", searchRegistrations);
				request.setAttribute("fromAll", true);
				
				RequestDispatcher req=request.getRequestDispatcher("regs.jsp");
	    		pw.println("<h4>Record with number \'"+deleteRecord+"\' not found!</h4>");
	    		req.include(request, response);
				
			}
				
			
		} catch (NumberFormatException e) {
			
			
			request.setAttribute("regs", searchRegistrations);
			request.setAttribute("fromAll", true);
			
			RequestDispatcher req=request.getRequestDispatcher("regs.jsp");
			pw.println("<h4>Invalid input value, please, add it again!</h4>");
			req.include(request, response);
			
		}
		
		
		
	}
	
	private void modifySearchRegistration(String modifyRecord, HttpServletRequest request, HttpServletResponse response,
			EntityManagerFactory emf, EntityManager em, PrintWriter pw) throws ServletException, IOException{
		
		
		try {	
			
			if(Integer.parseInt(modifyRecord)>0 && Integer.parseInt(modifyRecord)-1<searchRegistrations.size()) {
				
				PlanMetaData data=searchRegistrations.get(Integer.parseInt(modifyRecord)-1);
				
				request.setAttribute("record", data);
				request.setAttribute("fromAll", true);
				
				if(data.getIsOK()) {
					
					request.setAttribute("ready", true);
				}
				else {
					
					request.setAttribute("ready", false);
				}
				
				request.getRequestDispatcher("modify.jsp").forward(request, response);
			
			}
			else {
				
				request.setAttribute("regs", searchRegistrations);
				request.setAttribute("fromAll", true);

	        	RequestDispatcher req=request.getRequestDispatcher("regs.jsp");
	    		pw.println("<h4>Record with number \'"+modifyRecord+"\' not found!</h4>");
	    		req.include(request, response);
				
			}
			
		}
		catch(NumberFormatException e) {
			
			request.setAttribute("regs", searchRegistrations);
			request.setAttribute("fromAll", true);
			
			RequestDispatcher req=request.getRequestDispatcher("regs.jsp");
			pw.println("<h4>Invalid input value, please, add it again!</h4>");
			req.include(request, response);
			
			
		}
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
