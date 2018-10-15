package com.basic.controller;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajax.AjaxResult;
import com.ajax.ObjectVo;
import com.basic.entity.RoomInfoBean;
import com.basic.service.RoomInfoService;
import com.common.Page;

@Controller
@RequestMapping(value="/roomInfoController/")
public class RoomInfoController{
	
	@Resource
	private RoomInfoService roomInfoService;
	
	/**
	 * 根据ID查询
	 * @param roomId
	 * @return
	 */
	@RequestMapping(value="findById.do",method=RequestMethod.POST)
	public void findById(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		RoomInfoBean bean=bean=roomInfoService.findById(vo);
		result.setResult(bean);
		result.setStatusCode(0);
		result.setStatusMessage("查询成功");
		vo.writeJSON(result);
	}
	
	/**
	 * 根据ID删除
	 * @param roomId
	 * @return
	 */
	@RequestMapping(value="deleteById.do",method=RequestMethod.POST)
	public void deleteById(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		int cnt=roomInfoService.deleteById(vo);
		if(cnt>0) {
			result.setStatusCode(0);
			result.setStatusMessage("删除成功");
		}else {
			result.setStatusCode(-1);
			result.setStatusMessage("删除失败");
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 新增
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="insertRoom.do",method=RequestMethod.POST)
	public void insertRoom(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=roomInfoService.insertRoomInfo(vo);
			if(cnt>0) {
				result.setStatusCode(0);
				result.setStatusMessage("新增成功");
			}else {
				result.setStatusCode(-1);
				result.setStatusMessage("新增失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage("新增失败,"+e.getMessage());
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 新增
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="insertSelective.do",method=RequestMethod.POST)
	public void insertSelective(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=roomInfoService.insertSelective(vo);
			if(cnt>0) {
				result.setStatusCode(0);
				result.setStatusMessage("新增成功");
			}else {
				result.setStatusCode(-1);
				result.setStatusMessage("新增失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage("新增失败,"+e.getMessage());
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="updateSelective.do",method=RequestMethod.POST)
	public void updateSelective(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=roomInfoService.updateSelective(vo);
			if(cnt>0) {
				result.setStatusCode(0);
				result.setStatusMessage("修改成功");
			}else {
				result.setStatusCode(-1);
				result.setStatusMessage("修改失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage("修改失败,"+e.getMessage());
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="updateById.do",method=RequestMethod.POST)
	public void updateById(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=roomInfoService.updateSelective(vo);
			if(cnt>0) {
				result.setStatusCode(0);
				result.setStatusMessage("修改成功");
			}else {
				result.setStatusCode(-1);
				result.setStatusMessage("修改失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage("修改失败,"+e.getMessage());
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 分页查询
	 * @param roomId
	 * @return
	 */
	@RequestMapping(value="findByPage.do",method=RequestMethod.POST)
	@ResponseBody
	public Page findByPage(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		return roomInfoService.findByPage(vo);
	}
	
	/**
	 * 首页分页查询
	 * @param roomId
	 * @return
	 */
	@RequestMapping(value="findByWebPage.do",method=RequestMethod.POST)
	public void findByWebPage(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			Page page=roomInfoService.findByWebPage(vo);
			result.setStatusCode(0);
			result.setStatusMessage("查询成功");
			result.setResult(page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage(e.getMessage());
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 查询所有
	 * @param roomId
	 * @return
	 */
	@RequestMapping(value="findList.do",method=RequestMethod.POST)
	public void findList(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			List<RoomInfoBean> list=roomInfoService.findList(vo);
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
			int orderFlag=roomInfoService.getMaxOrderFlag(vo);
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
}
