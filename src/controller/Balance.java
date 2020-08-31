package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

public class Balance extends HttpServlet 
{
	public HttpSession session;
	boolean value;
	Model m;
	public void service(HttpServletRequest request, HttpServletResponse response) 
			                                           throws ServletException, IOException 
	{	
		m=new Model();
		try 
		{
			session=request.getSession();
			int accno=(int) session.getAttribute("accno");
			m.setAccno(accno);
			value=m.balance();
			if(value==true)
			{
				session.setAttribute("balance",m.getBalance());
				response.sendRedirect("/Bank/balance.jsp");
			}
			else
			{
				response.sendRedirect("/Bank/fail.html");
			}
		}
		catch(Exception e)
		{
			response.sendRedirect("/Bank/loginagain.html");
		}
	}

}
