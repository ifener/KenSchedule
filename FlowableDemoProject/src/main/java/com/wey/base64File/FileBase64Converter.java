package com.wey.base64File;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class FileBase64Converter {
    
    private static final int BUFFER_LENGTH = 1024;
    
    public static String encodeBase64File(String path) throws IOException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[BUFFER_LENGTH];
        StringBuffer sb = new StringBuffer();
        while (fis.read(buffer, 0, BUFFER_LENGTH) != -1) {
            String ret = new BASE64Encoder().encode(buffer);
            sb.append(ret);
        }
        
        fis.close();
        return sb.toString();
    }
    
    /**  
     * 将base64字符解码保存文件  
     * @param base64Code  
     * @param targetPath  
     * @throws Exception  
     */
    
    public static void decoderBase64File(String base64Code, String targetPath) throws Exception {
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
        
    }
}
