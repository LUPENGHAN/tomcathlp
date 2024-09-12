package com.hlp.tomcat;

import com.hlp.tomcat.handler.HlpRequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HLPTomcatV2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        serverSocket = new ServerSocket(9898);
        System.out.println("====9898 listing=====");
        // No closed No stop
        while (!serverSocket.isClosed()) {
            //data channel
            Socket socket = serverSocket.accept();
            //oop and thread
            new Thread(new HlpRequestHandler(socket)).start();
        }
    }
}
