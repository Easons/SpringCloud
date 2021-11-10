package com.pangpan.eurekaconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pangpan
 * @date 2021-11-10
 */
@RestController
public class RibbonController {

    @Autowired
    RestTemplate restTemplate;


    @Autowired
    LoadBalancerClient loadBalancerClient;


    @Autowired
    DiscoveryClient discoveryClient;

    static  AtomicInteger atomicInteger = new AtomicInteger();


    @GetMapping("/ribbon")
    public String ribbon(){

        //ribbon完成客户端的负载均衡，过滤掉了down了的机器
        ServiceInstance instanceInfo = loadBalancerClient.choose("eureka-provider");

        String hostName = instanceInfo.getHost();
        int port = instanceInfo.getPort();
        String url = "http://"+hostName + ":"+port+"/get";
        System.out.println("url"+url);

        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url, String.class);

        return forObject;
    }



    /**
     * 手动负载均衡
     * @return
     */
    @GetMapping("/handRibbon")
    public String handRibbon(){

        List<ServiceInstance> instances = discoveryClient.getInstances("eureka-provider");

        //随机
        int instanceIndex = new Random().nextInt(instances.size());

        //轮询
        int increment = atomicInteger.incrementAndGet();
        instanceIndex = increment % instances.size();


        ServiceInstance instanceInfo = instances.get(instanceIndex);

        String hostName = instanceInfo.getHost();
        int port = instanceInfo.getPort();
        String url = "http://"+hostName + ":"+port+"/get";
        System.out.println("url"+url);

        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url, String.class);

        return forObject;
    }


    @GetMapping("/handRibbon2")
    public String handRibbon2(){

        //ribbon完成客户端的负载均衡，过滤掉了down了的机器
        ServiceInstance instanceInfo = loadBalancerClient.choose("eureka-provider");

        String hostName = instanceInfo.getHost();
        int port = instanceInfo.getPort();
        String url = "http://"+hostName + ":"+port+"/get";
        System.out.println("url"+url);

        String forObject = new RestTemplate().getForObject(url, String.class);

        return forObject;
    }


    /**
     * 在restTemplet上加了RoundBanlance
     * @return
     */
    @GetMapping("/handRibbon3")
    public String handRibbon3(){

        //restTemplet 添加了RoadBanlance 此处就不用写ip了，可以直接调了
        String url ="http://eureka-provider/get";
        String respStr = restTemplate.getForObject(url, String.class);

        return respStr;
    }

}
