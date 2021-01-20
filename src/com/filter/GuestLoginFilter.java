package com.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;


public class GuestLoginFilter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		String user_account = (String) session.getAttribute("gs_email");
		if (user_account == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/front-end/Guest_Table/login2.jsp");
			return;
		}
		chain.doFilter(req, res);
		
	}

}