package hu.david.giczi.catvhungaria.planningregister.servlet;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import hu.david.giczi.catvhungaria.georegister.model.GeoJobPropertyStore;
import hu.david.giczi.catvhungaria.planningregister.model.PlanMetaData;

@WebServlet("/ShowContent")
public class ShowContent extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private boolean nonExisted = true;

	public ShowContent() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		if (!nonExisted) {
			goRegsPage(request, response);
			return;
		}

		String path = (String) request.getSession().getAttribute("path");
		String fileURL = "";

		nonExisted = false;

		if (path != null) {
			fileURL = lookUpFile(path);
		} else {
			fileURL = lookUpFile("C:\\");
		}

		if ( !fileURL.isEmpty() ) {

			Runtime run = Runtime.getRuntime();
			
			GeoJobPropertyStore.loadPropertiesFromFile();
			run.exec(GeoJobPropertyStore.URL3 + fileURL);

		}

		goRegsPage(request, response);
		nonExisted = true;

	}

	private String lookUpFile(String path) {

		JFileChooser jf = new JFileChooser(path) {

			private static final long serialVersionUID = 1L;

			@Override
			protected JDialog createDialog(Component parent) throws HeadlessException {

				JDialog dialog = super.createDialog(parent);

				dialog.setAlwaysOnTop(true);

				return dialog;
			}
		};

		jf.setDialogTitle("Fájl kiválasztás");

		jf.setApproveButtonText("Ok");

		jf.setAcceptAllFileFilterUsed(false);

		FileNameExtensionFilter filter = new FileNameExtensionFilter("eml", "eml");

		jf.addChoosableFileFilter(filter);

		int returnValue = jf.showOpenDialog(null);

		String fileUrl = "";

		if (returnValue == JFileChooser.APPROVE_OPTION) {

			fileUrl = jf.getSelectedFile().getAbsolutePath();
		}

		return fileUrl;
	}

	private void goRegsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Boolean from = (Boolean) request.getSession().getAttribute("fromAll");

		@SuppressWarnings(value = "unchecked")
		List<PlanMetaData> store = (List<PlanMetaData>) request.getSession().getAttribute("store");

		request.setAttribute("fromAll", from);
		request.setAttribute("regs", store);

		request.getRequestDispatcher("regs.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
