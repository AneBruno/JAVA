package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Data.*;
import entidades.*;
import logic.*;

/**
 * Servlet implementation class Inicio
 */
@SuppressWarnings("unused")
@WebServlet("/Inicio")
public class Inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Inicio() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//request.getSession().invalidate();
		//request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Boolean email_format, pass_format;
		Persona per = new Persona();
		Login ctrl = new Login();
		
			
		String email= request.getParameter("email");
		String pass= request.getParameter("pass");
		
		//VALIDAR FORMATO EMAIL Y PASSWORD
		email_format= emailIsValid(email);
		pass_format= passIsValid(pass);
		
		if(email_format==true && pass_format==true) {
			per.setEmail(email);
			per.setPassword(pass);
			
		//\VALIDAR QUE EXISTA EN LA BDD
			per= ctrl.validate(per);
			
			if (per!=null) {
				
				//VEO SI ES CLIENTE O EMPLEADO
				
				if(per.isCliente()) {
					
					request.getRequestDispatcher("mainpage.jsp").forward(request, response);
					
					request.getSession().setAttribute("usuario", per);
				}
				else if(per.isEmpleado()) {
					request.getRequestDispatcher("mainpage-admin.jsp").forward(request, response);
					
					request.getSession().setAttribute("usuario", per);
				}
				
				
				//response.getWriter().append("Bienvenido: "+ per.getApellido() + per.getNombre());
			}
			else {
				response.getWriter().append("Usuario no encontrado");
			}
		}else {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
		
		
		/*if ((per.getEmail().equals("jp@gmail.com")) && (per.getPassword().equals("jperez"))) {
			
			request.getRequestDispatcher("mainpage.jsp").forward(request, response);
			
			request.getSession().setAttribute("usuario", per);
		}
		else {
			response.getWriter().append("Usuario no encontrado");
		}*/
		
		//request.getRequestDispatcher("WEB-INF/mainpage.jsp").forward(request, response);
		
			}

	
		private static boolean emailIsValid(String email) {
	      String email_string = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      return email.matches(email_string);
	   }
	
		private static boolean passIsValid(String pass) {
		      String pass_string = "^([a-zA-Z0-9@*#]{6,15})$"; //Match all alphanumeric character and predefined wild characters. Password must consists of at least 8 characters and not more than 15 characters.
		      return pass.matches(pass_string);
		   }
		
	

}
