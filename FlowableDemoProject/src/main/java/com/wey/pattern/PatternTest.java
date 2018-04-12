package com.wey.pattern;

import java.text.MessageFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {
    
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\{(.*?)\\}");
        String template = "KEN{1,date,yyyyMMdd}{0}-{2,time,hhmmss}-{3,number,#.##}";
        Matcher matcher = pattern.matcher(template);
        while (matcher.find()) {
            System.out.println("1");
        }
        
        Object[] objects = new Object[4];
        objects[0] = "123";
        objects[1] = new Date();
        objects[2] = new Date();
        objects[3] = 6969.659;
        
        MessageFormat format = new MessageFormat(template);
        String result = format.format(objects);
        System.out.println(result);
        
    }
    
}
