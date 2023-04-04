package edu.ucam.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter extends HttpFilter implements Filter {

    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		System.out.println("FILTER");

		HttpServletRequest req = (HttpServletRequest)request;
		System.out.println("\tServlet Path: "+req.getServletPath());
		System.out.println("\tRequested URI: "+req.getRequestURI());

		System.out.println(req.getSession().getAttribute("EMAIL_LOGIN"));
		System.out.println(req.getSession());

		if(req.getSession().getAttribute("EMAIL_LOGIN")==null)
		{
			((HttpServletResponse)response).sendRedirect(req.getContextPath()+ "/login.jsp");
			System.out.println("SendRedirect to login.jsp");
			//req.getRequestDispatcher("login.jsp").forward(req, response);
		}
		else
		{
			System.out.println("Continue Filter");
			chain.doFilter(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
