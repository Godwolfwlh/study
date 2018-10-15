package com.util;

import org.apache.log4j.Logger;

/**  

* <p>Title: LogUtil.java</p>  

* <p>Description: The Log Util Class</p>  

* <p>Copyright: Copyright (c) 2018</p>  

* <p>Company: Kaili Yun Han Zhi Hui City Operation Management Co.,Ltd</p>  

* @author yangshenghua  

* @date 2018-03-05 09:32:34  

* @version 1.0  

*/
public class LogUtil {
	   public static Logger initLog(Class<?> clazz) {
			Logger logger = Logger.getLogger(clazz);
			return logger;
	   }

}
