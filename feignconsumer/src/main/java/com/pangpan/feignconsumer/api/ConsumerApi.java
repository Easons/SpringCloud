package com.pangpan.feignconsumer.api;

import com.pangpan.feignapi.feignapi.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author pangpan
 * @date 2021-11-14
 */
//不结合eureka，就是自定义一个client名字。就用url属性指定 服务器列表。url=“http://ip:port/”
//@FeignClient(name = "xxoo",url = "http://localhost:8081")

//另外一种写法，去eureka里面找这个名字的远程服务
@FeignClient(name = "feignprovider")
public interface ConsumerApi extends UserApi {


    /**
     * 此处的GetMapping 是给 Feign 看的，用来组装url
     * @param Id
     * @return
     */
    @GetMapping("getMap")
    public  String getMap(@RequestParam(value = "Id") String Id);

    @GetMapping("getMap3")
    Map<Integer, String> getMap3(@RequestParam  Map<String, Object> map);

    @PostMapping("postMap4")
    Map<Integer, String> postMap4(@RequestBody  Map<String, Object> map);

//    @GetMapping("/alive")
//    public  String alive();






}
