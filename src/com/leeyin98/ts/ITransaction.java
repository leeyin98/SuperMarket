package com.leeyin98.ts;

public interface ITransaction {
	//事务的开启
	public void begin();
	//事务的提交
	public void commit();
	//事务的回滚
	public void rollback();
}
