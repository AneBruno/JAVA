package Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entidades.*;

public class DataCategoria {

	public LinkedList<Categoria> getAll(){
		
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Categoria> categorias= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idCategoria,desc_categoria from categoria");
			if(rs!=null) {
				while(rs.next()) {
					Categoria c=new Categoria();
					c.setIdCategoria(rs.getInt("idCategoria"));
					c.setDescCategoria(rs.getString("desc_categoria"));
					
					categorias.add(c);
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
		return categorias;
	}
}
