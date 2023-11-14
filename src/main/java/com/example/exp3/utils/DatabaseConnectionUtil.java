package com.example.exp3.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//JDBC工具类（连接MySQL数据库的工具类）
public class DatabaseConnectionUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/bookmanager?serverTimezone = GMT";
    private static final String USER = "root";
    private static final String PASSWORD = "122600";
    private static Connection conn = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    //连接数据库方法
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("++++++++++" + "数据库连接成功" + "++++++++++");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn, PreparedStatement pre, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
