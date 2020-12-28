package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Data.*;
import entidades.*;
import logic.PersonaController;

/**
 * Servlet implementation class ListClientes
 */
@SuppressWarnings("unused")
@WebServlet("/ListClientes")
public class ListClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListClientes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		PersonaController ctrl= new PersonaController();
		//LinkedList<Persona> personas= new LinkedList<Persona>();
		LinkedList<Persona> clientes= new LinkedList<Persona>();
		clientes=ctrl.listarClientes();
		/*for(Persona per: personas) {
			if ((per.isCliente())==true) {
				clientes.add(per);
				
			}
		}*/
		//request.getAttribute("usuario");
		request.setAttribute("listado", clientes);
        request.getRequestDispatcher("WEB-INF/listarClientes.jsp").forward(request, response);

        
	}

}
