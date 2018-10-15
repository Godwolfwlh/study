package com.util;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class XmlUtile {
	@Test  
    public void readXMLDemo() throws Exception {  

        // 创建saxReader对象  
        SAXReader reader = new SAXReader();  
        // 通过read方法读取一个文件 转换成Document对象  
        Document document = reader.read(new File("D:\\TestSite\\hotel\\src\\com\\sys\\mapping\\UserMapper.xml"));  
        //获取根节点元素对象  
        Element node = document.getRootElement();  
        //遍历所有的元素节点  
        // listNodes(node); 

        elementMethod(node);

    } 
	
	/** 
     * 介绍Element中的element方法和elements方法的使用 
     *  
     * @param node 
     */  
    public void elementMethod(Element node) {  
        // 获取node节点中，子节点的元素名称为supercars的元素节点。  
        Element e = node.element("mapper");  
        // 获取supercars元素节点中，子节点为carname的元素节点(可以看到只能获取第一个carname元素节点)  
        Element carname = e.element("select");  

        System.out.println(e.getName() + "----" + carname.getText());  

        // 获取supercars这个元素节点 中，所有子节点名称为carname元素的节点 。  

        List<Element> carnames = e.elements("select");  
        for (Element cname : carnames) {  
            System.out.println(cname.getText());  
        }  

        // 获取supercars这个元素节点 所有元素的子节点。  
        List<Element> elements = e.elements();  

        for (Element el : elements) {  
            System.out.println(el.getText());  
        }  

    }  
}
