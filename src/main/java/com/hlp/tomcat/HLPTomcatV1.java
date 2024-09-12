package com.hlp.tomcat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HLPTomcatV1 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9898);
        System.out.println("=====MyTomcat port=9898======");
        while (!serverSocket.isClosed()) {
            //等待浏览器/客户端的连接
            //如果有连接来,就创建一个 socket
            //这个 socket 就是服务端和浏览器端的连接/通道
            Socket socket = serverSocket.accept();
            //先接收浏览器发送的数据
            //inputStream 是字节流 => BufferedReader( 字符流)
            //java 基础 IO , 第 19 章
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

            String mes = null;
            System.out.println("=====接受到浏览器发送的数据=====");
            while ((mes = bufferedReader.readLine()) != null) {
                //判断 mes 的长度是否为 0
                if (mes.length() == 0) {
                    break;
                } else {
                    System.out.println(mes);
                }
            }
            //我们的 tomcat 会送-http 响应方式

            OutputStream outputStream = socket.getOutputStream();
            //tomcat发送http响应头
            //http响应体前面有两个换行
            String respHeader = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html;charset=utf-8\r\n\r\n";
            String resp = respHeader + "hi,hlp";
            outputStream.write(resp.getBytes());//resp-->byte[]
            outputStream.flush();
            outputStream.close();
            socket.close();
//            inputStream.close();
//            socket.close();
        }
    }

}
