package com.wey.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ITextCreatePdf {
    
    public static void main(String[] args) {
       
        try {
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream("helloworld.pdf"));
            //设置字体
            BaseFont bfChinese = BaseFont.createFont("/font/STXIHEI.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);  
            com.itextpdf.text.Font FontChinese24 = new com.itextpdf.text.Font(bfChinese, 24, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese, 18, com.itextpdf.text.Font.BOLD); 
            com.itextpdf.text.Font FontChinese16 = new com.itextpdf.text.Font(bfChinese, 16, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font FontChinese12 = new com.itextpdf.text.Font(bfChinese, 12, com.itextpdf.text.Font.NORMAL);
            com.itextpdf.text.Font FontChinese11Bold = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font FontChinese11 = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.ITALIC);
            com.itextpdf.text.Font FontChinese11Normal = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.NORMAL);

            document.open();
            
            PdfPTable table1 = new PdfPTable(3);
            PdfPCell cellheader = new PdfPCell(new Paragraph("标题",FontChinese24)); 
            cellheader.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellheader.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellheader.setBorder(0);
            
            String imagePath=ITextCreatePdf.class.getClassLoader().getResource("resource/logo.png").getPath();
            Image image1 = Image.getInstance(imagePath); 

            Image image2 = Image.getInstance(imagePath); 
            
            int[] width = {35,40,25};
            table1.setWidths(width);
            table1.getDefaultCell().setBorder(0);
            table1.addCell(image1);  
            table1.addCell(cellheader);  
            table1.addCell(image2);
            document.add(table1);
            //加入空行
            Paragraph blankRow1 = new Paragraph(18f, " ", FontChinese18); 
            document.add(blankRow1);
            document.close();
            
            
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
