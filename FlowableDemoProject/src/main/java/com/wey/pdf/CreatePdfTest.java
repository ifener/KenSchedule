package com.wey.pdf;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class CreatePdfTest {
    
    public static void main(String[] args) throws DocumentException, IOException {
        CreatePdfInstance instance = new CreatePdfInstance();
        instance.createPdf();
    }
    
}
