package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Data.*;
import entidades.*;

public class DataProducto {
	
	public LinkedList<Producto> getByDescCat(Categoria cat){
		
		LinkedList<Producto> productos = new LinkedList <> ();
		Producto p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select desc_producto,marca from producto p inner join categoria c on c.idCategoria=p.id_categoria where c.desc_categoria=?"
					);
			stmt.setString(1, cat.getDescCategoria());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=new Producto();
				p.setDescProducto(rs.getString("desc_producto"));
				p.setMarca(rs.getString("marca")); //VER SI AGREGAR LOS OTROS ATRIBUTOS TMBN O NO
				productos.add(p);
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
		
		return productos;
		
	}

	public LinkedList<Producto> getAll(){
		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Producto> productos= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idProducto,desc_producto,stock,stockMinimo,marca,id_categoria from producto prod inner join precio pre on prod.idProducto=pre.idproducto and pre.fecha=CURDATE() ");
			if(rs!=null) {
				while(rs.next()) {
					Producto p=new Producto();
					p.setIdProducto(rs.getInt("idProducto"));
					p.setDescProducto(rs.getString("desc_producto"));
					p.setStock(rs.getInt("stock"));
					p.setStockMinimo(rs.getInt("stockMinimo"));
					p.setMarca(rs.getString("marca"));
					p.setId_categoria(rs.getInt("id_categoria"));
					
					productos.add(p);
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
		
		
		return productos;
	}
	
	public LinkedList<Producto> getByDesc(Producto p){
		
		LinkedList<Producto> productos = new LinkedList <> ();
		
		Producto prod =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select desc_producto,marca from producto p where p.desc_producto=?"
					);
			stmt.setString(1, p.getDescProducto());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				prod=new Producto();
				prod.setDescProducto(rs.getString("desc_producto"));
				prod.setMarca(rs.getString("marca")); //VER SI AGREGAR LOS OTROS ATRIBUTOS TMBN O NO
				productos.add(prod);
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
		
		return productos;
		
	}
}
