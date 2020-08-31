package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ChangePwdFIlter implements Filter 
{

    public ChangePwdFIlter() 
    {
    	
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		String npwd=request.getParameter("npwd");
		String cpwd=request.getParameter("cpwd");
		if(npwd.equals(cpwd))
		{
			chain.doFilter(request, response);			
		}
		else
		{
			((HttpServletResponse)(response)).sendRedirect("/Bank/mismatch.html");
		}
		
	}

}
