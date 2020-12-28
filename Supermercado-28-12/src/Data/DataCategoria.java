package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entidades.*;
//import logic.*;
//import Data.*;

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

	public void add(Categoria c) { 
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into categoria(idCategoria, desc_categoria) values(?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, c.getIdCategoria());
			stmt.setString(2, c.getDescCategoria());		
						
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
            	c.setIdCategoria(keyResultSet.getInt(1)); //por ser autoincremental!
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
	
	public Categoria editCategoria (Categoria c) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"UPDATE `java`.`categoria` SET `desc_categoria` = ? WHERE (`idCategoria` = ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			
			stmt.setInt(1, c.getIdCategoria());
			stmt.setString(2, c.getDescCategoria());
			
			
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                c.setIdCategoria(keyResultSet.getInt(1));
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
	return c;
	}
	
	public Categoria deleteCategoria(Categoria c) {
		
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from categoria where categoria.idCategoria=? ", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, c.getIdCategoria());
			
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                c.setIdCategoria(keyResultSet.getInt(1));
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
	return c;
	}
	
	public Categoria getById (String cat) {
		
		Categoria c=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idCategoria,desc_categoria from categoria where desc_categoria=? "
					);
			stmt.setString(1, cat);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				c=new Categoria();
				c.setIdCategoria(rs.getInt("idCategoria"));
				c.setDescCategoria(rs.getString("desc_categoria"));
				
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
	return c;
	}

}