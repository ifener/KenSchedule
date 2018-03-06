package com.wey.pdf;


public class PDFReaderTest {
    
    public static void main(String[] args) {
       String file= PDFReaderTest.class.getClassLoader().getResource("pdf/alibaba-java.pdf").getPath();
       System.out.println(file);
       //PDFReader.getPDFOutline(file);
       PDFReader.getPDFInformation(file);
    }
    
}
