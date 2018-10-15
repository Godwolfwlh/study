package com.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajax.AjaxResult;
import com.ajax.ObjectVo;
import com.common.Page;
import com.sys.entity.MenuBean;
import com.sys.entity.UserBean;
import com.sys.service.UserService;
import com.util.StringUtils;

@Controller
@RequestMapping(value="/userController/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="addUser.do",method=RequestMethod.POST)
	@ResponseBody
	public void addUser(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=userService.addUser(vo);
			if(cnt>0){
				result.setStatusCode(0);
				result.setStatusMessage("新增成功");
			}else{
				result.setStatusCode(-1);
				result.setStatusMessage("新增失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage(e.getMessage());
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="updateUser.do",method=RequestMethod.POST)
	public void updateUser(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=userService.updateUser(vo);
			if(cnt>0){
				result.setStatusCode(0);
				result.setStatusMessage("修改成功");
			}else{
				result.setStatusCode(-1);
				result.setStatusMessage("修改失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage(e.getMessage());
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 物理删除
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="deleteUser.do",method=RequestMethod.POST)
	public void deleteUser(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=userService.deleteUser(vo);
			if(cnt>0){
				result.setStatusCode(0);
				result.setStatusMessage("删除成功");
			}else{
				result.setStatusCode(-1);
				result.setStatusMessage("删除失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage(e.getMessage());
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 逻辑删除
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="removeUser.do",method=RequestMethod.POST)
	public void removeUser(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=userService.removeUser(vo);
			if(cnt>0){
				result.setStatusCode(0);
				result.setStatusMessage("删除成功");
			}else{
				result.setStatusCode(-1);
				result.setStatusMessage("删除失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage(e.getMessage());
		}
		vo.writeJSON(result);
	}
	
	@RequestMapping(value="findByPage.do",method=RequestMethod.POST)
	@ResponseBody
	public Page findByPage(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		return userService.findByPage(vo);
	}
	
	//系统用户登录
	@RequestMapping(value="userlogin.do",method=RequestMethod.POST)
	public void userlogin(HttpServletRequest request,HttpServletResponse response){
		try {
			ObjectVo vo=new ObjectVo(request, response);
			AjaxResult result=userService.userlogin(vo);
			vo.writeJSON(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//退出系统登录
	@RequestMapping(value="userloginOut.do",method=RequestMethod.POST)
	public void userloginOut(HttpServletRequest request,HttpServletResponse response){
		try {
			ObjectVo vo=new ObjectVo(request, response);
			AjaxResult result=userService.userloginOut(vo);
			vo.writeJSON(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="findUserList.do")
	@ResponseBody
	public AjaxResult findUserList(){
		AjaxResult result=new AjaxResult();
		try {
			List<UserBean> list=userService.findUserList();
			result.setResult(list);
			result.setStatusCode(0);
			result.setStatusMessage("成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage("失败");
		}
		return result;
	}
	
	/**
	 * 查询已选择用户
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="findSelectRole.do")
	public void findSelectRole(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		Object roleId=vo.getValueKey("roleId");
		if(roleId!=null && StringUtils.isNotBlank(roleId+"")) {
			List<UserBean> list=userService.findSelectRole(roleId.toString());
			result.setResult(list);
			result.setStatusCode(0);
			result.setStatusMessage("成功");
		}else {
			result.setStatusCode(-1);
			result.setStatusMessage("失败");
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 查询未选择用户
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="findNoSelectRole.do")
	public void findNoSelectRole(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		Object roleId=vo.getValueKey("roleId");
		if(roleId!=null && StringUtils.isNotBlank(roleId+"")) {
			List<UserBean> list=userService.findNoSelectRole(roleId.toString());
			result.setResult(list);
			result.setStatusCode(0);
			result.setStatusMessage("成功");
		}else {
			result.setStatusCode(-1);
			result.setStatusMessage("失败");
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 获得最大序列号
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="getMaxOrderFlag.do",method=RequestMethod.POST)
	public void getMaxOrderFlag(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int orderFlag=userService.getMaxOrderFlag(vo);
			result.setStatusCode(0);
			result.setStatusMessage("操作成功");
			result.setResult(orderFlag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage(e.getMessage());
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 根据ID查询
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="findById.do",method=RequestMethod.POST)
	public void findById(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			UserBean bean= userService.findById(vo);
			result.setStatusCode(0);
			result.setStatusMessage("操作成功");
			result.setResult(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage("操作失败");
		}
		vo.writeJSON(result);
	}
}
