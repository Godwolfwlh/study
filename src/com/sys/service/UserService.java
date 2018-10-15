package com.sys.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajax.AjaxResult;
import com.ajax.ObjectVo;
import com.common.Page;
import com.sys.dao.UserMapper;
import com.sys.entity.UserBean;
import com.util.DateTimeUtile;
import com.util.Md5;
import com.util.StringUtils;

@Service
public class UserService{
	
	@Resource
	private UserMapper userMapper;

	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int addUser(ObjectVo vo) throws Exception{
		UserBean entity=(UserBean) vo.JsonToBean(UserBean.class);
		entity.setStaffId(StringUtils.getUUid());
		entity.setCreateBy(vo.getCurrentUser().getStaffId());
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		entity.setPwd(new Md5().getMD5ofStr("123456"));
		entity.setEnabledFlag("1");
		return userMapper.addUser(entity);
	}
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateUser(ObjectVo vo) throws Exception{
		UserBean entity=(UserBean) vo.JsonToBean(UserBean.class);
		entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		return userMapper.updateUser(entity);
	}
	
	/**
	 * 物理删除
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int deleteUser(ObjectVo vo) throws Exception{
		Object staffId=vo.getFormValue().get("staffId");
		if(staffId!=null && StringUtils.isNotBlank(staffId.toString())) {
			return userMapper.deleteUser(staffId.toString());
		}else {
			return -1;
		}
	}
	
	/**
	 * 逻辑删除
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int removeUser(ObjectVo vo) throws Exception{
		Object staffId=vo.getFormValue().get("staffId");
		if(staffId!=null && StringUtils.isNotBlank(staffId.toString())) {
			return userMapper.removeUser(staffId.toString());
		}else {
			return -1;
		}
	}
	
	/**
	 * 分页显示
	 * @return
	 */
	public Page findByPage(ObjectVo vo){
		Map<String,Object> map=vo.getParameterMap();
		map.put("pageNow",(vo.getPage()-1) * vo.getPageSize());
		map.put("pageSize",vo.getPageSize());
		List<UserBean> list=userMapper.findByPage(map);
		Page page=new Page<UserBean>();
		page.setRows(list);
		page.setTotal(this.getCount(map));
		return page;
	}
	
	/**
	 * 根据id查询
	 * @return
	 */
	public UserBean findById(ObjectVo vo){
		Object staffId=vo.getFormValue().get("staffId");
		if(staffId!=null && StringUtils.isNotBlank(staffId.toString())) {
			return userMapper.findById(staffId.toString());
		}else {
			return null;
		}
	}
	
	/**
	 * 获得最大序列号
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public int getMaxOrderFlag(ObjectVo vo){
		Map<String, Object> map=vo.getParameterMap();
		return userMapper.getMaxOrderFlag(map);
	}
	
	/**
	 * 查找用户
	 * @return
	 */
	public List<UserBean> findUserList(){
		return userMapper.findUserList();
	}
	
	/**
	 * 查找用户
	 * @return
	 */
	public int getCount(Map<String,Object> map){
		return userMapper.getCount(map);
	}
	
	/**
	 * 查询已选择用户
	 * @param roleId
	 * @return
	 */
	public List<UserBean> findSelectRole(String roleId) {
		return userMapper.findSelectRole(roleId);
	}
	
	/**
	 * 查询未选择用户
	 * @param roleId
	 * @return
	 */
	public List<UserBean> findNoSelectRole(String roleId) {
		return userMapper.findNoSelectRole(roleId);
	}
	
	/**
	 * 系统登录
	 * @return
	 */
	public AjaxResult userlogin(ObjectVo vo){
		AjaxResult result=new AjaxResult();
		try {
			UserBean user=(UserBean) vo.JsonToBean(UserBean.class);
			Object randCheckCode=vo.getRequest().getSession().getAttribute("randCheckCode");
			Object captcha=vo.getFormValue().get("captcha");
			
			//System.out.println(randCheckCode+"--"+captcha);
			if(randCheckCode!=null && captcha!=null && StringUtils.hasLength(randCheckCode+"") && 
					StringUtils.hasLength(captcha+"") && captcha.toString().equalsIgnoreCase(randCheckCode.toString())){
				UserBean bean=userMapper.userlogin(user.getStaffNo());
				if(bean!=null){
					Md5 md5=new Md5();
					String pwd=md5.getMD5ofStr(user.getPwd());
					if(pwd.equals(bean.getPwd())){
						result.setStatusCode(0);
						result.setStatusMessage("登录成功！！！");
						vo.getRequest().getSession().setAttribute("current_user", bean);
					}else{
						result.setStatusCode(-1);
						result.setStatusMessage("密码输入不正确！！！");
					}
				}else{
					result.setStatusCode(-1);
					result.setStatusMessage("系统没有该用户！！！");
				}
			}else{
				result.setStatusCode(-1);
				result.setStatusMessage("验证码输入不正确！！！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 系统退出登录
	 * @return
	 */
	public AjaxResult userloginOut(ObjectVo vo){
		AjaxResult result=new AjaxResult();
		try {
			result.setStatusCode(0);
			result.setStatusMessage("注销成功！！！");
			vo.getRequest().getSession().removeAttribute("cuurUser");
			vo.getRequest().getSession().invalidate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatusCode(-1);
			result.setStatusMessage(e.getMessage());
		}
		return result;
	}
	
}
