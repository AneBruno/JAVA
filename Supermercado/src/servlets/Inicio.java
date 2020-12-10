package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.*;
import logic.*;

/**
 * Servlet implementation class Inicio
 */
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Persona per = new Persona();
		Login ctrl = new Login();
		
			
		String email= request.getParameter("email");
		String pass= request.getParameter("pass");
		
		//validar email y password
		
		per.setEmail(email);
		per.setPassword(pass);
		
		
		//per= ctrl.validate(per);
		
		if ((per.getEmail().equals("jp@gmail.com")) && (per.getPassword().equals("jperez"))) {
			
			request.getRequestDispatcher("mainpage.jsp").forward(request, response);
			
			request.getSession().setAttribute("usuario", per);
		}
		else {
			response.getWriter().append("Usuario no encontrado");
		}

		
		//request.getRequestDispatcher("WEB-INF/mainpage.jsp").forward(request, response);
		
		
			//request.getRequestDispatcher("login.jsp").forward(request, response);
			}

}
