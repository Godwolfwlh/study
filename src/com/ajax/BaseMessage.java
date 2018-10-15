package com.ajax;

import java.io.Serializable;

/**
 * 消息提示类
 * @author Administrator
 *
 */
public class BaseMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//添加成功
	public final static String ADD_SUCCESS="添加成功";
	
	//添加失败
	public final static String ADD_ERROR="添加失败";
	
	//修改成功
	public final static String UPDATE_SUCCESS="修改成功";
	
	//修改失败
	public final static String UPDATE_ERROR="修改失败:";
	
	//删除成功
	public final static String DELETE_SUCCESS="删除成功";
	
	//删除失败
	public final static String DELETE_ERROR="删除失败:";
	
	//查询成功
	public final static String QUERY_SUCCESS="查询成功";
	
	//查询失败
	public final static String QUERY_ERROR="查询失败:";
	
	//保存成功
	public final static String SAVE_SUCCESS="保存成功";
	
	//保存失败
	public final static String SAVE_ERROR="保存失败:";
	
	//请求成功
	public final static int CODE_200=200;
	
	//Bad Request 请求出现语法错误。
	public final static int CODE_400=400;
	
	//Not Found 无法找到指定位置的资源
	public final static int CODE_404=404;
	
	//Method Not Allowed 请求方法（GET、POST、HEAD、Delete、PUT、TRACE等）对指定的资源不适用，用来访问本页面的 HTTP 谓词不被允许（方法不被允许）
	public final static int CODE_405=405;
	
	//Internal Server Error 服务器遇到了意料不到的情况，不能完成客户的请求。
	public final static int CODE_500=500;
	
	//系统自定义
	//请求成功 状态码
	public final static int CODE0=0;
	//请求失败 状态码
	public final static int CODE_1=-1;
	//判断是否重复 状态码
	public final static int CODE1=1;
}
