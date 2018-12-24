package com.myproject.firstproject;


import com.google.common.collect.Lists;
import com.myproject.firstproject.util.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;

/**
 * @ClassName FtpTest
 * @Description TODO
 * @Author aisino
 * @Date 2018/12/19 14:00
 * @Version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class FtpTest {

    @Test
    public void upload() {
        String path = "E:/image/TIM图片20181113163056.jpg";
        File file = new File(path);
        try {

            FTPUtil.uploadFile(Lists.newArrayList(file));
        } catch (IOException e) {
            log.error("上传错误");
            e.printStackTrace();
        }
    }

    @Test
    public void connect() {
        FTPClient client = new FTPClient();
        try {
            client.connect("120.78.3.7");
            System.out.println("****************");
            client.login("ftpuser", "ftpuser");
            System.out.println("******************");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
