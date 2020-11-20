package com.leeyin98.util;

import com.leeyin98.dao.IObjectMapper;
import com.leeyin98.dao.impl.BillDAOImpl;
import com.leeyin98.entity.Bill;
import com.leeyin98.util.DbUtil;
import javax.swing.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FileUtil {
	public static void readFileToJtextArea(File f,JTextArea jta){
		BufferedReader br=null ; 
		try {
			br = new BufferedReader(new FileReader(f));
			String msg = null ; 
			while(null!=(msg = br.readLine())) {
				jta.append(msg+"\r\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
