package com.pangpan.feignconsumer.controller;

import com.pangpan.feignapi.feignapi.Person;
import com.pangpan.feignconsumer.api.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
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

    /**
     * Post请求
     */
    @GetMapping("/Map4")
    public  Map<Integer, String> postMap4(@RequestParam Map<String, Object> map){

        return consumerApi.postMap4(map);
    }

    /**
     * Post请求
     */
    @GetMapping("/postPerson")
    public  Person postPerson(@RequestParam Map<String, Object> map){

        Person person = new Person();
        person.setId(Integer.valueOf(map.get("id").toString()));
        person.setName(map.get("name").toString());


        return consumerApi.postPerson(person);
    }
}
