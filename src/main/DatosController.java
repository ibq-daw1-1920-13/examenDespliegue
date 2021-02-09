package main;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DatosDAO;

/**
 * Servlet implementation class DatosController
 */
@WebServlet("")
public class DatosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatosController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 RequestDispatcher despachador = null;
		 HashMap<String, String> datos = new HashMap<String, String>();
		 InetAddress ip;
		 ip = InetAddress.getLocalHost();
		 Calendar c = Calendar.getInstance();
		 TimeZone tz = c.getTimeZone();
		 Date date = new Date();
		
		 datos.put("HostName", ip.getHostName());
		 datos.put("HostAddress", ip.getHostAddress());
		 datos.put("OS Name", System.getProperty("os.name").toLowerCase());
		 datos.put("OS Arch", System.getProperty("os.arch").toLowerCase());
		 datos.put("OS Version", System.getProperty("os.version").toLowerCase());
		 datos.put("Date", date.toString());
		 datos.put("TimeZone", tz.getDisplayName());
		 datos.put("User Name", System.getProperty("user.name").toLowerCase());
		 datos.put("User Home", System.getProperty("user.home").toLowerCase());
		 datos.put("User Dir", System.getProperty("user.dir").toLowerCase());
		 datos.put("Java Vendor", System.getProperty("java.vendor").toLowerCase());
		 datos.put("Java Version", System.getProperty("java.version").toLowerCase());
		 
		 DatosDAO datosDAO = new DatosDAO();
		 datosDAO.insertarDatos(datos);
		 
		 request.setAttribute("numDatos",datosDAO.getNumDatos());
		 request.setAttribute("datos",datos);
				 
		 despachador = request.getServletContext().getRequestDispatcher("/index.jsp");
		 despachador.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
