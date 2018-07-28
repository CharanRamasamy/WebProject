package main.javafiles;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	HttpSession session = request.getSession(false); 
	
	if(session!=null) 
	{
	
	session.invalidate(); //removes all session attributes bound to the session
	request.setAttribute("errMessage", "You have logged out successfully");
	RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
	requestDispatcher.forward(request, response);
	System.out.println("Logged out");
	}
	}
}
