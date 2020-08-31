package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

public class ChangePassword extends HttpServlet 
{
	Model m;
	HttpSession session;
	boolean value;
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Model m=new Model();
		try 
		{
			m.setPassword(request.getParameter("opwd"));
			String newpassword=request.getParameter("npwd");
			session=request.getSession();
			int accno=(int) session.getAttribute("accno");
			m.setAccno(accno);
			value=m.changePassword(newpassword);
			if(value==true)
			{
				session.invalidate();
				response.sendRedirect("resetSuccess.html");
			}
			else
			{
				response.sendRedirect("resetFail.html");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}

}
