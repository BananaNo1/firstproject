package com.myproject.firstproject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.myproject.firstproject.entity.IpVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.noggit.JSONUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName TestIpApi
 * @Description TODO
 * @Author aisino
 * @Date 2018/12/17 11:17
 * @Version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestIpApi {

    @Resource
    private RestTemplate restTemplate;

    @Test
    public void taobaoApi() {
        String object = restTemplate.getForObject("http://ip.taobao.com/service/getIpInfo.php?ip=113.87.192.231", String.class);
        Map map = JSONObject.parseObject(object, Map.class);
        Object mapObj=map.get("data");
        Map<String,String>  m = (Map<String,String>)mapObj;
        IpVo ipVo = new IpVo();
        ipVo.setCity(m.get("city"));
        ipVo.setCountry(m.get("country"));
        ipVo.setRegion(m.get("region"));
        ipVo.setIsp(m.get("isp"));
    }

    @Test
    public void sinaApi(){
        String forObject = restTemplate.getForObject("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=113.87.192.231", String.class);

    }
}