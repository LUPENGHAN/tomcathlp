package com.hlp.tomcat.servlet;

import com.hlp.tomcat.http.HlpRequest;
import com.hlp.tomcat.http.HlpResponse;

public abstract class HlpHttpServlet implements HlpServlet{
    @Override
    public void service(HlpRequest request, HlpResponse response) {
        if ("GET".equals(request.getMethod())) {
            this.doGet(request, response);
        }else if("POST".equals(request.getMethod())) {
            this.doPost(request, response);
        }
    }
    //抽象模板设计模式
    public abstract void doGet(HlpRequest request, HlpResponse response);
    public abstract void doPost(HlpRequest request, HlpResponse response);
}
