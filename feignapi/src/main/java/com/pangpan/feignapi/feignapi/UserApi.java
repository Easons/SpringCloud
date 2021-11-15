package com.pangpan.feignapi.feignapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author pangpan
 * @date 2021-11-14
 */
@RequestMapping("/User")
public interface UserApi {
    @GetMapping("alive")
    public  String alive();
}
