package com.pangpan.feignconsumer.controller;

import com.pangpan.feignconsumer.api.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author pangpan
 * @date 2021-11-14
 */
@RestController
public class MainController {

    @Autowired
    ConsumerApi consumerApi;


    @GetMapping("/alive")
    public String alive(){

        return consumerApi.alive();
    }

    @GetMapping("/getById")
    public String getById(){
        return consumerApi.getById("测试");
    }


    @GetMapping("/getMap")
    public String getMap(){
        return consumerApi.getMap("测试");
    }


    /**
     * 接收多个参数
     * @param map
     * @return
     */
    @GetMapping("/getMap3")
    public Map<Integer, String> getMap3(@RequestParam Map<String, Object> map) {
        // TODO Auto-generated method stub
        System.out.println(map);
        return consumerApi.getMap3(map);
    }
}
