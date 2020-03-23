package hu.david.giczi.catvhungaria.planningregister.servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.david.giczi.catvhungaria.planningregister.model.Names;
import hu.david.giczi.catvhungaria.planningregister.model.PlanMetaData;
import hu.david.giczi.catvhungaria.planningregister.model.TimeStamp;

@WebServlet("/SaveDataToFile")
public class SaveDataToFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveDataToFile() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");

		EntityManager em = emf.createEntityManager();

		BufferedWriter out = null;

		try {

			TypedQuery<PlanMetaData> query = em.createQuery("SELECT p FROM PlanMetaData p", PlanMetaData.class);

			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File("C:\\SavedRegs\\Allregs_" + TimeStamp.timeStamp() + ".txt"))));

			List<PlanMetaData> allRegs = query.getResultList();
			
			 setVariableValueIfItIsEmptyOrNull(allRegs);
			
			for (PlanMetaData planMetaData : allRegs) {

				out.write(planMetaData.toString());
				out.newLine();
			}

			out.close();

			request.setAttribute("msg", "yes" + allRegs.size());

		} catch (IOException e) {

			request.setAttribute("msg", "no");

		}

		finally {

			em.close();

			request.setAttribute("years", TimeStamp.getYears(5));
			new Names();
			request.setAttribute("catvnames", Names.getCatvNames());
			request.setAttribute("upcnames", Names.getUpcNames());
			request.getRequestDispatcher("start.jsp").forward(request, response);

		}

	}

	private void setVariableValueIfItIsEmptyOrNull(List<PlanMetaData> regs) {

		for (PlanMetaData planMetaData : regs) {
			

			if ( planMetaData.getPlanNumber() == null ||  planMetaData.getPlanNumber().isEmpty() ) {

				planMetaData.setPlanNumber("-");

			}
			if ( planMetaData.getPlanName() == null || planMetaData.getPlanName().isEmpty() ) {

				planMetaData.setPlanName("-");

			}
			if ( planMetaData.getNameOfCATVControlPerson() == null || planMetaData.getNameOfCATVControlPerson().isEmpty() ) {

				planMetaData.setNameOfCATVControlPerson("-");

			} 
			if ( planMetaData.getDateOfCATVControl() == null || planMetaData.getDateOfCATVControl().isEmpty() ) {

				planMetaData.setDateOfCATVControl("-");

			} 
			if ( planMetaData.getNameOfUPCControlPerson() == null || planMetaData.getNameOfUPCControlPerson().isEmpty() ) {

				planMetaData.setNameOfUPCControlPerson("-");

			} 
			if ( planMetaData.getDateOfUPCControl() == null || planMetaData.getDateOfUPCControl().isEmpty() ) {

				planMetaData.setDateOfUPCControl("-");

			} 
			if ( planMetaData.getComment() == null || planMetaData.getComment().isEmpty() ) {

				planMetaData.setComment("-");

			} 
			if (planMetaData.getEkozmu() == null  || planMetaData.getEkozmu().isEmpty() ) {

				planMetaData.setEkozmu("-");

			} 
			if ( planMetaData.getRoadStatement() == null || planMetaData.getRoadStatement().isEmpty() ) {

				planMetaData.setRoadStatement("-");

			} 
			if ( planMetaData.getOwnerStatement() == null || planMetaData.getOwnerStatement().isEmpty() ) {

				planMetaData.setOwnerStatement("-");

			}

		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
