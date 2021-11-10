package com.pangpan.eurekaconsumer.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author pangpan
 * @date 2021-11-08
 */
@RestController
public class ConsumerController {


    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    EurekaClient eurekaClient;


    @Autowired
    LoadBalancerClient loadBalancerClient;


    @GetMapping("/client")
    public  String client()
    {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println("service："+service);
        }

        System.out.println("#################");

        List<ServiceInstance> instances = discoveryClient.getInstances("eureka-provider");
        for (ServiceInstance instance : instances) {
            System.out.println("instance："+instance);
        }

        return "client";
    }


    @GetMapping("/client1")
    public String client1()
    {

//        List<InstanceInfo> instances = eurekaClient.getInstancesById("eureka-provider");

        List<InstanceInfo> instances = eurekaClient.getInstancesByVipAddress("eureka-provider", false);

        for (Object instance : instances) {
            System.out.println(instance);
        }

        if(instances.size() > 0 )
        {
            InstanceInfo instanceInfo = instances.get(0);

            if(instanceInfo.getStatus() == InstanceInfo.InstanceStatus.UP)
            {
                String hostName = instanceInfo.getHostName();
                int port = instanceInfo.getPort();
                String url = "http://"+hostName + ":"+port+"/get";
                System.out.println("url"+url);

                RestTemplate restTemplate = new RestTemplate();
                String forObject = restTemplate.getForObject(url, String.class);

                System.out.println("result："+forObject);


            }
        }
        return "client1";
    }


    @GetMapping("/client2")
    public String client2()
    {

        //ribbon完成客户端的负载均衡，过滤掉了down了的机器
        ServiceInstance instanceInfo = loadBalancerClient.choose("eureka-provider");

        String hostName = instanceInfo.getHost();
        int port = instanceInfo.getPort();
        String url = "http://"+hostName + ":"+port+"/get";
        System.out.println("url"+url);

        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url, String.class);

        System.out.println("result："+forObject);

        return "client2";
    }
}
