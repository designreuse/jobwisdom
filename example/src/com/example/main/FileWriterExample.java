package com.example.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author 张进军
 * @date 2015年6月11日 下午10:38:58 
 */
public class FileWriterExample {
	public static void main(String[] args) throws Exception {
		FileWriter fw = new FileWriter(new File("/Users/smallpang/redis_cluster/redis_1.conf"));
		BufferedReader br = new BufferedReader(new FileReader("/Users/smallpang/redis_cluster/redis.conf"));
		String str = null;
		StringBuffer sb = new StringBuffer();
		while((str = br.readLine()) != null){
			if(!str.startsWith("#") && str.trim().length() > 1){
				sb.append(str + "\r\n");
			}
		}
		br.close();
		fw.write(sb.toString());
		fw.flush();
		fw.close();
	}
}
