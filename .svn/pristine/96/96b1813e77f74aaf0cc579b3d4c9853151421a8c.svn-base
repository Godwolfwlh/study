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
import com.basic.entity.ReceiptBean;
import com.basic.service.ReceiptService;
import com.common.Page;

@Controller
@RequestMapping(value="receiptController/")
public class ReceiptController{
	
	@Resource
	private ReceiptService receiptService;
	
	/**
	 * 根据ID查询
	 * @param roomId
	 * @return
	 */
	@RequestMapping(value="findReceiptById.do",method=RequestMethod.POST)
	public void findReceiptById(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		ReceiptBean bean=bean=receiptService.findReceiptById(vo);
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
	@RequestMapping(value="deleteReceiptById.do",method=RequestMethod.POST)
	public void deleteReceiptById(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		int cnt=receiptService.deleteReceiptById(vo);
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
	@RequestMapping(value="insertReceipt.do",method=RequestMethod.POST)
	public void insertReceipt(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=receiptService.insertReceipt(vo);
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
	@RequestMapping(value="insertReceiptRetId.do",method=RequestMethod.POST)
	public void insertReceiptRetId(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			ReceiptBean entity=receiptService.insertReceiptRetId(vo);
			if(entity !=null) {
				result.setStatusCode(0);
				result.setResult(entity.getReceiptId());
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
	@RequestMapping(value="insertReceiptSelective.do",method=RequestMethod.POST)
	public void insertReceiptSelective(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=receiptService.insertReceiptSelective(vo);
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
	@RequestMapping(value="updateReceiptSelective.do",method=RequestMethod.POST)
	public void updateReceiptSelective(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=receiptService.updateReceiptSelective(vo);
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
	@RequestMapping(value="updateReceiptById.do",method=RequestMethod.POST)
	public void updateReceiptById(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request, response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=receiptService.updateReceiptSelective(vo);
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
	@RequestMapping(value="findReceiptByPage.do",method=RequestMethod.POST)
	@ResponseBody
	public Page findReceiptByPage(HttpServletRequest request,HttpServletResponse response) {
		ObjectVo vo=new ObjectVo(request, response);
		return receiptService.findReceiptByPage(vo);
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
		return receiptService.findByPage(vo);
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
			int orderFlag=receiptService.getMaxOrderFlag(vo);
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
