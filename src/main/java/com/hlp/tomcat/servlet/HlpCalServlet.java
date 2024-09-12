package com.hlp.tomcat.servlet;

import com.hlp.tomcat.Utils.WebUtils;
import com.hlp.tomcat.http.HlpRequest;
import com.hlp.tomcat.http.HlpResponse;

import java.io.IOException;
import java.io.OutputStream;

public class HlpCalServlet extends HlpHttpServlet{
    @Override
    public void doGet(HlpRequest request, HlpResponse response) {
        int num1 = WebUtils.parseInt(request.getParameter("num1"),0);
        int num2 = WebUtils.parseInt(request.getParameter("num2"),0);

        int result = num1 + num2;

        OutputStream outputStream = response.getOutputStream();
        String respMes = HlpResponse.respHeader+ "<h1>"+num1+"+"+num2 +"="+ result +"HlpTomcatV3<h1>";

        try {
            outputStream.write(respMes.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(HlpRequest request, HlpResponse response) {
        this.doGet(request, response);
    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }
}
