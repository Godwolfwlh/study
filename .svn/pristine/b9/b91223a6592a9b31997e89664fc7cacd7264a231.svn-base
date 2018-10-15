package com.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ajax.AjaxResult;
import com.ajax.ObjectVo;
import com.sys.entity.UserBean;

/**
 * 基本拦截器：
 * @author Administrator
 *
 */
public class BaseInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("---------afterCompletion--------");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("---------postHandle--------");
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("---------preHandle--------");
		ObjectVo vo=new ObjectVo(arg0, arg1);
		UserBean user=vo.getCurrentUser();
		if(user==null) {
			AjaxResult result=new AjaxResult();
			result.setStatusCode(100);
			result.setStatusMessage("session已失效，请重新登录");
			vo.writeJSON(result);
			return false;
		}else {
			return true;
		}
	}

}
