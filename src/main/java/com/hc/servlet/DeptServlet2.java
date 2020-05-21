package com.hc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

@WebServlet("/dept2")
public class DeptServlet2 extends HttpServlet {

    @Override   //服务于demo051.html
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder("{ \"draw\": 1, \"recordsTotal\": 99, \"recordsFiltered\": 99, \"data\": [");
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            String s = UUID.randomUUID().toString();
            String temp = "[" + random.nextInt(15) + 10 + ",\"" + s.substring(28) + "\",\"" + s.substring(32) + "\"],";
            sb.append(temp);
        }
        sb.deleteCharAt(sb.length() - 1).append("] }");

        resp.getWriter().write(sb.toString());
        System.out.println(sb);
    }

}
