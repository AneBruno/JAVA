package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entidades.*;

public class DataProveedor {
	
	public LinkedList<Proveedor> getAll(){
			
			Statement stmt=null;
			ResultSet rs=null;
			LinkedList<Proveedor> proveedores= new LinkedList<>();
			
			try {
				stmt= DbConnector.getInstancia().getConn().createStatement();
				rs= stmt.executeQuery("select idProveedor,telefono ,email , razonSocial ,fechaBaja from proveedor ");
				if(rs!=null) {
					while(rs.next()) {
						Proveedor p=new Proveedor();
						p.setIdProveedor(rs.getInt("idProveedor"));
						p.setTelefono(rs.getString("telefono"));
						p.setMail(rs.getString("email"));
						p.setRazonSocial(rs.getString("razonSocial"));
						p.setFechaBaja(rs.getDate("fechaBaja"));
											
						proveedores.add(p);
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
			
			
			return proveedores;
		}

	public void add(Proveedor p) { 
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into proveedor(idProveedor, telefono, email, razonSocial, fechaBaja) values(?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, p.getIdProveedor());
			stmt.setString(2, p.getTelefono());
			stmt.setString(3, p.getMail());
			stmt.setString(4, p.getRazonSocial());
			stmt.setDate(5, p.getFechaBaja());
						
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setIdProveedor(keyResultSet.getInt(1)); //por ser autoincremental!
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
	
	public Proveedor editProveedor (Proveedor p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `java`.`proveedor` SET `telefono` = ?,`email` = ?,`razonSocial` = ?, `fechaBaja` = ? WHERE (`idProveedor` = ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			
			stmt.setString(1, p.getTelefono());
			stmt.setString(2, p.getMail());
			stmt.setString(3, p.getRazonSocial());
			stmt.setDate(4, p.getFechaBaja());
			stmt.setInt(5, p.getIdProveedor());
			
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setIdProveedor(keyResultSet.getInt(1));
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

	public Proveedor deleteProveedor(Proveedor p) {
			
			PreparedStatement stmt= null;
			ResultSet keyResultSet=null;
			try {
				stmt=DbConnector.getInstancia().getConn().
						prepareStatement(
								"delete from proveedor where proveedor.idProveedor=? ", PreparedStatement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, p.getIdProveedor());
				
				stmt.executeUpdate();
				
				keyResultSet=stmt.getGeneratedKeys();
	            if(keyResultSet!=null && keyResultSet.next()){
	                p.setIdProveedor(keyResultSet.getInt(1));
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

	
}