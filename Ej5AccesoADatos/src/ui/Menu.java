package ui;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;



import entities.*;
import logic.Login;

public class Menu {
	Scanner s=null;
	Login ctrlLogin = new Login(); //se crea una instancia de dataPersona
	int salir=0;

	public void start() {
		while(salir==0) {
		s = new Scanner(System.in);
		Persona p=login(); //obtengo la persona
		System.out.println("Bienvenido "+p.getNombre()+" "+p.getApellido());
		System.out.println();
		
		String command;
		do {
			command=getCommand();
			executeCommand(command);
			System.out.println();
			
		}while(!command.equalsIgnoreCase("exit"));
		
		s.close();
	}}

	private void executeCommand(String command) {
		switch (command) {
		case "list":
			System.out.println(ctrlLogin.getAll());
			break;
		case "find":
			System.out.println(find());
			break;
		case "search": System.out.println(search());
	
			break;
		case "new": System.out.println(new_());
			
			break;
		case "edit": System.out.println(edit());
			
			break;
		case "delete": delete();
			
			break;
		case "exit": salir=1;
					System.out.println("Programa finalizado");
			
			break;
		default:
			break;
		}
	}

	private String getCommand() {
		System.out.println("Ingrese el comando según la opción que desee realizar");
		System.out.println("list\t\tlistar todos");
		System.out.println("find\t\tbuscar por tipo y nro de documento"); //solo debe devolver 1
		System.out.println("search\t\tlistar por apellido"); //puede devolver varios
		System.out.println("new\t\tcrea una nueva persona y asigna un rol existente");
		System.out.println("edit\t\tbusca por tipo y nro de documento y actualiza todos los datos");
		System.out.println("delete\t\tborra por tipo y nro de documento");
		System.out.println("exit\t\tfinalizar programa");
		System.out.println();
		System.out.print("comando: ");
		return s.nextLine();
	}
	
	public Persona login() {
		Persona p=new Persona();
		
		System.out.print("Email: ");
		p.setEmail(s.nextLine()); //ingreso de datos del usuario en consola

		System.out.print("password: ");
		p.setPassword(s.nextLine());
		
		p=ctrlLogin.validate(p); 
		
		return p; 
		
	}
	
	private Persona find() {
		Persona p=new Persona();
		Documento d=new Documento();
		p.setDocumento(d);
		System.out.print("Tipo doc: ");
		d.setTipo(s.nextLine());

		System.out.print("Nro doc: ");
		d.setNro(s.nextLine());
		
		return ctrlLogin.getByDocumento(p);
	}
	
	private LinkedList<Persona> search() {
		System.out.println();
		Persona p= new Persona();
		System.out.println("Apellido: ");
		p.setApellido(s.nextLine());
		return ctrlLogin.getByApellido(p);
	}

	private Persona new_() {
		
		System.out.println();
		
		Persona p= new Persona();
		Documento doc= new Documento();
		Rol rol= new Rol();
		//p.setDocumento(doc);
		
		System.out.println("Tipo doc: ");
		doc.setTipo(s.nextLine());

		System.out.println("Nro doc: ");
		doc.setNro(s.nextLine());
		
		System.out.println("Nombre: ");
		p.setNombre(s.nextLine());
		
		System.out.println("Apellido: ");
		p.setApellido(s.nextLine());
		
		System.out.println("Teléfono: ");
		p.setTel(s.nextLine());
		
		System.out.println("Email: ");
		p.setEmail(s.nextLine());
		
		System.out.println("Password: ");
		p.setPassword(s.nextLine());
		
		System.out.println("true - habilitado// false - no habilitado: ");
		p.setHabilitado(Boolean.valueOf(s.nextLine()));
				
		p.setDocumento(doc);
		
		System.out.println("ROL: 1- Admin //  2- User : ");
		rol.setId(Integer.parseInt(s.nextLine())); //adentro del metodo se asignó las descripcion del rol tmbn
		p.addRol(rol);
		
		ctrlLogin.add(p);//creo persona ne base de datos
		ctrlLogin.setRolPersona(p,rol);
		
		return(p);
		
		
	}

	private Persona edit() {
		
		Persona p= new Persona();
		Rol r =new Rol();
		
		System.out.println("Persona a editar: ");
		p= find();
		while (p==null) {
			System.out.println("Persona inesxistente. Ingrese datos correctos: ");
			p=find();
		}

		System.out.println("");
		System.out.println("\n1- Editar datos personales \n2- Editar roles");
		String ans = s.nextLine();
				if ((ans).equals("1")){
				System.out.println("Ingrese los datos actualizados:");
				
				System.out.println("Nombre: ");
				p.setNombre(s.nextLine());
				
				System.out.println("Apellido: ");
				p.setApellido(s.nextLine());
				
				System.out.println("Teléfono: ");
				p.setTel(s.nextLine());
				
				System.out.println("Email: ");
				p.setEmail(s.nextLine());
				
				System.out.println("Password: ");
				p.setPassword(s.nextLine());
				
				System.out.println("true - habilitado// false - no habilitado: ");
				p.setHabilitado(Boolean.valueOf(s.nextLine()));
				
				ctrlLogin.editPersona(p);
				
				System.out.println("Persona actualizada con éxito:");
												
			}
			else if (ans.equals("2")){
				System.out.println("1-Eliminar rol\n2-Agregar rol");
				String rt=s.nextLine();
				if (rt.equals("1")) {
					deleteRol(p);
				}else if (rt.equals("2")) {
					editRol(p);				
				}			
		}
				
		return p;
	}

	private void editRol (Persona p) {
		Rol r =new Rol();
		System.out.println("Roles actuales de la persona: "+p.getRoles());
		System.out.println("ROL: 1- Admin //  2- User : ");
		r.setId(Integer.parseInt((s.nextLine())));
		r= ctrlLogin.getById(r);
		while (r==null) {
			System.out.println("El rol ingresado es inexistente, intente de nuevo: ");
			System.out.println("ROL: 1- Admin //  2- User : ");
			r=new Rol();
			r.setId(Integer.parseInt((s.nextLine())));
			r= ctrlLogin.getById(r);
		}
		if(p.hasRol(r)==true) {
			System.out.println("La persona ya tiene este rol asignado.");
		}
		if(p.hasRol(r)==false) {
			p.addRol(r);
			ctrlLogin.setRolPersona(p, r);
			System.out.println("Rol asignado con éxito");	
		}
	}
	
	private void deleteRol(Persona p) {
		Rol r = new Rol();
		System.out.println("Roles actuales de la persona: "+p.getRoles());
		System.out.println("Ingrese el id del rol que desea eliminar: ");
		r.setId(Integer.parseInt(s.nextLine()));
		r=ctrlLogin.getById(r);
		while(r==null) {
			System.out.println("El rol ingresado no existe. Intente de nuevo: ");
			r=new Rol();
			r.setId(Integer.parseInt(s.nextLine()));
			r=ctrlLogin.getById(r);
		}
		if (p.hasRol(r)==false) {
			System.out.println("La persona no tiene este rol asignado."); //no se lo puede eliminar
		}
		else {
			p.removeRol(r);
			ctrlLogin.removeRolesPersona(p); //asigno rol en tabla rol_persona db
			System.out.println("Eliminación exitosa.");
		}
	}
	private void delete() {
		
		Persona p= new Persona();
		//HashMap<Integer, Rol> roles= new HashMap<Integer, Rol>();
		System.out.println("Persona a eliminar: ");
		p= find();
		while (p==null) {
			System.out.println("Persona inesxistente. Ingrese datos correctos: ");
			p=find();
		}
		//roles= p.getRoles();
		System.out.println("Desea eliminar la persona: ID:"+p.getId()+" -Nombre: "+p.getNombre()+" -Apellido: "+p.getApellido()+"? (si/no)");
		String rta=s.nextLine();
		if(rta.equals("si")) {
			ctrlLogin.removeRolesPersona(p); //borra roles de la persona
			ctrlLogin.deletePersona(p);
			System.out.println("Eliminación exitosa.");
		}else if (rta.equals("no")) {
			System.out.println("Eliminación cancelada.");
		}
		
		
	}

}

