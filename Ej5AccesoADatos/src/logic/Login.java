package logic;

import java.util.HashMap;
import java.util.LinkedList;

import data.*;
import entities.*;

public class Login {
	private DataPersona dp;
	private DataRol dr;
	
	public Login() {
		dp=new DataPersona();
		dr=new DataRol();
	}
	
	public Persona validate(Persona p) {
		/* para hacer más seguro el manejo de passwords este sería un lugar 
		 * adecuado para generar un hash de la password utilizando un cifrado
		 * asimétrico como sha256 y utilizar el hash en lugar de la password en plano 
		 */
		return dp.getByUser(p);
	}

	public LinkedList<Persona> getAll(){
		return dp.getAll();
	}

	public Persona getByDocumento(Persona per) {
		return dp.getByDocumento(per);
		
	}
	
	public LinkedList<Persona> getByApellido(Persona per){
		return dp.getByApellido(per);
	}
	
	
	public void add(Persona per) {
		dp.add(per);	
	}
	
	public void setRolPersona(Persona p, Rol r) {
		dr.setRolPersona(p, r);
	}
	
	public Persona editPersona(Persona p) {
		return dp.editPersona(p);
	}
	
	public Rol getById(Rol r) {
		return dr.getById(r);
	}
	
	public Persona deletePersona (Persona p) {
		return dp.deletePersona(p);
	}
	
	public void removeRolesPersona(Persona p) {
		dr.removeRolesPersona(p);
	}

}