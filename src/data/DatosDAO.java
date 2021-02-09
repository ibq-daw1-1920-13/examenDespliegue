package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class DatosDAO {

	private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private String DB_URL = "jdbc:mysql://localhost/informacion";
	private String DB_USER = "director";
	private String DB_PASS = "director";
	private Connection conn = null;
	private Statement stm = null; 
	PreparedStatement ps = null;
	private ResultSet rs = null;
	 
	public DatosDAO() throws RuntimeException {
		super();
		// TODO Auto-generated constructor stub
		 try { 
	            Class.forName(JDBC_DRIVER);
	            System.out.println("Antes Conexión");
	            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	            System.out.println("Conexión");
	     } catch (ClassNotFoundException e) {
	    	 	System.out.println("error "+e);
	            throw new RuntimeException("ERROR: failed to load MySQL JDBC driver.",e);
	     } catch (SQLException e) {
	    	 System.out.println("error "+e);
	    	 	throw new RuntimeException("ERROR: failed to access database.",e);
	     }
	}
	
	public boolean insertar(Dato dato) throws RuntimeException {
		try {
			ps = conn.prepareStatement("insert into datos(nombre,valor) values(?,?)");
			ps.setString(1, dato.getNombre());
			ps.setString(2, dato.getValor());
			int i = ps.executeUpdate();
			
			if (i==1) {
				return true;
			}
		} catch (SQLException e) {
    	 	throw new RuntimeException("ERROR: failed to insert dato",e);
		}
		return false;
	}
	
	public void insertarDatos(HashMap<String,String> datos) throws RuntimeException {
		for (Entry<String, String> entry : datos.entrySet()) { 
			try {
				ps = conn.prepareStatement("insert into datos(nombre,valor) values(?,?)");
				ps.setString(1, entry.getKey());
				ps.setString(2, entry.getValue());
				ps.executeUpdate();
				
			} catch (SQLException e) {
	    	 	throw new RuntimeException("ERROR: failed to insert dato",e);
			}			
		 }
		
	}
	
	public ArrayList<Dato> getDatos() throws RuntimeException {
		try {
			stm = conn.createStatement();
			String consulta = "select * from datos";
			rs = stm.executeQuery(consulta);
			ArrayList<Dato> datos = new ArrayList<Dato>();
			while (rs.next()) {
				Dato dato = new Dato(rs.getString("nombre"),rs.getString("valor"));
				datos.add(dato);
			}
			
			return datos;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("failed to create statement",e);
		} 
		
	}
	
	public int getNumDatos() throws RuntimeException {
		int num=0;
		try {
			
			stm = conn.createStatement();
			String consulta = "select count(*) from datos";
			rs = stm.executeQuery(consulta);
			
			while (rs.next()) {
				num = rs.getInt(1);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("failed to create statement",e);
		} 
		return num;
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            	throw new RuntimeException("Error cerrando la conexión",e);
            }
        }
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new RuntimeException("Error cerrando el resultset",e);
			}
		}
		
	}
	
}
