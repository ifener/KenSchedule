package com.wey.pdf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;

public class PDFReader {
    
    public static String dateFormat( Calendar calendar ){  
        if( null == calendar )  
            return null;  
        String date = null;    
        String pattern = "yyyy-MM-dd HH:mm:ss";  
        SimpleDateFormat format = new SimpleDateFormat( pattern );  
        date = format.format( calendar.getTime() );  
        return date == null ? "" : date;  
    }  

    
    public static void getPDFOutline(String file) {
        try {
            FileInputStream fis = new FileInputStream(file);
          //加载 pdf 文档,获取PDDocument文档对象
            PDDocument document = PDDocument.load(fis);
          //获取PDDocumentCatalog文档目录对象
            PDDocumentCatalog catalog = document.getDocumentCatalog();
          //获取PDDocumentOutline文档纲要对象
            PDDocumentOutline outline = catalog.getDocumentOutline();
           
            if(outline!=null){
              //获取第一个纲要条目（标题1）
                PDOutlineItem item=outline.getFirstChild();
                //遍历每一个标题1
                while(item!=null){
                    //打印标题1的文本
                    System.out.println("Item:"+item.getTitle());
                    //获取标题1下的第一个子标题（标题2）
                    PDOutlineItem child=item.getFirstChild(); 
                    //遍历每一个标题2
                    while(child!=null){
                        //打印标题2的文本
                        System.out.println("    Child:"+child.getTitle());
                        //指向下一个标题2
                        child=child.getNextSibling();
                    }
                    //指向下一个标题1
                    item=item.getNextSibling();
                }
            }
            //关闭输入流
            document.close();
            fis.close();
            
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvalidPasswordException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public static void getPDFInformation(String file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            //加载 pdf 文档,获取PDDocument文档对象
            PDDocument document=PDDocument.load(fis);
            //文档属性信息 
            PDDocumentInformation info = document.getDocumentInformation();
            System.out.println("页数:"+document.getNumberOfPages());

            System.out.println( "标题:" + info.getTitle() );  
            System.out.println( "主题:" + info.getSubject() );  
            System.out.println( "作者:" + info.getAuthor() );  
            System.out.println( "关键字:" + info.getKeywords() );             

            System.out.println( "应用程序:" + info.getCreator() );  
            System.out.println( "pdf 制作程序:" + info.getProducer() );  

            System.out.println( "Trapped:" + info.getTrapped() );  

            System.out.println( "创建时间:" + dateFormat( info.getCreationDate() ));  
            System.out.println( "修改时间:" + dateFormat( info.getModificationDate()));  

            //关闭输入流
            document.close();
            fis.close();
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvalidPasswordException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public static void extractText(String file) {
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
          //实例化一个PDF解析器
         
            
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }
    
}
