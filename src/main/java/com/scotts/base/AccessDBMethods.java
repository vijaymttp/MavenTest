package com.scotts.base;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccessDBMethods {
	String projectName;

	AccessDBMethods(String projectName) {
		this.projectName = projectName;
	}

	public Connection getConnectionObj() {
		Connection conn = null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String networkdrive = System.getenv("Automation_Path");
			String dbName = networkdrive + "Data\\" + projectName + "\\TestSets.accdb";
			String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};Dbq=" + dbName + ";";
			conn = DriverManager.getConnection(database, "", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public String getSauceuserAccessKey(String sauceuser) {
		try {
			String sauceUserAccessKey = "";
			Connection conn = getConnectionObj();
			Statement smt = conn.createStatement();
			String sSQL = "select SauceUserAccessKey from InterfaceData where SauceUser='" + sauceuser + "'";
			smt.execute(sSQL);
			ResultSet rs = smt.getResultSet();
			while (rs.next()) {
				sauceUserAccessKey = rs.getString(1);
			}
			return sauceUserAccessKey;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> getTestCases(String sSQL) throws ClassNotFoundException, SQLException {
		Connection conn = getConnectionObj();
		Statement smt = conn.createStatement();
		smt.execute(sSQL);
		ResultSet rs = smt.getResultSet();

		// int i=0;
		List<String> testcases = new ArrayList<String>();
		while (rs.next()) {
			testcases.add(rs.getString(1));
			// i++;
		}
		smt.close();
		conn.close();
		return testcases;
	}

	public String[] getModules(String dbName, String sSQL) throws ClassNotFoundException, SQLException {

		// Dictionary dict = new Hashtable();
		String[] ColumnName;
		Connection conn = getConnectionObj();
		// Execute Query
		Statement smt = conn.createStatement();

		smt.execute(sSQL);
		ResultSet rs = smt.getResultSet();

		ColumnName = new String[rs.getMetaData().getColumnCount()];
		ResultSetMetaData rsmd = rs.getMetaData();

		rs.next();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			// System.out.println(rsmd.getColumnName(i)+": "+rs.getString(i));
			String sColName = rsmd.getColumnName(i);
			ColumnName[i - 1] = sColName;
		}

		smt.close();
		conn.close();
		return ColumnName;
	}

	public List<String> getModuleNames() throws ClassNotFoundException, SQLException {
		// Dictionary dict = new Hashtable();
		// String[] ColumnName ;
		Connection conn = getConnectionObj();
		List<String> tableNames = new ArrayList<String>();
		DatabaseMetaData md = conn.getMetaData();
		ResultSet rs = md.getTables(null, null, "%", null);
		String temp;
		while (rs.next()) {
			temp = rs.getString(3);
			if (!temp.contains("MSys")) {
				if (!temp.contains("Test")) {
					tableNames.add(temp);
				}
			}
		}
		conn.close();
		return tableNames;
	}

	public String[] getTestSets(String sSQL) throws ClassNotFoundException, SQLException {

		// Dictionary dict = new Hashtable();
		String[] ColumnName;
		Connection conn = getConnectionObj();
		// Execute Query
		Statement smt = conn.createStatement();
		smt.execute(sSQL);
		ResultSet rs = smt.getResultSet();
		ColumnName = new String[rs.getMetaData().getColumnCount()];
		ResultSetMetaData rsmd = rs.getMetaData();
		rs.next();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			String sColName = rsmd.getColumnName(i);
			ColumnName[i - 1] = sColName;
		}

		smt.close();
		conn.close();
		return ColumnName;
	}

	public String[] getInstanceName(String sSQL) throws ClassNotFoundException, SQLException {

		// Dictionary dict = new Hashtable();
		String[] ColumnName;
		Connection conn = getConnectionObj();
		// Execute Query
		Statement smt = conn.createStatement();
		smt.execute(sSQL);
		ResultSet rs = smt.getResultSet();
		ColumnName = new String[rs.getMetaData().getColumnCount()];
		ResultSetMetaData rsmd = rs.getMetaData();
		rs.next();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			String sColName = rsmd.getColumnName(i);
			ColumnName[i - 1] = sColName;
		}

		smt.close();
		conn.close();
		return ColumnName;
	}

	public void insertDataIntoAccessDB(String sSQL) throws ClassNotFoundException, SQLException {
		// Register JDBC driver
		/*
		 * Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); String
		 * dbName1="W:\\Data\\TestDB.accdb";
		 * 
		 * //Open a connection String database =
		 * "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};Dbq="
		 * +dbName1+";"; Connection conn = DriverManager.getConnection(database,
		 * "", "");
		 */

		Connection conn = getConnectionObj();
		// Execute Query
		Statement smt = conn.createStatement();
		// String sSQL = "Insert into TblPublicEval_Objects
		// values(37,"fg","","")";

		smt.execute(sSQL);
		smt.close();
		conn.close();

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// AccessDBMethods obj= new AccessDBMethods(sProjectName);
		// System.out.println("sauceaccesskey:"+obj.getSauceuserAccessKey("MyVMware_Sauce"));
		/*
		 * List<String>tbleNames=obj.getModuleNames(); for(int
		 * z=0;z<tbleNames.size();z++){ System.out.println("Table names:  "
		 * +tbleNames.get(z)); }
		 * 
		 * //getTestsets names String sTbleName="ISV"; String sSelectTestSets =
		 * "Select * from "+sTbleName+""; String[]
		 * testSets=obj.getTestSets(sSelectTestSets); for(int
		 * i=0;i<testSets.length;i++){ System.out.println("TestSet name:  "
		 * +testSets[i]); }
		 * 
		 * //getTestCases String sTbleName1="ISV"; String
		 * sTestSetname="TS_ISV_01"; String sSelectTCs = "Select "+sTestSetname+
		 * " from "+sTbleName1+""; List<String>
		 * testCases=obj.getTestCases(sSelectTCs); for(int
		 * j=0;j<testCases.size();j++){ System.out.println("TestCase Name :  "
		 * +testCases.get(j)); }
		 * 
		 * //Insert data into access db DateFormat dateFormat = new
		 * SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); Date date = new Date();
		 * System.out.println(dateFormat.format(date)); //String
		 * sExecutionTime=dateFormat.format(date).toString(); String
		 * sExecutionTime="fgf21"; String sUsername=
		 * System.getProperty("user.name");; String
		 * sExecution_Environment="test14"; String sModuleName="OEM"; String
		 * sTestSetName="TS_OEM_01"; String sTestCaseName="TC_OEM_Demo_01";
		 * String sSauceLabAccountName="sarithaRenukunta"; String
		 * sSessionID="1235"; String sStatus="PASS"; AccessDBMethods obj1= new
		 * AccessDBMethods(); String sInsertTestSetResults=
		 * "Insert into TestSetResults(ExecutionTime,Username,Execution_Environment,ModuleName,TestSetName,TestCaseName,SauceLabAccountName,SessionID,Status) values ('"
		 * +sExecutionTime+"','"+sUsername+"','"+sExecution_Environment+"','"+
		 * sModuleName+"','"+sTestSetName+"','"+sTestCaseName+"','"+
		 * sSauceLabAccountName+"','"+sSessionID+"','"+sStatus+"');"; // String
		 * sInsertTestSetResults=
		 * "Insert into TestSetResults(ExecutionTime,Username,SessionID) values ('"
		 * +sExecutionTime+"','"+sUsername+"','"+sSessionID+"');"; //String
		 * sInsertTestSetResults=
		 * "Insert into Test(UserId,Admin,ISV,OEM) values ('"
		 * +sExecutionTime+"','"+sUsername+"','"+sSessionID+"','"+sSessionID+
		 * "');"; obj1.insertDataIntoAccessDB(sInsertTestSetResults);
		 */
	}
}
