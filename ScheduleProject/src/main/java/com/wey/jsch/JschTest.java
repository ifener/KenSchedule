package com.wey.jsch;

import java.io.File;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class JschTest {
    
    public static void main(String[] args) {
        JSch jsch = new JSch();
        Channel channel = null;
        Session session = null;
        try {
            session = jsch.getSession("ftpaccount", "192.168.72.100", 21);
            session.setPassword("ftpaccount");
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config); // 为Session对象设置properties
            session.setTimeout(3000000); // 设置timeout时间
            session.connect(); // 通过Session建立链接
            channel = session.openChannel("sftp"); // 打开FTP通道..
            
            channel.connect(); // 建立SFTP通道的连接
            
            ChannelSftp chSftp = (ChannelSftp) channel;
            
            // 把远程系统上的目录切换到参数path所指定的目录
            // chSftp.cd("path");
            Vector ls = chSftp.ls("*.jpg");
            for (int i = 0; i < ls.size(); i++) {
                LsEntry entry = (LsEntry) ls.get(i);
                String filename = entry.getFilename();
                chSftp.get(entry.getFilename(), "localFileUrl");// 下载文件到指定的本地目录
                // // 备份源文件生成默认备份文件路径(据请求文件路径,生成同级目录的备份文件夹绝对路径)
                File f = new File("localFileUrl" + File.separator + entry.getFilename());
                
            }
        }
        catch (JSchException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SftpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            if (channel != null) {
                channel.disconnect();
            }
            
            if (session != null) {
                session.disconnect();
            }
        }
        
    }
    
}
