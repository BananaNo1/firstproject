package com.myproject.firstproject.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.List;

/**
 * @ClassName FTPUtil
 * @Description TODO
 * @Author aisino
 * @Date 2018/12/19 11:48
 * @Version 1.0
 **/
@Slf4j
@Data
public class FTPUtil {
    private static String ftpIp = PropertiesUtil.getProperty("ftp.server.ip");
    private static String fptUser = PropertiesUtil.getProperty("ftp.user");
    private static String fptPass = PropertiesUtil.getProperty("ftp.pass");

    public static boolean uploadFile(List<File> fileList) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(ftpIp, 21, fptUser, fptPass);
        log.info("开始连接ftp服务器");
        boolean result = ftpUtil.uploadFile("image", fileList);
        log.info("结束上传，上传结果{}", result);
        return result;
    }

    private boolean uploadFile(String remotePah, List<File> fileList) throws IOException {
        boolean uploaded = true;
        FileInputStream fis = null;
        if (connecServer(this.ip, this.port, this.user, this.pwd)) {
            try {
                System.out.println("uploadFile****************");
                ftpClient.changeWorkingDirectory(remotePah);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for (File file : fileList) {
                    fis = new FileInputStream(file);
                    ftpClient.storeFile(file.getName(), fis);
                }
            } catch (IOException e) {
                log.error("文件上传异常", e);
                uploaded = false;
                e.printStackTrace();
            } finally {
                fis.close();
                ftpClient.disconnect();
            }
        }
        return uploaded;
    }

    private boolean connecServer(String ip, int port, String user, String pwd) {
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user, pwd);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }
}
