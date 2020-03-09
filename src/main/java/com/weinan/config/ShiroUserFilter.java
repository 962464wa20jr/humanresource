package com.weinan.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weinan.common.Contant;
import com.weinan.common.result.CommResult;
import com.weinan.dao.UserDao;
import com.weinan.entity.User;

import net.sf.json.JSONObject;

public class ShiroUserFilter extends UserFilter {
	
	@Resource UserDao userDao;

	@Override
	protected boolean preHandle(ServletRequest request,ServletResponse response) throws Exception{
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if(httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
			setHeader(httpRequest,httpResponse);
			return true;
		}
//		System.out.println(httpRequest.getParameter("staffCode"));
//		System.out.println(httpRequest.getParameter("password"));
		return super.preHandle(request, response);
	}
	
	@Override
	protected void saveRequestAndRedirectToLogin(ServletRequest request,ServletResponse response) throws IOException{
		saveRequest(request);
		setHeader((HttpServletRequest)request,(HttpServletResponse)response);
		PrintWriter out = response.getWriter();
		out.println(JSONObject.fromObject(CommResult.ok(Contant.RESULT_UNAUTHORIZED_TEXT)).toString());
		out.flush();
		out.close();
	}
	
	private void setHeader(HttpServletRequest request,HttpServletResponse response){
        //跨域的header设置
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", request.getMethod());
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        //防止乱码，适用于传输JSON数据
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
    }

}
