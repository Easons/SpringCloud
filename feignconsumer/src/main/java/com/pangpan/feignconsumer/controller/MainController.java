package com.pangpan.feignconsumer.controller;

import com.pangpan.feignconsumer.api.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
