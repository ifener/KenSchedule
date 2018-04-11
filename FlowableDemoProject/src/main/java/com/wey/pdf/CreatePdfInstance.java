package com.wey.pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePdfInstance {
    
    BaseFont bfFont = null;
    Font fintTitle1 = null;
    Font fontTitle2 = null;
    Font fontChinese = null;
    Font fontContent = null;
    Font fontHidden = null;
    
    public CreatePdfInstance() {
        try {
            // bfFont = BaseFont.createFont("verdana", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            bfFont = BaseFont.createFont("/font/STXIHEI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            fintTitle1 = new Font(bfFont, 14, Font.BOLD);
            fontTitle2 = new Font(bfFont, 9.5f, Font.BOLD);
            fontChinese = new Font(bfFont, 9.5f, Font.NORMAL);
            fontContent = new Font(bfFont, 8.5f, Font.NORMAL);
            fontHidden = new Font(bfFont, 8.5f, Font.NORMAL);
            
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
    
    public void createPdf() throws FileNotFoundException, DocumentException {
        String url = System.currentTimeMillis() + ".pdf";
        // 创建文档
        Document document = new Document(new Rectangle(842F, 685F), 30F, 20F, 20F, 0F);
        // 初始化文档输出流
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(url)));
        
        document.open();
        
        Paragraph pdfTitle = new Paragraph("采购合同", fintTitle1);
        pdfTitle.setAlignment(Paragraph.ALIGN_CENTER);
        
        PdfPTable pdfHeader = createPdfHeader();
        
        PdfPTable pdfButtom = createPdfButtom();
        
        for (int i = 0; i < 3; i++) {
            document.add(pdfTitle);
            // 添加头部
            document.add(pdfHeader);
            
            PdfPTable middle = createPdfMid();
            document.add(middle);
            
            document.add(pdfButtom);
            
        }
        
        // 关闭文档
        document.close();
    }
    
    private PdfPTable createPdfHeader() throws DocumentException {
        
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
        int[] i = { 80, 180, 310, 80, 180 };
        table.setWidths(i);
        
        // 创建单元格
        PdfPCell cell1 = new PdfPCell(new Phrase("甲方", fontTitle2));
        // 左右对齐方式
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 垂直对齐方式默认为中间对齐
        cell1.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell1.setBorder(0);
        table.addCell(cell1);
        
        // 创建单元格
        PdfPCell cell2 = new PdfPCell(new Phrase("阿里巴巴", fontChinese));
        // 左右对齐方式
        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 垂直对齐方式默认为中间对齐
        cell2.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell2.setBorder(0);
        cell2.setBorderWidth(100);
        table.addCell(cell2);
        
        // 创建单元格
        PdfPCell cellT = new PdfPCell(new Phrase("   "));
        // 左右对齐方式
        cellT.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 垂直对齐方式默认为中间对齐
        cellT.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cellT.setBorder(0);
        cellT.setBorderWidth(100);
        table.addCell(cellT);
        
        // 创建单元格
        PdfPCell cell5 = new PdfPCell(new Phrase("订单编号", fontTitle2));
        // 左右对齐方式
        cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 垂直对齐方式默认为中间对齐
        cell5.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell5.setBorder(0);
        table.addCell(cell5);
        
        // 创建单元格
        PdfPCell cell6 = new PdfPCell(new Phrase("PUR888888", fontChinese));
        // 左右对齐方式
        cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 垂直对齐方式默认为中间对齐
        cell6.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell6.setBorder(0);
        table.addCell(cell6);
        
        // 创建单元格
        PdfPCell cell7 = new PdfPCell(new Phrase("乙方", fontTitle2));
        // 左右对齐方式
        cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 垂直对齐方式默认为中间对齐
        cell7.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell7.setBorder(0);
        table.addCell(cell7);
        
        // 创建单元格
        PdfPCell cell8 = new PdfPCell(new Phrase("酷派", fontChinese));
        // 左右对齐方式
        cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 垂直对齐方式默认为中间对齐
        cell8.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell8.setBorder(0);
        table.addCell(cell8);
        
        // 创建单元格
        PdfPCell cellTT = new PdfPCell(new Phrase("   "));
        // 左右对齐方式
        cellTT.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 垂直对齐方式默认为中间对齐
        cellTT.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cellTT.setBorder(0);
        cellTT.setBorderWidth(100);
        table.addCell(cellTT);
        
        // 创建单元格
        PdfPCell cell11 = new PdfPCell(new Phrase("签订地点", fontTitle2));
        // 左右对齐方式
        cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 垂直对齐方式默认为中间对齐
        cell11.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell11.setBorder(0);
        table.addCell(cell11);
        
        // 创建单元格
        PdfPCell cell12 = new PdfPCell(new Phrase("宇龙公司", fontChinese));
        // 左右对齐方式
        cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 垂直对齐方式默认为中间对齐
        cell12.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell12.setBorder(0);
        table.addCell(cell12);
        
        PdfPCell cell13 = new PdfPCell(new Phrase("一.甲方向乙方退回下列产品及定价（人民币）：", fontContent));
        // 左右对齐方式
        cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 垂直对齐方式默认为中间对齐
        cell13.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell13.setBorder(0);
        cell13.setColspan(3);
        table.addCell(cell13);
        
        PdfPCell cell3 = new PdfPCell(new Phrase("签订时间", fontTitle2));
        // 左右对齐方式
        cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 垂直对齐方式默认为中间对齐
        cell3.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell3.setBorder(0);
        table.addCell(cell3);
        
        // 创建单元格
        PdfPCell cell4 = new PdfPCell(new Phrase("2018-08-08", fontChinese));
        // 左右对齐方式
        cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 垂直对齐方式默认为中间对齐
        cell4.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell4.setBorder(0);
        table.addCell(cell4);
        
        return table;
        
    }
    
    private PdfPTable createPdfMid() throws DocumentException {
        PdfPTable table = new PdfPTable(12);
        table.setWidthPercentage(98);
        table.setHorizontalAlignment(PdfPTable.ALIGN_LEFT);
        table.setSpacingBefore(5F);
        int[] widths = { 36, 70, 140, 110, 35, 60, 80, 60, 60, 70, 70, 40 };
        table.setWidths(widths);
        
        table.addCell(createCell("序号"));
        table.addCell(createCell("物料编码"));
        table.addCell(createCell("物料描述"));
        table.addCell(createCell("旧物料编码"));
        table.addCell(createCell("单位"));
        table.addCell(createCell("数量"));
        table.addCell(createCell("维修单价（不含税）"));
        table.addCell(createCell("税率(%)"));
        table.addCell(createCell("含税单价"));
        table.addCell(createCell("价税合计"));
        table.addCell(createCell("交货日期"));
        table.addCell(createCell("备注"));
        
        for (int i = 1; i <= 8; i++) {
            table.addCell(createCell("序号" + i));
            table.addCell(createCell("物料编码" + i));
            table.addCell(createCell("物料描述" + i));
            table.addCell(createCell("旧物料编码" + i));
            table.addCell(createCell("单位" + i));
            table.addCell(createCell("数量" + i));
            table.addCell(createCell("维修单价（不含税）" + i));
            table.addCell(createCell("税率(%)" + i));
            table.addCell(createCell("含税单价" + i));
            table.addCell(createCell("价税合计" + i));
            table.addCell(createCell("交货日期" + i));
            table.addCell(createCell("备注" + i));
        }
        
        PdfPCell cell = createCell("价税合计总额(大写):");// + toUpperMoney(166666.2)
        cell.setColspan(6);
        
        table.addCell(cell);
        table.addCell(createCell(" "));
        table.addCell(createCell(" "));
        table.addCell(createCell(" "));
        table.addCell(createCell(formatNumber(9999.996, 2)));
        table.addCell(createCell(" "));
        table.addCell(createCell(" "));
        
        return table;
        
    }
    
    public String formatNumber(Double d, int digit) {
        if (d == null)
            return "";
        else
            return String.format("%." + digit + "f", new Object[] { d });
    }
    
    private PdfPCell createCell(String content) {
        
        // 创建单元格
        PdfPCell cell1 = new PdfPCell(new Phrase(content, fontContent));
        // 左右对齐方式
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 垂直对齐方式默认为中间对齐
        cell1.setVerticalAlignment(Element.ALIGN_CENTER);
        
        return cell1;
    }
    
    /**
     * 转换为大写金额(默认2位小数)
     * @return
     */
    public String toUpperMoney(Double money) {
        
        if (money == null) {
            return null;
        }
        
        String result = null;
        String strMoney = String.valueOf(money);
        
        if (strMoney.indexOf(".") != -1) {
            BigDecimal decimal = new BigDecimal(money).setScale(2, BigDecimal.ROUND_HALF_DOWN);
            strMoney = decimal.toEngineeringString().replaceAll("0{2,}$", "0");
            
        }
        
        String[] strMoneys = strMoney.split("\\.");
        String intMoney = strMoneys[0];
        int intLen = intMoney.length();
        
        if (intLen > 12) {
            return null;
        }
        
        String floatMoney = "";
        if (strMoneys.length > 1) {
            floatMoney = strMoneys[1];
        }
        
        if (intLen > 0 && intLen <= 4) {
            result = toChineseUp(intMoney.substring(0));
        }
        else if (intLen > 4 && intLen < 8) {
            result = toChineseUp(intMoney.substring(intLen - 4));
            
            result = toChineseUp(intMoney.substring(0, intLen - 4))
                    + (!"0000".equals(intMoney.substring(0, intLen - 4)) ? "万" : "") + result;
        }
        else if (intLen >= 8) {
            
            result = toChineseUp(intMoney.substring(intLen - 4));
            
            result = toChineseUp(intMoney.substring(intLen - 8, intLen - 4))
                    + (!"0000".equals(intMoney.substring(intLen - 8, intLen - 4)) ? "万" : "") + result;
            if (intLen > 8) {
                result = toChineseUp(intMoney.substring(0, intLen - 8)) + "亿" + result;
            }
        }
        
        if (result != "") {
            result += "元";
        }
        
        if (floatMoney != null && floatMoney.length() > 0) {
            
            if (result != "" && !"0".equals(floatMoney.substring(0, 1))) {
                
                result += toChineseUp(floatMoney.substring(0, 1))
                        + (!"0".equals(floatMoney.substring(0, 1)) ? "角" : "");
            }
            else if (floatMoney.length() >= 2) {
                result += toChineseUp(floatMoney.substring(0, 1));
            }
            
            if (floatMoney.length() >= 2 && !"0".equals(floatMoney.substring(1, 2))) {
                result += toChineseUp(floatMoney.substring(1, 2)) + "分";
            }
            
        }
        
        if (result != "" && (result.indexOf("分") == -1 && result.indexOf("角") == -1)) {
            result += "整";
        }
        
        return result;
    }
    
    /**
     * 转数字字符为中文大写
     * @param single
     * @return
     */
    public String toChineseUp(String strMoeny) {
        
        String[] chinessDigit = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String[] digitUnit = new String[] { "", "拾", "佰", "仟" };
        
        String result = "";
        
        if (null != strMoeny && strMoeny.length() > 0) {
            String[] strMoneys = strMoeny.split("");
            List<String> tmpList = Arrays.asList(strMoneys).subList(1, strMoneys.length);
            strMoneys = tmpList.toArray(new String[tmpList.size()]);
            for (int i = 0; i < strMoeny.length(); i++) {
                result += chinessDigit[Integer.valueOf(strMoneys[i])]
                        + (!"0".equals(strMoneys[i]) ? digitUnit[strMoneys.length - 1 - i] : "");
            }
            if ("零".equals(result)) {
                return result;
            }
            result = result.replaceAll("零+$", "").replaceAll("零{2,}", "零");
        }
        
        return result;
    }
    
    public PdfPTable createPdfButtom() throws DocumentException {
        
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);
        table.setSpacingBefore(5F);
        int[] widths = { 40, 160, 40, 160 };
        table.setWidths(widths);
        table.addCell(createNoBoardCell("二.产品验收标准和方法：根据双方确认的规格要求，由甲方IQC验收。 ", 4));
        table.addCell(createNoBoardCell("三.产品保修方式及期限：依据双方约定。若因乙方原因造成物料损坏或丢失，按甲方声明单价赔偿。 ", 4));
        table.addCell(createNoBoardCell("四.包装要求及费用负担：按甲方要求包装，包装费用由乙方承担。 ", 4));
        table.addCell(createNoBoardCell("五.交货地点及运输方法：乙方负责运输至甲方工厂东莞松山湖。  ", 4));
        table.addCell(createNoBoardCell("六.付款方式：现金电汇/现金支票/银行承兑汇票；", 4));
        table.addCell(createNoBoardCell("      付款期限：" + "。 ", 4));
        table.addCell(createNoBoardCell("七.合同变更声明：本合同签订后,任何涉及本合同变更、撤销以及合同履行相关事务，必须经本合同代表签字并经公司盖章后方对双方当事人产生法律约束力，其他任何人员所作行为均不产生法",
                                        4));
        table.addCell(createNoBoardCell("      律效力。", 4));
        table.addCell(createNoBoardCell("八.乙方应按质按时按量供货，否则应承担违约责任。 ", 4));
        table.addCell(createNoBoardCell("九.如乙方在遇到甲方员工不合理要求、对待时，可向甲方审计部投诉，电话：0755-33023733，邮箱（shenjibu@yulong.com）如在合作过程中遇到业务问题，请向供应链内控管理部咨询投诉：",
                                        4));
        table.addCell(createNoBoardCell("      gylneikongbu@yulong.com。 ", 4));
        table.addCell(createNoBoardCell("十.本合同自双方签字盖章之日起生效。 ", 4));
        table.addCell(createNoBoardCell("甲    方：                 ", 1, 6F));
        table.addCell(createNoBoardCell("阿里巴巴", 1, 6F));
        table.addCell(createNoBoardCell("乙    方：", 2, 6F));
        
        table.addCell(createNoBoardCell("代    表：    ", 1, 6F));
        
        table.addCell(createHiddenContentCell("###########关键字#####", 1, 6F));
        table.addCell(createNoBoardCell("代    表：    ", 2, 6F));
        
        table.addCell(createNoBoardCell("甲方地址：  ", 1, 6F));
        table.addCell(createNoBoardCell("东莞", 3, 6F));
        
        table.addCell(createNoBoardCell("联系电话：", 1, 6F));
        table.addCell(createNoBoardCell("88888899", 3, 6F));
        
        return table;
        
    }
    
    private PdfPCell createNoBoardCell(String content, int colspan) {
        
        return createNoBoardCell(content, colspan, 3F);
    }
    
    private PdfPCell createNoBoardCell(String content, int colspan, float padingTop) {
        
        // 创建单元格
        PdfPCell cell1 = new PdfPCell(new Phrase(content, fontContent));
        // 左右对齐方式
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 垂直对齐方式默认为中间对齐
        cell1.setVerticalAlignment(Element.ALIGN_CENTER);
        
        cell1.setColspan(colspan);
        
        cell1.setBorder(0);
        
        cell1.setPaddingTop(padingTop);
        
        return cell1;
    }
    
    private PdfPCell createHiddenContentCell(String hiddenContent, int colspan, float padingTop) {
        
        // Phrase hiddenPhrase = new Phrase(hiddenContent, fontHidden);
        
        // 创建单元格
        PdfPCell cell1 = new PdfPCell(new Phrase(hiddenContent, fontHidden));
        
        // 左右对齐方式
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        // 垂直对齐方式默认为中间对齐
        cell1.setVerticalAlignment(Element.ALIGN_CENTER);
        
        cell1.setColspan(colspan);
        
        cell1.setPaddingLeft(30F);
        
        cell1.setBorder(0);
        
        cell1.setPaddingTop(padingTop);
        
        return cell1;
    }
    
}
