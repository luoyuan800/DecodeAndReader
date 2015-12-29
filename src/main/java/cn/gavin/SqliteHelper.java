/*
 * SqliteHelper.java
 * Date: 12/21/2015
 * Time: 11:15 AM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package cn.gavin;

import java.sql.*;
import java.util.ArrayList;

public class SqliteHelper {
private Connection connection;
private String user;
private String password;
public SqliteHelper(String path, String user, String password) throws ClassNotFoundException {
	this.user = user;
	this.password = password;
	connection = getConnection(path);
}

public SqliteHelper(String path) throws ClassNotFoundException {
	connection = connection(path);
}

public void close(){
	try {
		if(connection!=null && !connection.isClosed()){
			connection.close();
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public Connection connection(String path) throws ClassNotFoundException {
	try {
		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection("jdbc:sqlite:" + path);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
}

public Connection getConnection(String path) throws ClassNotFoundException {
	try {
		Class.forName("org.sqlite.JDBC");
		return DriverManager.getConnection("jdbc:sqlite:" + path, user, password);

	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
}

public Connection getConnection(){
	return connection;
}

public ArrayList<String[]> query(int page, int rows){
	String sql = "select * from test order by id limit " + rows + " offset " + (page-1)*rows;
	Statement statement = null;
	try {
		ArrayList<String[]> result = new ArrayList<String[]>();
		statement = connection.createStatement();
		ResultSet resultSet =  statement.executeQuery(sql);
		while(resultSet.next()){
			String[] cell = new String[11];
			for(int i =0 ;i <11; i++){
				cell[i] = resultSet.getString(i+1);
			}
			result.add(cell);
		}
		return result;
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	return null;
}

public int getTotalPage(int row){
	String sql = "select count(*) from test";
	Statement statement = null;
	try {
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		if(resultSet.next()) {
			return resultSet.getInt(1) / row + 1;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	return 0;
}
}
