package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entidades.*;

public class DataLineaDePedido {

	public LinkedList<LineaDePedido> getAll(){
		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<LineaDePedido> lineas= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select id_pedido,id_producto,cantidad from lineapedido");
			//select id_pedido,id_producto,desc_producto,cantidad from lineapedido lp inner join producto p on p.idProducto=lp.id_producto
			if(rs!=null) {
				while(rs.next()) {
					LineaDePedido lp=new LineaDePedido();
					lp.setId_pedido(rs.getInt("id_pedido"));
					lp.setId_producto(rs.getInt("id_producto"));
					lp.setCantidad(rs.getInt("cantidad"));
					
					lineas.add(lp);
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
		return lineas;
	}

	
	public void add(LineaDePedido lp) { 
		PreparedStatement stmt= null;
		//ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into lineapedido( id_pedido,id_producto,cantidad) values(?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			
			stmt.setInt(1, lp.getId_pedido());
			stmt.setDouble(2, lp.getId_producto());
			stmt.setInt(3, lp.getCantidad());
			
						
			stmt.executeUpdate();
			
			//Esto no va porque no tiene un id incremental lineapedido sino que su clave primaria es compuesta de 2 id de otras tablas
			//keyResultSet=stmt.getGeneratedKeys();
            //if(keyResultSet!=null && keyResultSet.next()){
              // lp.set(keyResultSet.getInt(1)); 
          
            //}
           
			
		}  catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                //if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
    }

	public LineaDePedido editLineaDePedido (LineaDePedido lp) {
		PreparedStatement stmt= null;
		//ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `java`.`lineapedido` SET `cantidad` = ? WHERE (`id_pedido` = ? and `id_producto` = ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			
			stmt.setInt(1, lp.getCantidad());
			stmt.setInt(2, lp.getId_pedido());
			stmt.setInt(3, lp.getId_producto());
						
			stmt.executeUpdate();
			
			/*keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                d.setIdDcto(keyResultSet.getInt(1));
            }*/
			
		} catch (SQLException e) {
        e.printStackTrace();
		} finally {
        try {
        	 //if(keyResultSet!=null)keyResultSet.close();
            if(stmt!=null) stmt.close();
            DbConnector.getInstancia().releaseConn();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
	}
	return lp;
	}

	public LineaDePedido deleteLineaDePedido (LineaDePedido lp) {
			
			PreparedStatement stmt= null;
			//ResultSet keyResultSet=null;
			try {
				stmt=DbConnector.getInstancia().getConn().
						prepareStatement(
								"delete from lineapedido where lineapedido.id_producto=? and lineapedido.id_pedido=?", PreparedStatement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, lp.getId_producto());
				stmt.setInt(2, lp.getId_pedido());
				
				stmt.executeUpdate();
				
				/*keyResultSet=stmt.getGeneratedKeys();
	            if(keyResultSet!=null && keyResultSet.next()){
	                p.setIdProveedor(keyResultSet.getInt(1));
	            }*/
				
			} catch (SQLException e) {
	        e.printStackTrace();
			} finally {
	        try {
	        	//if(keyResultSet!=null)keyResultSet.close();
	            if(stmt!=null) stmt.close();
	            DbConnector.getInstancia().releaseConn();
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
		}
		return lp;
		}
	
}