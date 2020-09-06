package Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entidades.*;

public class DataPedido {
	
	public LinkedList<Pedido> getAll(){
		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Pedido> pedidos= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idPedido,fechaPedido,precioTotal,fechaEntrega,direccionEnvio,estado,id_persona,id_dcto from pedido");
			if(rs!=null) {
				while(rs.next()) {
					Pedido p=new Pedido();
					p.setIdPedido(rs.getInt("idPedido"));
					p.setFechaPedido(rs.getDate("fechaPedido"));
					p.setPrecioTotal(rs.getDouble("precioTotal"));
					p.setFechaEntrega(rs.getDate("fechaEntrega"));
					p.setDireccionEnvio(rs.getString("direccionEnvio"));
					p.setEstado(rs.getString("estado"));
					p.setId_persona(rs.getInt("id_persona"));
					p.setId_dcto(rs.getInt("id_dcto"));
					
				
					pedidos.add(p);
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
		return pedidos;
	}
}
