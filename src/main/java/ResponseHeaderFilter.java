package edu.uark.util.servletfilters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * @author Steven Cole, Michael Akerman
 */
public final class ResponseHeaderFilter implements Filter
{
	private volatile FilterConfig fc;

	@Override
	public void init ( final FilterConfig fc ) throws ServletException
	{
		this.fc = fc;
	}

	@Override
	public void doFilter ( final ServletRequest request, final ServletResponse response, final FilterChain chain ) throws IOException, ServletException
	{
		doFilter( (HttpServletRequest) request, (HttpServletResponse) response, chain );
	}

	private void doFilter ( final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain ) throws IOException, ServletException
	{
		final Enumeration parameters = fc.getInitParameterNames();
		while ( parameters.hasMoreElements() )
		{
			final String parameter = (String) parameters.nextElement();
			response.setHeader( parameter, fc.getInitParameter( parameter ) );
		}
		chain.doFilter( request, response );
	}

	@Override
	public void destroy ()
	{
		//required by the Filter interface
	}
}