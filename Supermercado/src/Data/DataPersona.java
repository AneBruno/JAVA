package Data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;


import Data.*;
import entidades.*;


@SuppressWarnings("unused")
public class DataPersona {

	public LinkedList<Persona> getAll(){
		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Persona> personas= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idPersona,tipoDoc,nroDoc,nombre,apellido,telefono,direccion,email,password,cuit,fechaIngreso,fechaRegistro,cliente, empleado from persona");
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
					p.setCliente(rs.getBoolean("cliente"));
					p.setEmpleado(rs.getBoolean("empleado"));
					
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
							"insert into persona(tipoDoc, nroDoc, nombre, apellido, telefono, direccion, email, password, cuit, fechaIngreso, fechaRegistro, cliente, empleado) values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
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
		
			if (p.isCliente()==true) {
				//LocalDate ld=  LocalDate.now();
				stmt.setString(9, null);
				stmt.setDate(10, null);
				stmt.setDate(11, p.getFechaRegistro());		
			}else if (p.isEmpleado()==true){
				stmt.setString(9, p.getCuit());
				stmt.setDate(10, p.getFechaIngreso());
				stmt.setDate(11, null);
			}
			stmt.setBoolean(12, p.isCliente());
			stmt.setBoolean(13, p.isEmpleado());
			
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

	public Persona editPersona (Persona p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `java`.`persona` SET `tipoDoc` = ?,`nroDoc` = ?,`nombre` = ?, `apellido` = ?, `telefono` = ?, `direccion` = ?, `email` = ?, `password` = ?, `cuit` = ?,`cliente` = ?,`empleado` = ?  WHERE (`idPersona` = ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			//NO PERMITIMOS EDITAR FECHAS DE INGRESO DE EMPLEADOS NI FEHCA DE REGISTRO DE CLIENTES PORQ ES DE INGRESO AUTOMATICO, NO INGRESO HUMANO.
			stmt.setString(1, p.getTipoDoc());
			stmt.setString(2, p.getNroDoc());
			stmt.setString(3, p.getNombre());
			stmt.setString(4, p.getApellido());
			stmt.setString(5, p.getTelefono());
			stmt.setString(6, p.getDireccion());
			stmt.setString(7, p.getEmail());
			stmt.setString(8, p.getPassword());
			stmt.setString(9, p.getCuit());
			stmt.setBoolean(10, p.isCliente());
			stmt.setBoolean(11, p.isEmpleado());
			stmt.setInt(12, p.getIdPersona());
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setIdPersona(keyResultSet.getInt(1));
            }
		} catch (SQLException e) {
        e.printStackTrace();
		} finally {
        try {
        	 if(keyResultSet!=null)keyResultSet.close();
            if(stmt!=null) stmt.close();
            DbConnector.getInstancia().releaseConn();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}
	return p;
	}
	
	public Persona deletePersona(Persona p) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from persona where persona.idPersona=? ", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, p.getIdPersona());
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setIdPersona(keyResultSet.getInt(1));
            }
		} catch (SQLException e) {
        e.printStackTrace();
		} finally {
        try {
        	 if(keyResultSet!=null)keyResultSet.close();
            if(stmt!=null) stmt.close();
            DbConnector.getInstancia().releaseConn();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}
	return p;
	}

	
	public Persona getByUser(Persona per) {
		Persona p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idPersona,tipoDoc,nroDoc,nombre,apellido,telefono,direccion,email,password,cuit,fechaIngreso,fechaRegistro,cliente, empleado from persona where email=? and password=?"
					);
			stmt.setString(1, per.getEmail());
			stmt.setString(2, per.getPassword());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=new Persona();
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
				p.setCliente(rs.getBoolean("cliente"));
				p.setEmpleado(rs.getBoolean("empleado"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return p;
}
	
}