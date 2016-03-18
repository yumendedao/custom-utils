package com.yumendedao.statics;

import java.io.File;

public class StringConstant {
	public static final String separator = "/";
	
	public static final String getSeParator(){
		String separator = File.separator;
		return separator;
	}
}
