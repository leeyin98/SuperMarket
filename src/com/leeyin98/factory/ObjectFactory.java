package com.leeyin98.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
 * 将系统中可能用到对象注册到内存中，需要的时候直接从内存取出来即可，不需要每次都new
 */
public class ObjectFactory {
	private final static Map<String,Object> OBJECT_MAP = new HashMap<String,Object>();
	static {
		//字节流
		InputStream is=ObjectFactory.class.getClassLoader().getResourceAsStream("object.txt");
		//字符流(转换流)
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String msg= null ;
		try {
			while(null!=(msg=br.readLine())){
				String key = msg.split("=")[0];
				String className=msg.split("=")[1];
				OBJECT_MAP.put(key, Class.forName(className).newInstance());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 提供外部可以拿值的方法。
	 * */
	public static Object getObject(String key) {
		return OBJECT_MAP.get(key);
	}
}
