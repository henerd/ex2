package com.henerd.utill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DBConnector {
	public static Connection getConnect(){
		Connection con = null;		
		try {
			Context init = new InitialContext();
			DataSource dataSource = (DataSource)init.lookup("java:comp/env/jdbc/auc");
			con = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void disConnect(Connection con){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void disConnect(PreparedStatement st, Connection con){
		try {
			st.close();
			DBConnector.disConnect(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void disConnect(ResultSet rs, PreparedStatement st, Connection con){
		try {
			rs.close();
			DBConnector.disConnect(st, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
