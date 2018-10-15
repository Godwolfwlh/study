package com.basic.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ajax.AjaxResult;
import com.ajax.BaseMessage;
import com.ajax.ObjectVo;
import com.basic.entity.CaseBean;
import com.basic.service.CaseService;
import com.common.Page;

@Controller
@RequestMapping(value="caseController/")
public class CaseController{
	
	@Resource
	private CaseService caseService;
	
	/**
	 * 根据ID查询
	 * @param roomId
	 * @return
	 */
	@RequestMapping(value="findById.do",method=RequestMethod.POST)
	public void findById(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			CaseBean bean=caseService.selectById(vo);
			result.setResult(bean);
			result.setStatusCode(BaseMessage.CODE0);
			result.setStatusMessage(BaseMessage.QUERY_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(BaseMessage.CODE_1);
			result.setStatusMessage(BaseMessage.QUERY_ERROR+e.getMessage());
		}
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
		try {
			int cnt=caseService.deleteById(vo);
			if(cnt>0) {
				result.setStatusCode(BaseMessage.CODE0);
				result.setStatusMessage(BaseMessage.DELETE_SUCCESS);
			}else {
				result.setStatusCode(BaseMessage.CODE_1);
				result.setStatusMessage(BaseMessage.DELETE_ERROR);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(BaseMessage.CODE_1);
			result.setStatusMessage(BaseMessage.DELETE_ERROR+e.getMessage());
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 新增
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="insertCase.do",method=RequestMethod.POST)
	public void insertCase(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=caseService.insertCase(vo);
			if(cnt>0) {
				result.setStatusCode(BaseMessage.CODE0);
				result.setStatusMessage(BaseMessage.SAVE_SUCCESS);
			}else {
				result.setStatusCode(BaseMessage.CODE_1);
				result.setStatusMessage(BaseMessage.SAVE_ERROR);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(BaseMessage.CODE_1);
			result.setStatusMessage(BaseMessage.SAVE_ERROR+e.getMessage());
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
			int cnt=caseService.insertSelective(vo);
			if(cnt>0) {
				result.setStatusCode(BaseMessage.CODE0);
				result.setStatusMessage(BaseMessage.SAVE_SUCCESS);
			}else {
				result.setStatusCode(-1);
				result.setStatusCode(BaseMessage.CODE_1);
				result.setStatusMessage(BaseMessage.SAVE_SUCCESS);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(BaseMessage.CODE_1);
			result.setStatusMessage(BaseMessage.SAVE_ERROR+e.getMessage());
		}
		vo.writeJSON(result);
	}
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="updateByIdSelective.do",method=RequestMethod.POST)
	public void updateByIdSelective(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=caseService.updateByIdSelective(vo);
			if(cnt>0) {
				result.setStatusCode(BaseMessage.CODE0);
				result.setStatusMessage(BaseMessage.SAVE_SUCCESS);
			}else {
				result.setStatusCode(-1);
				result.setStatusCode(BaseMessage.CODE_1);
				result.setStatusMessage(BaseMessage.SAVE_SUCCESS);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(BaseMessage.CODE_1);
			result.setStatusMessage(BaseMessage.SAVE_ERROR+e.getMessage());
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
			int cnt=caseService.updateById(vo);
			if(cnt>0) {
				result.setStatusCode(BaseMessage.CODE0);
				result.setStatusMessage(BaseMessage.SAVE_SUCCESS);
			}else {
				result.setStatusCode(-1);
				result.setStatusCode(BaseMessage.CODE_1);
				result.setStatusMessage(BaseMessage.SAVE_SUCCESS);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(BaseMessage.CODE_1);
			result.setStatusMessage(BaseMessage.SAVE_ERROR+e.getMessage());
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
		return caseService.findByPage(vo);
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
			int orderFlag=caseService.getMaxOrderFlag(vo);
			result.setStatusCode(BaseMessage.CODE0);
			result.setStatusMessage(BaseMessage.QUERY_SUCCESS);
			result.setResult(orderFlag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(BaseMessage.CODE_1);
			result.setStatusMessage(BaseMessage.QUERY_SUCCESS+e.getMessage());
		}
		vo.writeJSON(result);
	}
}
