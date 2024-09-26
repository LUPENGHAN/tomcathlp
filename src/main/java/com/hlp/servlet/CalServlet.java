package com.hlp.servlet;

import com.hlp.utils.webutils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class CalServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受提交的数据
        String strnum1 = req.getParameter("num1");
        String strnum2 = req.getParameter("num2");
        int num1 = webutils.paraInt(strnum1, 1);
        int num2 = webutils.paraInt(strnum2, 1);
        int result = num1 + num2;
        PrintWriter out = resp.getWriter();
        out.println(num1 + "+" + num2 + "=" + result);
        out.flush();
        out.close();

    }
}
