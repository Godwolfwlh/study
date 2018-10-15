package com.util;
import java.text.ParseException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateTimeUtile {
	private static final String DATE_FMT_Y="yyyy";
	private static final String DATE_FMT_YM="yyyy-MM";
	private static final String DATE_FMT_YMD="yyyy-MM-dd";
	private static final String DATE_FMT_YMDHMS="yyyy-MM-dd HH:mm:ss";
	private static final String DATE_FMT_YMDHM="yyyy-MM-dd HH:mm";
	
	/**
	 * 返回2000-01-01日
	 * @param date_str 日期字符串
	 * @return java.util.date
	 */
	public static java.util.Date getJavaUtilYmd(String date_str){
		java.util.Date date=null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FMT_YMD);
			date=sdf.parse(date_str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			date=null;
		}
		return date;
	}
	
	/**
	 * str 转成 Date
	 * @param str
	 * @param fmt
	 * @return
	 */
	public static java.util.Date LocaleFmtStr(String str,String fmt){
       try{
    	   if(StringUtils.isNotBlank(str) && !str.equals("null")){
    		   SimpleDateFormat sdf1 = new SimpleDateFormat (str, Locale.UK);
    		   java.util.Date date=sdf1.parse(str);
    		   SimpleDateFormat sdf=new SimpleDateFormat(fmt);
    		   String sDate=sdf.format(date);
    		   return getJavaUtilYmd(sDate);
    	   }else{
    		   return null;
    	   }
           //System.out.println(sDate+"---"+DateTimeUtile.getJavaSQlYmd(sDate));
       }
       catch (ParseException e){
           e.printStackTrace();
           return null;
       }
	}
	public static Date dd(Date d){
		if(d!=null){
			System.out.println("y:"+d);
			return d;
		}else{
			System.out.println("null:"+d);
			return d;
		}
	}
	/**
	 * 返回2000-01-01
	 * @param date_str 日期字符串
	 * @return java.util.date
	 */
	public static java.sql.Date getJavaSQlYmd(String date_str){
		java.sql.Date date=null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FMT_YMD);
			java.util.Date _date=sdf.parse(date_str);
			date=new java.sql.Date(_date.getTime());  
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			date=null;
		}
		return date;
	}
	/**
	 * 返回2000-01-01 11:11:11 
	 * @param date_str 日期字符串
	 * @return Timestamp
	 */
	public static Timestamp getTimesTmpStr(String date_str){
		if(StringUtils.isNotBlank(date_str) && StringUtils.hasLength(date_str)) {
			Timestamp ts = new Timestamp(System.currentTimeMillis());   
			try {   
				ts = Timestamp.valueOf(date_str);   
			} catch (Exception e) {   
				e.printStackTrace();   
			}  
			return ts;
		}else{
			return null;
		}
	}
	
	
	public static Timestamp getTimesTmp(){
		java.util.Date date = new java.util.Date();       
		Timestamp nousedate = new Timestamp(date.getTime());
		return nousedate;
	}
	/** 
	* 将java.util.Date日期转化为java.sql.Date 
	* @param udate 
	* @return 
	*/  
	public static java.sql.Date converUtilToSql(java.util.Date udate) {  
	   return new java.sql.Date(udate.getTime());  
	}  

	/** 
	* 将java.sql.Date日期转化为java.util.Date 
	* @param udate 
	* @return 
	*/  
	public static java.util.Date converSqlToUtil(java.sql.Date udate) {  
		if(udate!=null){
			return new java.util.Date(udate.getTime());  
		}else{
			return null;
		}
	} 
		
	/**
	 * 日期转字符串
	 * @param paramDate
	 * @param dateFormat
	 * @return
	 */
	public static String dateToStr(Date paramDate,String dateFormat){
		if (paramDate==null || dateFormat==null || dateFormat=="") {
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
		return sdf.format(paramDate);
	}
}
