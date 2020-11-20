package com.leeyin98.entity;

import java.io.Serializable;
import java.util.Date;

public class Bill implements Serializable {

	private static final long serialVersionUID = 1L;
	private int a_id;//账单编号
	private String a_name;//商品名称
	private int a_nums;//商品数量
	private double a_amount;//交易金额
	private String a_unit;//交易单位
	private String a_ispayed;//是否付款
	private String s_name;//供应商名称
	private String a_Info;//描述
	private Date a_Date;//账单时间

	public int getA_id() {
		return a_id;
	}
	public void setA_id(int aId) {
		a_id = aId;
	}
	public String getA_name() {
		return a_name;
	}
	public void setA_name(String aName) {
		a_name = aName;
	}
	public int getA_nums() {
		return a_nums;
	}
	public void setA_nums(int aNums) {
		a_nums = aNums;
	}
	public double getA_amount() {
		return a_amount;
	}
	public void setA_amount(double aAmount) {
		a_amount = aAmount;
	}
	public String getA_ispayed() {
		return a_ispayed;
	}
	public void setA_ispayed(String aIspayed) {
		a_ispayed = aIspayed;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String sName) {
		s_name = sName;
	}
	public String getA_Info() {
		return a_Info;
	}
	public void setA_Info(String aInfo) {
		a_Info = aInfo;
	}
	public Date getA_Date() {
		return a_Date;
	}
	public void setA_Date(Date aDate) {
		a_Date = aDate;
	}
	public String getA_unit() {
		return a_unit;
	}
	public void setA_unit(String aUnit) {
		a_unit = aUnit;
	}

	public Bill() {
	}
	public Bill(String a_name, int a_nums, double a_amount,String a_unit,String a_ispayed, String s_name, String a_Info, Date a_Date) {
		this.a_name = a_name;
		this.a_nums = a_nums;
		this.a_amount = a_amount;
		this.a_unit = a_unit;
		this.a_ispayed = a_ispayed;
		this.s_name = s_name;
		this.a_Info = a_Info;
		this.a_Date = a_Date;
	}
}
