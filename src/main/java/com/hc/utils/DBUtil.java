package com.hc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DBUtil {
    private static final String url = "jdbc:mysql://localhost:3306/db_test?useSSL=false&serverTimezone=GMT%2B8";//连接字符串
    private static final String name = "root";    //用户名
    private static final String pass = "root"; //密码

    static {// 1、加载驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, name, pass);
    }

    public static void free(ResultSet rs, Statement stmt, Connection conn) {
        try { // 建议采用这种形式来释放资源，因为finally里面的一定会被释放
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
