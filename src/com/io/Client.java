package com.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 客户端
 * @author Json<<json1990@foxmail.com>>
 */
public class Client {
	public static void main(String[] args) {
		
		//数据文件路径
		String file_path = "E:\\conacts.txt";
		//输出路径
		String output_path = "E:\\";
		
		//表头
		List<String> names = new ArrayList<String>();
		names.add("姓名");
		names.add("城市");
		names.add("公司");
		names.add("联系电话");
		
		List<Map<String,Object>> list = FileUtil.readeTxt(names,file_path);
		
		boolean b = ExcelUtil.exportExcel("人员信息表", "信息表", names, list,output_path);
		System.out.println(b);;
		
	}
}
