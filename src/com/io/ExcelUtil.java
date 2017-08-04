package com.io;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 基于poi导出excel
 * @author Json<<json1990@foxmail.com>>
 */
public class ExcelUtil {
	/**
	 * 导出excel
	 * @param fileName
	 * @param sheetName
	 * @param fieldNames
	 * @param list
	 * @return
	 */
	public static boolean exportExcel(String fileName,String sheetName,List<String> fieldNames,List<Map<String,Object>> list,String ouputPath){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName);
		HSSFRow row = sheet.createRow((int) 0);
		
		//样式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
		
		//设置表头
		HSSFCell cell = null;
		int fieldSize = fieldNames.size();
		for(int i=0;i<fieldSize;i++){
			cell = row.createCell((short) i);
			cell.setCellValue(fieldNames.get(i));
			cell.setCellStyle(style);
		}
		
		//保存字段值
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			for(int j=0;j<fieldSize;j++){
				row.createCell((short) j).setCellValue((String) list.get(i).get(fieldNames.get(j)));
			}
		}
		
		try {
			if(!ouputPath.endsWith("/")){
				ouputPath += "/";				
			}
			FileOutputStream fout = new FileOutputStream(ouputPath+fileName+".xls");
			wb.write(fout);
			fout.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	} 
	
	public static void main(String[] args) {
		List names = new ArrayList<String>();
		names.add("姓名");
		names.add("公司");
		names.add("联系电话");
		
		List list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("姓名", "赵亚玲");
		map.put("公司", "摩拜单车");
		map.put("联系电话", "13223030418");
		list.add(map);
		
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("姓名", "王思聪");
		map1.put("公司", "熊猫TV");
		map1.put("联系电话", "18823030418");
		list.add(map1);
		
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("姓名", "贾跃亭");
		map2.put("公司", "乐视");
		map2.put("联系电话", "15523030418");
		list.add(map2);
		
		boolean b = exportExcel("信息表", "信息表", names, list, "E:/output/");
		System.out.println(b);;
	}
}
