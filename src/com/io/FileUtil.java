package com.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取文件数据
 * @author Json<<json1990@foxmail.com>>
 */
public class FileUtil {
	public static List<Map<String,Object>> readeTxt(List<String> names,String filepath) {
		// 用于存放txt文件里所有的数据
		List<Map<String,Object>> txtList = new ArrayList<Map<String,Object>>();
		try {
			int id = 1; //记录当前行数
			File file=new File(filepath);
	        if(file.isFile() && file.exists()){ //判断文件是否存在
	            InputStreamReader read = new InputStreamReader(new FileInputStream(file),"GBK");
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String str = null;
	            while((str = bufferedReader.readLine()) != null){
	                //用于存放txt文件里每行数据
					Map<String,Object> map = new HashMap<String,Object>();
					String[] strs = str.split("[\\s\\p{Zs}]+"); //按空格分割
					if (strs.length == names.size()) {
						for(int i=0;i<names.size();i++){
							map.put(names.get(i),strs[i]);
						}
					} else {
						System.out.println("读取第" + id + "行txt文档内容时，发现不合要求的数据！");
					}
					txtList.add(map);
					id++;
	            }
	            read.close();
			}else{
			    System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return txtList;
	}
	
	public static void main(String[] args) {
		List<String> names = new ArrayList<String>();
		names.add("姓名");
		names.add("城市");
		names.add("公司");
		names.add("联系电话");
		System.out.println(readeTxt(names,"E:\\conacts.txt"));
	}
}
