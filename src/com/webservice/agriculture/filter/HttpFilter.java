package com.webservice.agriculture.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 自定义的HttpFilter,实现自Filter接口
 * @author Sunward
 * 2017/5
 */
public abstract class HttpFilter implements Filter{

	/**
	 * 用于保存FilterConfig对象
	 */
	private FilterConfig filterConfig;

	/**
	 * 原生的doFilter方法，在方法内部把ServletRequest和ServletResponse转为
	 * HttpServletRequest和HttpServletResonse，并调用了doFilter(HttpServletRequest request,HttpServletResponse response,
	 *	FilterChain chain) 方法
	 *若边线FilterConfig 的过滤方法不建议直接继承该方法，而继承 doFilter(HttpServletRequest request,HttpServletResponse response,
	 *		FilterChain chain) 
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		doFilter(request, response, chain);
	}

	/**
	 * 抽象方法，为Http请求定制，必须实现的方法
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	public abstract void doFilter(HttpServletRequest request,HttpServletResponse response,
			FilterChain chain) throws IOException, ServletException ;
	
	/**
	 * 不建议子类直接覆盖，若直接覆盖，将可能会导致filterConfig成员变量初始化失败
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		init();
	}
	/**
	 * 供子类继承的初始化方法，可以通过getFilterConfig()获取FilterConfig对象
	 */
	protected void init(){}
	
	/**
	 * 直接返回init(ServletConfig)的FilterConfig对象
	 * @return
	 */
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	/**
	 * 空的destroy()方法
	 */
	@Override
	public void destroy() {
		
	}
}
