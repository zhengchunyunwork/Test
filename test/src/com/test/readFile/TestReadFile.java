package com.test.readFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestReadFile {

	public static void main(String[] args) throws Exception {
    	// TODO Auto-generated method stub
//		String javaPath = "E:\\Workspaces\\dcv_jupiter1_tem\\src\\com\\uinv";
//		readfile(javaPath);
		
//		String jsPath = "E:\\Workspaces\\dcv_jupiter1_tem\\WebContent";
//		readfile(jsPath);
		
		String corePath = "E:\\Workspaces\\dcv_core1-tem\\src\\com\\uinv";
		String outpath = "E:\\test\\log_core.txt";
		readfile(corePath,outpath);
		
		
//		String pa = "E:\\Workspaces\\dcv_jupiter1_tem\\src\\com\\uinv";
//		File file = new File(pa);
////		 OutputStream ps= new FileOutputStream(file);
//		File[] st = file.listFiles();
//		for(int i = 0;i < st.length;i++){
//			if(st[i].isDirectory()){
//				System.out.println(st[i]+"是路径");
//			}else{
//				System.out.println(st[i].getName());
//			}
//			
//		}
//		if(!file.exists()){
//			System.out.println("不存在");
//		}
//		if(file.isDirectory()){
//			System.out.println("是路径");
//		}
	}
	
	public static boolean readfile(String filepath,String outPath) throws Exception {
		FileWriter fw = new FileWriter(outPath, true);
		try {
			File file = new File(filepath);
			if (!file.isDirectory()) {
//				System.out.println("文件");
//				System.out.println("parentPath=" + file.getParent());
//				System.out.println("path=" + file.getPath());
				Map wm = new HashMap();
				System.out.println("absolutepath=" + file.getAbsolutePath());
				Map map = findHz(file);
				if(map.size()>0){
					wm.put("\n"+file.getAbsolutePath(), map);
					fw.write(wm.toString());
				}
//				System.out.println("name=" + file.getName());

			} else if (file.isDirectory()) {
//				System.out.println("文件夹");
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "\\" + filelist[i]);
					if (!readfile.isDirectory()) {
//						System.out.println("path=" + readfile.getParent());
//						System.out.println("path=" + readfile.getPath());
						Map wm = new HashMap();
						System.out.println("absolutepath=" + readfile.getAbsolutePath());
						Map map = findHz(readfile);
						if(map.size()>0){
							wm.put("\n"+readfile.getAbsolutePath(), map);
							fw.write(wm.toString());
						}
//						System.out.println("name=" + readfile.getName());

					} else if (readfile.isDirectory()) {
						readfile(filepath + "\\" + filelist[i],outPath);
					}
				}

			}

		} catch (Exception e) {
			System.out.println("readfile()   Exception:" + e.getMessage());
		}finally{
			 fw.close();
		}
		return true;
	}
	
	/**
	 * 
	 * @param file
	 * @throws Exception
	 */
	public static Map findHz(File file) throws Exception{
		String reg = "[\u4e00-\u9fa5]+";
    	Pattern pat = Pattern.compile(reg);  
    	
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader in = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(in);
		int size = 0;
		Map retunMap = new TreeMap();
		 for (String line = br.readLine(); line != null; line = br.readLine()) {
			 size++;
			 line = line.replaceAll(" ", "");
			 line = line.replaceAll("\t", "");
			 if(line.startsWith("//") || line.startsWith("/*") || line.startsWith("*")){
//				 System.out.println("屏蔽字符开头");
			 }else{
				 if(line.indexOf("//") != -1){
					 line = line.substring(0, line.indexOf("//"));
				 }
				 Matcher mat=pat.matcher(line); 
				 int j = 1;
//				 while(mat.find()){
//					 System.out.println(mat.start());
//					 System.out.println(mat.end());
//					 String str = mat.group();
//					 System.out.println(str);
//					 retunMap.put("行["+size+"]---数["+j+"]",str);
//					 j++;
//					 
//				 }
				 int start = 0;
				 int end = 0;
				 int c = 0;
				 while(mat.find()){
					 if(c == 0){
						 start = mat.start();
					 }
					 c++;
					 start = Math.min(start, mat.start());
					 end = Math.max(end, mat.end());
//					 System.out.println(mat.start());
//					 System.out.println(mat.end());
//					 String str = mat.group();
//					 System.out.println(str);
//					 retunMap.put("行["+size+"]---数["+j+"]",str);
					 
				 }
				 if(end != 0){
					 retunMap.put(size,line.substring(start, end)+"\n");
				 }
				 
			 }
		 }
		return retunMap;
	}
	
}
