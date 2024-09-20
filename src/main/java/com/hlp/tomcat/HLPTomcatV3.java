package com.hlp.tomcat;

import com.hlp.tomcat.servlet.HlpHttpServlet;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class HLPTomcatV3 {
    //存放容器 servletMapping
    public static final ConcurrentHashMap<String, HlpHttpServlet> servletMapping = new ConcurrentHashMap<>();
    //Urk mapping
    public static  final ConcurrentHashMap<String, String> servletUrlMapping = new ConcurrentHashMap<>();

    public void init(){
        //读取web.xml => dom4j =>
        //得到web.xml的路径
        String path = HLPTomcatV3.class.getResource("/").toExternalForm();

        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new File(path+"web.xml"));
            // root element
            Element rootElement = document.getRootElement();
            // all element in root element
            List<Element> elements = rootElement.elements();
            for (Element element : elements) {
                if (element.getName().equals("servlet")) {
                    Element servletElement = element.element("servlet");
                    String servletName = servletElement.attributeValue("name");
                    String servletClass = servletElement.attributeValue("class");
                }
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
