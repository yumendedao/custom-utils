package com.yumendedao.commons.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.yumendedao.statics.StringConstant;

public class PropertyUtil {

//	private static BufferedInputStream in = null;

	public static Properties loadProperties(InputStream inputStream) {
		Properties prop = null;
		try {
			prop = new Properties();
//			in = new BufferedInputStream(inputStream);
			prop.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
//				in.close();
				if(inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop;
	}

	/**
	 * 按给定名称，获取配置文件
	 * 
	 * @param name
	 *            所需资源名称
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Properties loadProperties(String name) throws FileNotFoundException {
		String resource = null;
		if(name.startsWith(StringConstant.getSeParator()))
			resource = name;
		else
			resource = StringConstant.getSeParator() + name;
		InputStream inputStream = PropertyUtil.class.getResourceAsStream(resource);
		if(inputStream != null)
			return loadProperties(inputStream);
		else {
			return null;
		}
	}

	// 写资源文件，含中文,中文写入properties文件会按unicode编码被转义
	public static void writePropertiesFile(Properties properties, String path, String key, String value, String comments) {
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(path);
			properties.setProperty(key, value);
			properties.store(outputStream, comments);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void writePropertiesFile(Properties properties, String path, String key, String value) {
		writePropertiesFile(properties, path, key, value, null);
	}
	
//	public static void main(String[] args) {
//		String name = "/MQ.properties";
//		Properties loadProperties = null;
//		try {
//			loadProperties = loadProperties(name);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		Set<String> stringPropertyNames = loadProperties.stringPropertyNames();
//		for (String string : stringPropertyNames) {
//			System.out.println(string);
//		}
//		Set<Object> keySet = loadProperties.keySet();
//		for (Object object : keySet) {
//			System.out.println(object);
//		}
//	}
	
}
