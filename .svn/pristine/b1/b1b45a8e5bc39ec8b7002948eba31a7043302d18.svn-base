package com.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;

import com.ajax.AjaxResult;
import com.ajax.ObjectVo;
import com.common.Page;
import com.common.TreeNode;
import com.sys.entity.MenuBean;
import com.sys.service.MenuService;
import com.util.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 菜单类型管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/menuController/")
public class MenuController {
	@Resource
	private MenuService menuService;
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="addMenu.do",method=RequestMethod.POST)
	@ResponseBody
	public void addMenu(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=menuService.addMenu(vo);
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
	@RequestMapping(value="updateMenu.do",method=RequestMethod.POST)
	public void updateMenu(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=menuService.updateMenu(vo);
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
	@RequestMapping(value="deleteMenu.do",method=RequestMethod.POST)
	public void deleteMenu(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String,Object> map){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=menuService.deleteMenu(vo);
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
	@RequestMapping(value="findMenuByPage.do",method=RequestMethod.POST)
	@ResponseBody
	public Page findMenuByPage(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		try {
			return menuService.findMenuByPage(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Page();
		}
	}
	
	/**
	 * 根据ID查询
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="findByMenuId.do",method=RequestMethod.POST)
	public void findByMenuId(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			MenuBean bean= menuService.findById(vo);
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
	@RequestMapping(value="findMenuList.do",method=RequestMethod.POST)
	@ResponseBody
	public List<MenuBean> findMenuList(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		/*AjaxResult result=new AjaxResult();
		try {*/
			List<MenuBean> list= menuService.findMenuList(vo);
			return list;
			/*result.setStatusCode(0);
			result.setStatusMessage("操作成功");
			result.setResult(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage("操作失败");
		}
		vo.writeJSON(result);*/
	}
	
	/**
	 * 权限查询菜单
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="findPermissionMenu.do",method=RequestMethod.POST)
	public void findPermissionMenu(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			List<MenuBean> list= menuService.findPermissionMenu(vo);
			Map<String,Object> map=new HashMap<String,Object>();
			if(list!=null && list.size()>0) {
				List<MenuBean> list1=new ArrayList<MenuBean>();
				List<MenuBean> list2=new ArrayList<MenuBean>();
				for (MenuBean entity : list) {
					if("".equals(entity.getParentId()) || StringUtils.isBlank(entity.getParentId()) 
							|| "-1".equals(entity.getParentId()) || "0".equals(entity.getParentId())) {
						list1.add(entity);
					}else {
						list2.add(entity);
					}
				}
				map.put("list1",list1);
				map.put("list2",list2);
			}
			
			result.setStatusCode(0);
			result.setStatusMessage("操作成功");
			result.setResult(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage("操作失败");
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 树形菜单查询
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="findTree.do",method=RequestMethod.POST)
	public void findTree(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			List<TreeNode> list=menuService.findTree(vo);
			result.setStatusCode(0);
			result.setStatusMessage("操作成功");
			result.setResult(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage(e.getMessage());
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 树形菜单查询
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="findMenuTree.do",method=RequestMethod.POST)
	@ResponseBody
	public List<MenuBean> findMenuTree(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		try {
			List<MenuBean> list=menuService.findMenuList(vo);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 树形查询
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="findTreeNode.do",method=RequestMethod.POST)
	public void findTreeNode(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			List<TreeNode> listNode=new ArrayList<TreeNode>();
			List<MenuBean> list=menuService.findMenuList(vo);
			if(list.size()>0 && list!=null) {
				for (MenuBean en : list) {
					TreeNode node=new TreeNode();
					node.setId(en.getMenuId());
					node.setName(en.getMenuName());
					node.setOpen(true);
					if(StringUtils.hasLength(en.getParentId())) {
						node.setPid(en.getParentId());
					}else {
						node.setPid("0");
					}
					listNode.add(node);
				}
			}
			result.setStatusCode(0);
			result.setStatusMessage("操作成功");
			result.setResult(listNode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage("操作失败");
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 获得最大序列号
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="getMaxOrderFlag.do",method=RequestMethod.GET)
	public void getMaxOrderFlag(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int orderFlag=menuService.getMaxOrderFlag();
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
	 * 插入中间表
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="addMenuRole.do",method=RequestMethod.POST)
	public void addMenuRole(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int orderFlag=menuService.addMenuRole(vo);
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
	 * 角色菜单信息
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="findMenuByRoleId.do",method=RequestMethod.POST)
	@ResponseBody
	public Page findMenuByRoleId(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		try {
			return menuService.findMenuByRoleId(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; 
		}
	}
	
	/**
	 * 根据角色ID查询菜单信息
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="queryMenuByRoleId.do",method=RequestMethod.POST)
	public void queryMenuByRoleId(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			List<MenuBean> list=menuService.queryMenuByRoleId(vo);
			result.setStatusCode(0);
			result.setStatusMessage("操作成功");
			result.setResult(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage(e.getMessage());
		}
		vo.writeJSON(result);
	}
}
