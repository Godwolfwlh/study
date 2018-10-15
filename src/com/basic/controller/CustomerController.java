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
import com.basic.entity.CustomerBean;
import com.basic.service.CustomerService;
import com.common.Page;

@Controller
@RequestMapping(value="customerController/")
public class CustomerController{
	
	@Resource
	private CustomerService customerService;
	
	/**
	 * 根据ID查询
	 * @param roomId
	 * @return
	 */
	@RequestMapping(value="findCustById.do",method=RequestMethod.POST)
	public void findCustById(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		CustomerBean bean=bean=customerService.findCustById(vo);
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
	@RequestMapping(value="deleteCustById.do",method=RequestMethod.POST)
	public void deleteCustById(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		int cnt=customerService.deleteCustById(vo);
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
	@RequestMapping(value="insertCust.do",method=RequestMethod.POST)
	public void insertCust(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=customerService.insertCust(vo);
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
	@RequestMapping(value="insertCustRetId.do",method=RequestMethod.POST)
	public void insertCustRetId(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			CustomerBean entity=customerService.insertCustRetId(vo);
			if(entity !=null) {
				result.setStatusCode(0);
				result.setResult(entity.getCustId());
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
	@RequestMapping(value="insertCustSelective.do",method=RequestMethod.POST)
	public void insertCustSelective(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=customerService.insertCustSelective(vo);
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
	@RequestMapping(value="updateCustSelective.do",method=RequestMethod.POST)
	public void updateCustSelective(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=customerService.updateCustSelective(vo);
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
	@RequestMapping(value="updateCustById.do",method=RequestMethod.POST)
	public void updateCustById(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=customerService.updateCustSelective(vo);
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
	@RequestMapping(value="findCustByPage.do",method=RequestMethod.POST)
	@ResponseBody
	public Page findCustByPage(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		return customerService.findCustByPage(vo);
	}
	
	/**
	 * 获得最大序列号
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="getCustMaxOrderFlag.do",method=RequestMethod.GET)
	public void getCustMaxOrderFlag(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int orderFlag=customerService.getCustMaxOrderFlag(vo);
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
