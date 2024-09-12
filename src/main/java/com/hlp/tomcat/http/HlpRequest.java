package com.hlp.tomcat.http;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

//封装http请求的数据
//等价于HttpServletRequest
public class HlpRequest {
    private String method;
    private String uri;
    private Map<String, String> parameterMapping =
            new HashMap<>();
    private InputStream inputStream = null;

    public HlpRequest(InputStream inputStream) {
        this.inputStream = inputStream;
        init();
    }

    private void init() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String requestLine = bufferedReader.readLine();
            String[] requestLines = requestLine.split(" ");
            method = requestLines[0];
            int index = requestLines[1].indexOf("?");
            if (index == -1) {
                uri = requestLine.split(" ")[1];
            } else {
                uri = requestLines[1].substring(0, index);
                String parameters = requestLines[1].substring(index + 1);
                String[] parametersPair = parameters.split("&");

                for (String parameterPair : parametersPair) {
                    String[] parrameterVal = parameterPair.split("=");
                    if (parrameterVal.length == 2) {
                        parameterMapping.put(parrameterVal[0], parrameterVal[1]);

                    }
                }
            }
//            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public String getParameter(String name) {
        if (parameterMapping.containsKey(name)) {
            return parameterMapping.get(name);
        } else {
            return null;
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

}
