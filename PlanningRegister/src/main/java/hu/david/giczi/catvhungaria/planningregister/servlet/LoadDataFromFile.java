package hu.david.giczi.catvhungaria.planningregister.servlet;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

@WebServlet("/LoadDataFromFile")
public class LoadDataFromFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoadDataFromFile() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String[]> inputdata = request.getParameterMap();

		String year = inputdata.get("year")[0];
		String month = Integer.parseInt(inputdata.get("month")[0]) < 10 ? "0" + inputdata.get("month")[0]
				: inputdata.get("month")[0];
		String day = Integer.parseInt(inputdata.get("day")[0]) < 10 ? "0" + inputdata.get("day")[0]
				: inputdata.get("day")[0];

		String fileName = "Allregs_" + year + "-" + month + "-" + day + ".txt";

		BufferedReader in = null;

		List<PlanMetaData> inputRegs = new ArrayList<>();

		StringBuilder inputData = new StringBuilder();

		EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");

		EntityManager em = emf.createEntityManager();

		try {

			in = new BufferedReader(new FileReader("C:\\SavedRegs\\" + fileName));

			String line = in.readLine();

			while (line != null) {

				inputData.append(line);

				if (inputData.toString().endsWith("true") || inputData.toString().endsWith("false")) {

					transformInputRegistrationFromStringToPlanMetaData(inputData.toString(), inputRegs);

					inputData.setLength(0);
				}

				line = in.readLine();

			}

			
			int persistedRegsCounter = persistInputRegistration(inputRegs, em);
			
			request.setAttribute("msg", "load" + persistedRegsCounter);
			
			in.close();

		} catch (IOException e) {

			request.setAttribute("msg", "notfound" + fileName);

		} finally {

			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();

			request.setAttribute("years", TimeStamp.getYears(5));
			new Names();
			request.setAttribute("catvnames", Names.getCatvNames());
			request.setAttribute("upcnames", Names.getUpcNames());
			request.getRequestDispatcher("start.jsp").forward(request, response);

		}

	}

	private void transformInputRegistrationFromStringToPlanMetaData(String record, List<PlanMetaData> inputRegs) {

		String[] inputStore = record.split("\\^");

		PlanMetaData inputEntity = new PlanMetaData();

		inputEntity.setId(Long.parseLong(inputStore[0]));
		inputEntity.setPlanNumber(inputStore[1]);
		inputEntity.setPlanName(inputStore[2]);
		inputEntity.setNameOfCATVControlPerson(inputStore[3]);
		inputEntity.setDateOfCATVControl(inputStore[4]);
		inputEntity.setNameOfUPCControlPerson(inputStore[5]);
		inputEntity.setDateOfUPCControl(inputStore[6]);
		inputEntity.setComment(inputStore[7]);
		inputEntity.setEkozmu(inputStore[8]);
		inputEntity.setRoadStatement(inputStore[9]);
		inputEntity.setOwnerStatement(inputStore[10]);
		inputEntity.setIsOK(Boolean.parseBoolean(inputStore[11]));

		inputRegs.add(inputEntity);

	}

	private int persistInputRegistration(List<PlanMetaData> inputRegs, EntityManager em) {

		TypedQuery<PlanMetaData> query = em.createQuery("SELECT p FROM PlanMetaData p", PlanMetaData.class);

		List<PlanMetaData> existedRegs = query.getResultList();

		int persistCounter = 0;

		for (PlanMetaData input : inputRegs) {

			int i = 0;

			while ( i < existedRegs.size() && !input.equals(existedRegs.get(i)) ) {

				
				i++;
				
				
			}

			if (i == existedRegs.size()) {
				
				input.setId(null);

				em.getTransaction().begin();

				em.persist(input);

				em.getTransaction().commit();

				persistCounter++;
			}

		}

		return persistCounter;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
