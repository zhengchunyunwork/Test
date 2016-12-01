package com.test.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestSimpleDateFormat {
	public static void main(String[] args) {
		SimpleDateFormat sdf00 = new SimpleDateFormat("yyyyMMdd000000");
		SimpleDateFormat s_d_f = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Date date = new Date();
		String str = sdf00.format(date);
		String str2 = s_d_f.format(date);
		System.out.println(str);
		System.out.println(str2);
	}

}
