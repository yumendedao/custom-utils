package com.yumendedao.commons.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringUtil {

	private static final Random rnd = new Random();
	
	/**
	 * 随机字符串字典，兼容code39/93编码
	 */
	private static final char[] RANDOM_DICT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+-/$%. ".toCharArray();
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * yyyy-MM-dd
	 */
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * HH:mm:ss
	 */
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	
	public static final SimpleDateFormat fileFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	/**
	 * 换行符
	 */
	public static final String eol = getLineSeparator();
	private static String getLineSeparator() {
		String eol = "\r\n";
		try {
			eol = System.getProperty("line.separator");
		} catch (Throwable ex) { }
		return eol;
	}
	
	public static final String separator = "/";
	
	/**
	 * 产生指定长度的随机字符串
	 * @author wangwj<br>2015年1月15日
	 * @param length
	 * @return
	 */
	public static String randomString(int length) {
		return randomString(length, null);
	}
	
	public static final String EMPTY = "";
	public static final String SPACE = " ";
	
	/**
	 * 根据给定字典, 产生指定长度的随机字符串
	 * @author wangwj<br>2015年1月15日
	 * @param length
	 * @param dict
	 * @return
	 */
	public static String randomString(int length, String dict) {
		String s = EMPTY;
		char[] d;

		d = isNull(dict) ? RANDOM_DICT : dict.toCharArray();

		for (int i = 0; i < length; i++) {
			s += d[(int) Math.floor(rnd.nextDouble() * d.length)];
		}

		return s;
	}
	
	public static boolean isNull(Object o) {
		return o == null || EMPTY.equals(o.toString().trim());
	}
	
	public static boolean isTrue(Object o) {
		return !isNull(o) && ("1".equals(o.toString().trim()) || "true".equals(o.toString().toLowerCase().trim())); 
	}
	
	public static String toHexString(byte[] input) {
		return toHexString(input, true);
	}
	
	public static String toHexString(byte[] input, boolean split) {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < input.length; i++) {
			String hex = Integer.toHexString(input[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			result.append(hex.toUpperCase());

			if (split)
				result.append(SPACE);
		}

		return result.toString();
	}
	
	
	public static String getTempNameByTimeStamp(String fileName, String baseName) {
		String extend = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		String tempFileName = baseName.substring(0, baseName.length() - extend.length()) + "_"
				+ TimeUtil.getTimeStamp() + extend;
		return tempFileName;
	}
	public static String getTempNameByTimeStamp(String fileName) {
		return getTempNameByTimeStamp(fileName, fileName);
	}
	
	/**
	 * 根据给定的字符集, 获得字符串的字节长度
	 * @author wangwj<br>2015年1月15日
	 * @param input
	 * @param charsetName
	 * @return
	 */
	public static int getByteWidth(String input, String charsetName) {
		int l = 0;
		
		if (!isNull(input)) {
			try {
				l = input.getBytes(charsetName).length;
			} catch (Exception e) {
				l = input.length();
			}
		}
		
		return l;
	}
	
	/**
	 * 获得字符串GBK字符集的字节长度
	 * @author wangwj<br>2015年1月15日
	 * @param input
	 * @return
	 */
	public static int getByteWidth(String input) {
		return getByteWidth(input, "GBK");
	}
	
	/**
	 * 重复输入字符串
	 * @author wangwj<br>2015年1月15日
	 * @param input
	 * @param length
	 * @return
	 */
	public static String repeat(String input, int length) {
		StringBuilder b;
		b = new StringBuilder(input);
		while(b.length() < length) 
			b.append(input);
		return b.toString().substring(0, length);
	}
	
	public static String center(String input, int length) {
		int l = getByteWidth(input);
		return repeat(SPACE, (length - l) / 2) + input + repeat(SPACE, length - l - (length - l) / 2 );
	}
	
	private static String padding(String input, int length, String fill, boolean left) {
		StringBuilder b;
		int gap = 0;
		
		if (isNull(input))
			input = EMPTY;
		
		if (isNull(fill))
			fill = SPACE;
		
		gap = length - StringUtil.getByteWidth(input);
		
		if (gap <= 0)
			return input;
		
		b = new StringBuilder(length - input.length());
		while (b.length() < gap) {
			b.append(fill);
		}
		
		return left ? b.toString().substring(0, gap) + input : input + b.toString().substring(0, gap);
	}
	
	/**
	 * 在字符串前添加空格使之达到指定长度
	 * @author wangwj<br>2015年1月15日
	 * @param input 输入字符串
	 * @param length 输出字符串的字节长度,ascii字符为1, 中文字符为2
	 * @return
	 */
	public static String leftPad(String input, int length) {
		return padding(input, length, SPACE, true);
	}
	
	/**
	 * 在字符串后添加空格使之达到指定长度
	 * @author wangwj<br>2015年1月15日
	 * @param input 输入字符串
	 * @param length 输出字符串的字节长度, ascii字符为1, 中文字符为2
	 * @return
	 */
	public static String rightPad(String input, int length) {
		return padding(input, length, SPACE, false);
	}
	
	public static String leftPad(String input, int length, String fill) {
		return padding(input, length, fill, true);
	}
	
	public static String rightPad(String input, int length, String fill) {
		return padding(input, length, fill, false);
	}
	
	/**
	 * 将字符串按照指定长度折行, 若行尾出现中文字符则并入下一行, 防止乱码
	 * @author wangwj<br>2015年1月15日
	 * @param input 输入字符串
	 * @param length 每行字符串的字节长度, ascii字符为1, 中文字符为2<br />
	 * @return
	 */
	public static String[] warp(String input, int length) {
		final byte[] line = new byte[length];
		List<String> lines = new ArrayList<String>();
		byte[] c = null;
		int l = 0;
		boolean odd = false;
		
		try {
			c = input.getBytes("GBK");
		} catch (Exception e) {
			return new String[] { input };
		}
		
		for (int i = 0; i < c.length; i++) {
			line[l++] = c[i];
			odd = !odd && (c[i] & 0xFF) > 127;
			
			if (l == length || i == c.length - 1) {
				l -= odd ? 1 : 0;
				
				try {
					lines.add(new String(line, 0, l, "GBK"));
				} catch (Exception e) { }
				
				line[0] = c[i];
				l = odd ? 1 : 0;
			}
		}
		
		return lines.size() > 0 ? lines.toArray(new String[lines.size()]) : null;
	}
	
	/**
	 * 对给多个字段值按照指定宽度排版, 若小于指定宽度则添加空格, 若大于指定宽度则折行
	 * @author wangwj<br>2015年1月15日
	 * @param fields
	 * @param colsWidth
	 * @return
	 */
	public static String[] composing(String[] fields, int[] colsWidth) {
		String f, t[][], r[];
		int h = 1;
		
		if (fields.length > colsWidth.length)
			throw new RuntimeException("specified columns width not match for fields!");
		
		t = new String[fields.length][];
		
		for (int i = 0; i < fields.length; i++) {
			f = fields[i];
			
			t[i] = getByteWidth(f) > colsWidth[i]
					? StringUtil.warp(f, colsWidth[i])
					: new String[] { f };
					
			h = h < t[i].length ? t[i].length : h;
		}
		
		r = new String[h];
		for (int i = 0; i < h; i++) {
			f = EMPTY;
			for (int _i = 0; _i < fields.length; _i++) {
				f += rightPad(i > t[_i].length - 1 ? EMPTY : t[_i][i], colsWidth[_i]);
			}
			r[i] = f;
		}
		
		return r;
	}
	
//	public static String cut(String input, int length) {
//		byte[] s = null;
//		
//		try {
//			s = input.getBytes("GBK");
//			return new String(s, 0, length, "GBK");
//		} catch (UnsupportedEncodingException e) {
//			throw new PosConvertException(e.getMessage(), e, s, String.class);
//		}
//	}

	//	public static void test1() {
//		String s = "中华人r民共和国全国信息技术标准化技术委员会";
//		byte[] d = null;
//		
//		try {
//			d = s.getBytes("GBK");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		for (int i = 0; i < d.length; i++) {
//			System.out.print((0xFF & d[i]) + ",");
//		}
//	}
//	
//	public static void test2() throws Throwable {
//		System.out.println(new String(new byte[]{(byte)205}, "GBK"));
//	}
//	
//	public static void test3() {
//		List<Object> l = new ArrayList<Object>();
//		int[] a;
//		
//		for (int i = 0; i < 30; i++) {
//			l.add(i);
//		}
//		
//		a = new int[l.size()];
//		
//		//l.toArray(a);
//	}
//	
//	public static void test4() throws Throwable {
//		String dict = "GBK全称《汉字内码扩展规范》（GBK即“国标”、“扩展”汉语拼音的第一个字母，英文名称：Chinese Internal Code Specification） ，中华人民共和国全国信息技术标准";
//		String r, s[];
//		int c;
//		
//		for (int i = 0; i < 30; i++) {
//			r = randomString(40, dict);
//			System.out.println(r);
//			
//			s = warp(r, 11);
//			for (int j = 0; j < s.length; j++) {
//				c = StringUtil.getByteWidth(s[j]);
////				if (j < s.length - 1 && c < 10) {
////					System.out.println(r);
//					System.out.println(s[j] + "|" + j + "|" + c);
////				}
//			}
//			
//			System.out.println("-------------");
//		}
//	}
//	
//	public static void m1(int[] a1) {
//		a1[0] *= 10;
//	}
//	
//	public static void test5() {
//		int[] a = new int[] {2};
//		m1(a);
//		System.out.println(a[0]);
//		
//		for (int i = 0; i < 30; i++) {
//			System.out.print(rnd.nextInt(100) + ",");
//		}
//	}
//	
//	public static void test6() {
//		final int w[] = {3, 30, 5, 10};
//		final String[] s = new String[4];
//		final String dict = "GBK全称《汉字内码扩展规范》（GBK即“国标”、“扩展”汉语拼音的第一个字母，英文名称：Chinese Internal Code Specification） ，中华人民共和国全国信息技术标准";
//		String r[], f;
//		
//		for (int i = 0; i < 20; i++) {
//			f = randomString(rnd.nextInt(100));
////			if (getByteWidth(f) > 30)
////				continue; 
//			
//			s[0] = Integer.toString(rnd.nextInt(100));
//			s[1] = f;
//			s[2] = leftPad(Integer.toString(rnd.nextInt(10000)), 5);
//			s[3] = leftPad(Double.toString(Math.round(rnd.nextDouble() * 1000000) / 10D), 10);
//			r = composing(s, w);
//			
//			System.out.println(f);
//			for (int _i = 0; _i < r.length; _i++) {
//				System.out.println(r[_i]);
//			}
//			System.out.println("------------------------------------------------");
//		}
//	}
//	
	
//	public static void test7() {
//		//System.out.println(center("hello world!", 60));
//		String s = repeat("-", 40);
//		List<String> l = new LinkedList<String>();
//		l.add(s);
//		l.add("abc");
//		l.add(s);
//		l.add("def");
//		l.add(s);
//		
//		for (int i = 0; i < l.size(); i++) {
//			System.out.println(l.get(i));
//		}
//	}
//	
//	public static void main(String[] args) throws Throwable {
//		test7();
//	}
}
