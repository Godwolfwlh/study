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
import com.sys.entity.DictionaryBean;
import com.sys.service.DictionaryService;
import com.util.StringUtils;

/**
 * 菜单类型管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/dictionaryController")
public class DictionaryController {
	@Resource
	private DictionaryService dictionaryService;
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="addDictionary.do",method=RequestMethod.POST)
	public void addDictionary(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=dictionaryService.addDictionary(vo);
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
	@RequestMapping(value="updateDictionary.do",method=RequestMethod.POST)
	public void updateDictionary(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=dictionaryService.updateDictionary(vo);
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
	@RequestMapping(value="deleteDictionary.do",method=RequestMethod.POST)
	public void deleteDictionary(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String,Object> map){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int cnt=dictionaryService.deleteDictionary(vo);
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
	@RequestMapping(value="findDictionaryByPage.do",method=RequestMethod.POST)
	@ResponseBody
	public Page findDictionaryByPage(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		return dictionaryService.findDictionaryByPage(vo);
	}
	
	/**
	 * 根据ID查询
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="findByDictionaryId.do",method=RequestMethod.POST)
	public void findByDictionaryId(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			DictionaryBean bean= dictionaryService.findByDictionaryId(vo);
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
	 * 根据ID查询
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="findDictionaryList.do",method=RequestMethod.POST)
	public void findDictionaryList(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			List<DictionaryBean> bean= dictionaryService.findDictionaryList(vo);
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
	 * 获得最大序列号
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value="getMaxOrderFlag.do",method=RequestMethod.GET)
	public void getMaxOrderFlag(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			int orderFlag=dictionaryService.getMaxOrderFlag();
			if("".equals(orderFlag) || StringUtils.isBlank(orderFlag+"")) {
				orderFlag=1;
			}
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
	 * 根据分类编码查询
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="findByTypeCode.do",method=RequestMethod.POST)
	public void findByTypeCode(HttpServletRequest request,HttpServletResponse response){
		ObjectVo vo=new ObjectVo(request,response);
		AjaxResult result=new AjaxResult();
		try {
			Map<String, Object> map=dictionaryService.findByTypeCode(vo);
			result.setStatusCode(0);
			result.setStatusMessage("操作成功");
			result.setResult(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage(e.getMessage());
		}
		vo.writeJSON(result);
	}
	
}
