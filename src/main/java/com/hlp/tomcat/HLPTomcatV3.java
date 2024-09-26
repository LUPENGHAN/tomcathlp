package com.hlp.tomcat;

import com.hlp.tomcat.handler.HlpRequestHandler;
import com.hlp.tomcat.servlet.HlpHttpServlet;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class  HLPTomcatV3 {
    //存放容器 servletMapping
    public static final ConcurrentHashMap<String, HlpHttpServlet> servletMapping =
            new ConcurrentHashMap<>();
    //Urk mapping
    public static  final ConcurrentHashMap<String, String> servletUrlMapping =
            new ConcurrentHashMap<>();

    public void init() {
        //读取web.xml => dom4j =>
        //得到web.xml的路径
        String path = HLPTomcatV3.class.getResource("/").getPath();
        System.out.println(path);
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new File(path + "web.xml"));

            // root element
            Element rootElement = document.getRootElement();
            // all element in root element
            List<Element> elements = rootElement.elements();
            for (Element element : elements) {
                if ("servlet".equals(element.getName())) {
                    Element servletName = element.element("servlet-name");
                    Element servletClass = element.element("servlet-class");
                    servletMapping.put(servletName.getText(), (HlpHttpServlet) Class.forName(servletClass.getText().trim()).newInstance());
                } else if ("servlet-mapping".equals(element.getName())) {
                    Element servletName = element.element("servlet-name");
                    Element urlPattern = element.element("url-pattern");
                    servletUrlMapping.put( urlPattern.getText(),servletName.getText());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("servletMapping = " + servletMapping);
        System.out.println("servletUrlMapping = " + servletUrlMapping);
    }
    public static void main(String[] args) {
        HLPTomcatV3 tomcat = new HLPTomcatV3();
        tomcat.init();
        tomcat.run();
    }
    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(9898);
            System.out.println("9898 listening");
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                HlpRequestHandler hlpRequestHandler =
                        new HlpRequestHandler(socket);
                new Thread(hlpRequestHandler).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
