package com.leeyin98.ts.impl;

import com.leeyin98.ts.ITransaction;
import com.leeyin98.util.DbUtil;
import java.sql.SQLException;

public class TransactionImpl implements ITransaction {

	@Override
	public void begin() {
		try {
			//将自动提交关闭
			DbUtil.getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void commit() {
		try {
			DbUtil.getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void rollback() {
		try {
			DbUtil.getConnection().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
