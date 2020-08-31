package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;

public class Register extends HttpServlet 
{
	boolean value;
	Model m;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		m=new Model();
		m.setName(request.getParameter("name"));
		m.setUserid(request.getParameter("user"));
		m.setPassword(request.getParameter("pwd"));
		m.setEmail(request.getParameter("email"));
		String accno1=request.getParameter("accno");
		int accno=Integer.parseInt(accno1);
		m.setAccno(accno);
		String balance1=request.getParameter("balance");
		int balance=Integer.parseInt(balance1);
		m.setBalance(balance);
				
		try 
		{
			value=m.register();
			if(value==true)
			{
				response.sendRedirect("/Bank/success.html");
			}
			else
			{
				response.sendRedirect("/Bank/failure.html");
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
}


