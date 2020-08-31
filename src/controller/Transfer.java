package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

public class Transfer extends HttpServlet 
{
	HttpSession session;
	boolean value;
	boolean value2;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			Model m=new Model();
			String rvcno1=request.getParameter("rvcno");
			String amount1=request.getParameter("amount");
			int rvcno=Integer.parseInt(rvcno1);
			m.setRvcno(rvcno);
	
			int amount=Integer.parseInt(amount1);
			m.setAmount(amount);

			session=request.getSession();
			int accno=(int) session.getAttribute("accno");
			m.setAccno(accno);

			value=m.transfer1();
			if(value==true)
			{
				value2=m.transfer2(rvcno);
				if(value2==true)
				{
					response.sendRedirect("/Bank/transferSuccess.html");
				}
				else
				{
					response.sendRedirect("/Bank/refund.html");
				}

			}
			else
			{
				response.sendRedirect("/Bank/transferFail.html");

			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

}
