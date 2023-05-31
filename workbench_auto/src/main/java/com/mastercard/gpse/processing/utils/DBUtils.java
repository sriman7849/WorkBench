package com.mastercard.gpse.processing.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mastercard.gpse.processing.fileoperations.TestProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class DBUtils {

	private static Logger logger = (Logger) LogManager.getLogger(DBUtils.class);

	/**
	 * Execute the query in DB and return the value of the given column
	 * 
	 * @param query - DB query
	 * @param columnName - column name from which value has to be fetched
	 * @return - value of the given column name from the executed query result
	 */
	public static String executeDatabaseQuery(String query, String columnName) {
		String columnValue = null;
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = createDBConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				columnValue = rs.getString(columnName);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			loggInfo(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			closeDBConnection(connection);
		}
		loggInfo("Column Value : " + columnValue);
		return columnValue;
	}

	/**
	 * Execute the query in DB to check if a record is present in DB or not.
	 *
	 * @param query - DB query
	 * @return true if record present, false if record is not present in DB
	 */
	public static boolean isRecordPresent(String query) {
		boolean recordPresent = false;
		int recordCount = 0;
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = createDBConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			recordCount = rs.getInt(1);
			rs.close();
			stmt.close();
		} catch (Exception e) {
			loggInfo(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			closeDBConnection(connection);
		}
		if (recordCount != 0) {
			recordPresent = true;
			loggInfo("Record present? : " + recordPresent);
		} else {
			loggInfo("Record present? : " + recordPresent);
		}
		return recordPresent;
	}

	/**
	 * Get number of records present in DB from the result when the given query is executed
	 *
	 * @param query - DB query
	 * @return record count in the executed query
	 */
	public static int recordCount(String query) {
		int recordCount = 0;
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = createDBConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			recordCount = rs.getInt(1);
			rs.close();
			stmt.close();
		} catch (Exception e) {
			loggInfo(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			closeDBConnection(connection);
		}
		loggInfo("No. of records present are: " + recordCount);
		return recordCount;
	}

	/**
	 * Create the Database connection
	 * 
	 * @return
	 */
	public static Connection createDBConnection() {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(TestProperties.getInstance().getDBProperty("db.url"),
					TestProperties.getInstance().getDBProperty("db.username"),
					TestProperties.getInstance().getDBProperty("db.password"));
			c.setAutoCommit(false);
		} catch (Exception e) {
			loggInfo(e.getClass().getName() + ": " + e.getMessage());
		}
		return c;
	}

	/**
	 * Close the Database connection
	 * 
	 * @param connection
	 */
	public static void closeDBConnection(Connection connection) {
		try {
			connection.close();
		} catch (Exception e) {
			loggInfo(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	/**
	 * Logs the informational message
	 * 
	 * @param logmessage
	 */
	public static void loggInfo(String logmessage) {
		logger.info(logmessage);
	}
}
