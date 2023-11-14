package com.example.exp3.daos.impl;


import com.example.exp3.daos.UserDAO;
import com.example.exp3.utils.DatabaseConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAOImpl implements UserDAO {
    private Connection conn = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;

    @Override
    public int login(String username, String password) {
        try {
            conn = DatabaseConnectionUtil.getConnection();
            String sql = "select count(*) from user where username=? and password=?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnectionUtil.close(conn, pre, rs);
        }
        return 0;
    }

}
