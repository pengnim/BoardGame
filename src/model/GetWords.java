package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetWords {
	public static ResultSet get4w(Connection con, int num) throws SQLException{
		String sqlSt="SELECT letter, meaning FROM 4letter_word WHERE num=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			if(st.execute(sqlSt+num)) {
				return st.getResultSet();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet get5w(Connection con, int num) throws SQLException{
		String sqlSt="SELECT letter, meaning FROM 5letter_word WHERE num=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			if(st.execute(sqlSt+num)) {
				return st.getResultSet();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet get6w(Connection con, int num) throws SQLException{
		String sqlSt="SELECT letter, meaning FROM 6letter_word WHERE num=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			if(st.execute(sqlSt+num)) {
				return st.getResultSet();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
