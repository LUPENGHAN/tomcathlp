package com.hlp.tomcat.http;

import java.io.OutputStream;

public class HlpResponse {
    private OutputStream outputStream = null;

    public  static final  String respHeader = "HTTP/1.1 200 OK" +
            "Content-Type: text/html;charset=utf-8\r\n\r\n";
    public HlpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
