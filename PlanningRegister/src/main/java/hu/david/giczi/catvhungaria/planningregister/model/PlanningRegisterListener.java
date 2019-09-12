package hu.david.giczi.catvhungaria.planningregister.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class PlanningRegisterListener implements ServletContextListener {

    
    public PlanningRegisterListener() {
        
    }

	
    public void contextDestroyed(ServletContextEvent e)  { 
        
    	  EntityManagerFactory emf =
    	            (EntityManagerFactory)e.getServletContext().getAttribute("emf");
    	        emf.close();
    	
    }

    public void contextInitialized(ServletContextEvent e)   { 
        
    	 EntityManagerFactory emf =
    	            Persistence.createEntityManagerFactory("PlanningRegister");
    	        e.getServletContext().setAttribute("emf", emf);
    }
	
}
