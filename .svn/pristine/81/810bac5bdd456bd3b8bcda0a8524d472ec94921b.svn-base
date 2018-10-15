package com.util.gps;

import java.net.URL;

import com.util.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 通过经纬度
 * @author Administrator
 *
 */
public class LatLonUtile {
	/**
	 * 纬度
	 * 值：26,33,51.0974  说明：26(度)，33(分)，51.0974(秒)
	 * 计算公式: 纬度=((分+(秒/60))/60)+度;
	 * @param latitude
	 * @return
	 */
	public static double gpsLatitude(String latitude) {
		double result=0;
		if(StringUtils.isNotBlank(latitude) && StringUtils.hasLength(latitude) && !"undefined".equals(latitude)) {
			String[] str=latitude.split(",");
			double d=0;//度
			double f=0;//分
			double m=0;//秒
			if(str.length>0 && str!=null) {
				if(StringUtils.isNotBlank(str[0]) && StringUtils.hasLength(str[0])) {
					d=Double.parseDouble(str[0]);
				}else {
					d=0f;
				}
				if(StringUtils.isNotBlank(str[1]) && StringUtils.hasLength(str[1])) {
					f=Double.parseDouble(str[1]);
				}else {
					f=0f;
				}
				if(StringUtils.isNotBlank(str[2]) && StringUtils.hasLength(str[2])) {
					m=Double.parseDouble(str[2]);
				}else {
					m=0f;
				}
				//计算公式: 纬度=((分+(秒/60))/60)+度;
				result=((f+(m/60))/60)+d;
			}
		}
		return result;
	}
	
	/**
	 * 经度
	 * 值：26,33,51.0974  说明：26(度)，33(分)，51.0974(秒)
	 * 计算公式: 经度=((分+(秒/60))/60)+度;
	 * @param latitude
	 * @return
	 */
	public static double gpsLongitude(String longitude) {
		double result=0;
		if(StringUtils.isNotBlank(longitude) && StringUtils.hasLength(longitude) && !"undefined".equals(longitude)) {
			String[] str=longitude.split(",");
			double d=0;//度
			double f=0;//分
			double m=0;//秒
			if(str.length>0 && str!=null) {
				if(StringUtils.isNotBlank(str[0]) && StringUtils.hasLength(str[0])) {
					d=Double.parseDouble(str[0]);
				}else {
					d=0f;
				}
				if(StringUtils.isNotBlank(str[1]) && StringUtils.hasLength(str[1])) {
					f=Double.parseDouble(str[1]);
				}else {
					f=0f;
				}
				if(StringUtils.isNotBlank(str[2]) && StringUtils.hasLength(str[2])) {
					m=Double.parseDouble(str[2]);
				}else {
					m=0f;
				}
				//计算公式: 纬度=((分+(秒/60))/60)+度;
				result=((f+(m/60))/60)+d;
			}
		}
		return result;
	}
	
	/**
	 * 显示具体地理位置
	 * @param latitude 纬度
	 * @param longitude 经度
	 * @param 格式 ：   107,57,24.0216
	 * 返回值格式：[{"addr":"","admCode":"522601","admName":"贵州省,黔东南苗族侗族自治州,凯里市,","distance":153.082,"id":"ANB0357008P5","name":"凯里城南客运站","nearestPoint":[107.95662,26.56572],"status":1,"type":"poi"}]
	 * 值说明：编码-admCode  省市县-admName  name：具体位置
	 * @return 
	 */
	public static JSONArray gpsLocation(String longitude,String latitude) {
		 if(StringUtils.isNotBlank(latitude) && StringUtils.isNotBlank(longitude) &&
				 !"undefined".equals(longitude) && !"undefined".equals(latitude)) {
			 String add = getAdd(longitude,latitude);
			 JSONObject jsonObject=JSONObject.fromObject(add);
			 JSONArray jsonArray = jsonObject.getJSONArray("addrList");
			 if(jsonArray!=null && jsonArray.size()>0) {
				 /*JSONObject jso = jsonArray.getJSONObject(0);
				 String allAdd = jso.getString("admName");  
				 String arr[] = allAdd.split(",");  
				 if(arr!=null && arr.length>0) {
					 String adds = jso.getString("name");  
					 System.out.println(jsonArray);
					 System.out.println("省："+arr[0]+"\n市："+arr[1]+"\n区："+arr[2]+"\n"+adds);
				 }*/
				 return jsonArray;
			 }else {
				 return null;
			 }
		 }else {
			 return null;
		 }
	}
	
	/** 
     * 经纬度格式  转换
     * @param point 坐标点 
     * @return 
     */ 
    public static String pointToLatlong (String point ) { 
    	if(StringUtils.isBlank(point) && StringUtils.hasLength(point)) {
    		Double du = Double.parseDouble(point.substring(0, point.indexOf("°")).trim());  
    		Double fen = Double.parseDouble(point.substring(point.indexOf("°")+1, point.indexOf("'")).trim());  
    		Double miao = Double.parseDouble(point.substring(point.indexOf("'")+1, point.indexOf("\"")).trim());  
    		Double duStr = du + fen / 60 + miao / 60 / 60 ;  
    		return duStr.toString();  
    	}else {
    		return "";
    	}
    }  
    /**
     * 
     * @param log 经度
     * @param lat 纬度
     * @return
     */
    public static String getAdd(String log, String lat ){  
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)  
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";  
        String res = "";     
        try {     
            URL url = new URL(urlString);    
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();    
            conn.setDoOutput(true);    
            conn.setRequestMethod("POST");    
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));    
            String line;    
           while ((line = in.readLine()) != null) {    
               res += line+"\n";    
         }    
            in.close();    
        } catch (Exception e) {    
            System.out.println("error in wapaction,and e is " + e.getMessage());    
        }   
        return res;    
    }  
    
    public static void main(String[] args) {
    	//26,33,51.1415
    	//JSONArray json=gpsLocation(gpsLatitude("107,57,24.0216")+"",gpsLongitude("26,33,51.0974")+"");
    	//JSONArray json=gpsLocation(gpsLatitude("107,57,23.9183")+"",gpsLongitude("26,33,51.1415")+"");
    	JSONArray json=gpsLocation("107.95650482177734","26.564220428466797");
    	System.out.println(json);
    }
}
