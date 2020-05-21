package com.hc.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hc.entity.Dept;
import com.hc.service.DeptService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 梁云亮
 * @Date 2020/5/21 2:41
 * @Description:
 */
@WebServlet(urlPatterns = "/dept3")
public class DeptServlet3 extends HttpServlet {

    private DeptService deptService = new DeptService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        List<Dept> depts = null;
        try {
            depts = deptService.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String json = JSON.toJSONString(depts);
        String res = "{\"data\":" + json + "}";
        System.out.println(res);
        out.write(res);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        try {
            getWithPage(req, out);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.flush();
    }

    private void getWithPage(HttpServletRequest req, PrintWriter out) throws SQLException {
        String sEcho = "";
        int pageNum = 1;
        int pageSize = 10;

        String params = req.getParameter("aoData");
        System.out.println(params);
        JSONArray array = JSONArray.parseArray(params);
        //这里获取从前台传递过来的参数，从而确保是否分页、是否进行查询、是否排序等
        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = (JSONObject) array.get(i);
            if (obj.get("name").equals("sEcho")) {
                sEcho = obj.getString("value");
            }
            if (obj.get("name").equals("iDisplayStart")) {
                pageNum = obj.getIntValue("value");
            }
            if (obj.get("name").equals("iDisplayLength")) {
                pageSize = obj.getIntValue("value");
            }
        }

        List<Object[]> data = new ArrayList<>();
        List<Dept> depts = deptService.getWithPage(pageNum, pageSize);
        for (Dept dept : depts) {
            Object[] obj = {dept.getDeptno(), dept.getDname(), dept.getLoc()};
            data.add(obj);
        }

        int count = deptService.count();

        JSONObject res = new JSONObject();
        res.put("sEcho", sEcho);
        res.put("iTotalRecords", count);//实际的行数    
        res.put("iTotalDisplayRecords", count);//显示的行数   
        res.put("aaData", data);//要以JSON格式返回，否则前台没法显示    

        System.out.println(res);

        out.write(res.toJSONString());
    }

}
