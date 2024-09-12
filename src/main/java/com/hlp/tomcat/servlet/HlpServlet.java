package com.hlp.tomcat.servlet;

import com.hlp.tomcat.http.HlpRequest;
import com.hlp.tomcat.http.HlpResponse;

public interface HlpServlet {
    void init();
    void destroy();
    void service(HlpRequest request, HlpResponse response);
}
