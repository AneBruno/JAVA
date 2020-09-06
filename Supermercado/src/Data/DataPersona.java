package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

import data.DbConnector;
import entidades.*;
import entities.Persona;

public class DataPersona {

	public LinkedList<Persona> getAll(){
		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Persona> personas= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idPersona,tipoDoc,nroDoc,nombre,apellido,telefono,direccion,email,password,cuit,fechaIngreso,fechaRegistro from persona");
			if(rs!=null) {
				while(rs.next()) {
					Persona p=new Persona();
					p.setIdPersona(rs.getInt("idPersona"));
					p.setTipoDoc(rs.getString("tipoDoc"));
					p.setNroDoc(rs.getString("nroDoc"));
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setTelefono(rs.getString("telefono"));
					p.setDireccion(rs.getString("direccion"));
					p.setEmail(rs.getString("email"));
					p.setPassword(rs.getString("password"));
					p.setCuit(rs.getString("cuit"));
					p.setFechaIngreso(rs.getDate("fechaIngreso"));
					p.setFechaRegistro(rs.getDate("fechaRegistro"));
					
					personas.add(p);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return personas;
	}

	public void add(Persona p) { 
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into persona(tipoDoc, nroDoc, nombre, apellido, telefono, direccion, email, password, cuit, fechaIngreso, fechaRegistro) values(?,?,?,?,?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, p.getTipoDoc());
			stmt.setString(2, p.getNroDoc());
			stmt.setString(3, p.getNombre());
			stmt.setString(4, p.getApellido());
			stmt.setString(5, p.getTelefono());
			stmt.setString(6, p.getDireccion());
			stmt.setString(7, p.getEmail());
			stmt.setString(8, p.getPassword());
			stmt.setString(9, p.getCuit());
		
			if (p.getTipo()==1) {
				//stmt.setDate(10, LocalDate.now());
				//null en el otro
			}else {
				//setear fechaRegistro
				//null
			}
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setIdPersona(keyResultSet.getInt(1)); //por ser autoincremental!
            }
           
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
    }

	
}
