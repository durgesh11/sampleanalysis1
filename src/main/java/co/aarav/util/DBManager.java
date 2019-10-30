/**
*
* @author  : Durgesh Mudras
* @Date    : 16-10-2019
* @version : 1.0.0
* 
*/
package co.aarav.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.NoInitialContextException;
import javax.sql.DataSource;

public class DBManager {

	private Connection con;
	private Statement stmt;
	private Statement updateStmt;
	private PreparedStatement prepStmt;

	public DBManager() throws NamingException, SQLException,
			ClassNotFoundException {
		DataSource ds;
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/iqoshq");
			con = ds.getConnection();
		} catch (NoInitialContextException nicEx) { // DBManager called outside
													// servlet container
			nicEx.printStackTrace();
		}
	}

	public synchronized ResultSet doQuery(String query) throws SQLException {
		stmt = con.createStatement();
		return stmt.executeQuery(query);
	}

	public synchronized int doUpdate(String query) throws SQLException {
		if (updateStmt == null) {
			updateStmt = con.createStatement();
		}
		int ret = updateStmt.executeUpdate(query);
		return ret;
	}

	public synchronized Long doInsert(String query) throws SQLException {
		if (updateStmt == null) {
			updateStmt = con.createStatement();
		}
		int ret = updateStmt.executeUpdate(query);
		Long id = -1L;
		if(ret>0){
			ResultSet rs = doQuery("SELECT LAST_INSERT_ID()");
			if(rs.next()){
				id = rs.getLong(1);
			}
		}
		return id;
	}
	
	public synchronized ResultSet doQry(String query) throws SQLException {
		prepStmt = con.prepareStatement(query);
		return prepStmt.executeQuery();
	}
	
	public synchronized int[] doUpdte(List<String> queryStringList) throws SQLException {
		if (updateStmt == null) {
			updateStmt = con.createStatement();
		}
		for (String queryString : queryStringList) {
			if (!queryString.trim().equals("")) {
				updateStmt.addBatch(queryString);
			}
		}
		return updateStmt.executeBatch();
	}
	
	public synchronized void close() throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
		if (updateStmt != null) {
			updateStmt.close();
		}
		if (con != null && !con.isClosed()) {
			con.close();
		}
	}

	public synchronized boolean isClosed() {
		try {
			return con.isClosed();
		} catch (SQLException e) {
			return true;
		}
	}
}