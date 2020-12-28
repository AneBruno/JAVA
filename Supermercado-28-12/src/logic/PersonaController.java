  
package logic;

import java.util.LinkedList;

import Data.*;
import entidades.*;

@SuppressWarnings("unused")
public class PersonaController {
	
	private DataPersona dp;
	private DataProducto dprod;
	private DataCategoria dc;
	
	public PersonaController() {
		dp=new DataPersona();
	}

	
	public LinkedList<Persona> listarPersonas() {
		return dp.getAll();
	}
	
	public LinkedList<Persona> listarClientes() {
		return dp.getAllClientes();
	}
	
	public Persona editPersona (Persona p) {
		return dp.editPersona(p);
	}
	public Persona deletePersona (Persona p) {
		return dp.deletePersona(p);
	}
	
	public Persona getById(Persona per) {
		Persona p=new Persona();
		return dp.getById(per);
	}
	
	
	 

}