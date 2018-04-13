package com.wey.base64ToImg;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import sun.misc.BASE64Decoder;

public class Base64ToImage {
    
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        InputStream inputStream = Base64ToImage.class.getClassLoader().getResourceAsStream("dg_seal.dat");
        BASE64Decoder bd = new BASE64Decoder();
        byte[] bytes = bd.decodeBuffer(inputStream);
        InputStream stream = new ByteArrayInputStream(bytes);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(stream);
        document.getDocumentElement();
    }
    
}
