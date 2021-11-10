package com.pangpan.eurekaprovider.controller;

import com.pangpan.eurekaprovider.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pangpan
 * @date 2021-11-07
 */
@RestController
public class MainController {

    @Value(value = "${server.port}")
    public String port;

    @GetMapping("/get")
    public String get()
    {
        return "Hi，端口为：" + port;
    }

    @Autowired
    HealthStatusService healthStatusService;

    /**
     * 此处的健康设置是区别于服务提供者与eureka服务器的心跳
     *   1、由于业务逻辑的一些错误或者异常，需要进行的服务下线，心跳还是继续
     *   2、避免一直调用一直报错，造成无效调用
     * @param status
     * @return
     */
    @GetMapping("/health")
    public String getHealth(@RequestParam("status") boolean status){

        healthStatusService.setStatus(status);

        return healthStatusService.getStatus();
    }

}
