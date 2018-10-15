package com.basic.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajax.AjaxResult;
import com.ajax.ObjectVo;
import com.basic.entity.OccupancyBean;
import com.basic.service.OccupancyService;
import com.common.Page;

@Controller
@RequestMapping(value="occupancyController/")
public class OccupancyController{
	
	@Resource
	private OccupancyService occupancyService;
	
	/**
	 * 根据ID查询
	 * @param roomId
	 * @return
	 */
	@RequestMapping(value="findPancyById.do",method=RequestMethod.POST)
	public void findPancyById(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		OccupancyBean bean=bean=occupancyService.findPancyById(vo);
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
	@RequestMapping(value="deletePancyById.do",method=RequestMethod.POST)
	public void deletePancyById(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		int cnt=occupancyService.deletePancyById(vo);
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
	 * 退房信息
	 * @param roomId
	 * @return
	 */
	@RequestMapping(value="cancelRoom.do",method=RequestMethod.POST)
	public void cancelRoom(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		int cnt=occupancyService.removePancyById(vo);
		if(cnt>0) {
			result.setStatusCode(0);
			result.setStatusMessage("取消房间成功");
		}else {
			result.setStatusCode(-1);
			result.setStatusMessage("取消房间失败");
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 新增
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="insertPancy.do",method=RequestMethod.POST)
	public void insertPancy(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=occupancyService.insertPancy(vo);
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
	@RequestMapping(value="insertPancySelective.do",method=RequestMethod.POST)
	public void insertPancySelective(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=occupancyService.insertPancySelective(vo);
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
	@RequestMapping(value="updatePancySelective.do",method=RequestMethod.POST)
	public void updatePancySelective(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=occupancyService.updatePancySelective(vo);
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
	@RequestMapping(value="updatePancyById.do",method=RequestMethod.POST)
	public void updatePancyById(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=occupancyService.updatePancySelective(vo);
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
	@RequestMapping(value="findPancyByPage.do",method=RequestMethod.POST)
	@ResponseBody
	public Page findPancyByPage(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		return occupancyService.findPancyByPage(vo);
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
		return occupancyService.findByPage(vo);
	}
	
	/**
	 * 获得最大序列号
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="getPancyMaxOrderFlag.do",method=RequestMethod.GET)
	public void getPancyMaxOrderFlag(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int orderFlag=occupancyService.getPancyMaxOrderFlag(vo);
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
	 * 订单关联查询
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="queryAssoByPancyId.do",method=RequestMethod.POST)
	public void queryAssoByPancyId(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			OccupancyBean entity=occupancyService.queryAssoByPancyId(vo);
			result.setStatusCode(0);
			result.setStatusMessage("操作成功");
			result.setResult(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage(e.getMessage());
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 状态为预定 转成入住
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="checkInRoom.do",method=RequestMethod.POST)
	public void checkInRoom(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			occupancyService.checkInRoom(vo);
			result.setStatusCode(0);
			result.setStatusMessage("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage("保存失败："+e.getMessage());
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 退房
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="checkOutRoom.do",method=RequestMethod.POST)
	public void checkOutRoom(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			occupancyService.checkOutRoom(vo);
			result.setStatusCode(0);
			result.setStatusMessage("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage("保存失败："+e.getMessage());
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 已入住，要求换房
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="changeRoom.do",method=RequestMethod.POST)
	public void changeRoom(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			result=occupancyService.changeRoom(vo);
			//result.setStatusCode(0);
			//result.setStatusMessage("保存失败："+"保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage("保存失败："+e.getMessage());
		}
		vo.writeJSON(result);
	}
}
