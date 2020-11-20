package com.leeyin98.util;

import com.leeyin98.entity.Supplier;
import com.leeyin98.factory.ObjectFactory;
import com.leeyin98.service.BillService;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class ComboBoxUtil {
	private BillService service = (BillService) ObjectFactory.getObject("BillService");
	static Map<Integer,String> map = new HashMap<Integer, String>();
	static{
		BillService b = (BillService) ObjectFactory.getObject("BillService");
		List<Supplier> list = b.listSupplier();
		for (Supplier supplier : list) {
			map.put(supplier.getS_id(), supplier.getS_name());
		}
	}
	public static String getSelectValue(int sno){
		return map.get(sno);
	}

	public JComboBox getComboBox(){
		List<Supplier> list= service.listSupplier();
		Object[] datas = new Object[list.size()];
		int i=0;
		for(Supplier s:list) {
			datas[i]=list.get(i).getS_name();
			i++ ;
		}
		JComboBox combox = new JComboBox(datas);
		return combox;
	}
}
