package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 字符串工具类
 * 
 * @author panrz 2016-03-15
 */
public class StringUtils {
	/**
	 * 将字符串有某种编码转变成另一种编码
	 * 
	 * @param string
	 *            编码的字符串
	 * @param originCharset
	 *            原始编码格式
	 * @param targetCharset
	 *            目标编码格式
	 * @return String 编码后的字符串
	 */
	public static String encodeString(String string, Charset originCharset,
			Charset targetCharset) {
		return string = new String(string.getBytes(originCharset),
				targetCharset);
	}
	
	/**
	 * 
	 * replace
	 * 
	 * @描述：字符串替换函数
	 * @param inString
	 * 輸入字符串 ,oldPattern 将要被替换的子串，newPattern 替换以后的子串
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @Exception 异常对象
	 */
	public static String replace(String inString, String oldPattern,
			String newPattern) {
		if (!hasLength(inString) || !hasLength(oldPattern)
				|| newPattern == null) {
			return inString;
		}
		StringBuffer sbuf = new StringBuffer();
		int pos = 0;
		int index = inString.indexOf(oldPattern);
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));
		return sbuf.toString();
	}

	/**
	 * URL编码
	 * 
	 * @param string
	 *            编码字符串
	 * @param charset
	 *            编码格式
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public static String encodeUrl(String string, String charset) {
		if (null != charset && !charset.isEmpty()) {
			try {
				return URLEncoder.encode(string, charset);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return URLEncoder.encode(string);
	}

	/**
	 * URL编码
	 * 
	 * @param string
	 *            解码字符串
	 * @param charset
	 *            解码格式
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public static String decodeUrl(String string, String charset) {
		if (null != charset && !charset.isEmpty()) {
			try {
				return URLDecoder.decode(string, charset);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		}
		return URLDecoder.decode(string);
	}

	/**
	 * 判断字符串是否是空的 方法摘自commons.lang
	 * 
	 * <pre>
	 * StringUtils.isEmpty(null)      = true
	 * StringUtils.isEmpty("")        = true
	 * StringUtils.isEmpty(" ")       = false
	 * StringUtils.isEmpty("bob")     = false
	 * StringUtils.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * <p>
	 * 判断字符串是否是""," ",null,注意和isEmpty的区别
	 * </p>
	 * 方法摘自commons.lang
	 * 
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * 判断字符串是否是""," ",null,注意和isEmpty的区别
	 * </p>
	 * 方法摘自commons.lang
	 * 
	 * <pre>
	 */
	public static boolean isNotBlank(String str) {
		if (str == null || (str.length()) == 0 || "".equals(str.trim())
				|| "undefined".equalsIgnoreCase(str)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获取项目的绝对路径
	 * 
	 * @return Sting 获取项目的绝对路径
	 */
	public static String getProjectAbsolutelyPath() {
		return System.getProperty("user.dir");
	}

	/**
	 * 
	 * getWebClassesPath
	 * 
	 * @描述：取运行的CLass文件路径
	 * @param name
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @Exception 异常对象
	 */
	public static String getWebClassesPath() {
		if (StringUtils.class.getResource("/") == null) {
			return "";
		}
		String path = StringUtils.class.getResource("/").getFile();
		path = StringUtils.replaceAll(path, "%20", " ");
		return path;
	}

	/**
	 * 获取所在的盘符
	 * 
	 * @return String
	 */
	public static String getProjectDrivePath() {
		return new File("/").getAbsolutePath();
	}

	/**
	 * 获取指定类的路径
	 * 
	 * @param clazz
	 * @return String
	 */
	public static String getClassAbsolutePath(Class<?> clazz) {
		return clazz.getResource("").getPath().substring(1);
	}

	/**
	 * 获取字符串的MD5
	 * 
	 * @param String
	 *            字符串
	 * @return String MD5字符串
	 * @throws NoSuchAlgorithmException
	 */
	public static String getStringMD5(String str)
			throws NoSuchAlgorithmException {
		byte[] byteString = str.getBytes(Charset.forName("utf-8"));
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] array = md.digest(byteString);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(
					1, 3));
		}
		return sb.toString();
	}

	/**
	 * 获取文件MD5
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws DigestException
	 */
	public static String getFileMD5(String filePath)
			throws NoSuchAlgorithmException, IOException, DigestException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		int length = 0;
		byte[] buffer = new byte[1024];
		byte[] array = null;
		InputStream is = new FileInputStream(new File(filePath));
		while ((length = is.read(buffer)) != -1) {
			md.update(buffer, 0, length);
		}
		is.close();
		array = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(
					1, 3));
		}
		return sb.toString();
	}

	/**
	 * 
	 * replace
	 * 
	 * @描述：字符串替换函数
	 * @param inString
	 *            輸入字符串 ,oldPattern 将要被替换的子串，newPattern 替换以后的子串
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @Exception 异常对象
	 */
	public static String replaceAll(String inString, String oldPattern,
			String newPattern) {
		if (newPattern == null) {
			return inString;
		}
		StringBuffer sbuf = new StringBuffer();
		int pos = 0;
		int index = inString.indexOf(oldPattern);
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));
		return sbuf.toString();
	}

	/**
	 * 获得uuid
	 * 
	 * @return
	 */
	public static final String getUUid() {
		UUID uuid = UUID.randomUUID();
		String uid = uuid.toString();
		return uid.replaceAll("-", "");
	}

	/**
	 * 获取文件后缀名称
	 * @param path 路径
	 * @param ident 标识("," "." ...)
	 * @return
	 */
	public static String getFileSuffix(String path, String ident) {
		String name = path.substring(path.lastIndexOf(ident) + 1);
		return name;
	}
	
	public static void main(String[] args) {
		// System.out.println(getUUid());
		System.out.println(isNotBlank("") ? "y" : "no");
		
		System.out.println(parseBigDecimal("100.00"));
		
	}
	
	/**
	 * 
	 * length
	 * 
	 * @描述：获取字符串的长度
	 * @param name
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @Exception 异常对象
	 */
	public int length(String str) {
		if (StringUtils.hasLength(str)) {
			return str.length();
		} else {
			return 0;
		}
	}
	/**
	 * 
	 * hasLength
	 * 
	 * @描述：判断字符串是否为空，字符串<>NULL and length>0 才返回TRUE
	 * @param name
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @Exception 异常对象
	 */
	public static boolean hasLength(String str) {
		return hasLength((CharSequence) str);
	}
	
	/**
	 * hasLength
	 * @描述：判断字符串是否为空，字符串<>NULL and length>0 才返回TRUE
	 * @param name
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @Exception 异常对象
	 */
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}
	
	/**
	 * obj 转成 BigDecimal类型
	 * @param obj
	 * @return
	 */
	public static BigDecimal parseBigDecimal(Object obj) {
		if(obj!=null && !"undefined".equals(obj) && StringUtils.hasLength(obj.toString()) 
				 && StringUtils.isNotBlank(obj.toString())) {
			return new BigDecimal(obj.toString());
		}else {
			return new BigDecimal("0.00");
		}
	}
	
	/**
	 * obj 转成 Float类型
	 * @param obj
	 * @return
	 */
	public static Float parseFloat(Object obj) {
		if(obj!=null && !"undefined".equals(obj) && StringUtils.hasLength(obj.toString()) 
				 && StringUtils.isNotBlank(obj.toString())) {
			return Float.parseFloat(obj.toString());
		}else {
			return Float.parseFloat("0.00");
		}
	}
	
	/**
	 * obj 转成 Float类型
	 * @param obj
	 * @return
	 */
	public static double parseDouble(Object obj) {
		if(obj!=null && !"undefined".equals(obj) && StringUtils.hasLength(obj.toString()) 
				 && StringUtils.isNotBlank(obj.toString())) {
			return Double.parseDouble(obj.toString());
		}else {
			return Double.parseDouble("0.00");
		}
	}
	
	/**
	 * obj 转成 Int类型
	 * @param obj
	 * @return
	 */
	public static int parseInt(Object obj) {
		if(obj!=null && !"undefined".equals(obj) && StringUtils.hasLength(obj.toString()) 
				 && StringUtils.isNotBlank(obj.toString())) {
			return Integer.parseInt(obj.toString());
		}else {
			return Integer.parseInt("0");
		}
	}
	
	/**
     * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
     * 例如：HelloWorld->HELLO_WORLD
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线大写方式命名的字符串
     */
    public static String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
     * 例如：HELLO_WORLD->HelloWorld
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel :  camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
    
    //首字母转小写
	public static String toLowerCaseFirstOne(String s){
	  if(Character.isLowerCase(s.charAt(0)))
	    return s;
	  else
	    return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}


    //首字母转大写
    public static String toUpperCaseFirstOne(String s){
      if(Character.isUpperCase(s.charAt(0)))
        return s;
      else
        return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    
    /**
	 * 模糊查询 返回 % keyword %
	 * @param keyword
	 * @return
	 */
	public static String getLikeKeyword(Object keyword) {
		if(keyword!=null && StringUtils.isNotBlank(keyword.toString()) && StringUtils.hasLength(keyword.toString())) {
			String keyword1=keyword.toString(); 
			keyword1 = keyword1.replace("%", "\\\\%");
			keyword1 = keyword1.replace("_", "\\\\_");
			return "%" + keyword + "%";
		}else {
			return "";
		}
	}
	
	/**
	 * 模糊查询 返回  keyword %
	 * @param keyword
	 * @return
	 */
	public static String getLikeKeywordLeft(Object keyword) {
		if(keyword!=null && StringUtils.isNotBlank(keyword.toString()) && StringUtils.hasLength(keyword.toString())) {
			String keyword1=keyword.toString(); 
			keyword1 = keyword1.replace("%", "\\\\%");
			keyword1 = keyword1.replace("_", "\\\\_");
			return keyword1 + "%";
		}else {
			return "";
		}
	}

	/**
	 * 模糊查询 返回 keyword %
	 * @param keyword
	 * @return
	 */
	public static String getPreffixLikeKeyword(Object keyword) {
		if(keyword!=null && StringUtils.isNotBlank(keyword.toString()) && StringUtils.hasLength(keyword.toString())) {
			String keyword1=keyword.toString(); 
			keyword1 = keyword1.replace("%", "\\\\%");
			keyword1 = keyword1.replace("_", "\\\\_");
			return keyword1 + "%";
		}else {
			return "";
		}
	}

	/**
	 * 模糊查询 返回 % keyword
	 * 
	 * @param keyword
	 * @return
	 */
	public static String getSubffixLikeKeyword(String keyword) {
		keyword = keyword.replace("%", "\\\\%");
		keyword = keyword.replace("_", "\\\\_");
		return "%" + keyword;
	}
	
	/**
	 * 返回 清楚前后空格 keyword
	 * 
	 * @param keyword
	 * @return
	 */
	public static String getTrimKeyword(String keyword) {
		keyword = keyword.replace("%", "\\\\%");
		keyword = keyword.replace("_", "\\\\_");
		return keyword.trim();
	}
}
