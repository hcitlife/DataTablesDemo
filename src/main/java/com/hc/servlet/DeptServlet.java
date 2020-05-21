package com.hc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

@WebServlet("/dept")
public class DeptServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder("{\"data\":[");
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            int deptno = random.nextInt(15) + 10;
            String dname = UUID.randomUUID().toString().substring(0,5);
            String loc = UUID.randomUUID().toString().substring(0,5);
            sb.append("[").append(deptno).append(",\"").append(dname).append("\",\"").append(loc).append("\"],");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]}");

        resp.getWriter().write(sb.toString());
        System.out.println(sb);
    }

}
