package com.test.international;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class ExcelReader {

	/**
	 * 读取 office 2003 excel
	 * 
	 * @param url
	 *            EXCEL路径
	 * @return Map<String,List>
	 */
	public static Map readExcel(String realPath) throws Exception {
		Map map = new HashMap();
		File file = new File(realPath);
		if (file.isFile()) {
			try {
				Workbook workbook = Workbook.getWorkbook(file);
				Sheet[] sheets = workbook.getSheets();
					Sheet sheet = sheets[0];
					List rowList = new ArrayList();
					for (int j = 1; j < sheet.getRows(); j++) {
						Cell[] cells = sheet.getRow(j);
						if(cells.length == 2){
							String key = cells[0].getContents();
							String value = cells[1].getContents();
							map.put(key, value);
						}
					}
				workbook.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("路径不存在："+realPath);
		}
		return map;
	}
	
	public static Map readJS(String path,Map map){
		File myFile = new File(path);
		if (myFile.exists()) {
			try {
				InputStreamReader read = new InputStreamReader(new FileInputStream(myFile), "utf-8");
				BufferedReader reader = new BufferedReader(read);
				String line;
				StringBuffer str = new StringBuffer();
				while ((line = reader.readLine()) != null) {
					line = line.substring(0, line.indexOf("//"));
					str.append(line.trim());
				}
				reader.close();
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	/**
	 * 创建excel工作
	 * 
	 * @param realPath
	 *            输出工作表路径
	 * @param map
	 *            {Map<String,List<Map<String,String>>>}写入工作表内容
	 * @return
	 */
	public static boolean write(String realPath, Map map) throws Exception {
		boolean ret = false;
		try {
			OutputStream os = new FileOutputStream(realPath);
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			if (map.isEmpty()) {
				workbook.createSheet("sheet1", 0);
				workbook.write();
				workbook.close();
				os.close();
				ret = true;
				return ret;
			}
			Iterator ite = map.keySet().iterator();
			while (ite.hasNext()) {
				String key = ite.next().toString();
				if (map.get(key) != null) {
					WritableSheet sheet = workbook.createSheet(key, 0); // 添加第一个工作表
					List list = (List) map.get(key);
					List oneRow = new ArrayList();
					Map cm = (Map) list.get(0);
					Iterator iter = cm.entrySet().iterator();
					int num = 0;
					jxl.write.Label label = null;
					// TODO 写入Excel的第一条数据
					while (iter.hasNext()) {
						Map.Entry entry = (Map.Entry) iter.next();
						String k = entry.getKey().toString();
						label = new jxl.write.Label(num, 0, k);
						sheet.addCell(label);
						num++;
						oneRow.add(k);
					}
					// TODO 写入Excel每条到表头的对应数据
					for (int i = 0; i < list.size(); i++) {
						Map m = (Map) list.get(i);
						for (int j = 0; j < oneRow.size(); j++) {
							String k = oneRow.get(j).toString();
							String v = m.containsKey(k) ? m.get(k).toString() : "";
							label = new jxl.write.Label(j, i + 1, v);
							sheet.addCell(label);
						}
					}
				}
			}
			workbook.write();
			workbook.close();
			os.close();
			ret = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
}
