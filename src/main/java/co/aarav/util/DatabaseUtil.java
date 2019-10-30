/**
*
* @author  : Durgesh Mudras
* @Date    : 16-10-2019
* @version : 1.0.0
* 
*/
package co.aarav.util;

import java.sql.SQLException;

import javax.naming.NamingException;

public class DatabaseUtil {
	private static final ThreadLocal<DBManager> currentDbManager = new ThreadLocal<DBManager>();

	public static DBManager getManager() {
		DBManager dbManager = (DBManager) currentDbManager.get();
		if (dbManager == null || dbManager.isClosed()) {
			try {
				dbManager = new DBManager();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			currentDbManager.set(dbManager);
			return dbManager;
		}
		return dbManager;
	}

	public static void closeManager() {
		DBManager dbManager = (DBManager) currentDbManager.get();
		if (dbManager != null) {
			try {
				dbManager.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			currentDbManager.set(null);
		}
	}
}
