package com.hc.service;

import com.hc.entity.Dept;
import com.hc.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 梁云亮
 * @Date 2020/5/21 2:25
 * @Description:
 */
public class DeptService {

    public List<Dept> getAll() throws SQLException {
        List<Dept> depts = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        String sql = "select * from tb_dept";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            depts.add(new Dept(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc")));
        }
        return depts;
    }

    public List<Dept> getWithPage(Integer pageNum, Integer pageSize) throws SQLException {
        List<Dept> depts = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        String sql = "select * from tb_dept limit ?,?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, pageNum); //前端datatable插件传递过来的pageNum从0开始，且其值为pageNum * pageSize
        ps.setInt(2, pageSize);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            depts.add(new Dept(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc")));
        }
        DBUtil.free(rs,ps,conn);
        return depts;
    }

    public int count() throws SQLException {
        Connection conn = DBUtil.getConnection();
        String sql = "select count(*) from tb_dept";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int res = rs.getInt(1);
        DBUtil.free(rs,ps,conn);
        return res;
    }
}
