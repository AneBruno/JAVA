package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Data.*;
import entidades.*;


@SuppressWarnings("unused")
public class DataProducto {
	
	public LinkedList<Producto> getByDescCat(Categoria cat){
		
		LinkedList<Producto> productos = new LinkedList <> ();
		Producto p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idProducto, desc_producto,stock, stockMinimo, marca, id_categoria, precio from producto p inner join categoria c on c.idCategoria=p.id_categoria where c.desc_categoria=?"
					);
			stmt.setString(1, cat.getDescCategoria());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=new Producto();
				p.setIdProducto(rs.getInt("idProducto"));
				p.setDescProducto(rs.getString("desc_producto"));
				p.setStock(rs.getInt("stock"));
				p.setStockMinimo(rs.getInt("stockMinimo"));
				p.setMarca(rs.getString("marca"));
				p.setId_categoria(rs.getInt("id_categoria"));
				p.setPrecio(rs.getDouble("precio"));
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
			rs= stmt.executeQuery("select idProducto,desc_producto,stock,stockMinimo,marca,id_categoria, precio from producto ");
			if(rs!=null) {
				while(rs.next()) {
					Producto p=new Producto();
					p.setIdProducto(rs.getInt("idProducto"));
					p.setDescProducto(rs.getString("desc_producto"));
					p.setStock(rs.getInt("stock"));
					p.setStockMinimo(rs.getInt("stockMinimo"));
					p.setMarca(rs.getString("marca"));
					p.setId_categoria(rs.getInt("id_categoria"));
					p.setPrecio(rs.getDouble("precio"));
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
					"select idProducto, desc_producto,stock, stockMinimo, marca, id_categoria, precio from producto p where p.desc_producto=?"
					);
			stmt.setString(1, p.getDescProducto());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				prod=new Producto();
				prod.setIdProducto(rs.getInt("idProducto"));
				prod.setDescProducto(rs.getString("desc_producto"));
				prod.setStock(rs.getInt("stock"));
				prod.setStockMinimo(rs.getInt("stockMinimo"));
				prod.setMarca(rs.getString("marca")); 
				prod.setId_categoria(rs.getInt("id_categoria"));
				prod.setPrecio(rs.getDouble("precio"));
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

	public void add(Producto p) { 
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into producto(idProducto, desc_producto, stock, stockMinimo, marca, id_categoria) values(?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, p.getIdProducto());
			stmt.setString(2, p.getDescProducto());
			stmt.setInt(3, p.getStock());
			stmt.setInt(4, p.getStockMinimo());
			stmt.setString(5, p.getMarca());
			stmt.setInt(6, p.getId_categoria());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setIdProducto(keyResultSet.getInt(1)); //por ser autoincremental!
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

	public Producto editProducto (Producto p) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `java`.`producto` SET  `desc_producto` = ?, `stock` = ?, `stockMinimo` = ?, `marca` = ?, `id_categoria` = ?, `precio` = ? WHERE (`idProducto` = ?)", PreparedStatement.RETURN_GENERATED_KEYS);
				
			stmt.setString(1, p.getDescProducto());
			stmt.setInt(2, p.getStock());
			stmt.setInt(3, p.getStockMinimo());
			stmt.setString(4, p.getMarca());
			stmt.setInt(5, p.getId_categoria());
			stmt.setDouble(6, p.getPrecio());
			stmt.setInt(7, p.getIdProducto());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setIdProducto(keyResultSet.getInt(1));
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
	
	public Producto deleteProducto(Producto p) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from producto where producto.idProducto=? ", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, p.getIdProducto());
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                p.setIdProducto(keyResultSet.getInt(1));
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

	public LinkedList<Producto> getByPrecio (int max) {
		
		LinkedList<Producto> productos = new LinkedList <> ();
		Producto p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idProducto, desc_producto, stock, stockMinimo, marca, id_categoria, precio from producto where precio<= ?"
					);
			stmt.setDouble(1, max);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				p=new Producto();
				p.setIdProducto(rs.getInt("idProducto"));
				p.setDescProducto(rs.getString("desc_producto"));
				p.setStock(rs.getInt("stock"));
				p.setStockMinimo(rs.getInt("stockMinimo"));
				p.setMarca(rs.getString("marca")); 
				p.setId_categoria(rs.getInt("id_categoria"));
				p.setPrecio(rs.getDouble("precio"));
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
}