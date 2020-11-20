package com.leeyin98.entity;

import java.io.Serializable;

public class Supplier implements Serializable {

	private static final long serialVersionUID = 1L;
	private int s_id;
	private String s_name;
	private String s_info;//描述
	private String s_linkman;//联系人
	private String s_phone;
	private String s_address;
	private String s_faxes;//传真
	
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int sId) {
		s_id = sId;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String sName) {
		s_name = sName;
	}
	public String getS_info() {
		return s_info;
	}
	public void setS_info(String sInfo) {
		s_info = sInfo;
	}
	public String getS_linkman() {
		return s_linkman;
	}
	public void setS_linkman(String sLinkman) {
		s_linkman = sLinkman;
	}
	public String getS_phone() {
		return s_phone;
	}
	public void setS_phone(String sPhone) {
		s_phone = sPhone;
	}
	public String getS_address() {
		return s_address;
	}
	public void setS_address(String sAddress) {
		s_address = sAddress;
	}
	public String getS_faxes() {
		return s_faxes;
	}
	public void setS_faxes(String sFaxes) {
		s_faxes = sFaxes;
	}

	public Supplier() {
	}

	public Supplier(String s_name, String s_info, String s_linkman, String s_phone, String s_faxes, String s_address) {
		this.s_name = s_name;
		this.s_info = s_info;
		this.s_linkman = s_linkman;
		this.s_phone = s_phone;
		this.s_faxes = s_faxes;
		this.s_address = s_address;
	}
}
