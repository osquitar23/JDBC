package curso.mysql;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Consultar {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		/*Me conecto al conecor de mysql*/
		Class.forName("com.mysql.jdbc.Driver");
		/*Inicio la conexcion a mysql metiendome a mi BBDD con getConnection*/
		Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca","root","");
		
		/*Select*/
		
		/*Hacemos una consulta con un Statement*/
		Statement st = (Statement) conn.createStatement();
		
		/*Aqui creo la consulta con un executeQuery a mi tabla de libros*/
		ResultSet rs = st.executeQuery("select * from libros");
		
		/*rs me muestra los datos*/
		/*con este while creo la tabla*/
		while(rs.next()) {/*Con los get, te muestra la columna de tu tabla, a traves del conector*/
			System.out.println("Titulo "+rs.getString(2));
			System.out.println("Precio "+rs.getFloat(4));
			System.out.println("Fecha de publicacion "+rs.getDate(5));
		}
		
		/*Update*/
		
		/*Con el executeUpdate hago un insert o cualquier consulta*/
		/*int insertar = st.executeUpdate("insert into libros(titulo,autor,precio,fechadepublicacion) values ('J2SE','Java',10.25,'2018-01-15')");*/
		
		/*System.out.println("Fila Insertada "+insertar);*/
		
		/*Aqui se hace la consulta*/
		CallableStatement cstmt = conn.prepareCall("{call listalibros}");
			
		/*Haces la consulta sin parametros*/
		ResultSet rs2 = cstmt.executeQuery();
		
		while(rs2.next()) {/*Con los get, te muestra la columna de tu tabla, a traves del conector*/
			System.out.println("Titulo "+rs2.getString(2));
		}
		
		/*Aqui se hace la consulta y la extraes de la mysql*/
		CallableStatement cstm = conn.prepareCall("{call listalibrosporautor(?)}");
		
		cstm.setString(1, "Java");
			
		/*Haces la consulta sin parametros*/
		ResultSet rs3 = cstm.executeQuery();
		
		while(rs3.next()) {/*Con los get, te muestra la columna de tu tabla, a traves del conector*/
			System.out.println("Autor "+rs3.getString(3));
		}
	}

}
