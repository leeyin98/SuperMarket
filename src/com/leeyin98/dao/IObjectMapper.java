package com.leeyin98.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * IObjectMap：定义了ResultSet对象-->Object的规则
 */
public interface IObjectMapper<T> {
	public T getObjectFromResultSet(ResultSet rs) throws SQLException;
}
