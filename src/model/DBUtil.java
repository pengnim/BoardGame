package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
public class DBUtil {
	public static ResultSet findUser(Connection con,String uid) throws SQLException{
		String sqlSt="SELECT passwd FROM user WHERE id=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			if(st.execute(sqlSt+"'"+uid+"'")) {
				return st.getResultSet();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ResultSet findDeq2Col(Connection con,String uid) throws SQLException{
		String sqlSt="SELECT * FROM deque2_color WHERE grp_name=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			if(st.execute(sqlSt+"'"+uid+"'")) {
				return st.getResultSet();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ResultSet findDeq2Num(Connection con,String uid) throws SQLException{
		String sqlSt="SELECT * FROM deque2_num WHERE grp_name=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			if(st.execute(sqlSt+"'"+uid+"'")) {
				return st.getResultSet();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ResultSet findDeq3Col(Connection con,String uid) throws SQLException{
		String sqlSt="SELECT * FROM deque3_color WHERE grp_name=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			if(st.execute(sqlSt+"'"+uid+"'")) {
				return st.getResultSet();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ResultSet findDeq3Num(Connection con,String uid) throws SQLException{
		String sqlSt="SELECT * FROM deque3_num WHERE grp_name=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			if(st.execute(sqlSt+"'"+uid+"'")) {
				return st.getResultSet();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ResultSet findDeq4(Connection con,String uid) throws SQLException{
		String sqlSt="SELECT * FROM deque4 WHERE grp_name=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			if(st.execute(sqlSt+"'"+uid+"'")) {
				return st.getResultSet();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ResultSet findScore(Connection con,String uid) throws SQLException{
		String sqlSt="select * from player where id='"+uid+"'";
		Statement st=null;
		ResultSet rs=null;
		try {
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			if(st.execute(sqlSt)) {
				return st.getResultSet();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ResultSet findDPlayer(Connection con,String uid) throws SQLException{
		String sqlSt="SELECT * FROM davinci_player WHERE id=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			if(st.execute(sqlSt+"'"+uid+"'")) {
				return st.getResultSet();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static boolean isOnline(Connection con,String uid) throws SQLException{
		String sqlSt="SELECT * FROM player WHERE id=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			if(st.execute(sqlSt+"'"+uid+"'")) {
				rs=st.getResultSet();
				while(rs.next()) {
					int r=rs.getInt("online");
					if(r==0) return false;
					else return true;
				}
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public static boolean checkAccount(Connection con,String uid) throws SQLException{
		String sqlSt="SELECT * FROM user WHERE id=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			rs=st.executeQuery(sqlSt+"'"+uid+"'");
			if(rs!=null) {
				if(rs.next()) {
					System.out.println("has id");
					return false;
				}
				else return true;
			}
			else {
				System.out.println("null result set");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public static boolean checkDeq2Col(Connection con,String uid) throws SQLException{
		String sqlSt="SELECT * FROM deque2_color WHERE grp_name=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			rs=st.executeQuery(sqlSt+"'"+uid+"'");
			if(rs!=null) {
				if(rs.next()) {
					return false;
				}
				else return true;
			}
			else {
				System.out.println("null result set");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public static boolean checkDeq2Num(Connection con,String uid) throws SQLException{
		String sqlSt="SELECT * FROM deque2_num WHERE grp_name=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			rs=st.executeQuery(sqlSt+"'"+uid+"'");
			if(rs!=null) {
				if(rs.next()) {
					return false;
				}
				else return true;
			}
			else {
				System.out.println("null result set");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public static boolean checkDeq3Col(Connection con,String uid) throws SQLException{
		String sqlSt="SELECT * FROM deque3_color WHERE grp_name=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			rs=st.executeQuery(sqlSt+"'"+uid+"'");
			if(rs!=null) {
				if(rs.next()) {
					return false;
				}
				else return true;
			}
			else {
				System.out.println("null result set");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public static boolean checkDeq3Num(Connection con,String uid) throws SQLException{
		String sqlSt="SELECT * FROM deque3_num WHERE grp_name=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			rs=st.executeQuery(sqlSt+"'"+uid+"'");
			if(rs!=null) {
				if(rs.next()) {
					return false;
				}
				else return true;
			}
			else {
				System.out.println("null result set");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public static boolean checkDeq4(Connection con,String uid) throws SQLException{
		String sqlSt="SELECT * FROM deque4 WHERE grp_name=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			rs=st.executeQuery(sqlSt+"'"+uid+"'");
			if(rs!=null) {
				if(rs.next()) {
					return false;
				}
				else return true;
			}
			else {
				System.out.println("null result set");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public static boolean checkDPlayer(Connection con,String uid) throws SQLException{
		if(uid==null) return true;
		String sqlSt="SELECT * FROM davinci_player WHERE id=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			rs=st.executeQuery(sqlSt+"'"+uid+"'");
			if(rs!=null) {
				if(rs.next()) {
					return false;
				}
				else return true;
			}
			else {
				System.out.println("null result set");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public static boolean checkGrpId(Connection con,String uid,int num) throws SQLException{
		String sqlSt="SELECT * FROM group"+num+" WHERE grp_name=";
		Statement st;
		ResultSet rs=null;
		try {
			st=con.createStatement();
			rs=st.executeQuery(sqlSt+"'"+uid+"'");
			if(rs!=null) {
				if(rs.next()) {
					return false;
				}
				else return true;
			}
			else {
				System.out.println("null result set");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public static void addAccount(Connection con,String uid,String pw) throws SQLException {
		PreparedStatement pstmt=null;
		PreparedStatement plstmt=null;
		try {
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("Insert INTO user Values(?,?,?,?)");
			pstmt.setString(1, uid);
			pstmt.setString(2, pw);
			pstmt.setString(3, null);
			pstmt.setString(4, "none");
			pstmt.executeUpdate();
			con.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	public static void addDeq2Color(Connection con,String uid,String[] col) throws SQLException {
		PreparedStatement pstmt=null;
		PreparedStatement plstmt=null;
		try {
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("Insert INTO deque2_color Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, uid);
			for(int i=0;i<16;i++) {
				pstmt.setString(i+2,col[i]);
			}
			pstmt.executeUpdate();
			con.commit();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	public static void addDeq2Num(Connection con,String uid,int[] num) throws SQLException {
		PreparedStatement pstmt=null;
		PreparedStatement plstmt=null;
		try {
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("Insert INTO deque2_num Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, uid);
			for(int i=0;i<16;i++) {
				pstmt.setInt(i+2,num[i]);
			}
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	public static void addDeq3Color(Connection con,String uid,String[] col) throws SQLException {
		PreparedStatement pstmt=null;
		PreparedStatement plstmt=null;
		try {
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("Insert INTO deque3_color Values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, uid);
			for(int i=0;i<12;i++) {
				pstmt.setString(i+2,col[i]);
			}
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	public static void addDeq3Num(Connection con,String uid,int[] num) throws SQLException {
		PreparedStatement pstmt=null;
		PreparedStatement plstmt=null;
		try {
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("Insert INTO deque3_num Values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, uid);
			for(int i=0;i<12;i++) {
				pstmt.setInt(i+2,num[i]);
			}
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	public static void addDeq4(Connection con,String uid,String[] col,int[] num) throws SQLException {
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("Insert INTO deque4 Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, uid);
			//2 4 6 8 10 12 14 16
			for(int i=0;i<8;i++) {
				pstmt.setInt((i+1)*2, num[i]);
			}
			//3 5 7 9 11 13 15 17
			for(int i=0;i<8;i++) {
				pstmt.setString((i+1)*2+1,col[i]);
			}
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	public static void addPlayer(Connection con,String uid) throws SQLException {
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("Insert INTO player Values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, uid);
			pstmt.setInt(2, 0);
			pstmt.setInt(3, 0);
			pstmt.setInt(4, 0);
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("Success");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	public static void addDPlayer(Connection con,String uid,Block bl1,Block bl2,Block bl3,Block bl4) throws SQLException {
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("Insert INTO davinci_player Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, uid);
			pstmt.setInt(2, bl1.num);
			pstmt.setString(3, bl1.color);
			pstmt.setInt(4, bl2.num);
			pstmt.setString(5, bl2.color);
			pstmt.setInt(6, bl3.num);
			pstmt.setString(7, bl3.color);
			pstmt.setInt(8, bl4.num);
			pstmt.setString(9, bl4.color);
			pstmt.setInt(10, 1);
			pstmt.setInt(11, 0);
			pstmt.setInt(12, 0);
			pstmt.setInt(13, 0);
			pstmt.setInt(14, 0);
			pstmt.setInt(15, 0);
			pstmt.setInt(16, 4);
			pstmt.setInt(17, -1);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("Success");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	
	public static void addGrp2(Connection con,String uid) throws SQLException {
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("Insert INTO group2 Values(?,?,?)");
			pstmt.setString(1, uid);
			pstmt.setString(2, uid);
			pstmt.setString(3, "none");
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("Success");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	public static void addGrp3(Connection con,String uid) throws SQLException {
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("Insert INTO group3 Values(?,?,?,?)");
			pstmt.setString(1, uid);
			pstmt.setString(2, uid);
			pstmt.setString(3, "none");
			pstmt.setString(4, "none");
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("Success");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	public static void addGrp4(Connection con,String uid) throws SQLException {
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("Insert INTO group4 Values(?,?,?,?,?)");
			pstmt.setString(1, uid);
			pstmt.setString(2, uid);
			pstmt.setString(3, "none");
			pstmt.setString(4, "none");
			pstmt.setString(5, "none");
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("Success");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	public static void modMember(Connection con,String uid,int num,String newMem) throws SQLException {
		Statement stmt=null;
		try {
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs=stmt.executeQuery("Select * from group"+num+" where grp_name="+"'"+uid+"'");
			while(uprs.next()) {
				String old="";
				int i=1;
				for(i=1;i<=num;i++) {
					old=uprs.getString("p"+i);
					if(old.equals("none")) break;
				}
				uprs.updateString("p"+i, newMem);
				uprs.updateRow();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(stmt!=null) stmt.close();
		}
	}
	public static void modPasswd(Connection con,String uid,String pw) throws SQLException {
		Statement stmt=null;
		try {
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs=stmt.executeQuery("Select * from user where id="+"'"+uid+"'");
			while(uprs.next()) {
				String old=uprs.getString("passwd");
				uprs.updateString("passwd",pw);
				uprs.updateRow();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(stmt!=null) stmt.close();
		}
	}
	public static void modOpen(Connection con,String uid,String bl,int b) throws SQLException {
		Statement stmt=null;
		try {
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs=stmt.executeQuery("Select * from davinci_player where id="+"'"+uid+"'");
			while(uprs.next()) {
				String old=uprs.getString(bl+"_open");
				uprs.updateInt(bl+"_open",b);
				uprs.updateRow();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(stmt!=null) stmt.close();
		}
	}
	public static void modIp(Connection con,String uid,String ip) throws SQLException {
		Statement stmt=null;
		try {
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs=stmt.executeQuery("Select * from user where id="+"'"+uid+"'");
			while(uprs.next()) {
				String old=uprs.getString("ip");
				uprs.updateString("ip",ip);
				uprs.updateRow();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(stmt!=null) stmt.close();
		}
	}
	public static void modCor(Connection con,String uid) throws SQLException {
		Statement stmt=null;
		try {
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs=stmt.executeQuery("Select * from davinci_player where id="+"'"+uid+"'");
			while(uprs.next()) {
				int old=uprs.getInt("correct");
				uprs.updateInt("correct",(old+1));
				uprs.updateRow();
				System.out.println("correct updated:"+(old+1));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(stmt!=null) stmt.close();
		}
	}
	public static void modMaxCor(Connection con,String uid,int max) throws SQLException {
		Statement stmt=null;
		try {
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs=stmt.executeQuery("Select * from davinci_player where id="+"'"+uid+"'");
			while(uprs.next()) {
				int old=uprs.getInt("max_cor");
				uprs.updateInt("max_cor",max);
				uprs.updateRow();
				System.out.println("max correct updated:"+max);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(stmt!=null) stmt.close();
		}
	}
	public static void modLeft(Connection con,String uid) throws SQLException {
		Statement stmt=null;
		try {
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs=stmt.executeQuery("Select * from davinci_player where id="+"'"+uid+"'");
			while(uprs.next()) {
				int old=uprs.getInt("closed");
				if(old-1>=0)
					uprs.updateInt("closed",(old-1));
				uprs.updateRow();
				System.out.println("closed updated:"+(old-1));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(stmt!=null) stmt.close();
		}
	}
	public static void modTurn(Connection con,String uid,int num) throws SQLException {
		Statement stmt=null;
		try {
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs=stmt.executeQuery("Select * from davinci_player where id="+"'"+uid+"'");
			while(uprs.next()) {
				int old=uprs.getInt("next_turn");
				uprs.updateInt("next_turn",num);
				uprs.updateRow();
				System.out.println("changed turn:"+num);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(stmt!=null) stmt.close();
		}
	}
	
	public static void modScore(Connection con,String uid,int result,String game) throws SQLException {
		Statement stmt=null;
		try {
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs=stmt.executeQuery("Select * from player where id="+"'"+uid.trim()+"'");
			while(uprs.next()) {
				System.out.println("user: "+uprs.getString("id"));
				if(game.equals("d")) {
					if(result==1) {
						String old=uprs.getString("victory_d");
						int o=Integer.parseInt(old);
						uprs.updateInt("victory_d",o+1);
						uprs.updateRow();
					}
					else {
						String old=uprs.getString("defeat_d");
						int o=Integer.parseInt(old);
						uprs.updateInt("defeat_d",o+1);
						uprs.updateRow();
					}
				}
				else if(game.equals("m")) {
					int old=uprs.getInt("matching_score");
					int o=old;
					uprs.updateInt("matching_score",o+result);
					uprs.updateRow();
					System.out.println("succeess:"+o);
				}
				else if(game.equals("mo")) {
					int old=uprs.getInt("mole_score");
					int o=old;
					uprs.updateInt("mole_score",o+result);
					uprs.updateRow();
					System.out.println("succeess:"+o);
				}
				else if(game.equals("h")) {
					if(result==1) {
						String old=uprs.getString("victory_h");
						int o=Integer.parseInt(old);
						uprs.updateInt("victory_h",o+1);
						uprs.updateRow();
					}
					else {
						String old=uprs.getString("defeat_h");
						int o=Integer.parseInt(old);
						uprs.updateInt("defeat_h",o+1);
						uprs.updateRow();
					}
				}
				else if(game.equals("b")) {
					if(result==1) {
						String old=uprs.getString("victory_b");
						int o=Integer.parseInt(old);
						uprs.updateInt("victory_b",o+1);
						uprs.updateRow();
					}
					else {
						String old=uprs.getString("defeat_b");
						int o=Integer.parseInt(old);
						uprs.updateInt("defeat_b",o+1);
						uprs.updateRow();
					}
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(stmt!=null) stmt.close();
		}
		System.out.println(game+" mod score."+" uid: "+uid);
	}
	public static void modOnline(Connection con,String uid,int enable) throws SQLException {
		Statement stmt=null;
		try {
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs=stmt.executeQuery("Select * from player where id="+"'"+uid+"'");
			while(uprs.next()) {
				int old=uprs.getInt("online");
				uprs.updateInt("online",enable);
				uprs.updateRow();
				int now=uprs.getInt("online");
				System.out.println("online:"+now);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(stmt!=null) stmt.close();
		}
	}
	public static void modGroup(Connection con,String uid,String grp) throws SQLException {
		Statement stmt=null;
		try {
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs=stmt.executeQuery("Select * from user where id="+"'"+uid+"'");
			while(uprs.next()) {
				String old=uprs.getString("grp");
				uprs.updateString("grp", grp);
				uprs.updateRow();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(stmt!=null) stmt.close();
		}
	}
	public static void delGroup(Connection con,String uid) throws SQLException {
		Statement stmt=null;
		try {
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs=stmt.executeQuery("Select * from user where id="+"'"+uid+"'");
			while(uprs.next()) {
				String old=uprs.getString("grp");
				uprs.updateString("grp","none");
				uprs.updateRow();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(stmt!=null) stmt.close();
		}
	}
	public static ResultSet getGrpMem(Connection con,String grp,int num) throws SQLException{
		String sqlSt="select * from group"+num+" where grp_name='"+grp+"'";
		Statement st=null;
		ResultSet rs=null;
		try {
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			if(st.execute(sqlSt)) {
				return st.getResultSet();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String getGrp(Connection con,String id) throws SQLException{
		String sqlSt="select * from user where id='"+id+"'";
		Statement st=null;
		ResultSet rs=null;
		String g="";
		try {
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			if(st.execute(sqlSt)) {
				rs= st.getResultSet();
				while(rs.next()) {
					g=rs.getString("grp");
				}
				return g;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return g;
	}
	public static void deleteMember(Connection con,String uid,int num) throws SQLException {
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("delete from group"+num+" where grp_name=?");
			pstmt.setString(1, uid);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("Success");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	public static void deleteDeq2(Connection con,String uid) throws SQLException {
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("delete from deque2_num where grp_name=?");
			pstmt.setString(1, uid);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("Success");
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("delete from deque2_color where grp_name=?");
			pstmt.setString(1, uid);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("Success");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	public static void deleteDeq3(Connection con,String uid) throws SQLException {
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("delete from deque3_num where grp_name=?");
			pstmt.setString(1, uid);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("Success");
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("delete from deque3_color where grp_name=?");
			pstmt.setString(1, uid);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("Success");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	public static void deleteDeq4(Connection con,String uid) throws SQLException {
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("delete from deque4 where grp_name=?");
			pstmt.setString(1, uid);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("Success");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	public static void deleteDPlayer(Connection con,String uid) throws SQLException {
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("delete from davinci_player where id=?");
			pstmt.setString(1, uid);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("Success");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) pstmt.close();
		}
	}
	
}
