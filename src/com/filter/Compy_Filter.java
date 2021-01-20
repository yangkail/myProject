package com.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Compy_Filter implements Filter{

	private FilterConfig config=null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config=config;
	}
	
	@Override
	public void destroy() {
		config=null;
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		
		HttpSession session=req.getSession();
		if(session.getAttribute("authority_compy")==null){
			res.sendRedirect(req.getContextPath()+"/front-end/compy_table/front_CompyLogin.jsp");
		}else{
			Map<String,String> map=(Map)session.getAttribute("authority_compy");	
			if("2".equals(map.get("authority"))||"11".equals(map.get("authority"))||"12".equals(map.get("authority"))){
				chain.doFilter(request, response);
			}else{
				res.sendRedirect(req.getContextPath()+"/front-end/compy_table/front_CompyLogin.jsp");
			}
		}
		
	}

}
