package Biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearBase {
	private Statement st;
	ResultSet rs = null;
	boolean existe;
	
	public void crearDB(){
		Connection con = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost:3306/?useTimezone=true&serverTimezone=UTC";
		String user = "root";
		String pwd = "root";

		try{

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pwd);
			String nombreBD = "bibmt";
			st = con.createStatement();
			if(con != null){
	
				rs = con.getMetaData().getCatalogs();
	
				while(rs.next()){
					String catalogs = rs.getString(1);
					
					if(nombreBD.equals(catalogs)){
						existe = true;
					}
				}
				if(!existe) {
					st.executeUpdate("create database bibmt;");
					st.executeUpdate("use bibmt;");
					st.executeUpdate("create table usuarios (id_usuario SMALLINT UNIQUE auto_increment,usuario VARCHAR(20)not null,contra VARCHAR(100) not null,tipo_usuario smallint,PRIMARY KEY (id_usuario));");
					st.executeUpdate("create table tipo_documento (id int UNIQUE auto_increment,tipodoc varchar(100) NOT NULL,primary key (id));");
					st.executeUpdate("create table almacen (id int UNIQUE auto_increment, tipo int  null; titulo VARCHAR(150) not null, genero VARCHAR(50) not null, editorial VARCHAR(150) not null, autor VARCHAR(50)  null,tomos int  null, paginas int  null, precio int  null, fecha varchar(20)  NULL, fecha_caducidad varchar(20)  NULL, unidades int  null'', PRIMARY KEY (id));");
				}
	
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			if( rs != null){
				try{
				    rs.close();
				}
				catch(SQLException ex){
					ex.printStackTrace();
				}
			}
			if( con != null){
				try{
				    con.close();
				}
				catch(SQLException ex){
					ex.printStackTrace();
				}
			}
		}
	}
}

