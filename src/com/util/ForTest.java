package com.util;

/**
 * 凯撒密码
 * 
 * @author wlh 2018-10-11 14:24:52
 */
public class ForTest {

	/**
	 * 向右移动
	 * 
	 * @param ch
	 * @author wlh 2018-10-11 11:26:21
	 * @return
	 */
	public static int rotateRight(int ch) {
		if (ch >= 'A' && ch <= 'Y') {
			return ch + 1;
		} else if (ch >= 'a' && ch <= 'y') {
			return ch + 1;
		} else if (ch == 'Z') {
			return 'A';
		} else if (ch == 'z') {
			return 'a';
		} else {
			return ch;
		}
	}

	/**
	 * 向左移动
	 * 
	 * @param ch
	 * @author wlh 2018-10-11 11:28:21
	 * @return
	 */
	public static int rotateLeft(int ch) {
		if (ch >= 'B' && ch <= 'Z') {
			return ch - 1;
		} else if (ch >= 'b' && ch <= 'z') {
			return ch - 1;
		} else if (ch == 'A') {
			return 'Z';
		} else if (ch == 'a') {
			return 'z';
		} else {
			return ch;
		}
	}

	/**
	 * 加密
	 * 
	 * @param ch
	 * @param shift
	 * @author wlh 2018-10-11 11:52:46
	 */
	public static int encode(int ch, int shift) {
		if (shift == 0) {
			return ch;
		} else if (shift > 0) {
			for (int i = 0; i < shift; i++) {
				ch = rotateRight(ch);
			}
			return ch;
		} else {
			for (int i = 0; i < -shift; i++) {
				ch = rotateLeft(ch);
			}
			return ch;
		}
	}

	/**
	 * 转码
	 *
	 * @param src
	 * @return
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		// 遍历,对源字符串每一位进行转码
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			// 数字,字符不需要转码
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
				tmp.append(j);
			// ascil码转码
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else
			// 其他转码
			{
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * 解码
	 *
	 * @param src
	 * @return
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		// 查找%,进行解码
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			// 是经过转玛的字符
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u')// 是中文
				{
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else
				// 是ascil码
				{
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else
			// 不需要解码
			{
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	public static void main(String[] args) {

		// 凯撒密码加密
		System.out.print("凯撒密码加密：");
		String str = "Android";
		char[] ch = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			ch[i] = (char) encode(str.charAt(i), 3);
			System.out.print(ch[i]);
		}

		// 凯撒密码解密
		System.out.print("\n凯撒密码解密：");
		String strs = "Dqgurlg";
		char[] chs = new char[strs.length()];
		for (int i = 0; i < strs.length(); i++) {
			chs[i] = (char) encode(strs.charAt(i), -3);
			System.out.print(chs[i]);
		}

		String tmp = "你们123";
		tmp = escape(tmp);
		System.out.println("\n加密：" + tmp);
		System.out.println("解密：" + unescape(tmp));
	}

}
