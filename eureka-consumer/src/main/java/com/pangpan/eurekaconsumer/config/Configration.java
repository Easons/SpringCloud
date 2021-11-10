package com.pangpan.eurekaconsumer.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author pangpan
 * @date 2021-11-10
 */
@Configuration
public class Configration {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplet(){

        return new RestTemplate();
    }

//    @Bean
//    public IRule myRule()
//    {
//
//        return new RandomRule();
//    }


}
