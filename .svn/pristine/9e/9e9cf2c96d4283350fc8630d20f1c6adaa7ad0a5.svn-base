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
import com.sys.entity.MenuTypeBean;
import com.sys.service.MenuTypeService;

/**
 * 菜单类型管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="menuTypeController/")
public class MenuTypeController {
	@Resource
	private MenuTypeService menuTypeService;
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="addMenuType.do",method=RequestMethod.POST)
	public void addMenuType(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			MenuTypeBean entity=(MenuTypeBean) vo.JsonToBean(MenuTypeBean.class);
			int cnt=menuTypeService.addMenuType(entity);
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
	@RequestMapping(value="updateMenuType.do",method=RequestMethod.POST)
	public void updateMenuType(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			MenuTypeBean entity=(MenuTypeBean) vo.JsonToBean(MenuTypeBean.class);
			int cnt=menuTypeService.updateMenuType(entity);
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
	 * 修改
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="deleteMenuType.do",method=RequestMethod.POST)
	public void deleteMenuType(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String,Object> map){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			MenuTypeBean entity=(MenuTypeBean) vo.JsonToBean(MenuTypeBean.class);
			int cnt=menuTypeService.deleteMenuType(entity);
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
	@RequestMapping(value="findMenuTypeByList.do",method=RequestMethod.POST)
	@ResponseBody
	public Page findMenuTypeByList(@RequestParam Map<String,Object> map) throws Exception{
		return menuTypeService.findMenuTypeByList(map);
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
			MenuTypeBean bean= menuTypeService.findById(vo);
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
			List<MenuTypeBean> list= menuTypeService.findByList(vo);
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
