package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

public class Login extends HttpServlet 
{
	public HttpSession session;
	boolean value;
	Model m;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		m=new Model();
		m.setUserid(request.getParameter("user"));
		m.setPassword(request.getParameter("pwd"));		
		try 
		{
			value=m.login();
			if(value==true)
			{
				session=request.getSession(true);
				session.setAttribute("accno",m.getAccno());
				response.sendRedirect("/Bank/home.html");
			}
			else
			{
				response.sendRedirect("/Bank/loginfail.html");
			}
		}
		catch(Exception e)
		{
			System.out.println("In Login Servlet");
		}
	}
}
