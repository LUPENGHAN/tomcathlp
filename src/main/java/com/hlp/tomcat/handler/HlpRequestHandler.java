package com.hlp.tomcat.handler;

import com.hlp.tomcat.HLPTomcatV3;
import com.hlp.tomcat.http.HlpRequest;
import com.hlp.tomcat.http.HlpResponse;
import com.hlp.tomcat.servlet.HlpCalServlet;
import com.hlp.tomcat.servlet.HlpHttpServlet;

import java.io.*;
import java.net.Socket;

public class HlpRequestHandler implements Runnable {

    private Socket socket;

    public HlpRequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // InputStream inputStream = socket.getInputStream();

//            BufferedReader bufferedReader  = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
//
//            System.out.println("=====tomcat receive data ======");
//            String mes = null;
//            while((mes = bufferedReader.readLine()) != null) {
//                if (mes.length() ==0){break;}
//                System.out.println(mes);
//
//            }
//            System.out.println(Thread.currentThread().getName());
            HlpRequest hlpRequest = new HlpRequest(socket.getInputStream() );
//            String num1 = hlpRequest.getParameter("num1");
//            String num2 = hlpRequest.getParameter("num2");
//            System.out.println(num1 + " " + num2);
//            //give data back to browner --->http response


            HlpResponse hlpResponse = new HlpResponse(socket.getOutputStream());
            //创建HlpCalServlet对象,一会用反射构建对象
//            HlpCalServlet hlpCalServlet = new HlpCalServlet();
//            hlpCalServlet.doGet(hlpRequest, hlpResponse );
            //1. url 就是servletUrlMapping 的 url-pattern
            String uri = hlpRequest.getUri();
            System.out.println("Requested URI: " + uri);
            String servletName = HLPTomcatV3.servletUrlMapping.get(uri);
            System.out.println("Mapped Servlet Name: " + servletName);

            //2.get url -> servletName's url=pattern真正运行的类型是其子类
            if (servletName == null) {
                servletName = " ";
            }
            HlpHttpServlet hlpHttpServlet =
                    HLPTomcatV3.servletMapping.get(servletName);
            //调用service，动态绑定 运行 doget和dopost
            if (hlpHttpServlet != null) {
                hlpHttpServlet.service(hlpRequest, hlpResponse);
            }else{
                String resp = HlpResponse.respHeader+"<h1>404 not found<h1>";
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(resp.getBytes());
                outputStream.flush();
                outputStream.close();

            }


//            String resp = HlpResponse.respHeader + "<h1> hi hlp hi<h1>";
//
//            OutputStream outputStream = hlpResponse.getOutputStream();
//
//            outputStream.write(resp.getBytes());
//            outputStream.flush();
//            outputStream.close();
//            inputStream.close();
//            //build http response header
////            String respheader = "HTTP/1.1 200 OK\r\n" +
////                    "Content-type: text/html;charset=utf-8\r\n\r\n";
////            String resp = respheader + "hlp is coming";
////            OutputStream outputStream = socket.getOutputStream();
////            //resp.getBytes 字符串转为字节数组
////            outputStream.write(resp.getBytes());
////            outputStream.flush();
////            outputStream.close();
////            inputStream.close();

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
