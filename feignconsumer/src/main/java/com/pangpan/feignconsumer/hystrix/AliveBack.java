package com.pangpan.feignconsumer.hystrix;

import com.pangpan.feignapi.feignapi.Person;
import com.pangpan.feignconsumer.api.ConsumerApi;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 此处是针对每个方法进行限流、熔断的处理
 * @author pangpan
 * @date 2021-11-17
 */
@Component
public class AliveBack implements ConsumerApi {

    @Override
    public String alive() {
        return "降级了。。。";
    }

    @Override
    public String getMap(String Id) {
        //当把provider关闭后，因为不通了，所以触发异常
        return "降级了";
    }

    @Override
    public Map<Integer, String> getMap3(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<Integer, String> postMap4(Map<String, Object> map) {
        return null;
    }

    @Override
    public String getById(String s) {
        return null;
    }

    @Override
    public Person postPerson(Person person) {
        return null;
    }
}
