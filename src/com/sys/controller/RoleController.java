package com.sys.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajax.AjaxResult;
import com.ajax.ObjectVo;
import com.common.Page;
import com.sys.entity.RoleBean;
import com.sys.entity.UserRoleBean;
import com.sys.service.RoleService;

/**
 * 菜单类型管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/roleController/")
public class RoleController {
	@Resource
	private RoleService roleService;
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="addRole.do",method=RequestMethod.POST)
	public void addRole(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=roleService.addRole(vo);
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
	 * 新增用户角色
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="saveUserRole.do",method=RequestMethod.POST)
	public void saveUserRole(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int status=roleService.addUserRole(vo);
			if(status!=-1) {
				result.setStatusCode(0);
				result.setStatusMessage("新增成功");
			}else {
				result.setStatusCode(-1);
				result.setStatusMessage("插入角色失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage("插入角色失败："+e.getMessage());
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="updateRole.do",method=RequestMethod.POST)
	public void updateRole(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=roleService.updateRole(vo);
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
	 * 删除角色
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="deleteRole.do",method=RequestMethod.POST)
	public void deleteRole(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=roleService.deleteRole(vo);
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
	 * 删除菜单和角色中间表
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="deleteRoleIdMenuId.do",method=RequestMethod.POST)
	public void deleteRoleIdMenuId(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=roleService.deleteRoleIdMenuId(vo);
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
	 * 分页查询
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="findRoleByPage.do",method=RequestMethod.POST)
	@ResponseBody
	public Page findRoleByList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ObjectVo vo=new ObjectVo(request,response);
		return roleService.findRoleByPage(vo);
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
			RoleBean bean= roleService.findById(vo);
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
	
	/**
	 * 查询所有
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="findByList.do",method=RequestMethod.POST)
	public void findByList(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			List<RoleBean> list= roleService.findByList(vo);
			result.setStatusCode(0);
			result.setStatusMessage("操作成功");
			result.setResult(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage("操作失败");
		}
		vo.writeJSON(result);
	}
}
