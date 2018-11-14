package com.hafele.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年9月20日 下午5:24:18
* 数据库操作工具
*/
public class DbHelper {
	private static String dbClassName = "com.mysql.cj.jdbc.Driver";
	private static String dbUrl = "jdbc:mysql://localhost:3306/db_LibraryManagementSystem?useSSL=false";
	private static String dbUser = "root";
	private static String dbPassword = "root";
	private static DbHelper instance = null;
	  
    private DbHelper() {  
    }  
  
    public static DbHelper getInstance() {  
        if (instance == null) {  
            synchronized (DbHelper.class) {  
                if (instance == null) {  
                    instance = new DbHelper();  
                }  
            }  
        }  
        return instance;  
    }  
  
    static {  
        try {  
            Class.forName(dbClassName); 
        } catch (ClassNotFoundException e) {  
            throw new ExceptionInInitializerError(e);  
        }  
    }  
  
    public static Connection getConnection() throws SQLException {  
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);  
    }  
    
    public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn = null;
		}
	}
    
    public static void Close(ResultSet rs, Statement st, Connection conn) {  
        try {  
            if (rs != null)  
                rs.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (st != null)  
                    st.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            } finally {  
                if (conn != null)  
                    try {  
                        conn.close();  
                    } catch (SQLException e) {  
                        e.printStackTrace();  
                    }  
            }  
        }  
    }  
}
