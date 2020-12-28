package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CerrarSesion
 */
@WebServlet("/CerrarSesion")
public class CerrarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CerrarSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 HttpSession sesion = request.getSession();
		 sesion.invalidate();
		if ( sesion== null || sesion.getAttribute("email") == null) { 
			//response.sendRedirect("index.jsp"); // No logged-in user found, so redirect to login page. 
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} else { response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1. response.setHeader("Pragma", "no-cache"); // HTTP 1.0. response.setDateHeader("Expires", 0); chain.doFilter(req, res); } 
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
	}

}
