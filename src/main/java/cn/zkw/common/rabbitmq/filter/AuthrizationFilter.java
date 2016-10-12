/**
 * Classname:AuthrizationFilter.java
 * Version:1.0
 * Date:2013-06-26
 * Copyright (c) 2013 cloud su.
 */
package cn.zkw.common.rabbitmq.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.zkw.common.rabbitmq.common.Constants;
import org.apache.commons.lang3.StringUtils;

/**
 * Class description:
 * @author cloud
 * @date
 * @modify by cloud su 2012-12-3
 */
public class AuthrizationFilter implements Filter {
	
	private String[] excludesURI;
	/**
     * 
     * @param 
     * @return
     * @throws 
     */
	@Override
	public void destroy() {
	}

	/**
     * 
     * @param ServletRequest
     * @param ServletResponse
     * @param FilterChain
     * @return
     * @throws IOException, ServletException
     */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String path = request.getRequestURI();
		if (isNeedAuth(path) && !isLogin(request.getSession())) {
			response.sendRedirect(request.getContextPath() + "/" + Constants.INDEX_URL);
			return;
		}
		
		chain.doFilter(request, response);
	}

	/**
     * 
     * @param 
     * @return boolean
     * @throws 
     */
	private boolean isLogin(HttpSession hSession) {
		return hSession.getAttribute(Constants.LOGGED_IN_USER) != null;
	}
	
	/**
     * 
     * @param 
     * @return boolean
     * @throws 
     */
	private boolean isNeedAuth(String path) {
		if(path.indexOf(Constants.JSON_REQUEST)>=0){
			return false;
		}
		boolean flag = true;
		int n = excludesURI.length;
		for(int i=0;i<n;i++){
			if(path.indexOf(excludesURI[i])>=0){
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	/**
     * 
     * @param FilterConfig
     * @return
     * @throws ServletException
     */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		String exs = filterConfig.getInitParameter("excludes");
		if(StringUtils.isNotBlank(exs)) {
			if(exs.endsWith(",")) {
				exs = exs.substring(0, exs.length() - 1);
			}
			excludesURI = exs.split(",");
		}
	}

}
