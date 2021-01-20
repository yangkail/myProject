package com.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;


public class LoginFilter implements Filter {

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

		String user_account = (String) session.getAttribute("user_account");
		System.out.println(user_account==null);
		System.out.println("取得session,"+user_account);
		if (user_account == null) {
			session.setAttribute("location", req.getRequestURI());
			System.out.println("載入濾器");
			res.sendRedirect(req.getContextPath() + "/back-end/user/page/logIn.jsp");
			return;
		}
		chain.doFilter(req, res);
		
	}

}
