/**
*
* @author  : Durgesh Mudras
* @Date    : 16-10-2019
* @version : 1.0.0
* 
*/
package co.aarav.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.NoInitialContextException;
import javax.sql.DataSource;

public class SQLHelper {
	public static String date2sql(String date) {
		date = date.substring(6, 10) + "-" + date.substring(3, 5) + "-"
				+ date.substring(0, 2);
		return date;
	}

	public static String sql2date(String date) {
		date = date.substring(8, 10) + "." + date.substring(5, 7) + "."
				+ date.substring(0, 4);
		return date;
	}

	public static String timestamp2sql(String date) {
		date = date.substring(6, 10) + "-" + date.substring(3, 5) + "-"
				+ date.substring(0, 2) + " " + date.substring(11, 19);
		return date;
	}

	public static String sql2timestamp(String date) {
		date = date.substring(8, 10) + "." + date.substring(5, 7) + "."
				+ date.substring(0, 4) + " " + date.substring(11, 19);
		return date;
	}

	public static void backupDB(String dbName, String dbUser, String dbPass,
			String pathOfMysqlBin, String pathToStoreBackupFile) {

		File checkFilePath = new File(pathToStoreBackupFile);

		try {
			checkFilePath.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (checkFilePath.exists()) {
			/***********************************************************/
			// Execute Shell Command
			/***********************************************************/
			String executeCmd = "\"" + pathOfMysqlBin + "mysqldump\""
					+ " --single-transaction  -u " + dbUser + " -p" + dbPass
					+ " " + dbName + " -r " + pathToStoreBackupFile;

			try {
				Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
				int processComplete = runtimeProcess.waitFor();
				if (processComplete == 0) {
					System.out.println("Backup taken successfully");

				} else {
					System.out.println("Could not take mysql backup");
				}
			} catch (IOException ioe) {
				System.out
						.println("Could not take mysql backup. mysql path is not proper.");
				ioe.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out
					.println("db backup not possible as no directory present for backup.\nPlease create proper directory/path for mysql backup.");
		}

	}

	public static String getPropertyFromDB(String propertyName,
			String contextLookUpPath) {
		Statement stmt = null;
		Connection con = null;
		String dBChangesFileNameWithPath = null;

		String query = "select * from  properties where `name`='"
				+ propertyName + "'";
		try {
			DataSource ds = null;
			
			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(contextLookUpPath);
			con = ds.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				dBChangesFileNameWithPath = rs.getString("value");
			}
		} catch (NoInitialContextException nicEx) {
			System.out.println(" initial context lokup failed.");
			System.out
					.println("Please set proper context. Halting application");
			nicEx.printStackTrace();
			System.exit(0);
		} catch (NamingException naEx) { // called outside servlet
			System.out.println(" database conectivity not possible.");
			System.out.println("Please set proper jndi. Halting application");
			naEx.printStackTrace();
			System.exit(0);
		} catch (SQLException e) {
			System.out.println("property " + propertyName
					+ " can not be retrieved from database.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				if(!con.isClosed()) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dBChangesFileNameWithPath;
	}

	
	public static ResultSet executeCustomQueryFetchResultSet(String contextLookUpPath, String query) {

		ResultSet resultSet = null;
		Statement stmt = null;
		Connection con = null;

		try {
			con = getConnection(contextLookUpPath);
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(query);			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultSet;
	}
	
	public static Connection getConnection(String contextLookUpPath){
		Connection con = null;
		try {
		InitialContext ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup(contextLookUpPath);
		con = ds.getConnection();
		} catch (NoInitialContextException nicEx) {
			System.out.println(" initial context lokup failed.");
			System.out.println("Please set proper context. Halting application");
			nicEx.printStackTrace();
			System.exit(0);
		} catch (NamingException naEx) { // called outside servlet
			System.out.println(" database conectivity not possible.");
			System.out.println("Please set proper jndi. Halting application");
			naEx.printStackTrace();
			System.exit(0);
		} catch (SQLException e) {
			e.printStackTrace();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void executeCustomQuery(String contextLookUpPath, String query,String message) {

		Statement stmt = null;
		Connection con = null;

		try {
			con = getConnection(contextLookUpPath);
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			System.out.println(message);
			stmt.close();
			con.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
}
