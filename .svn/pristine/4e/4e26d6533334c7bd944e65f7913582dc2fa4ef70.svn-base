package com.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sys.entity.UserBean;
import com.util.DateTimeUtile;
import com.util.StringUtils;

import net.sf.json.JSONObject;

/**
 * 所有实体bean对象
 * @author Administrator
 *
 */
public class ObjectVo implements Serializable{
	private static final long serialVersionUID = 7110397857359375935L;
	Logger logger=LoggerFactory.getLogger(getClass());
	private HttpServletRequest request;
	private HttpServletResponse response;
	private int page=0;
	private int pageSize=10;
	
	public ObjectVo() {
		// TODO Auto-generated constructor stub
	}
	
	public int getPage() {
		String page1=this.getRequest().getParameter("page");
		if(!"".equals(page1) && StringUtils.isNotBlank(page1)) {
			return Integer.parseInt(page1);
		}else {
			return page;
		}
	}

	public int getPageSize() {
		String rows=this.getRequest().getParameter("rows");
		if(!"".equals(rows) && StringUtils.isNotBlank(rows)) {
			return Integer.parseInt(rows);
		}else {
			return pageSize;
		}
	}

	public ObjectVo(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated constructor stub
		this.request=request;
		this.response=response;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	/**
	 * 获得当前登录人
	 * @return
	 */
	public UserBean getCurrentUser(){
		UserBean user=(UserBean) this.getRequest().getSession().getAttribute("current_user");
		if(user!=null){
			return user;
		}else{
			return null;
		}
	}
	
	//==============================================================================获得Ajax提交 字符串转成java对象form表单数据 返回map===================================================================//
	/**
	 * ajax前台传来的参数
	 * @param
	 * @return
	 */
	public JSONObject getFormValue() {
		try {
			this.getResponse().setContentType("application/json; charset=utf-8");
			this.getResponse().setHeader("Cache-Control","no-store, no-cache, must-revalidate, max-age=0");
			String str=new String(this.getRequest().getParameter("ajax_params").getBytes("ISO-8859-1"),"UTF-8");
			if(StringUtils.isNotBlank(str)){
				str = str.replaceAll("%(?![0-9a-fA-F]{2})","%25");
				String urlStr = URLDecoder.decode(str,"UTF-8");
				urlStr= processSpecialSymbols(urlStr);
				return JSONObject.fromObject(urlStr);
			}else{
				logger.debug("页面传来参数值为空");
				return null;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.debug(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获得远程参数
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public JSONObject getRomoteParams() throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String input = new String(request.getParameter("ajax_params").getBytes("ISO-8859-1"),"UTF-8");
		if(StringUtils.isNotBlank(input) && StringUtils.hasLength(input)){
			input = URLDecoder.decode(input, "utf-8");
			input = processSpecialSymbols(input);
			JSONObject jsonObject = JSONObject.fromObject(input);
			return jsonObject;
		}else {
			return null;
		}
	}
	
	/**
	 * 处理特殊符号 @ 当前处理了& --$|$
	 * 
	 * @param param
	 * @return
	 */
	public String processSpecialSymbols(String param) {
		if (StringUtils.hasLength(param)) {
			param= StringUtils.replace(param, "$|$", "&");
			return StringUtils.replace(param, "%2B", "+");
		} else {
			return param;
		}
	}
	
	/**
	 * 获得key值
	 * @param key
	 * @return
	 */
	public Object getValueKey(String key) {
		if(StringUtils.isNotBlank(key) && this.getFormValue().size()>0 && !"".equals(key)){
			return this.getFormValue().get(key);
		}else{
			return null;
		}
	}
	//=============================================================================end==================================================================//
	
	// =========================================================从request中获得参数Map，并返回可读的Map=======================================================//
	/**
	 * 从request中获得参数Map，并返回可读的Map
	 * 例如：request中的参数t1=1&t1=2&t2=3
	 * 形成的map结构： key=t1;value[0]=1,value[1]=2  
	 * 直接用map.get("t1")
	 * @param request
	 * @return map集合参数
	 */
	public Map<String,Object> getParameterMap() {  
	    // 返回值Map  
		Map<String, Object> returnMap=null;
		try {
			// 参数Map  
			Map<String, String[]> properties = this.request.getParameterMap();  
			returnMap = new HashMap<String, Object>();  
			Iterator entries = properties.entrySet().iterator();  
			Map.Entry entry=null;  
			String name = "";  
			String value = "";  
			while (entries.hasNext()) {  
			    entry = (Map.Entry) entries.next();  
			    name = (String) entry.getKey();  
			    Object valueObj = entry.getValue();  
			    if(null == valueObj){  
			        value = "";  
			    }else if(valueObj instanceof String[]){  
			        String[] values = (String[])valueObj;  
			        for(int i=0;i<values.length;i++){  
			            value = values[i] + ",";  
			        }  
			        value = value.substring(0, value.length()-1);  
			    }else{  
			        value = valueObj.toString();  
			    }  
			    returnMap.put(name, value);  
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    return returnMap;  
	}
	
	/**
	 * 获得request参数
	 * @return
	 */
	public Object getReqParams(String key){
		Map<String,Object> map=this.getParameterMap();
		if(map!=null){
			if(!"".equals(key) && StringUtils.hasLength(key)){
				return map.get(key);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	
	//========================================================================================================================================//
	
	//========================================================================JSON装成pojo实体对象=========================================================//
	/**
	 * JSON装成pojo实体对象
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public Object JsonToBean(Class cls) throws Exception{
		//JSONObject jsonObject=this.getRomoteParams();
		JSONObject jsonObject=this.getFormValue();
		Object object =null;
		if(jsonObject!=null && jsonObject.size()>0){
			object = Class.forName(cls.getName()).newInstance();  
	    	Class<?> obj = object.getClass();  
	    	Field[] file=obj.getDeclaredFields();
	    	for(int i=0;i<file.length;i++){
	    		Field flist=file[i];
	    		flist.setAccessible(true);  
	    		//字段名称
	    		String name=flist.getName();
	    		//字段类型
	    		Class<?> ftype=flist.getType();
	    		String type=ftype.getName();
	    		if(jsonObject.containsKey(name)){
	    			//int类型
	        		if(DateType.TYPE_INT.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_INT)!=-1){
	        			if(StringUtils.isNotBlank(jsonObject.get(name).toString())){
	        				flist.set(object,Integer.parseInt(jsonObject.get(name).toString()));
	        			}
	        		}else if(DateType.TYPE_INTEGER.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_INTEGER)!=-1){//long类型
	        			if(StringUtils.isNotBlank(jsonObject.get(name).toString())){
	        				flist.set(object,Integer.parseInt(jsonObject.get(name).toString()));
	        			}
	        		}else if(DateType.TYPE_LONG.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_LONG)!=-1){//long类型
	        			if(StringUtils.isNotBlank(jsonObject.get(name).toString())){
	        				flist.set(object,Long.parseLong(jsonObject.get(name).toString()));
	        			}
	        		}else if(DateType.TYPE_CHAR.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_CHAR)!=-1){//char类型
	        			if(StringUtils.isNotBlank(jsonObject.get(name).toString())){
	        				flist.set(object,jsonObject.get(name).toString().charAt(0));
	        			}
	        		}else if(DateType.TYPE_BIGDECIMAL.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_BIGDECIMAL)!=-1){//BigDecimal类型
	        			if(StringUtils.isNotBlank(jsonObject.get(name).toString())){
	        				flist.set(object,new BigDecimal(jsonObject.get(name).toString()));
	        			}
	        		}else if(DateType.TYPE_FLOAT.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_FLOAT)!=-1){//Float类型
	        			if(StringUtils.isNotBlank(jsonObject.get(name).toString())){
	        				flist.set(object,Float.parseFloat(jsonObject.get(name).toString()));
	        			}
	        		}if(DateType.TYPE_DOUBLE.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_DOUBLE)!=-1){//Double类型
	        			if(StringUtils.isNotBlank(jsonObject.get(name).toString())){
	        				flist.set(object,Double.parseDouble(jsonObject.get(name).toString()));
	        			}
	        		}else if(DateType.TYPE_TIMESTAMP.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_TIMESTAMP)!=-1){//Timestamp类型
	        			if(StringUtils.isNotBlank(jsonObject.get(name).toString()) ){
	        				flist.set(object,DateTimeUtile.getTimesTmpStr(jsonObject.get(name).toString()));
	        			}
	        		}else if(DateType.TYPE_SQL_DATE.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_SQL_DATE)!=-1){//sql.Date类型
	        			if(StringUtils.isNotBlank(jsonObject.get(name).toString())){
	        				flist.set(object,DateTimeUtile.getJavaSQlYmd(jsonObject.get(name).toString()));
	        			}
	        		}else if(DateType.TYPE_UTIL_DATE.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_UTIL_DATE)!=-1){//Date类型
	        			if(StringUtils.isNotBlank(jsonObject.get(name).toString())){
	        				flist.set(object,DateTimeUtile.getJavaUtilYmd(jsonObject.get(name).toString()));
	        			}
	        		}else if(DateType.TYPE_STRING.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_STRING)!=-1){//String类型
	        			if(StringUtils.isNotBlank(jsonObject.get(name).toString())){
	        				flist.set(object,jsonObject.get(name));
	        			}else{
	        				flist.set(object,"");
	        			}
	        		}
	    		}
	    		
	    	}
		}else{
			
		}
    	return object;
	}
	
	/**
	 * 所有vo对象实体
	 * @param cls 实体bean
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static Object ModelToBean(Class cls,Map<String,Object> map) throws Exception{
    	Object object = Class.forName(cls.getName()).newInstance();  
    	Class<?> obj = object.getClass();  
    	Field[] file=obj.getDeclaredFields();
    	for(int i=0;i<file.length;i++){
    		Field flist=file[i];
    		flist.setAccessible(true);  
    		//字段名称
    		String name=flist.getName();
    		//字段类型
    		Class<?> ftype=flist.getType();
    		String type=ftype.getName();
    		if(map.containsKey(name)){
    			//int类型
        		if(DateType.TYPE_INT.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_INT)!=-1){
        			if(StringUtils.isNotBlank(map.get(name).toString())){
        				flist.set(object,Integer.parseInt(map.get(name).toString()));
        			}
        		}else if(DateType.TYPE_LONG.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_LONG)!=-1){//long类型
        			if(StringUtils.isNotBlank(map.get(name).toString())){
        				flist.set(object,Long.parseLong(map.get(name).toString()));
        			}
        		}else if(DateType.TYPE_CHAR.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_CHAR)!=-1){//char类型
        			if(StringUtils.isNotBlank(map.get(name).toString())){
        				flist.set(object,map.get(name).toString().charAt(0));
        			}
        		}else if(DateType.TYPE_BIGDECIMAL.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_BIGDECIMAL)!=-1){//BigDecimal类型
        			if(StringUtils.isNotBlank(map.get(name).toString())){
        				flist.set(object,new BigDecimal(map.get(name).toString()));
        			}
        		}else if(DateType.TYPE_FLOAT.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_FLOAT)!=-1){//Float类型
        			if(StringUtils.isNotBlank(map.get(name).toString())){
        				flist.set(object,Float.parseFloat(map.get(name).toString()));
        			}
        		}if(DateType.TYPE_DOUBLE.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_DOUBLE)!=-1){//Double类型
        			if(StringUtils.isNotBlank(map.get(name).toString())){
        				flist.set(object,Double.parseDouble(map.get(name).toString()));
        			}
        		}else if(DateType.TYPE_TIMESTAMP.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_TIMESTAMP)!=-1){//Timestamp类型
        			if(StringUtils.isNotBlank(map.get(name).toString())){
        				flist.set(object,DateTimeUtile.getTimesTmpStr(map.get(name).toString()));
        			}
        		}else if(DateType.TYPE_SQL_DATE.equalsIgnoreCase(type)){//sql.Date类型
        			if(StringUtils.isNotBlank(map.get(name)+"")){
        				flist.set(object,DateTimeUtile.getJavaSQlYmd(map.get(name).toString()));
        			}
        		}else if(DateType.TYPE_UTIL_DATE.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_UTIL_DATE)!=-1){//Date类型
        			if(StringUtils.isNotBlank(map.get(name).toString())){
        				flist.set(object,DateTimeUtile.getJavaUtilYmd(map.get(name).toString()));
        			}
        		}else if(DateType.TYPE_STRING.equalsIgnoreCase(type) || type.indexOf(DateType.TYPE_STRING)!=-1){//String类型
        			if(StringUtils.isNotBlank(map.get(name).toString())){
        				flist.set(object,map.get(name));
        			}else{
        				flist.set(object,"");
        			}
        		}
    		}
    		
    	}
    	return object;
    }
	
	/**
	 * jsonObject转化map
	 * @param json
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String,Object> jsonObjectConverMap(JSONObject json){
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			if(json!=null) {
				Set set=json.keySet();
				Iterator it=set.iterator();
				while(it.hasNext()){
			        String key = (String) it.next();
			        //得到value的值
			        Object value = json.get(key);
			        map.put(key, value);
			    }
				return map;
			}else {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	//==============================================================================================================================================//
	
	/**
	 * 返回json字符串
	 * @param obj
	 * @throws IOException
	 */
	public void writeJSON(Object obj){
		if(obj!=null){
			PrintWriter w=null;
			try {
				this.response.setContentType("application/json; charset=utf-8");
				this.response.setHeader("Cache-Control","no-store, no-cache, must-revalidate, max-age=0");
				w = this.response.getWriter();
				w.write(JSONObject.fromObject(obj).toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				w.flush();
				w.close();
			}
		}
	}
}
