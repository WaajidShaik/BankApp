package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

public class GetStatement extends HttpServlet 
{
	HttpSession session;
	Model m=new Model();
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			session=request.getSession();
			int accno=(int) session.getAttribute("accno");
			m.setAccno(accno);
			ArrayList al1=m.getCredit();
			ArrayList al2=m.getDebit();
			if((al1.isEmpty())&&(al2.isEmpty()))
			{
				response.sendRedirect("/Bank/statementfail.html");
			}
			else
			{
				session=request.getSession();
				session.setAttribute("al1", al1);
				session.setAttribute("al2", al2);
				response.sendRedirect("/Bank/GetStatement.jsp");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}


	}

}
